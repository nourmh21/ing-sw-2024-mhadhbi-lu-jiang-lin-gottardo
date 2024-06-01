package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

public class CreateLobbyMessage implements Message {

    int numOfPlayer;
    public CreateLobbyMessage(int numOfPlayer){
        this.numOfPlayer = numOfPlayer;
    }


    @Override
    public MessageType getType() {
        return MessageType.CREATE_LOBBY;
    }

    public int getNumOfPlayer() {
        return numOfPlayer;
    }

}
