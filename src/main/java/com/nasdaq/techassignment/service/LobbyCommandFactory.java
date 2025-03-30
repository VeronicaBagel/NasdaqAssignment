package com.nasdaq.techassignment.service;

import com.nasdaq.techassignment.command.*;
import com.nasdaq.techassignment.constant.Action;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LobbyCommandFactory {
    private static final Map<Action, LobbyCommand> commands = new ConcurrentHashMap<>();

    static {
        commands.put(Action.JOIN_LINE, new JoinLineCommand());
        commands.put(Action.LEAVE_LINE, new LeaveLineCommand());
        commands.put(Action.VIEW_CURRENT_POSITION, new ViewCurrentPositionCommand());
        commands.put(Action.EXIT_LOBBY, new ExitLobbyCommand());
    }

    public static LobbyCommand getCommand(Action action) {
        if (action == null) {
            throw new IllegalArgumentException("Action can't be null");
        }
        return commands.get(action);
    }
}
