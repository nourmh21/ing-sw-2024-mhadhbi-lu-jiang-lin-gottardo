package it.polimi.ingsw.model;


import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.observer.ModelObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * The ChatHistory class represents the history of the messages sent through the chat
 */
public class ChatHistory extends ModelObservable {
    private List<ChatMessage> chatMessageList;

    /**
     * the constructor creates an empty ArrayList
     */
    public ChatHistory() {
        chatMessageList = new ArrayList<>();
    }

    /**
     * adds a message to the chatMessageList as a {@link ChatMessage} object.
     * Additionally, the designated chat (private or public) is notified depending on the isPublic attribute of the message
     *
     * @param message specifies the messages that needs to be added
     */
    public void addNewMessage(ChatMessage message) {
        chatMessageList.add(message);
        if (!message.isPublic())
            notify_private_chat(message);
        else
            notify_public_chat(message);
    }

}
