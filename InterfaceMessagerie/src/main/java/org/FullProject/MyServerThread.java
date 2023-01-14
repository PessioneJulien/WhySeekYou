package org.FullProject;

import java.io.IOException;

public record MyServerThread(MyServer server, int port) implements Runnable {

    @Override
    public void run() {
        try {
            this.server.listen(this.port);
        } catch (IOException e) {
            System.out.println("Server crashed! Port: " + this.port);
            e.printStackTrace();
        }
    }
}
