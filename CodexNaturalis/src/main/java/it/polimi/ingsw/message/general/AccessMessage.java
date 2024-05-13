package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

public class AccessMessage implements Message {

    private final String nickname;
    private final String pwd;
    private final boolean isRegistered;

    public AccessMessage(String nickname, String pwd, boolean isRegistered){
        this.nickname = nickname;
        this.pwd = pwd;
        this.isRegistered = isRegistered;
    }

    public MessageType getType() {
        return MessageType.ACCESS;
    }


    public String getNickname() {
        return nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public boolean isRegistered() {
        return isRegistered;
    }
}
