package at.cgsit.train.java.json;

import at.cgsit.train.java.sharedObj.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonObjMapDemo {

  static void main() throws JsonProcessingException {

    // 1. Objekt erzeugen
    ChatMessage msg = new ChatMessage("Wifi Kurs", "<html><body><h1>hello world</h1></body></html>");

    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    String json = mapper.writeValueAsString(msg);

    System.out.println("Objekt gespeichert: " + json);

    ChatMessage c = mapper.readValue(json, ChatMessage.class);



  }

}
