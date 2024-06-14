package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

/**
 * The ReqLobbiesMessage implements {@link Message} [client -> server]
 * Used to notify a client action: the choice of join an existing lobby (then request available lobbies)
 */
public class ReqLobbiesMessage implements Message {
    @Override
    public MessageType getType() {
        return MessageType.REQ_LOBBIES;
    }
}
