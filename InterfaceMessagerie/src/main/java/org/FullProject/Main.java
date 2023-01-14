package org.FullProject;
import org.FullProject.Chat.ChatMessage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int port1 = 3501;
        int port2 = 3502;

        String author1 = "One";
        String author2 = "Two";

        MyServerThread server1 = new MyServerThread(new MyServer(author1), port1);
        MyServerThread server2 = new MyServerThread(new MyServer(author2), port2);

        new Thread(server1).start();
        new Thread(server2).start();

        Thread.sleep(1000);

        try {
            var connection = server1.server().connectTo("127.0.0.1", port2);
            Thread.sleep(1000);
            connection.sendMessage(new ChatMessage<>(author1, "Hello there"));
        } catch (IOException e) {
            System.out.println("Crashed during test!");
            e.printStackTrace();
        }
    }
}