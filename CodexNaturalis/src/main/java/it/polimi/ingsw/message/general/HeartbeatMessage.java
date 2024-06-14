package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

/**
 * The HeartbeatMessage implements {@link Message} [client -> server]
 * Used to maintain connection alive
 */
public class HeartbeatMessage implements Message {

    public MessageType getType() {
        return MessageType.HEARTBEAT;
    }

}
