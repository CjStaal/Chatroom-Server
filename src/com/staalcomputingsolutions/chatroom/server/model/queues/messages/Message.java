/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.server.model.queues.messages;

/**
 *
 * @author Charles
 */
public class Message {
 
    private final String uuid;
    private String message;
    
    public Message(String uuid) {
        this.uuid = uuid;
    }
    
    public Message setMessage(String message){
        this.message = message;
        return this;
    }
    
    public String getUUID(){
        return this.uuid;
    }
    
    public String getMessage(){
        return this.message;
    }
    
}
