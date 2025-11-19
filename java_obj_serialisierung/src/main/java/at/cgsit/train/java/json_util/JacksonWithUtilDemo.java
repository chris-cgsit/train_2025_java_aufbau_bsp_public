package at.cgsit.train.java.json_util;

import at.cgsit.train.java.sharedObj.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonWithUtilDemo {

  static void main() throws JsonProcessingException {

    // 1. Objekt erzeugen
    ChatMessage msg = new ChatMessage("Wifi Kurs", "<html><body><h1>hello world</h1></body></html>");

    String json = JsonUtils.toJson(msg);
    System.out.println("Objekt gespeichert: " + json);

    ChatMessage chatMessageDeserialized = JsonUtils.fromJson(json);
    System.out.println("Objekt gespeichert deserialized: " + chatMessageDeserialized);



  }

}
