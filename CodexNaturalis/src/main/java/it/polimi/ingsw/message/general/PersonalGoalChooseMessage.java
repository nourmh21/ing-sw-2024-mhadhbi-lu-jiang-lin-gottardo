package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

import java.util.Map;

public class PersonalGoalChooseMessage implements Message {

    Integer idCard;

    public PersonalGoalChooseMessage(Integer idCard){
        this.idCard = idCard;
    }

    @Override
    public MessageType getType() {
        return MessageType.PERSONAL_GOAL_CHOOSE;
    }

    public Integer getIdCard() {
        return idCard;
    }
}
