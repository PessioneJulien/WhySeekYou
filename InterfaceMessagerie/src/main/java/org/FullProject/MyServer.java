package org.FullProject;

import org.FullProject.Chat.ChatMessage;
import org.FullProject.Chat.ChatServer;
import org.FullProject.PeerToPeer.PeerToPeerConnection;

import java.io.IOException;

public class MyServer extends ChatServer<String> {

    public final String author;

    public MyServer(String author) {
        this.author = author;
    }

    @Override
    public void onMessageReceived(ChatMessage<String> message, PeerToPeerConnection<String> connection) {
        System.out.println("Message received from " + connection.getPort() + ": " + message.content);
    }

    @Override
    public void onConnectionEstablished(PeerToPeerConnection<String> connection) {
        System.out.println("Connection established with " + connection.getPort());
    }

    @Override
    public void onConnectionClosed(PeerToPeerConnection<String> connection) {
        System.out.println("Connection closed with " + connection.getPort());
    }

    @Override
    public void onMessage(ChatMessage<String> message, PeerToPeerConnection<ChatMessage<String>> connection) {
        System.out.println("From " + message.author + " at " + message.date + ":\n" + message.content);
        if (message.content.equals("Hello there")) {
            System.out.println("Replying to handshake");
            try {
                message.reply(this.author, "General Kenobi", connection);
            } catch (IOException e) {
                System.out.println("Failed to reply to handshake!");
                e.printStackTrace();
            }
        }
    }
}
