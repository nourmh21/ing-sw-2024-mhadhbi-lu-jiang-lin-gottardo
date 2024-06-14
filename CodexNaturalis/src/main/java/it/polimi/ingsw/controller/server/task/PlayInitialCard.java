package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.enums.GameState;

/**
 * The PlayInitialCard is one of Runnable executed by {@link GameController}
 * It manages the play of initial card
 */
public class PlayInitialCard implements Runnable{

    Game game;
    Client client;
    InitialCard initialCard;
    boolean isBackside;

    /**
     * Constructor
     * @param client the {@link Client} who made the action
     * @param game the {@link Game} in which client participates
     * @param card initial card
     * @param isBackSide side of card
     */
    public PlayInitialCard(Client client, Game game, Card card, boolean isBackSide){
        this.client = client;
        this.game = game;
        initialCard = (InitialCard) card;
        this.isBackside = isBackSide;
    }


    @Override
    public void run() {
        //basic check
        if (game.getGameState() == GameState.SETUP_PHASE_1){
            int i = 0;

            for (Player p: game.getPlayers()) {
                if (p.getNickname().equals(client.getNickname())){
                    //specific check
                    if (!p.getBoard().getIsInitCardPlaced() &&
                            p.getInitialCard().equals(initialCard.getIdCard()))
                        p.updatePoint(p.getBoard().placeInitCard(initialCard, isBackside));
                    //otherwise this action will be considered illegal and will be ignored
                    break;
                }
            }

            //count player who has already played his initial card
            for (Player p: game.getPlayers()) {
                if (p.getBoard().getIsInitCardPlaced())
                    i++;
            }

            //if all players have already played their initial cards
            if (i == game.getNumOfPlayer()){
                //draw three hand cards for each player in the game
                Desk desk = game.getDesk();
                for (Player p: game.getPlayers()) {
                    p.addCardToHandCards(desk.pickNextRCard());
                    p.addCardToHandCards(desk.pickNextRCard());
                    p.addCardToHandCards(desk.pickNextGCard());
                }
                //switch game state to next state
                game.setGameState(GameState.SETUP_PHASE_2);
                GameController.getInstance().executeTask(new DrawGoals(game));
            }

        }
    }


}
