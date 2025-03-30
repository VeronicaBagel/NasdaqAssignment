package com.nasdaq.techassignment.command;

import com.nasdaq.techassignment.constant.RESPONSE_MESSAGE;
import com.nasdaq.techassignment.model.LobbyQueue;

import java.util.concurrent.atomic.AtomicReference;

public class LeaveLineCommand implements LobbyCommand {

    @Override
    public String execute(LobbyQueue queue, AtomicReference<String> customerId) {
        if (customerId.get() == null) {
            return RESPONSE_MESSAGE.NOT_IN_LINE;
        }

        queue.leave(customerId.get());

        customerId.set(null);

        return RESPONSE_MESSAGE.LINE_EXIT;
    }
}
