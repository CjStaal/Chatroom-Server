package com.staalcomputingsolutions.chatroom.server.model.listener;


import com.staalcomputingsolutions.chatroom.server.model.ServerContext;
import java.net.InetAddress;


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
     * @param serverContext The current {@link ServerContext}
     * 
     */
    void start(ServerContext serverContext);

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

    

    /**
     * Get the port on which this listener is waiting for requests. For
     * listeners where the port is automatically assigned, this will return the
     * bound port.
     * 
     * @return The port
     */
    int getPort();

    /**
     * Get the {@link InetAddress} used for binding the local socket. Defaults
     * to null, that is, the server binds to all available network interfaces
     * 
     * @return The local socket {@link InetAddress}, if set
     */
    String getServerAddress();

}