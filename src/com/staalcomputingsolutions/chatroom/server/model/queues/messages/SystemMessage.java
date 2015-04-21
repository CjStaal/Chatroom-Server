/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model.queues.messages;

import java.net.Socket;
import java.util.List;

/**
 *
 * @author Charles Joseph Staal
 */
public class SystemMessage {
    private String uuid, userName, command, hashedPass;
    private List<String> other;
    private Socket socket;
    private Object object;
    
    public SystemMessage(){
        
    }
    
    public SystemMessage setUUID(String uuid){
        this.uuid = uuid;
        return this;
    }
    
    public SystemMessage setUserName(String userName){
        this.userName = userName;
        return this;
    }
    
    public SystemMessage setCommand(String command){
        this.command = command;
        return this;
    }
    
    public SystemMessage setHashedPassword(String hashedPass){
        this.hashedPass = hashedPass;
        return this;
    }
    
    public SystemMessage setSocket(Socket socket){
        this.socket = socket;
        return this;
    }
    
    public SystemMessage setObject(Object object){
        this.object = object;
        return this;
    }
    
    public SystemMessage setOther(List<String> other){
        this.other = other;
        return this;
    }
    
    public String getUUID(){
        return this.uuid;
    }
    
    public String getUserName(){
        return this.userName;
    }
    
    public String getCommand(){
        return this.command;
    }
    
    public String getHashedPassword(){
        return this.hashedPass;
    }
    
    public Socket getSocket(){
        return this.socket;
    }
    
    public Object getObject(){
        return this.object;
    }
    
    public List<String> getOther(){
        return this.other;
    }
}
