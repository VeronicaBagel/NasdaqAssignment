package com.nasdaq.techassignment.command;

import com.nasdaq.techassignment.constant.RESPONSE_MESSAGE;
import com.nasdaq.techassignment.model.LobbyQueue;

import java.util.concurrent.atomic.AtomicReference;

public class ViewCurrentPositionCommand implements LobbyCommand {

    @Override
    public String execute(LobbyQueue queue, AtomicReference<String> customerId) {
        if (customerId.get() == null) {
            return RESPONSE_MESSAGE.NOT_IN_LINE;
        }

        Integer currentPosition = queue.getCurrentPosition(customerId.get());

        return currentPosition != null ? RESPONSE_MESSAGE.CURRENT_LINE_POSITION + currentPosition
                : RESPONSE_MESSAGE.NOT_IN_LINE;
    }
}
