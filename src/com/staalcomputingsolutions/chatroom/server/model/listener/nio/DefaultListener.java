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

    private SocketAcceptor acceptor;

    public DefaultListener(String serverAddress, int port) {
        super(serverAddress, port);
    }

    @Override
    public synchronized void start(ChatServerContext context) {
        if (!isStopped()) {
            throw new IllegalStateException("Listener already started.");
        } else {
            acceptor = new SocketAcceptor(getPort(), context);
            acceptor.start();
        }
    }

    @Override
    public void stop() {
        acceptor.stop();

    }

    @Override
    public boolean isStopped() {
        return this.acceptor == null;
    }

    @Override
    public void suspend() {
        if (acceptor != null && !suspended) {
            acceptor.stop();
            suspended = true;
        }
    }

    @Override
    public void resume() {
        if (acceptor != null && suspended) {
            acceptor.start();
            suspended = false;
        }
    }

    @Override
    public boolean isSuspended() {
        return this.suspended;
    }

}
