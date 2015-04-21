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
public class DefaultChatServerContext implements ChatServerContext {

    private SystemQueue sysQ;
    private ClientManager cm;
    private SystemExecutor se;
    private ChatExecutor ce;
    private InputQueueSorter iqs;
    private Listener listener;

    public DefaultChatServerContext() {
        this.sysQ = SystemQueue.getInstance();
        this.cm = ClientManager.getInstance();
        this.se = SystemExecutor.getInstance();
        this.iqs = InputQueueSorter.getInstance();
        this.ce = ChatExecutor.getInstance();
    }

    @Override
    public SystemQueue getSystemQueue() {
        return this.sysQ;
    }
    
    @Override
    public ClientManager getClientManager(){
        return this.cm;
    }
    
    @Override
    public SystemExecutor getSystemExecutor(){
        return this.se;
    }
    
    @Override
    public ChatExecutor getChatExecutor(){
        return this.ce;
    }
    
    @Override
    public InputQueueSorter getInputQueueSorter(){
        return this.iqs;
    }
    
    @Override
    public void setListener(Listener listener){
        this.listener = listener;
    }

}
