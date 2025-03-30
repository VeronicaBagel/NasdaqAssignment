package com.nasdaq.techassignment.command;

import com.nasdaq.techassignment.model.LobbyQueue;

import java.util.concurrent.atomic.AtomicReference;

public interface LobbyCommand {

    String execute(LobbyQueue queue, AtomicReference<String> customerId);

}
