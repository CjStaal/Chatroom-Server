/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model.commands;

import com.staalcomputingsolutions.chatroom.server.model.clients.ClientManager;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.SystemMessage;

/**
 *
 * @author Charles Joseph Staal
 */
public class LogoutModule {

    private final static ClientManager clientManager;
    
    static{
        clientManager = ClientManager.getInstance();
    }
    public static void logout(SystemMessage sm) {
        clientManager.removeClient(sm.getUUID());
    }
    
}
