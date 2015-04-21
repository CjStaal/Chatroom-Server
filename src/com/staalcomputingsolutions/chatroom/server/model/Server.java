package com.staalcomputingsolutions.chatroom.server.model;

import com.staalcomputingsolutions.chatroom.server.model.exceptions.ChatServerException;

/**
 * This is the starting point of all the servers. It invokes a new listener
 * thread. <code>Server</code> implementation is used to create the server
 * socket and handle client connection.
 *
 * @author Charles Joseph Staal
 */
public interface Server {

    /**
     * Start the server. Open a new listener thread.
     * @throws ChatServerException 
     */
    void start() throws ChatServerException;
    
    /**
     * Stop the server. Stop the listener thread.
     */
    void stop();
    
    /**
     * Get the server status.
     * @return true if the server is stopped
     */
    boolean isStopped();
    
    /**
     * Suspend further requests
     */
    void suspend();
    
    /**
     * Resume the server handler
     */
    void resume();
    
    /**
     * Is the server suspended
     * @return true if the server is suspended
     */
    boolean isSuspended();
    
}