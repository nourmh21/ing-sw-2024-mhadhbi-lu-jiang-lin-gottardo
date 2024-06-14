package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

/**
 * The ChatMessage implements {@link Message} [client <-> server]
 * Used to notify a chat
 */
public class ChatMessage implements Message {
    boolean isPublic;
    String sender;
    String recipient;
    String content;

    public ChatMessage(String sender, String recipient, String content){
        isPublic = false;
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    public ChatMessage(String sender, String content){
        isPublic = true;
        recipient = null;
        this.sender = sender;
        this.content = content;
    }

    @Override
    public MessageType getType() {
        return MessageType.CHAT;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

}
