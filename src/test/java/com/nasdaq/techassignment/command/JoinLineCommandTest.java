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

public class JoinLineCommandTest {

    @Mock
    private LobbyQueue lobbyQueue;

    @Mock
    private AtomicReference<String> customerId;

    @InjectMocks
    private JoinLineCommand joinLineCommand;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void testExecute_alreadyInLine() {
        customerId.set("CUSTOMER-1");

        String response = joinLineCommand.execute(lobbyQueue, customerId);

        assertEquals(RESPONSE_MESSAGE.ALREADY_IN_LINE, response);
        verify(lobbyQueue, never()).join();
    }

    @Test
    public void testExecute_firstTimeInLine() {
        when(lobbyQueue.join()).thenReturn("CUSTOMER-1");

        String response = joinLineCommand.execute(lobbyQueue, customerId);

        assertEquals(RESPONSE_MESSAGE.JOINED_THE_LINE, response);
        assertEquals("CUSTOMER-1", customerId.get());

        verify(lobbyQueue).join();
    }
}