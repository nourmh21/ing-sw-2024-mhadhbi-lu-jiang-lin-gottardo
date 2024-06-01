package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

public class ReqLobbiesMessage implements Message {
    @Override
    public MessageType getType() {
        return MessageType.REQ_LOBBIES;
    }
}
