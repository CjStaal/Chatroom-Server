/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model.queues;

import com.staalcomputingsolutions.chatroom.server.model.queues.messages.Message;
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
public final class InputQueue {

    private static InputQueue instance = null;

    private static BlockingQueue<Message> outputQueue;

    /**
     * <strong>DO NOT USE DIRECTLY</strong>
     * This is the constructor for the OutputQueue.
     */
    private InputQueue() {
        InputQueue.outputQueue = new LinkedBlockingQueue();
    }

    /**
     * This is the method used to add messages to the queue.
     *
     * @param message
     * @return
     */
    public synchronized boolean add(Message message) {
        return outputQueue.add(message);
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized boolean addAll(Collection<Message> messages) {
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
    public synchronized boolean contains(Message message) {
        return outputQueue.contains(message);
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized boolean containsAll(Collection<Message> messages) {
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
    public synchronized Message element() {
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
    public synchronized boolean offer(Message message) {
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
    public synchronized boolean offer(Message message, long timeout, TimeUnit unit) throws InterruptedException {
        return outputQueue.offer(message, timeout, unit);
    }

    /**
     *
     * @return @
     */
    public synchronized Message peek() {
        return outputQueue.peek();
    }

    /**
     *
     * @return @
     */
    public synchronized Message poll() {
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
    public synchronized Message poll(Long timeout, TimeUnit unit) throws InterruptedException {
        return outputQueue.poll(timeout, unit);
    }

    /**
     *
     * @param message
     * @throws InterruptedException
     * @
     */
    public synchronized void put(Message message) throws InterruptedException {
        outputQueue.put(message);
    }

    /**
     *
     * @return @
     */
    public synchronized Message remove() {
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
    public synchronized boolean removeAll(Collection<Message> messages) {
        return outputQueue.removeAll(messages);
    }

    /**
     *
     * @param messages
     * @return
     * @
     */
    public synchronized boolean retainAll(Collection<Message> messages) {
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
    public synchronized Message take() throws InterruptedException {
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
    public synchronized Message[] toArray(Message[] messages) {
        return outputQueue.toArray(messages);
    }

    /**
     * This is the method to obtain the <code>InputQueue</code> class object.
     *
     * @return InputQueue the singleton InputQueue.
     */
    public static synchronized InputQueue getInstance() {
        if (instance == null) {
            instance = new InputQueue();
        }
        return instance;
    }
}
