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

public class ViewCurrentPositionCommandTest {

    @Mock
    private LobbyQueue lobbyQueue;

    @Mock
    private AtomicReference<String> customerId;

    @InjectMocks
    private ViewCurrentPositionCommand viewCurrentPositionCommand;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void testExecute_notInLine() {
        String response = viewCurrentPositionCommand.execute(lobbyQueue, customerId);

        assertEquals(RESPONSE_MESSAGE.NOT_IN_LINE, response);

        verify(lobbyQueue, never()).getCurrentPosition(anyString());
    }

    @Test
    public void testExecute_returnCurrentLinePosition() {
        customerId.set("CUSTOMER-1");
        when(lobbyQueue.getCurrentPosition("CUSTOMER-1")).thenReturn(1);

        String response = viewCurrentPositionCommand.execute(lobbyQueue, customerId);

        assertEquals(RESPONSE_MESSAGE.CURRENT_LINE_POSITION + 1, response);

        verify(lobbyQueue).getCurrentPosition("CUSTOMER-1");
    }

}