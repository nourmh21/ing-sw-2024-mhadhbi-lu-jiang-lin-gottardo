package it.polimi.ingsw.message;

import it.polimi.ingsw.message.enums.MessageType;

import java.io.Serializable;

/**
 *  Message is used as a base interface for all types of messages exchanged between server and client
 *  It implements Serializable.
 */
public interface Message extends Serializable {
    public MessageType getType();
}
