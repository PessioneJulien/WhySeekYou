import org.FullProject.MyServer;
import org.FullProject.MyServerThread;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.FullProject.Chat.ChatMessage;
import org.FullProject.Chat.ChatServer;
import org.FullProject.PeerToPeer.PeerToPeerConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MyServerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before("")
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After("")
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testOnMessageReceived() {
        MyServer server = new MyServer("author");
        PeerToPeerConnection<String> connection = new PeerToPeerConnection<>(1234);
        ChatMessage<String> message = new ChatMessage<>("author", "Hello");
        server.onMessageReceived(message, connection);
        assertTrue(outContent.toString().contains("Message received from 1234: Hello"));
    }

    @Test
    public void testOnConnectionEstablished() {
        MyServer server = new MyServer("author");
        PeerToPeerConnection<String> connection = new PeerToPeerConnection<>(1234);
        server.onConnectionEstablished(connection);
        assertTrue(outContent.toString().contains("Connection established with 1234"));
    }

    @Test
    public void testOnConnectionClosed() {
        MyServer server = new MyServer("author");
        PeerToPeerConnection<String> connection = new PeerToPeerConnection<>(1234);
        server.onConnectionClosed(connection);
        assertTrue(outContent.toString().contains("Connection closed with 1234"));
    }

    @Test
    public void testOnMessageWithHandshake() {
        MyServer server = new MyServer("author");
        PeerToPeerConnection<ChatMessage<String>> connection = new PeerToPeerConnection<>(1234);
        ChatMessage<String> message = new ChatMessage<>("author", "Hello there");
        server.onMessage(message, connection);
        assertTrue(outContent.toString().contains("Replying to handshake"));
        assertTrue(outContent.toString().contains("From author at "));
        assertTrue(outContent.toString().contains("Hello there"));
    }
    @Test
    public void testRunMethod() {
        MyServer server = new MyServer("author");
        int port = 1234;
        MyServerThread thread = new MyServerThread(server, port);
        thread.run();
        assertTrue(outContent.toString().contains("Server crashed! Port: 1234"));
    }
}