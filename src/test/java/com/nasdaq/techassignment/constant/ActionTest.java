package com.nasdaq.techassignment.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {

    @Test
    public void testFromUserInput_success() {
        assertEquals(Action.JOIN_LINE, Action.fromUserInput("JOIN_LINE"));
        assertEquals(Action.LEAVE_LINE, Action.fromUserInput("LEAVE_LINE"));
        assertEquals(Action.VIEW_CURRENT_POSITION, Action.fromUserInput("VIEW_CURRENT_POSITION"));
        assertEquals(Action.EXIT_LOBBY, Action.fromUserInput("EXIT_LOBBY"));
        assertEquals(Action.JOIN_LINE, Action.fromUserInput("join_line"));
        assertEquals(Action.EXIT_LOBBY, Action.fromUserInput("exit_lobby"));
    }

    @Test
    public void testFromUserInput_caseInsensitive() {
        assertEquals(Action.JOIN_LINE, Action.fromUserInput("JoiN_LiNE"));
        assertEquals(Action.LEAVE_LINE, Action.fromUserInput("leave_linE"));
        assertEquals(Action.VIEW_CURRENT_POSITION, Action.fromUserInput("VIew_CURRENT_positioN"));
        assertEquals(Action.EXIT_LOBBY, Action.fromUserInput("exit_LOBBY"));
    }

    @Test
    public void testFromUserInput_nullInput() {
        assertNull(Action.fromUserInput(null));
    }

    @Test
    public void testFromUserInput_invalidInput() {
        assertNull(Action.fromUserInput("test test"));
    }
}