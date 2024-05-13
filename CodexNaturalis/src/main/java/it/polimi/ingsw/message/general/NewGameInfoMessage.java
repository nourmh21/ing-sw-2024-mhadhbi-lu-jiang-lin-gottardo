package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

import java.util.Map;

public class NewGameInfoMessage implements Message {

    int numOfPlayer;
    public NewGameInfoMessage(int numOfPlayer){
        this.numOfPlayer = numOfPlayer;
    }


    @Override
    public MessageType getType() {
        return MessageType.NEW_GAME_INFO;
    }

    public int getNumOfPlayer() {
        return numOfPlayer;
    }

}
