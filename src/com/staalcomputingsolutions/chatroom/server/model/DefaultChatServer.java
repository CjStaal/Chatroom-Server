/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model;

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
    private ServerContext serverContext;

    private boolean started = false;

    private boolean suspended = false;

    /**
     * Internal constructor, do not use directly. Use {@link ChatServerFactory}
     * instead
     *
     * @param serverContext
     */
    public DefaultChatServer(ServerContext serverContext) {
        this.serverContext = serverContext;
    }

    @Override
    public void start() throws ChatServerException {
        if (serverContext == null) {
            // The chat server already been stopped, can not be restarted.
            throw new IllegalStateException("ChatServer has been stopped. Restart is not supported");
        }
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isStopped() {
        return !this.started;
    }

    @Override
    public void suspend() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSuspended() {
        return this.suspended;
    }

    public ServerContext getServerContext() {
        return this.serverContext;
    }

}