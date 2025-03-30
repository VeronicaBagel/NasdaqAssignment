package com.nasdaq.techassignment.model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LobbyQueue {

    private final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<String, AtomicInteger> positions = new ConcurrentHashMap<>();
    private final AtomicInteger customerId = new AtomicInteger(0);

    private final ReentrantLock lobbyLock = new ReentrantLock();

    private static final String CUSTOMER_ID_PREFIX = "CUSTOMER-";

    public String join() {
        lobbyLock.lock();
        try {
            String clientId = CUSTOMER_ID_PREFIX + customerId.incrementAndGet();
            queue.add(clientId);
            configureLinePositions();
            return clientId;
        } finally {
            lobbyLock.unlock();
        }
    }

    public void leave(String clientId) {
        lobbyLock.lock();
        try {
            if (queue.remove(clientId)) {
                configureLinePositions();
            }
        } finally {
            lobbyLock.unlock();
        }
    }

    public Integer getCurrentPosition(String clientId) {
        if (clientId == null) {
            return null;
        }
        AtomicInteger position = positions.get(clientId);
        return position != null ? position.get() : null;
    }

    private void configureLinePositions() {
        lobbyLock.lock();
        try {
            ConcurrentHashMap<String, AtomicInteger> updatedLine = new ConcurrentHashMap<>();
            int defaultPosition = 1;
            for (String id : queue) {
                updatedLine.put(id, new AtomicInteger(defaultPosition++));
            }
            positions.clear();
            positions.putAll(updatedLine);
        } finally {
            lobbyLock.unlock();
        }
    }

}
