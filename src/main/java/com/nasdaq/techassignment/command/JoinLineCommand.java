package com.nasdaq.techassignment.command;

import com.nasdaq.techassignment.constant.RESPONSE_MESSAGE;
import com.nasdaq.techassignment.model.LobbyQueue;

import java.util.concurrent.atomic.AtomicReference;

public class JoinLineCommand implements LobbyCommand {

    @Override
    public String execute(LobbyQueue queue, AtomicReference<String> customerId) {
        if (customerId.get() != null) {
            return RESPONSE_MESSAGE.ALREADY_IN_LINE;
        }

        customerId.set(queue.join());

        return RESPONSE_MESSAGE.JOINED_THE_LINE;
    }
}
