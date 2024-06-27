package it.polimi.ingsw.message.error;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.MessageType;

/**
 * The ErrorMessage implements {@link Message} [server -> client]
 * Used to notify a negative client action result
 */
public class ErrorMessage implements Message {
    private ErrorType error;

    /**
     * Constructor
     *
     * @param error {@link ErrorType}
     */
    public ErrorMessage(ErrorType error) {
        this.error = error;
    }

    public MessageType getType() {
        return MessageType.ERROR;
    }

    /**
     * getter for error
     *
     * @return error
     */
    public ErrorType getError() {
        return error;
    }

}
