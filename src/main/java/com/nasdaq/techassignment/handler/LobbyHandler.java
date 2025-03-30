package com.nasdaq.techassignment.handler;

import com.nasdaq.techassignment.command.LobbyCommand;
import com.nasdaq.techassignment.constant.Action;
import com.nasdaq.techassignment.constant.RESPONSE_MESSAGE;
import com.nasdaq.techassignment.model.LobbyQueue;
import com.nasdaq.techassignment.service.LobbyCommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicReference;

public class LobbyHandler implements Runnable {

    private final Socket socket;
    private final LobbyQueue queue;
    private final AtomicReference<String> customerId = new AtomicReference<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(LobbyHandler.class);

    public LobbyHandler(Socket socket, LobbyQueue queue) {
        this.socket = socket;
        this.queue = queue;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            output.println(RESPONSE_MESSAGE.LOBBY_INSTRUCTION);

            String inputAction;
            while ((inputAction = input.readLine()) != null) {

                Action action = Action.fromUserInput(inputAction);
                LobbyCommand command = action != null ? LobbyCommandFactory.getCommand(action) : null;

                String response = command != null
                        ? command.execute(queue, customerId)
                        : RESPONSE_MESSAGE.UNRECOGNIZED_ACTION;

                output.println(response);

                if (action == Action.EXIT_LOBBY) {
                    break;
                }
            }
        } catch (IOException e) {
            LOGGER.error("Something went wrong: " + e.getMessage());
        } finally {
            closeSocket(socket);
        }
    }

    private void closeSocket(Socket socket) {
        try {
            if (socket != null && !socket.isClosed()) {
                LOGGER.info("Closing connection from: " + socket.getRemoteSocketAddress());
                socket.close();
            }
        } catch (IOException e) {
            LOGGER.error("Something went wrong while closing socket for " + socket.getRemoteSocketAddress()
                            + ": " + e.getMessage());
        }
    }

}