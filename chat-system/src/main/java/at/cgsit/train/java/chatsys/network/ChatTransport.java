package at.cgsit.train.java.chatsys.network;

import java.io.IOException;

public interface ChatTransport {
    void send(String senderId, String text, String chatId) throws IOException;
}
