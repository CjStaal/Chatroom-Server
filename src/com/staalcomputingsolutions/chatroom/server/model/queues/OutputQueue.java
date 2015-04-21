/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model.queues;

import com.staalcomputingsolutions.chatroom.server.model.queues.messages.ChatMessage;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * This is the object used to queue the messages that are outgoing. This is a
 * class, initialize in the creation of the server and then only use the methods
 * there on out.
 *
 * This object uses the singleton pattern to ensure there is only one of these
 * objects ever created.
 *
 * This class wraps the LinkedBlockinQueue class.
 *
 * Call {@link #getInstance()} to obtain the class object.
 *
 * @author Charles Joseph Staal
 */
public final class OutputQueue {

    private static OutputQueue instance = null;

    private static BlockingQueue<ChatMessage> outputQueue;

    /**
     * <strong>DO NOT USE DIRECTLY</strong>
     * This is the constructor for the OutputQueue.
     */
    private OutputQueue() {
        OutputQueue.outputQueue = new LinkedBlockingQueue();
    }

    /**
     * This is the method used to add messages to the queue.
     *
     * @param message
     * @return
     */
    public synchronized boolean add(ChatMessage message) {
        return outputQueue.add(message);
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized boolean addAll(Collection<ChatMessage> messages) {
        return outputQueue.addAll(messages);
    }

    /**
     *
     * @
     */
    public synchronized void clear() {
        outputQueue.clear();
    }

    /**
     *
     * @param message
     * @return
     * @
     */
    public synchronized boolean contains(ChatMessage message) {
        return outputQueue.contains(message);
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized boolean containsAll(Collection<ChatMessage> messages) {
        return outputQueue.containsAll(messages);
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized int drainTo(Collection messages) {
        return outputQueue.drainTo(messages);
    }

    /**
     *
     * @param messages
     * @param maxElements
     * @return
     * @
     */
    public synchronized int drainTo(Collection messages, int maxElements) {
        return outputQueue.drainTo(messages, maxElements);
    }

    /**
     *
     * @return @
     */
    public synchronized ChatMessage element() {
        return outputQueue.element();
    }

    /**
     *
     * @return @
     */
    public synchronized boolean isEmpty() {
        return outputQueue.isEmpty();
    }

    /**
     *
     * @return @
     */
    public synchronized Iterator iterator() {
        return outputQueue.iterator();
    }

    /**
     *
     * @param message
     * @return
     * @
     */
    public synchronized boolean offer(ChatMessage message) {
        return outputQueue.offer(message);
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
    public synchronized boolean offer(ChatMessage message, long timeout, TimeUnit unit) throws InterruptedException {
        return outputQueue.offer(message, timeout, unit);
    }

    /**
     *
     * @return @
     */
    public synchronized ChatMessage peek() {
        return outputQueue.peek();
    }

    /**
     *
     * @return @
     */
    public synchronized ChatMessage poll() {
        return outputQueue.poll();
    }

    /**
     *
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     * @
     */
    public synchronized ChatMessage poll(Long timeout, TimeUnit unit) throws InterruptedException {
        return outputQueue.poll(timeout, unit);
    }

    /**
     *
     * @param message
     * @throws InterruptedException
     * @
     */
    public synchronized void put(ChatMessage message) throws InterruptedException {
        outputQueue.put(message);
    }

    /**
     *
     * @return @
     */
    public synchronized ChatMessage remove() {
        return outputQueue.remove();
    }

    /**
     *
     * @return @
     */
    public synchronized int remainingCapacity() {
        return outputQueue.remainingCapacity();
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized boolean removeAll(Collection<ChatMessage> messages) {
        return outputQueue.removeAll(messages);
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized boolean retainAll(Collection<ChatMessage> messages) {
        return outputQueue.retainAll(messages);
    }

    /**
     *
     * @return @
     */
    public synchronized int size() {
        return outputQueue.size();
    }

    /**
     *
     * @return @throws InterruptedException
     */
    public synchronized ChatMessage take() throws InterruptedException {
        return outputQueue.take();
    }

    /**
     *
     * @return @
     */
    public synchronized Object[] toArray() {
        return outputQueue.toArray();
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized ChatMessage[] toArray(ChatMessage[] messages) {
        return outputQueue.toArray(messages);
    }

    /**
     * This is the method to obtain the <code>InputQueue</code> class object.
     *
     * @return InputQueue the singleton InputQueue.
     */
    public static synchronized OutputQueue getInstance() {
        if (instance == null) {
            instance = new OutputQueue();
        }
        return instance;
    }
}
