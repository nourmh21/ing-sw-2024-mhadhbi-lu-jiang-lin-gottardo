package it.polimi.ingsw.message;

import it.polimi.ingsw.message.enums.MessageType;

import java.io.Serializable;

public interface Message extends Serializable {
    public MessageType getType();
}
