package com.nasdaq.techassignment.server;

import com.nasdaq.techassignment.constant.CONNECTION_DEFAULT;
import com.nasdaq.techassignment.handler.LobbyHandler;
import com.nasdaq.techassignment.model.LobbyQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private final ExecutorService executorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public Server(int port) {
        this.port = port;
        this.executorService = Executors.newCachedThreadPool();
    }

    public void startServer() {
        LobbyQueue queue = new LobbyQueue();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOGGER.info("Server started on port: " + port);

            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();
                LOGGER.info("Accepted connection from: " + socket.getRemoteSocketAddress());

                executorService.submit(new LobbyHandler(socket, queue));
            }
        } catch (IOException e) {
            LOGGER.error("Something went wrong: " + e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(CONNECTION_DEFAULT.PORT);
        server.startServer();
    }
}