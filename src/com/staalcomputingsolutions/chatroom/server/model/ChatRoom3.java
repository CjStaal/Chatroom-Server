/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model;

import com.staalcomputingsolutions.chatroom.server.model.clients.ClientManager;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.executors.SystemExecutor;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.SystemMessage;
import com.staalcomputingsolutions.chatroom.server.model.queues.SystemQueue;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.executors.ChatExecutor;
import com.staalcomputingsolutions.chatroom.server.model.queues.sorters.InputQueueSorter;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * Copyright [2015] [Charles Joseph Staal]
 */

/**
 *
 * @author Charles Joseph Staal
 */
public class ChatRoom3 {

    /**
     * @param args the command line arguments
     */
    private static AtomicBoolean isRunning = new AtomicBoolean();
    private static SystemQueue sysQ;
    private static ClientManager cm;
    private static SystemExecutor se;
    private static ChatExecutor ce;
    private static InputQueueSorter iqs;
    private static Thread seThread, ceThread, iqsThread;
    
    public static void main(String[] args) throws IOException {
        isRunning.set(true);
        sysQ = SystemQueue.getInstance();
        cm = ClientManager.getInstance();
        se = SystemExecutor.getInstance();
        iqs = InputQueueSorter.getInstance();
        ce = ChatExecutor.getInstance();
        seThread = new Thread(se);
        ceThread = new Thread(ce);
        iqsThread = new Thread(iqs);
        seThread.start();
        ceThread.start();
        iqsThread.start();
        ServerSocket ss = new ServerSocket(1000);
        
        while(isRunning.get()){
            sysQ.add(new SystemMessage().setCommand("LOGIN").setSocket(ss.accept()));
        }
    }

    public static synchronized boolean isRunning() {
        return isRunning.get();
    }
    
}
