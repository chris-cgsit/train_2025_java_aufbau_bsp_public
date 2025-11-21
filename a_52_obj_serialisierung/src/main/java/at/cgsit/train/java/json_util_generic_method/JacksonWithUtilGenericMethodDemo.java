package at.cgsit.train.java.json_util_generic_method;

import at.cgsit.train.java.json_util_generic.JsonUtilsGen;
import at.cgsit.train.java.sharedObj.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JacksonWithUtilGenericMethodDemo {

  static void main() throws JsonProcessingException {

    // 1. Objekt erzeugen
    ChatMessage msg = new ChatMessage("Wifi Kurs", "<html><body><h1>hello world</h1></body></html>");

    String json = JsonUtilsGenMethod.toJson(msg);
    System.out.println("Objekt gespeichert: " + json);

    ChatMessage chatMessageDeserialized = JsonUtilsGenMethod.fromJson(json, ChatMessage.class);
    System.out.println("Objekt gespeichert deserialized: " + chatMessageDeserialized);

  }

}
