package at.cgsit.train.java.chatsys.network;

import java.io.IOException;

public class HttpChatTransport implements ChatTransport {
    @Override
    public void send(String senderId, String text, String chatId) throws IOException {
        ChatHttpClient.send(senderId, text, chatId);
    }
}