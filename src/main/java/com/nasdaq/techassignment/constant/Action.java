package com.nasdaq.techassignment.constant;

public enum Action {
    JOIN_LINE,
    VIEW_CURRENT_POSITION,
    LEAVE_LINE,
    EXIT_LOBBY;

    public static Action fromUserInput(String customerInput) {
        try {
            return valueOf(customerInput.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
