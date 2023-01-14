package org.FullProject.PeerToPeer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class PeerToPeerConnection<T extends Serializable> implements Runnable {
    public final Socket socket;
    private final PeerToPeerServer<T> server;
    private ObjectOutputStream messageOutputStream = null;

    public PeerToPeerConnection(Socket socket, PeerToPeerServer<T> server) {
        this.socket = socket;
        this.server = server;
    }

    public PeerToPeerConnection(int i) {
        this.socket = null;
        this.server = null;
    }

    @Override
    public void run() {
        System.out.println("Starting peer to peer connection to " + socket.getInetAddress());
        try {
            this.messageOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Output stream gotten to " + socket.getInetAddress());

            ObjectInputStream messageInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Input stream gotten to " + socket.getInetAddress());
            while (true) {
                T message = (T) messageInputStream.readObject();
                this.server.onMessage(message, this);
            }
        } catch (Exception e) {
//
        }
        this.messageOutputStream = null;
        server.disconnect(this);
        System.out.println("Ended peer to peer connection to " + socket.getInetAddress());
    }

    public void sendMessage(T message) throws IOException, IllegalStateException {
        if (!this.isConnected()) {
            throw new IllegalStateException("Cannot send message before output stream has been acquired or if socket is disconnected");
        }
        this.messageOutputStream.writeObject(message);
    }

    public boolean isConnected() {
        return this.socket.isConnected() && this.messageOutputStream != null;
    }

    public void close() throws IOException {
        this.socket.close();
    }

    public String getPort() {
        return this.socket.getInetAddress().getHostAddress() + ":" + this.socket.getPort();
    }
}