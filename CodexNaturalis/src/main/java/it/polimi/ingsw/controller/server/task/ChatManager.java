package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.model.Game;

public class ChatManager implements Runnable{
    Game game;
    ChatMessage message;
    public ChatManager(Game game, ChatMessage message){
        this.game = game;
        this.message = message;
    }

    @Override
    public void run() {
        game.addNewChat(message);
    }
}
