package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

public class HeartbeatMessage implements Message {

    public MessageType getType() {
        return MessageType.HEARTBEAT;
    }

}
