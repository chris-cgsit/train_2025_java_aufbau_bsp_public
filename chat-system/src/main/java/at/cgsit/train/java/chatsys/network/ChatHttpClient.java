package at.cgsit.train.java.chatsys.network;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static java.lang.Integer.parseInt;

public class ChatHttpClient {

  private static String BASE = "http://localhost:";


  public static void main(String[] args) throws Exception {

    String chatSrvPort = System.getenv("CHAT_SRV_PORT");
    if (chatSrvPort == null) {
      chatSrvPort = "8080";
    }
    System.out.printf("Chat SRV Port: %s \n", chatSrvPort);
    Integer port = parseInt(chatSrvPort);
    System.out.printf("Chat SRV Port INT: %s \n ", port);
    // must be parseable to int
    BASE = BASE + port;
    System.out.printf("Server url is: %s \n", BASE);

    String senderId = java.util.UUID.randomUUID().toString(); // jeder Teilnehmer: eigene ID
    System.out.println("Meine senderId = " + senderId);

    // 1) Nachricht senden (ohne Raum)
    post("/message",
        "senderId=" + enc(senderId) +
            "&content=" + enc("Hallo aus dem Kurs!") +
            "&chatId=" + enc(""));

    // 2) Optional: in einen Raum schicken
    post("/message",
        "senderId=" + enc(senderId) +
            "&content=" + enc("Hallo Raum R-42!") +
            "&chatId=" + enc("R-42"));

    try {
      // 3) Polling – 3 Runden (since=0 heißt: alles ab 0)
      int since = 0;
      for (int i = 0; i < 5; i++) {
        String resp = get("/messages?scope=all&since=" + since);
        System.out.println("POLL " + i + ":\n" + resp);
        since = updateSince(since, resp);
        Thread.sleep(2000);
      }
    } catch (Exception e) {
      System.out.println("Exception from server" + e.getMessage());
      e.printStackTrace();
    }

  }

  /** Öffentliche API für Clients: sendet eine Nachricht (optional mit chatId). */
  public static void send(String senderId, String text, String chatId) throws IOException {
    String body =
        "senderId=" + enc(senderId) +
            "&content=" + enc(text) +
            "&chatId="  + enc(chatId == null ? "" : chatId);
    post("/message", body);
  }

  /** Convenience: ohne Raum */
  public static void send(String senderId, String text) throws IOException {
    send(senderId, text, "");
  }


  private static String enc(String s) throws UnsupportedEncodingException {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

  private static void post(String path, String body) throws IOException {
    HttpURLConnection con = null;
    try {
      URL url = new URL(BASE + path);
      con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setDoOutput(true);
      con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
      try (OutputStream os = con.getOutputStream()) {
        os.write(body.getBytes(StandardCharsets.UTF_8));
      }
      int code = con.getResponseCode();
      String resp = read(con);
      System.out.println("POST " + path + " -> " + code + " | " + resp);
    } finally {
      if (con != null ) con.disconnect();
    }
  }

  private static String get(String path) throws IOException {
    URL url = new URL(BASE + path);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    int code = con.getResponseCode();
    String resp = read(con);
    System.out.println("GET " + path + " -> " + code);
    return resp;
  }

  private static String read(HttpURLConnection con) throws IOException {
    InputStream is = (con.getResponseCode() >= 400) ? con.getErrorStream() : con.getInputStream();
    if (is == null) return "";
    try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
      StringBuilder sb = new StringBuilder();
      for (String line; (line = br.readLine()) != null; ) sb.append(line).append("\n");
      return sb.toString();
    }
  }

  // setzt since auf (letzte empfangene Indexzeile + 1)
  private static int updateSince(int oldSince, String resp) {
    int max = oldSince;
    for (String line : resp.split("\n")) {
      if (line.isBlank()) continue;
      int sep = line.indexOf('|');
      if (sep > 0) {
        try {
          int idx = Integer.parseInt(line.substring(0, sep));
          if (idx >= max) max = idx + 1;
        } catch (NumberFormatException ignored) {
        }
      }
    }
    return max;
  }
}
