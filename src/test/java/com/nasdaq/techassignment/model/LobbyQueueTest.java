package com.nasdaq.techassignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class LobbyQueueTest {

    @InjectMocks
    private LobbyQueue lobbyQueue;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void testJoin_success() {
        String customerId1 = lobbyQueue.join();
        String customerId2 = lobbyQueue.join();

        assertNotNull(customerId1);
        assertNotNull(customerId2);
        assertNotEquals(customerId1, customerId2);
    }

    @Test
    public void testGetCurrentPosition_success() {
        String customerId1 = lobbyQueue.join();
        String customerId2 = lobbyQueue.join();

        assertEquals(1, lobbyQueue.getCurrentPosition(customerId1));
        assertEquals(2, lobbyQueue.getCurrentPosition(customerId2));
    }

    @Test
    public void testLeave_success() {
        String customerId1 = lobbyQueue.join();
        String customerId2 = lobbyQueue.join();
        String customerId3 = lobbyQueue.join();

        lobbyQueue.leave(customerId1);

        assertNull(lobbyQueue.getCurrentPosition(customerId1));
        assertEquals(1, lobbyQueue.getCurrentPosition(customerId2));
        assertEquals(2, lobbyQueue.getCurrentPosition(customerId3));
    }

    @Test
    public void testGetCurrentPosition_unrecognizedId() {
        assertNull(lobbyQueue.getCurrentPosition("test test"));
    }
}