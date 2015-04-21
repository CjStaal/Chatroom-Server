/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.server.model;

import com.staalcomputingsolutions.chatroom.server.model.clients.ClientManager;
import com.staalcomputingsolutions.chatroom.server.model.listener.Listener;
import com.staalcomputingsolutions.chatroom.server.model.queues.SystemQueue;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.executors.ChatExecutor;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.executors.SystemExecutor;
import com.staalcomputingsolutions.chatroom.server.model.queues.sorters.InputQueueSorter;

/**
 *
 * @author Charles
 */
public interface ChatServerContext {
    
    public SystemQueue getSystemQueue();
    
    public ClientManager getClientManager();
    
    public SystemExecutor getSystemExecutor();
    
    public ChatExecutor getChatExecutor();
    
    public InputQueueSorter getInputQueueSorter();
    public void setListener(Listener listener);
    
    
}
