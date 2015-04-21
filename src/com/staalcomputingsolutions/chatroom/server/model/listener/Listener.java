package com.staalcomputingsolutions.chatroom.server.model.listener;

import com.staalcomputingsolutions.chatroom.server.model.ChatServerContext;

/**
 * Interface for the component responsible for waiting for incoming socket
 * requests and kicking off {@link FtpIoSession}s
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public interface Listener {

    /**
     * Start the listener, will initiate the listener waiting on the socket. The
     * method should not return until the listener has started accepting socket
     * requests.
     *
     * @param serverContext The current {@link ChatServerContext}
     *
     */
    void start(ChatServerContext serverContext);

    /**
     * Stop the listener, it should no longer accept socket requests. The method
     * should not return until the listener has stopped accepting socket
     * requests.
     */
    void stop();

    /**
     * Checks if the listener is currently started.
     *
     * @return False if the listener is started
     */
    boolean isStopped();

    /**
     * Temporarily stops the listener from accepting socket requests. Resume the
     * listener by using the {@link #resume()} method. The method should not
     * return until the listener has stopped accepting socket requests.
     */
    void suspend();

    /**
     * Resumes a suspended listener. The method should not return until the
     * listener has started accepting socket requests.
     */
    void resume();

    /**
     * Checks if the listener is currently suspended
     *
     * @return True if the listener is suspended
     */
    boolean isSuspended();

    public String getServerAddress();

    public int getPort();
}
