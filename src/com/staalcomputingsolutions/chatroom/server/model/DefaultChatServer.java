/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model;

import com.staalcomputingsolutions.chatroom.server.model.exceptions.ChatServerConfigurationException;
import com.staalcomputingsolutions.chatroom.server.model.exceptions.ChatServerException;
import com.staalcomputingsolutions.chatroom.server.model.listener.ListenerFactory;
import java.util.logging.Level;

/*
 * Copyright [2015] [Charles Joseph Staal]
 */
/**
 *
 * @author Charles Joseph Staal
 */
public class DefaultChatServer implements Server {

    private final ChatServerContext serverContext;

    private boolean started = false;

    private boolean suspended = false;

    /**
     * Internal constructor, do not use directly. Use {@link ChatServerFactory}
     * instead
     *
     * @param serverContext
     */
    public DefaultChatServer(ChatServerContext serverContext) {
        this.serverContext = serverContext;
    }

    @Override
    public void start() throws ChatServerException {
        if (serverContext == null) {
            // The chat server already been stopped, can not be restarted.
            throw new IllegalStateException("ChatServer has been stopped. Restart is not supported");
        } else {
            try {
                serverContext.setListener(ListenerFactory.createListener());
                serverContext.getInputQueueSorter().start();
                serverContext.getSystemExecutor().start();
                serverContext.getChatExecutor().start();
                serverContext.getListener().start(serverContext);
                started = true;
            } catch (ChatServerConfigurationException ex) {
                java.util.logging.Logger.getLogger(DefaultChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void stop() {
        serverContext.getInputQueueSorter().stop();
        serverContext.getSystemExecutor().stop();
        serverContext.getChatExecutor().stop();
        serverContext.getListener().stop();
        started = false;
    }

    @Override
    public boolean isStopped() {
        return !this.started;
    }

    @Override
    public void suspend() {
        serverContext.getInputQueueSorter().stop();
        serverContext.getSystemExecutor().stop();
        serverContext.getChatExecutor().stop();
        serverContext.getListener().suspend();
        suspended = true;
    }

    @Override
    public void resume() {
        try {
            serverContext.setListener(ListenerFactory.createListener());
            serverContext.getInputQueueSorter().start();
            serverContext.getSystemExecutor().start();
            serverContext.getChatExecutor().start();
            serverContext.getListener().resume();
            suspended = false;
            started = true;
        } catch (ChatServerConfigurationException ex) {
            java.util.logging.Logger.getLogger(DefaultChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean isSuspended() {
        return this.suspended;
    }

    public ChatServerContext getServerContext() {
        return this.serverContext;
    }

}
