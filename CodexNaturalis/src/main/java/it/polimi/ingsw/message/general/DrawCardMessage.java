package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.enums.MessageType;

/**
 * The DrawCardMessage implements {@link Message} [client -> server]
 * Used to notify a client action: try to draw a card
 */
public class DrawCardMessage implements Message {

    private LocationType location;
    private Integer idCard;


    public DrawCardMessage(LocationType location){
        this.location = location;
        idCard = null;
    }

    public DrawCardMessage(LocationType location, Integer idCard){
        this.location = location;
        this.idCard = idCard;
    }


    @Override
    public MessageType getType() {
        return MessageType.DRAW_CARD;
    }

    public LocationType getLocation() {
        return location;
    }

    public Integer getIdCard() {
        return idCard;
    }
}
