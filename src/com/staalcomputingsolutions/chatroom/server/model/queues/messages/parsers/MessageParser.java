/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model.queues.messages.parsers;

import com.staalcomputingsolutions.chatroom.server.model.queues.messages.ChatMessage;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.Message;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.SystemMessage;

/**
 *
 * @author Charles Joseph Staal
 */
public class MessageParser { 

    public static ChatMessage buildChatMessage(Message m) {
        String privateSenderUUID = m.getUUID();
        String message = m.getMessage();
        message = message.substring(message.indexOf("{")+1, message.indexOf("}"));
        String type = message.split(":")[0];
        message = message.split(":")[1];
        
        String parse[] = message.split(",");
        
        ChatMessage cm = new ChatMessage(privateSenderUUID).setMessage(parse[0].split("=")[1]).setPublic(true);
        
        if(type.equals("PRIVATE")){
            
            cm.setRecipiantPublicUUID(parse[1].split("=")[1]).setPublic(false);
        }
        
        return cm;
    }

    public static SystemMessage buildSystemMessage(Message m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
