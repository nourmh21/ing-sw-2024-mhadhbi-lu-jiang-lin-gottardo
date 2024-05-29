package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.ObjectiveCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

public class EndGame implements Runnable{
    private final Game game;

    public EndGame(Game game){
        this.game = game;
    }

    @Override
    public void run() {

        if (game.getGameState() == GameState.ENDING){

            for (Player p: game.getPlayers()) {
                p.addGoalPoint(p.getBoard().calculateGoalPoint((ObjectiveCard)GameController.getInstance().getCard(game.getCommonGoals().get(0))));
                p.addGoalPoint(p.getBoard().calculateGoalPoint((ObjectiveCard)GameController.getInstance().getCard(game.getCommonGoals().get(1))));
                p.addGoalPoint(p.getBoard().calculateGoalPoint((ObjectiveCard)GameController.getInstance().getCard(p.getPersonalGoal())));
            }

            game.checkMaxPoint();

            game.setGameState(GameState.FINISHED);

            for (Player p: game.getPlayers()) {
                GameController.getInstance().removeUserInGame(p.getNickname());
            }
        }

    }

}

