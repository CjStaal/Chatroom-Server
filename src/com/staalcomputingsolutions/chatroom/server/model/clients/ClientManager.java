/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model.clients;

import com.staalcomputingsolutions.chatroom.server.model.queues.messages.ChatMessage;
import com.staalcomputingsolutions.chatroom.server.model.queues.InputQueue;
import com.staalcomputingsolutions.chatroom.server.model.DefaultServer;
import com.staalcomputingsolutions.chatroom.server.model.queues.messages.Message;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles Joseph Staal
 */
public class ClientManager {

    /**
     * The HashMap uses the users private UUID to map it to the UserConnection.
     */
    private static ConcurrentHashMap<String, UserConnection> privateUUIDToConnection;

    /**
     * The HashMap uses the users private UUID to map it to the username.
     */
    private static ConcurrentHashMap<String, String> publicUUIDToUserName;

    /**
     * The HashMap uses the users private UUID to map it to the public UUID.
     */
    private static ConcurrentHashMap<String, String> privateToPublicUUID;
    /**
     * The HashMap uses the users public UUID to map it to the private UUID.
     */
    private static ConcurrentHashMap<String, String> publicToPrivateUUID;

    private static InputQueue inputQueue;
    private static Thread listeningThread;

    private static ClientManager instance;

    public static ClientManager getInstance() {
        if (instance == null) {
            instance = new ClientManager();
        }
        return instance;
    }

    private ClientManager() {
        privateUUIDToConnection = new ConcurrentHashMap();
        publicToPrivateUUID = new ConcurrentHashMap();
        privateToPublicUUID = new ConcurrentHashMap();
        publicUUIDToUserName = new ConcurrentHashMap();
        inputQueue = InputQueue.getInstance();
        listeningThread = new Thread(new Listener());
        this.startListen();
    }

    public void addClient(String privateUUID, String publicUUID, String userName, UserConnection uc) {
        privateUUIDToConnection.put(privateUUID, uc);
        publicUUIDToUserName.put(publicUUID, userName);
        privateToPublicUUID.put(privateUUID, publicUUID);
        publicToPrivateUUID.put(publicUUID, privateUUID);
    }

    public synchronized void removeClient(String privateUUID) {
        if (privateUUIDToConnection.containsKey(privateUUID)) {
            if (privateUUIDToConnection.get(privateUUID).getSocket() != null
                    & !privateUUIDToConnection.get(privateUUID).getSocket().isClosed()) {
                privateUUIDToConnection.get(privateUUID).close();
            }
            privateUUIDToConnection.remove(privateUUID);
            publicUUIDToUserName.remove(privateUUID);
            publicToPrivateUUID.remove(privateToPublicUUID.get(privateUUID));
            privateToPublicUUID.remove(privateUUID);
        }
    }

    public void pushUserList() {
        String userList = "USERLIST:";
        userList = publicUUIDToUserName.keySet().stream().map((publicUUID)
                -> "PUBLICUUID=" + publicUUID + ",USERNAME="
                + publicUUIDToUserName.get(publicUUID) + ",")
                .reduce(userList, String::concat);

        sendSystemMessage(userList.substring(0, userList.length() - 1));
    }

    public void sendChatMessage(ChatMessage cm) {
        String senderPublicUUID = privateToPublicUUID.get(cm.getSenderPrivateUUID());
        if (cm.isPublic()) {
            sendMessage("CHAT{PUBLIC:MSG=" + cm.getMessage() + ",FROM=" + senderPublicUUID + "}");
        } else {
            sendPrivateMessage(publicToPrivateUUID.get(cm.getRecipiantPublicUUID()), "CHAT{PRIVATE:MSG=" + cm.getMessage() + ",FROM=" + senderPublicUUID + "}");
        }
    }

    public void sendSystemMessage(String message) {
        sendMessage("SYSTEM{" + message + "}");
    }

    private synchronized void sendMessage(String message) {
        privateUUIDToConnection.values().stream().forEach((UserConnection uc) -> {
            try {
                uc.getDataOutputStream().writeUTF(message);
            } catch (IOException ex) {
                removeClient(uc.getPrivateUUID());
                Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void sendPrivateMessage(String privateUUIDToRecipiant, String message) {
        try {
            privateUUIDToConnection.get(privateUUIDToRecipiant).getDataOutputStream()
                    .writeUTF(message);
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
            removeClient(privateUUIDToRecipiant);
        }

    }

    private void startListen() {
        listeningThread.start();
    }

    public void stopListen() {
        listeningThread.stop();
    }

    private class Listener implements Runnable {

        @Override
        public synchronized void run() {
            DataInputStream dataInputStream;
            while (DefaultServer.isRunning()) {
                UserConnection uc;
                for (String uuid : privateUUIDToConnection.keySet()) {
                    uc = privateUUIDToConnection.get(uuid);
                    try {
                        dataInputStream = uc.getDataInputStream();
                        if (dataInputStream.available() > 0) {
                            inputQueue.add(new Message(uuid).setMessage(dataInputStream.readUTF()));
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                        removeClient(uc.getPrivateUUID());
                    }
                }
            }
        }
    }
}
