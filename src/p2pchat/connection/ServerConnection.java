package p2pchat.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import p2pchat.model.Message;

/**
 *
 */
public class ServerConnection {

    private boolean connected = false;
    private ConnectionManager manager;
    Socket tempSocket;
    ServerSession serverSession[] = new ServerSession[10];
    private int currentConnections = 0;
    private int currentSocket = 0;

    public ServerConnection(ConnectionManager manager) {
        this.manager = manager;
        this.connected = false;
    }

    public void startServer(String address, int port) {

        init();

        while (true) {

// Create a socket with a timeout
            try {

                System.out.println("Starting server at port " + port + currentSocket);

                // Create an unbound socket
                ServerSocket serverSocket = new ServerSocket(port + currentSocket);

                tempSocket = serverSocket.accept();

                for (int i = 0; i < 10; i++) {
                    if (!serverSession[i].isAlive()) {
                        serverSession[i].init(tempSocket);
                        serverSession[i].start();
                        break;
                    }
                }

                

            } catch (IOException e) {
                System.out.println("Error: Connection error");
            } finally {
                currentSocket = (currentSocket + 1) % 10;
            }


        }

    }

    public boolean isConnected() {
        return connected;
    }

    private void init() {
        for (int i = 0; i < 10; i++) {
            serverSession[i] = new ServerSession();
        }
    }
}
