package com.nasdaq.techassignment.command;

import com.nasdaq.techassignment.constant.RESPONSE_MESSAGE;
import com.nasdaq.techassignment.model.LobbyQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ExitLobbyCommandTest {

    @Mock
    private LobbyQueue lobbyQueue;

    @Mock
    private AtomicReference<String> customerId;

    @InjectMocks
    private ExitLobbyCommand exitLobbyCommand;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void testExecute_previouslyNotInLine() {
        String response = exitLobbyCommand.execute(lobbyQueue, customerId);

        assertEquals(RESPONSE_MESSAGE.EXITING_LOBBY, response);

        verify(lobbyQueue, never()).leave(anyString());
    }

    @Test
    public void testExecute_previouslyInLine() {
        customerId.set("CUSTOMER-1");

        String response = exitLobbyCommand.execute(lobbyQueue, customerId);

        assertEquals(RESPONSE_MESSAGE.EXITING_LOBBY, response);

        verify(lobbyQueue).leave("CUSTOMER-1");
    }
}