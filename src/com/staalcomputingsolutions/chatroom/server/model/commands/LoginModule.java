/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model.commands;

import com.staalcomputingsolutions.chatroom.server.model.clients.ClientManager;
import com.staalcomputingsolutions.chatroom.server.model.clients.UserConnection;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.SystemMessage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles Joseph Staal
 */
public class LoginModule {

    private static final ClientManager clientManager;

    static {
        clientManager = ClientManager.getInstance();
    }

    public static void login(SystemMessage sm) {
        try {
            String publicUUID = java.util.UUID.randomUUID().toString();
            String privateUUID = java.util.UUID.randomUUID().toString();
            UserConnection uc = new UserConnection(sm.getSocket(), privateUUID);
            String message = uc.getDataInputStream().readUTF();
            uc.getDataOutputStream().writeUTF("SYSTEM{UUID:PUBLIC=" + publicUUID + ",PRIVATE=" + privateUUID + "}");
            message = message.split(":")[1];
            String[] info = message.split(",");
            clientManager.addClient(privateUUID, publicUUID, info[0].split("=")[1], uc);
            uc.getDataOutputStream().writeUTF("CHAT{PUBLIC:MSG=connected to server.,FROM=Server}");
        } catch (IOException ex) {
            Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
