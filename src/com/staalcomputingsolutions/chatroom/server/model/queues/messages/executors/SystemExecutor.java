/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model.queues.messages.executors;

import com.staalcomputingsolutions.chatroom.server.model.commands.LoginModule;
import com.staalcomputingsolutions.chatroom.server.model.commands.LogoutModule;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.SystemMessage;
import com.staalcomputingsolutions.chatroom.server.model.queues.SystemQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles Joseph Staal
 */
public class SystemExecutor implements Runnable{
    
    private final SystemQueue systemQueue;
    
    private static SystemExecutor instance = null;
    
    private SystemExecutor(){
        systemQueue = SystemQueue.getInstance();
    }
    
    public static synchronized SystemExecutor getInstance(){
        if(instance == null){
            instance = new SystemExecutor();
        }
        return instance;
    }

    @Override
    public void run() {
        SystemMessage sm;
        while(true){
            if(!systemQueue.isEmpty())
                try {
                    sm = systemQueue.take();
                    switch(sm.getCommand()){
                        case "LOGIN":
                            LoginModule.login(sm);
                            break;
                        case "LOGOUT":
                            LogoutModule.logout(sm);
                            break;
                    }
            } catch (InterruptedException ex) {
                Logger.getLogger(SystemExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
