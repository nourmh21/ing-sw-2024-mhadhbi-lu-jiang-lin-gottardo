package it.polimi.ingsw.message.notify;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;
import it.polimi.ingsw.message.enums.NotifyType;

/**
 * The NotifyMessage implements {@link Message} [server -> client]
 * Used to notify a positive client action result and any model changes
 */
public class NotifyMessage implements Message {
    private final NotifyType notifyType;

    private final Object object;

    /**
     * Constructor with a new object status
     *
     * @param notifyType {@link NotifyType}
     * @param object     in general a class in Immutable package, or otherwise a list or an array
     */
    public NotifyMessage(NotifyType notifyType, Object object) {
        this.notifyType = notifyType;
        this.object = object;
    }

    /**
     * Constructor for fast notify
     *
     * @param notifyType {@link NotifyType}
     */
    public NotifyMessage(NotifyType notifyType) {
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
