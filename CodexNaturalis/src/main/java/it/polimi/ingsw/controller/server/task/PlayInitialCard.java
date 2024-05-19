package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.enums.GameState;

public class PlayInitialCard implements Runnable{

    Game game;
    String nickname;

    InitialCard initialCard;
    boolean isBackside;

    public PlayInitialCard(Game game, String nickname, Card card, boolean isBackSide){
        this.nickname = nickname;
        this.game = game;
        initialCard = (InitialCard) card;
        this.isBackside = isBackSide;
    }

    @Override
    public void run() {
        if (game.getGameState() == GameState.SETUP_PHASE_1){
            int i = 0;
            for (Player p: game.getPlayers()) {
                if (p.getNickname().equals(nickname)){
                    if (!p.getBoard().getIsInitCardPlaced() &&
                            p.getInitialCard().equals(initialCard.getIdCard()))
                        p.getBoard().placeInitCard(initialCard, isBackside);
                    break;
                }
            }

            for (Player p: game.getPlayers()) {
                if (p.getBoard().getIsInitCardPlaced())
                    i++;
            }

            if (i == game.getNumOfPlayer()){
                //draw players hand card
                Desk desk = game.getDesk();
                for (Player p: game.getPlayers()) {
                    p.addCardToHandCards(desk.pickNextRCard());
                    p.addCardToHandCards(desk.pickNextRCard());
                    p.addCardToHandCards(desk.pickNextGCard());
                }
                //change to next state
                game.setGameState(GameState.SETUP_PHASE_2);
                GameController.getInstance().executeTask(new DrawGoals(game));
            }

        }
    }


}
