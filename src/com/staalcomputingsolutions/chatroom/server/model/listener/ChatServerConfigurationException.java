/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.server.model.listener;


/**
 *
 * @author Charles
 */
public class ChatServerConfigurationException extends Exception {

    /**
     * {@link RuntimeException#RuntimeException()}
     */
    public ChatServerConfigurationException() {
        super();
    }

    /**
     * {@link RuntimeException#RuntimeException(String, Throwable)}
     * @param message
     * @param cause
     */
    public ChatServerConfigurationException(final String message,
            final Throwable cause) {
        super(message, cause);
    }

    /**
     * {@link RuntimeException#RuntimeException(String)}
     * @param message
     */
    public ChatServerConfigurationException(final String message) {
        super(message);
    }

    /**
     * {@link RuntimeException#RuntimeException(Throwable)}
     * @param cause
     */
    public ChatServerConfigurationException(final Throwable cause) {
        super(cause);
    }

}
