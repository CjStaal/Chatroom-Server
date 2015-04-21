/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.model.queues.messages;

/**
 *
 * @author Charles Joseph Staal
 */
public class ChatMessage {

    private final String senderPrivateUUID;

    private String message;
    private String recipiantPublicUUID;
    
    private boolean type; //Is it public {true} or private {false}.

    public ChatMessage(String senderPrivateUUID) {
        this.senderPrivateUUID = senderPrivateUUID;
    }

    public ChatMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public ChatMessage setRecipiantPublicUUID(String recipiantPublicUUID) {
        this.recipiantPublicUUID = recipiantPublicUUID;
        return this;
    }
    
    public ChatMessage setPublic(boolean type){
        this.type = type;
        return this;
    }
    
    public String getSenderPrivateUUID() {
        return this.senderPrivateUUID;
    }

    public String getMessage() {
        return this.message;
    }

    public String getRecipiantPublicUUID() {
        return this.recipiantPublicUUID;
    }
    
    public boolean isPublic(){
        return type;
    }

}
