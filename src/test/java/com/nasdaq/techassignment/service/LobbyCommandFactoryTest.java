package com.nasdaq.techassignment.service;

import com.nasdaq.techassignment.command.ExitLobbyCommand;
import com.nasdaq.techassignment.command.JoinLineCommand;
import com.nasdaq.techassignment.command.LeaveLineCommand;
import com.nasdaq.techassignment.command.ViewCurrentPositionCommand;
import com.nasdaq.techassignment.constant.Action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LobbyCommandFactoryTest {

    @Test
    public void testGetCommand_success() {
        assertInstanceOf(JoinLineCommand.class, LobbyCommandFactory.getCommand(Action.JOIN_LINE));
        assertInstanceOf(LeaveLineCommand.class, LobbyCommandFactory.getCommand(Action.LEAVE_LINE));
        assertInstanceOf(ViewCurrentPositionCommand.class, LobbyCommandFactory.getCommand(Action.VIEW_CURRENT_POSITION));
        assertInstanceOf(ExitLobbyCommand.class, LobbyCommandFactory.getCommand(Action.EXIT_LOBBY));
    }

    @Test
    public void testGetCommand_unknownAction() {
        Exception result = assertThrows(IllegalArgumentException.class,
                () -> LobbyCommandFactory.getCommand(null));

        String message = "Action can't be null";
        assertEquals(message, result.getMessage());
    }
}