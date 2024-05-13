package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.enums.MessageType;

import java.util.Optional;

public class DrawCardMessage implements Message {

    private LocationType location;
    private Optional<Integer> idCard;


    public DrawCardMessage(LocationType location){
        this.location = location;
        idCard = Optional.empty();
    }

    public DrawCardMessage(LocationType location, Integer idCard){
        this.location = location;
        this.idCard = Optional.of(idCard);
    }


    @Override
    public MessageType getType() {
        return MessageType.DRAW_CARD;
    }

    public LocationType getLocation() {
        return location;
    }

    public Optional<Integer> getIdCard() {
        return idCard;
    }
}
