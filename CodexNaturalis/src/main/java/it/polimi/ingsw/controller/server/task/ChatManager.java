package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.controller.server.GameController;

/**
 * The ChatManager is one of Runnable executes by {@link GameController}
 * It manages the chat messages
 */
public class ChatManager implements Runnable{
    Game game;
    ChatMessage message;

    /**
     * Constructor
     * @param game the {@link Game} in which the exchange of chat messages takes place
     * @param message the {@link ChatMessage}
     */
    public ChatManager(Game game, ChatMessage message){
        this.game = game;
        this.message = message;
    }

    @Override
    public void run() {
        game.addNewChat(message);
    }
}
