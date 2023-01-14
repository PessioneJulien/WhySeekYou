package org.FullProject.Chat;
import org.FullProject.PeerToPeer.PeerToPeerConnection;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class ChatMessage<T extends Serializable> implements Serializable {

    public String author;
    public Date date;
    public T content;

    public ChatMessage(String author, T content) {
        this.date = new Date();
        this.content = content;
    }

    public void reply(String author, T content, PeerToPeerConnection<ChatMessage<T>> connection) throws IOException {
        ChatMessage<T> message = new ChatMessage<>(author, content);
        connection.sendMessage(message);
    }
}
