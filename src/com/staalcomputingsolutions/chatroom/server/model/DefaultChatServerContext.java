/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.server.model;

import com.staalcomputingsolutions.chatroom.server.model.clients.ClientManager;
import com.staalcomputingsolutions.chatroom.server.model.queues.SystemQueue;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.executors.ChatExecutor;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.executors.SystemExecutor;
import com.staalcomputingsolutions.chatroom.server.model.queues.sorters.InputQueueSorter;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author Charles
 */
public class DefaultChatServerContext implements ServerContext {

    private AtomicBoolean isRunning = new AtomicBoolean();
    private SystemQueue sysQ;
    private ClientManager cm;
    private SystemExecutor se;
    private ChatExecutor ce;
    private InputQueueSorter iqs;
    private Thread seThread, ceThread, iqsThread;

    public DefaultChatServerContext() {
        sysQ = SystemQueue.getInstance();
        cm = ClientManager.getInstance();
        se = SystemExecutor.getInstance();
        iqs = InputQueueSorter.getInstance();
        ce = ChatExecutor.getInstance();
    }
    
    public void start(){
        isRunning.set(true);
        seThread = new Thread(se);
        ceThread = new Thread(ce);
        iqsThread = new Thread(iqs);
        seThread.start();
        ceThread.start();
        iqsThread.start();
        
    }
    
    public AtomicBoolean isRunning(){
        return this.isRunning;
    }
    

}
