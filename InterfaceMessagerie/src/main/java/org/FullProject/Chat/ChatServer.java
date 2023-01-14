package org.FullProject.Chat;
import org.FullProject.PeerToPeer.PeerToPeerConnection;
import org.FullProject.PeerToPeer.PeerToPeerServer;

import java.io.Serializable;

public abstract class ChatServer<T extends Serializable> extends PeerToPeerServer<ChatMessage<T>> {
    public abstract void onMessageReceived(ChatMessage<String> message, PeerToPeerConnection<String> connection);

    public abstract void onConnectionEstablished(PeerToPeerConnection<String> connection);

    public abstract void onConnectionClosed(PeerToPeerConnection<String> connection);

    public abstract void onMessage(ChatMessage<String> message, PeerToPeerConnection<ChatMessage<String>> connection);
}
