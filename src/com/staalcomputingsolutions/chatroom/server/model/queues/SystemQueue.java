/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model.queues;

import com.staalcomputingsolutions.chatroom.server.model.queues.messages.SystemMessage;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Charles Joseph Staal
 */
public class SystemQueue {
    
    private static SystemQueue instance = null;

    private static BlockingQueue<SystemMessage> systemQueue;

    /**
     * <strong>DO NOT USE DIRECTLY</strong>
     * This is the constructor for the SystemQueue.
     */
    private SystemQueue() {
        SystemQueue.systemQueue = new LinkedBlockingQueue();
    }

    /**
     * This is the method used to add messages to the queue.
     *
     * @param message
     * @return
     */
    public synchronized boolean add(SystemMessage message) {
        return systemQueue.add(message);
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized boolean addAll(Collection<SystemMessage> messages) {
        return systemQueue.addAll(messages);
    }

    /**
     *
     * @
     */
    public synchronized void clear() {
        systemQueue.clear();
    }

    /**
     *
     * @param message
     * @return
     * @
     */
    public synchronized boolean contains(SystemMessage message) {
        return systemQueue.contains(message);
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized boolean containsAll(Collection<SystemMessage> messages) {
        return systemQueue.containsAll(messages);
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized int drainTo(Collection messages) {
        return systemQueue.drainTo(messages);
    }

    /**
     *
     * @param messages
     * @param maxElements
     * @return
     * @
     */
    public synchronized int drainTo(Collection messages, int maxElements) {
        return systemQueue.drainTo(messages, maxElements);
    }

    /**
     *
     * @return @
     */
    public synchronized SystemMessage element() {
        return systemQueue.element();
    }

    /**
     *
     * @return @
     */
    public synchronized boolean isEmpty() {
        return systemQueue.isEmpty();
    }

    /**
     *
     * @return @
     */
    public synchronized Iterator iterator() {
        return systemQueue.iterator();
    }

    /**
     *
     * @param message
     * @return
     * @
     */
    public synchronized boolean offer(SystemMessage message) {
        return systemQueue.offer(message);
    }

    /**
     *
     * @param message
     * @param timeout
     * @param unit
     * @return
     * @throws java.lang.InterruptedException
     * @
     */
    public synchronized boolean offer(SystemMessage message, long timeout, TimeUnit unit) throws InterruptedException {
            return systemQueue.offer(message, timeout, unit);
    }

    /**
     *
     * @return @
     */
    public synchronized SystemMessage peek() {
        return systemQueue.peek();
    }

    /**
     *
     * @return @
     */
    public synchronized SystemMessage poll() {
        return systemQueue.poll();
    }

    /**
     *
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     * @
     */
    public synchronized SystemMessage poll(Long timeout, TimeUnit unit) throws InterruptedException {
            return systemQueue.poll(timeout, unit);
    }

    /**
     *
     * @param message
     * @throws InterruptedException
     * @
     */
    public synchronized void put(SystemMessage message) throws InterruptedException {
            systemQueue.put(message);
    }

    /**
     *
     * @return @
     */
    public synchronized SystemMessage remove() {
            return systemQueue.remove();
    }

    /**
     *
     * @return @
     */
    public synchronized int remainingCapacity() {
            return systemQueue.remainingCapacity();
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized boolean removeAll(Collection<SystemMessage> messages) {
            return systemQueue.removeAll(messages);
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized boolean retainAll(Collection<SystemMessage> messages) {
            return systemQueue.retainAll(messages);
    }

    /**
     *
     * @return @
     */
    public synchronized int size() {
            return systemQueue.size();
    }

    /**
     *
     * @return @throws InterruptedException
     */
    public synchronized SystemMessage take() throws InterruptedException {
            return systemQueue.take();
    }

    /**
     *
     * @return @
     */
    public synchronized Object[] toArray() {
            return systemQueue.toArray();
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized SystemMessage[] toArray(SystemMessage[] messages) {
            return systemQueue.toArray(messages);
    }

    /**
     * This is the method to obtain the <code>SystemQueue</code> class object.
     *
     * @return SystemQueue the singleton SystemQueue.
     */
    public static synchronized SystemQueue getInstance() {
        if (instance == null) {
            instance = new SystemQueue();
        }
        return instance;
    }
}
