package it.polimi.ingsw.message.notify;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;
import it.polimi.ingsw.message.enums.NotifyType;

public class NotifyMessage implements Message {
    private final NotifyType notifyType;

    private final Object object;

    public NotifyMessage(NotifyType notifyType, Object object){
        this.notifyType = notifyType;
        this.object = object;
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
