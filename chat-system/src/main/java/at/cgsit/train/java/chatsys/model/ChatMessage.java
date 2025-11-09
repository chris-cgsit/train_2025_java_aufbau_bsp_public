package at.cgsit.train.java.chatsys.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class ChatMessage {

  // a universal unique identifier, to be able to create it anywhere in the universe
  // immediately when the message is constructed
  private UUID messageId  = UUID.randomUUID();

  // chat room ID or 1:1 ID
  private String chatId;

  // the senders user id as a string
  private String senderId;
  // the user who's the message recipent or null for room messages
  // optional for 1:1, null for room broadcast
  private String recipientId;

  // this is the message itself, or any other content as a string
  private String content;

  private String type;             // text | image | file

  // a timestamp when the message was created
  private Instant timestamp;

  public ChatMessage(String senderId, String recipientId, String content) {
    this.senderId = senderId;
    this.recipientId = recipientId;
    this.content = content;
  }

  public UUID getMessageId() {
    return messageId;
  }

  public void setMessageId(UUID messageId) {
    this.messageId = messageId;
  }

  public String getChatId() {
    return chatId;
  }

  public void setChatId(String chatId) {
    this.chatId = chatId;
  }

  public String getSenderId() {
    return senderId;
  }

  public void setSenderId(String senderId) {
    this.senderId = senderId;
  }

  public String getRecipientId() {
    return recipientId;
  }

  public void setRecipientId(String recipientId) {
    this.recipientId = recipientId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ChatMessage that)) return false;
    return Objects.equals(getMessageId(), that.getMessageId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getMessageId());
  }
}

