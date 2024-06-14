package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

/**
 * The PlayInitCardMessage implements {@link Message} [client -> server]
 * Used to notify a client action: play of initial card
 */
public class PlayInitCardMessage implements Message {
    private Integer idCard;
    private boolean isBackSide;

    public PlayInitCardMessage(Integer idCard, boolean isBackSide){
        this.idCard = idCard;
        this.isBackSide = isBackSide;
    }

    @Override
    public MessageType getType() {
        return MessageType.PLAY_INITIAL_CARD;
    }

    public Integer getIdCard() {
        return idCard;
    }

    public boolean isBackSide() {
        return isBackSide;
    }
}
