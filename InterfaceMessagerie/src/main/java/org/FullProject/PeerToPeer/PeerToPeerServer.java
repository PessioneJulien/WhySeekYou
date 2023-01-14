package org.FullProject.PeerToPeer;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PeerToPeerServer<T extends Serializable> {
    protected List<PeerToPeerConnection<T>> connections = Collections.synchronizedList(new ArrayList<>());

    public void listen(int port) throws IOException {
        System.out.println("Starting server on " + port);
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server socket established on " + port);
            while (true) {
                Socket socket = server.accept();
                System.out.println("Received inbound connection: " + socket.getInetAddress());
                this.connectTo(socket);
            }
        }
    }

    public PeerToPeerConnection<T> connectTo(String host, int port) throws IOException {
        return this.connectTo(new Socket(host, port));
    }

    /** socket must already be connected when calling this method */
    public PeerToPeerConnection<T> connectTo(Socket socket) {
        PeerToPeerConnection<T> connection = new PeerToPeerConnection<>(socket, this);
        this.connections.add(connection);
        new Thread(connection).start();
        return connection;
    }

    public void disconnect(PeerToPeerConnection<T> connection) {
        System.out.println("Disconnecting connection: " + connection.socket.getInetAddress());
        this.connections.remove(connection);
    }

    public abstract void onMessage(T message,PeerToPeerConnection<T> connection);

}
