package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

public class LoginSuccessMessage implements Message {
    @Override
    public MessageType getType() {
        return MessageType.LOGIN_SUCCESS;
    }
}
