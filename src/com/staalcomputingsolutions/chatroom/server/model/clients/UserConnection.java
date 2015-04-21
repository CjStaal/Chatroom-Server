/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model.clients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles Joseph Staal
 */
public class UserConnection {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private String privateUUID;
    public UserConnection(Socket socket, String privateUUID) {
        try {
            this.privateUUID = privateUUID;
            this.socket = socket;
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
            this.dataInputStream = new DataInputStream(this.inputStream);
            this.dataOutputStream = new DataOutputStream(this.outputStream);
        } catch (IOException ex) {
            Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DataOutputStream getDataOutputStream() {
        return this.dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return this.dataInputStream;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public String getPrivateUUID(){
        return this.privateUUID;
    }
    
    public void close() {
        try {
            this.dataInputStream.close();
            this.dataOutputStream.close();
            this.inputStream.close();
            this.outputStream.close();
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
