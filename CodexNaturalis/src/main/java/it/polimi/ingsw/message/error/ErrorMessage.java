package it.polimi.ingsw.message.error;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.MessageType;

public class ErrorMessage implements Message {
    private ErrorType error;

    public ErrorMessage(ErrorType error){
        this.error = error;
    }

    public MessageType getType() {
        return MessageType.ERROR;
    }

    public ErrorType getError(){
        return error;
    }

}
