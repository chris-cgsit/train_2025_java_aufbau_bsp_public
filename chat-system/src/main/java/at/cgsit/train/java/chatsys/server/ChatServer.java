package at.cgsit.train.java.chatsys.server;

import at.cgsit.train.java.chatsys.exceptions.ProfantitatException;
import at.cgsit.train.java.chatsys.model.ChatMessage;
import at.cgsit.train.java.chatsys.model.ChatUser;
import at.cgsit.train.java.chatsys.model.SystemUserCreator;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * Der ChatServer verwaltet Benutzer und verteilt Nachrichten.
 */
/**
 * ChatServer: verwaltet User, verteilt Nachrichten, führt Historie, prüft Schimpfwörter.
 */
public class ChatServer implements ChatInterface, UserManagement {

  public static final String KEY_SYSTEM  = "_SYSTEM";
  public static final String KEY_NO_ROOM = "_NO_ROOM";

  // Den Chef kennen wir den System user bzw Admin
  private ChatUser systemUser;

  /**
  * alle aktiven gerade chattenden user im system
  * login loutout gibst derzeit noch nicht wer nicht da ist ist hier nicht vorhanden
  * Benutzer werden jetzt über Map verwaltet (UUID → ChatUser).
  * wenn wir dann ggf eine sortierung benötigen dann mit ordered tree Set O(log n)
  * new TreeSet<>(Comparator.comparing(ChatUser::getUsername))
  */
  private final Map<UUID, ChatUser> users = new ConcurrentHashMap<>();

  // historie aller Chat Nachrichten die versendet wurden seit dem Server start
  /** Historien-Buckets:
   *  _SYSTEM  -> alle Systemnachrichten
   *  _NO_ROOM -> alle Nachrichten ohne chatId
   *  <chatId> -> pro Raum die eigene Liste */
  private final Map<String, List<ChatMessage>> histories = new HashMap<>();

  // Case-insensitive Liste; enthält "Iditot" (wie vorgegeben), "Dummkopf", "Trottel"
  private static final Set<String> PROFANITIES = Set.of("iditot", "dummkopf", "trottel");

  public ChatServer() {
    this.systemUser = SystemUserCreator.createSystemUser();
    users.put(systemUser.getId(), systemUser);
    // Buckets anlegen
    histories.put(KEY_SYSTEM,  new ArrayList<>());
    histories.put(KEY_NO_ROOM, new ArrayList<>());
  }

  /**
   * user hinzufuegen, wir gehen davon aus dass das login, register woanders stattfindet
   * und hier der user aktiv bereits hinzugefügt wird
   * @param user
   */
  @Override
  public void addUser(ChatUser user) {
    users.put(user.getId(), user);
    System.out.printf("[Server] User: [%s] ist dem Chat beigetreten.\n", user.getUsername() );
    // eigentlich sollten wir das broadcasten zu den clients, als Message mit typ user joined
  }

  /**
   * sortierte liste der user. wir haben ja "nur" ein hash set mit ids
   * hier können wir wie mit einem Comparator als Functional one method interface
   * @FunctionalInterface nach Name vergleichen.<br/>
   * wir verwenden dazu die methoden referenz ChatUser::getUsername
   * und sortieren aber case insensitive
   * @return
   */
  @Override
  public List<ChatUser> getSortedUsersByName() {

    // java erlaubt uns hier die warning zu unterdrücken dass wir das gleich als return = schreiben könnten.
    @SuppressWarnings("UnnecessaryLocalVariable")
    List<ChatUser> result
        = users.values().stream()
        .sorted(Comparator.comparing(ChatUser::getUsername,  String.CASE_INSENSITIVE_ORDER))
        .toList();

    Consumer<ChatUser> funktion = new Consumer<ChatUser>() {
      @Override
      public void accept(ChatUser chatUser) {
        System.out.println(chatUser.getUsername());
        System.out.println(chatUser.getEmail());
      }
    };

    Consumer<ChatUser> funktion2 = this::printAll;

    // result.forEach(this::printAll);
    result.forEach( funktion );

    result.forEach(this::printAll);

    return  result;
  }

  private String printAll( ChatUser chatUser ) {
    System.out.println(chatUser.getUsername());
    System.out.println(chatUser.getEmail());
    return "";
  }

  /** Einzige Send-Methode: akzeptiert fertige ChatMessage. */
  @Override
  public void sendMessage(ChatMessage message) throws ProfantitatException {
    // if we fail, fail fast, we do not whant to get a NullPointerException later on!
    Objects.requireNonNull(message);

    // reverse the string to a UUID with fromString static helper
    UUID userUUID = UUID.fromString(message.getSenderId());

    // lookup the User in der Server active users List, if not there its a security leak or hack from outside
    ChatUser chatUser = users.get(userUUID);
    if (chatUser == null) {
      // lets use the message format here, so the string is created efficiently and if we just log, we log it ..
      String formatted = MessageFormat.format("Technical Error: user not found in user List: {0}", message.getSenderId());
      // if the user is not found here we focus a technical error or security leak. so just throw Runtime Exception here
      throw new RuntimeException(formatted);
    }
    validateNoProfanity(chatUser, message.getContent());
    // Erst nach erfolgreicher Prüfung speichern:
    broadcast(message);
    // if the broacast fails and throws an exception, we do not append to the history, only after sucess
    appendToHistory(message);

  }

  /** System-Broadcast (bleibt), wird ebenfalls geprüft. */
  @Override
  public void sendSystemMessage(String text) throws ProfantitatException {
    validateNoProfanity(systemUser, text);
    ChatMessage systemMsg = new ChatMessage(systemUser.getStringId(), text, true);
    systemMsg.setSystemMessage(true);
    appendToSystemHistory(systemMsg);
    broadcast(systemMsg);
  }

  /**
   * Historie verteilen: _SYSTEM, _NO_ROOM, oder per chatId
   */
  private void appendToHistory(ChatMessage msg) {
    String chatId = msg.getChatId();
    if (Boolean.TRUE.equals(msg.getSystemMessage())) {
      appendToSystemHistory(msg);
      return;
    }
    if (chatId == null || chatId.isBlank()) {
      histories.get(KEY_NO_ROOM).add(msg);
    } else {
      histories.computeIfAbsent(chatId, k -> new ArrayList<>()).add(msg);
    }
  }

  private void appendToSystemHistory(ChatMessage msg) {
    histories.get(KEY_SYSTEM).add(msg);
  }

  private void validateNoProfanity(ChatUser user,String content) throws ProfantitatException {
    String lower = content.toLowerCase(Locale.ROOT);
    for (String bad : PROFANITIES) {
      if (lower.contains(bad)) {
        String message = MessageFormat.format("Nachricht enthält verbotenes Wort: {0}", bad);
        throw new ProfantitatException(user.getStringId(), message);
      }
    }
  }

  private void broadcast(ChatMessage msg) {
    for (ChatUser u : users.values()) {
      // einfache Konsolen-Simulation
      String from = msg.getSystemMessage() ? "[System]" : msg.getSenderId();
      System.out.println("[" + u.getUsername() + " empfängt] " + from + ": " + msg.getContent());
    }
  }

  public List<ChatMessage> getHistory() {
    // currently only non room messages are seen here .. system and room are not included
    return Collections.unmodifiableList(histories.getOrDefault(KEY_NO_ROOM, List.of()));
  }
}

