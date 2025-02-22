package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;


/**
 * The JoinLobbyMessage implements {@link Message} [client -> server]
 * Used to notify a client action: join a lobby
 */
public class JoinLobbyMessage implements Message {
    Integer idLobby;

    public JoinLobbyMessage(Integer idLobby) {
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
