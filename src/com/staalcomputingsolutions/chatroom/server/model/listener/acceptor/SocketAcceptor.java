/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.server.model.listener.acceptor;

import com.staalcomputingsolutions.chatroom.server.model.ChatServerContext;
import com.staalcomputingsolutions.chatroom.server.model.queues.SystemQueue;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.SystemMessage;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles
 */
public class SocketAcceptor implements Runnable{

    private SystemQueue systemQueue;
    private ServerSocket ss;
    
    private Thread acceptorThread;
    
    private boolean started = false;

    public SocketAcceptor(int port, ChatServerContext context) {
        this.systemQueue = context.getSystemQueue();
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(SocketAcceptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void run() {
        while(true){
            try {
                systemQueue.add(new SystemMessage().setCommand("LOGIN").setSocket(ss.accept()));
            } catch (IOException ex) {
                Logger.getLogger(SocketAcceptor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void start(){
        acceptorThread = new Thread(this);
        acceptorThread.start();
        started = true;
    }
    
    public void stop(){
        acceptorThread.stop();
        acceptorThread = null;
        started = false;
    }
    public void restart(){
        stop();
        start();
    }
}
