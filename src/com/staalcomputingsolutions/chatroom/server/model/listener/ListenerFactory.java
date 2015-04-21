/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.server.model.listener;

import com.staalcomputingsolutions.chatroom.server.model.exceptions.ChatServerConfigurationException;
import com.staalcomputingsolutions.chatroom.server.model.listener.nio.DefaultListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Charles Joseph Staal
 */
public class ListenerFactory {
    
    private static String serverAddress;
    
    private static int port = 1000;
    
    
    public ListenerFactory(){
        
    }
    
    public ListenerFactory(Listener listener){
        serverAddress = listener.getServerAddress();
        port = listener.getPort();
    }
    
    public static Listener createListener() throws ChatServerConfigurationException {
        try{
            InetAddress.getByName(serverAddress);
        } catch (UnknownHostException ex){
            throw new ChatServerConfigurationException("Unknown host.", ex);
        }
        return new DefaultListener(serverAddress, port);
    }
    
    public void setPort(int port){
        this.port = port;
    }
    
    public int getPort(){
        return this.port;
    }
    
    public void setServerAddress(String serverAddress){
        this.serverAddress = serverAddress;
    }
}
