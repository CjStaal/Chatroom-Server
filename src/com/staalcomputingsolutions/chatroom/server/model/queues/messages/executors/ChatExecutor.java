/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.server.model.queues.messages.executors;

import com.staalcomputingsolutions.chatroom.server.model.clients.ClientManager;
import com.staalcomputingsolutions.chatroom.server.model.queues.OutputQueue;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.ChatMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles
 */
public class ChatExecutor implements Runnable{

    private final static OutputQueue outputQueue;
    private final static ClientManager clientManager;
    private static ChatExecutor instance = null;
    
    static{
        outputQueue = OutputQueue.getInstance();
        clientManager = ClientManager.getInstance();
    }
    
    private ChatExecutor(){
        
    }

    public static ChatExecutor getInstance() {
        if(instance == null){
            instance = new ChatExecutor();
        }
        return instance;
    }
    
    @Override
    public void run() {
        while(true){
            if(!outputQueue.isEmpty()){
                try {
                    ChatMessage cm = outputQueue.take();
                    if(cm.isPublic()){
                        clientManager.sendChatMessage(cm);
                    } else {
                        clientManager.sendPrivateMessage(cm.getRecipiantPublicUUID(), cm.getMessage());
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ChatExecutor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
