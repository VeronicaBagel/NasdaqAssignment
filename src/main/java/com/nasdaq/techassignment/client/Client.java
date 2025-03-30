package com.nasdaq.techassignment.client;

import com.nasdaq.techassignment.constant.CONNECTION_DEFAULT;
import com.nasdaq.techassignment.constant.RESPONSE_MESSAGE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        try (Socket socket = new Socket(CONNECTION_DEFAULT.HOST, CONNECTION_DEFAULT.PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(in.readLine());

            while (true) {
                String command = scanner.nextLine();
                out.println(command);

                String response = in.readLine();
                System.out.println(response);

                if (RESPONSE_MESSAGE.EXITING_LOBBY.equals(response)) {
                    break;
                }
            }
        } catch (IOException e) {
            LOGGER.error("Something went wrong: " + e.getMessage());
        }
    }
}