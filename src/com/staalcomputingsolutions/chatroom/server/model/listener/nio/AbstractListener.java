/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.server.model.listener.nio;

import com.staalcomputingsolutions.chatroom.server.model.listener.Listener;

/**
 *
 * @author Charles
 */
public abstract class AbstractListener implements Listener {

    private final String serverAddress;

    private int port = 1000;

    public AbstractListener(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    @Override
    public String getServerAddress() {
        return serverAddress;
    }
    
    @Override
    public int getPort(){
        return this.port;
    }
    
    protected void setPort(int port){
        this.port = port;
    }
}
