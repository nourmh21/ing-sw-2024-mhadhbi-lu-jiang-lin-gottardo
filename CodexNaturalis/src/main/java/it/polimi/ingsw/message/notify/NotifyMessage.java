package it.polimi.ingsw.message.notify;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;
import it.polimi.ingsw.message.enums.NotifyType;

import java.util.List;
import java.util.Optional;

public class NotifyMessage implements Message{
    private final NotifyType notifyType;

    private final Object object;

    public NotifyMessage(NotifyType notifyType, Object object){
        this.notifyType = notifyType;
        this.object = object;
    }

    public NotifyMessage(NotifyType notifyType){
        this.notifyType = notifyType;
        object = null;
    }

    @Override
    public MessageType getType() {
        return MessageType.NOTIFY;
    }

    public NotifyType getNotifyType() {
        return notifyType;
    }

    public Object getObject() {
        return object;
    }

}
