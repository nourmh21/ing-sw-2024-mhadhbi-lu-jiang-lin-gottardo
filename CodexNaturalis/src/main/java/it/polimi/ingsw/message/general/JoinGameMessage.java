package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

import java.util.Map;

public class JoinGameMessage implements Message {
    @Override
    public MessageType getType() {
        return MessageType.JOIN_GAME;
    }
}
