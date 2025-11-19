package at.cgsit.train.java.json_util;

import at.cgsit.train.java.sharedObj.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper()
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    public static String toJson(ChatMessage msg) {
        try {
            return mapper.writeValueAsString(msg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ChatMessage fromJson(String json) {
        try {
            return mapper.readValue(json, ChatMessage.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
