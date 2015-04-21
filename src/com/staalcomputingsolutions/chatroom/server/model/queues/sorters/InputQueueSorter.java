/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model.queues.sorters;

import com.staalcomputingsolutions.chatroom.server.model.queues.OutputQueue;
import com.staalcomputingsolutions.chatroom.server.model.queues.InputQueue;
import com.staalcomputingsolutions.chatroom.server.model.queues.SystemQueue;
import com.staalcomputingsolutions.chatroom.server.model.DefaultServer;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.Message;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.parsers.MessageParser;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles Joseph Staal
 */
public class InputQueueSorter implements Runnable{

    private static InputQueue inputQueue;
    private static OutputQueue outputQueue;
    private static SystemQueue systemQueue;
    private static InputQueueSorter instance = null;
    
    private InputQueueSorter(){
        inputQueue = InputQueue.getInstance();
        outputQueue = OutputQueue.getInstance();
        systemQueue = SystemQueue.getInstance();
    }
    public static synchronized InputQueueSorter getInstance(){
        if(instance == null){
            instance = new InputQueueSorter();
        }
        return instance;
    }
    @Override
    public void run() {
        while(DefaultServer.isRunning()){
            if(!inputQueue.isEmpty()){
                try {
                    Message m = inputQueue.take();
                    String message = m.getMessage();
                    if(message.contains("CHAT")){
                        outputQueue.add(MessageParser.buildChatMessage(m));
                    }
                    else if(message.contains("SYSTEM")){
                        systemQueue.add(MessageParser.buildSystemMessage(m));
                        
                    }
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(InputQueueSorter.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }
    
}
