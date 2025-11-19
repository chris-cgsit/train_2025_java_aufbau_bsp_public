package at.cgsit.train.java.sharedObj;

import java.io.Serializable;

public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sender;
    private String text;

    public ChatMessage() {
      super();
    }

    public ChatMessage(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

  public String getSender() {
    return sender;
  }

  public String getText() {
    return text;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
    public String toString() {
        return sender + ": " + text;
    }



}
