/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.server.model.main;

import com.staalcomputingsolutions.chatroom.server.model.DefaultChatServer;
import com.staalcomputingsolutions.chatroom.server.model.DefaultChatServerContext;
import com.staalcomputingsolutions.chatroom.server.model.Server;
import com.staalcomputingsolutions.chatroom.server.model.exceptions.ChatServerException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles
 */
public class CommandLine {
    
    public static void main(String[] args){
        Server server = new DefaultChatServer(new DefaultChatServerContext());
        
        try {
            server.start();
        } catch (ChatServerException ex) {
            Logger.getLogger(CommandLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
