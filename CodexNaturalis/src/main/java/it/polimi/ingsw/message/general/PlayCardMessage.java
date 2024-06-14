package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

/**
 * The PlayCardMessage implements {@link Message} [client -> server]
 * Used to notify a client action: try to play a card
 */
public class PlayCardMessage implements Message {

    private final Integer idCard;
    private final boolean isBackSide;
    private final int[] position;

    public PlayCardMessage(Integer idCard, boolean isBackSide, int[] position){
        this.idCard = idCard;
        this.isBackSide = isBackSide;
        this.position = position;
    }

    @Override
    public MessageType getType() {
        return MessageType.PLAY_CARD;
    }

    public Integer getIdCard() {
        return idCard;
    }

    public boolean isBackSide() {
        return isBackSide;
    }

    public int[] getPosition() {
        return position;
    }
}
