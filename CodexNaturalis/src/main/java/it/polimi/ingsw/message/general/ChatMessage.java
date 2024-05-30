package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

public class ChatMessage implements Message {
    private final String sender;
    private final String recipient;
    private final String content;

    public ChatMessage(String sender, String recipient, String content){
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    @Override
    public MessageType getType() {
        return MessageType.CHAT;
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
