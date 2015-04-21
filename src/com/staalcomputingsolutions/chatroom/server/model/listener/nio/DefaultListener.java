/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.server.model.listener.nio;

import com.staalcomputingsolutions.chatroom.server.model.ChatServerContext;
import com.staalcomputingsolutions.chatroom.server.model.listener.acceptor.SocketAcceptor;
import java.net.InetSocketAddress;

/**
 *
 * @author Charles
 */
public class DefaultListener extends AbstractListener {


    boolean suspended = false;

    private InetSocketAddress address;

    private ChatServerContext context;

    private SocketAcceptor acceptor;

    private Thread acceptorThread;

    public DefaultListener(String serverAddress, int port) {
        super(serverAddress, port);
    }

    @Override
    public synchronized void start(ChatServerContext context) {
        if (!isStopped()) {
            throw new IllegalStateException("Listener already started.");
        }
        try {
            this.context = context;
            if (getServerAddress() != null) {
                address = new InetSocketAddress(getServerAddress(), getPort());
            } else {
                address = new InetSocketAddress(getPort());
            }

            acceptor = new SocketAcceptor(getPort(), context);
            acceptorThread = new Thread(acceptor);
            acceptorThread.start();
        } catch (RuntimeException ex) {
            stop();
            throw ex;
        }
    }

    @Override
    public void stop() {
        if (acceptor != null) {
            acceptor = null;
            acceptorThread.stop();
        }

    }

    @Override
    public boolean isStopped() {
        return this.acceptor == null;
    }

    @Override
    public void suspend() {
        if(acceptor != null && !suspended){
            acceptorThread.stop();
            acceptor = null;
            suspended = true;
        }
    }

    @Override
    public void resume() {
        if(acceptor != null && suspended){
                acceptor = new SocketAcceptor(getPort(), context);
                acceptorThread = new Thread(acceptor);
                acceptorThread.start();
                suspended = false;
        }
    }

    @Override
    public boolean isSuspended() {
        return this.suspended;
    }

}
