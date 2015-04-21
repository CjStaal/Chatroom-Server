/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model;

import com.staalcomputingsolutions.chatroom.server.model.exceptions.ChatServerConfigurationException;
import com.staalcomputingsolutions.chatroom.server.model.exceptions.ChatServerException;
import com.staalcomputingsolutions.chatroom.server.model.listener.ListenerFactory;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Copyright [2015] [Charles Joseph Staal]
 */
/**
 *
 * @author Charles Joseph Staal
 */
public class DefaultChatServer implements Server {

    private final Logger log = LoggerFactory.getLogger(DefaultChatServer.class);
    private final ChatServerContext serverContext;

    private boolean started = false;

    private boolean suspended = false;

    private Thread iqsThread, seThread, ceThread;
    
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
            iqsThread = new Thread(serverContext.getInputQueueSorter());
            seThread = new Thread(serverContext.getSystemExecutor());
            ceThread = new Thread(serverContext.getChatExecutor());
            try {
                serverContext.setListener(ListenerFactory.createListener());
                started = true;
                iqsThread.start();
                seThread.start();
                ceThread.start();
                serverContext.getListener().start(serverContext);
            } catch (ChatServerConfigurationException ex) {
                java.util.logging.Logger.getLogger(DefaultChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void stop() {
        iqsThread.stop();
        seThread.stop();
        ceThread.stop();
        serverContext.getListener().stop();
        started = false;
    }

    @Override
    public boolean isStopped() {
        return !this.started;
    }

    @Override
    public void suspend() {
        iqsThread.stop();
        seThread.stop();
        ceThread.stop();
        serverContext.getListener().suspend();
        suspended = true;
    }

    @Override
    public void resume() {
            iqsThread = new Thread(serverContext.getInputQueueSorter());
            seThread = new Thread(serverContext.getSystemExecutor());
            ceThread = new Thread(serverContext.getChatExecutor());
            try {
                serverContext.setListener(ListenerFactory.createListener());
                started = true;
                iqsThread.start();
                seThread.start();
                ceThread.start();
                serverContext.getListener().resume();
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
