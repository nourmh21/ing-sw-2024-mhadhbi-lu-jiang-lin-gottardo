package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

public class JoinLobbyeMessage implements Message {
    Integer idLobby;

    public JoinLobbyeMessage(Integer idLobby){
        this.idLobby = idLobby;
    }

    @Override
    public MessageType getType() {
        return MessageType.JOIN_LOBBY;
    }

    public Integer getIdLobby() {
        return idLobby;
    }
}
