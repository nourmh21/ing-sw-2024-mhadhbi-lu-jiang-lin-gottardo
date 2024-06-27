package it.polimi.ingsw.message.general;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;

/**
 * The ChatMessage implements {@link Message} for communication between client and server
 * Used to notify a chat
 */
public class ChatMessage implements Message {
    boolean isPublic;
    String sender;
    String recipient;
    String content;

    /**
     * the constructor is used for private messages
     *
     * @param sender    defines the nickname of the user sending the message
     * @param recipient defines the nickname of the user receiving the message
     * @param content   defines the content of the message
     */
    public ChatMessage(String sender, String recipient, String content) {
        isPublic = false;
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    /**
     * the constructor is used for public messages
     *
     * @param sender  defines the nickname of the user sending the message
     * @param content defines the content of the message
     */
    public ChatMessage(String sender, String content) {
        isPublic = true;
        recipient = null;
        this.sender = sender;
        this.content = content;
    }

    /**
     * getter for MessageType
     *
     * @return MessageType.CHAT
     */
    @Override
    public MessageType getType() {
        return MessageType.CHAT;
    }

    /**
     * if isPublic is true, the message will be visible to all players
     * if isPublic is false, the message will be visible to the designated player
     *
     * @return
     */
    public boolean isPublic() {
        return isPublic;
    }

    /**
     * getter for sender
     *
     * @return nickname of the user sending the message
     */
    public String getSender() {
        return sender;
    }

    /**
     * getter for recipient
     *
     * @return nickname of the user receiving the message
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * getter for content
     *
     * @return content of the message
     */
    public String getContent() {
        return content;
    }

}
