package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

public class ReqNewGameInfoMessage implements Message {

    @Override
    public MessageType getType() {
        return MessageType.REQ_NEW_GAME_INFO;
    }

}
