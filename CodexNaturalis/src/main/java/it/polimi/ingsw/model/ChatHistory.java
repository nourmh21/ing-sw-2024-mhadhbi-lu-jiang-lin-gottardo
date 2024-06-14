package it.polimi.ingsw.model;


import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.observer.ModelObservable;

import java.util.ArrayList;
import java.util.List;

public class ChatHistory extends ModelObservable {
    private List<ChatMessage> chatMessageList;

    public ChatHistory(){
        chatMessageList = new ArrayList<>();
    }

    public void addNewMessage(ChatMessage message){
        chatMessageList.add(message);
        if (!message.isPublic())
            notify_private_chat(message);
        else
            notify_public_chat(message);
    }

}
