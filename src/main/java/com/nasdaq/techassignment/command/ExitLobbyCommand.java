package com.nasdaq.techassignment.command;

import com.nasdaq.techassignment.constant.RESPONSE_MESSAGE;
import com.nasdaq.techassignment.model.LobbyQueue;

import java.util.concurrent.atomic.AtomicReference;

public class ExitLobbyCommand implements LobbyCommand {

    @Override
    public String execute(LobbyQueue queue, AtomicReference<String> customerId) {
        String currentId = customerId.get();
        if (currentId != null) {
            queue.leave(currentId);
        }
        return RESPONSE_MESSAGE.EXITING_LOBBY;
    }
}
