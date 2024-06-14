package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.ObjectiveCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

/**
 * The EndGame is one of Runnable executed for the progression of a game
 * It is called to execute by {@link TurnManager} in specific conditions
 * It manages the calculation of goal points and the termination of the game
 */
public class EndGame implements Runnable{
    private final Game game;

    /**
     * Constructor
     * @param game the {@link Game} to be ended
     */
    public EndGame(Game game){
        this.game = game;
    }


    @Override
    public void run() {

        if (game.getGameState() == GameState.ENDING){
            //Calculate goal points for each player
            for (Player p: game.getPlayers()) {
                p.addGoalPoint(p.getBoard().calculateGoalPoint((ObjectiveCard)GameController.getInstance().getCard(game.getCommonGoals().get(0))));
                p.addGoalPoint(p.getBoard().calculateGoalPoint((ObjectiveCard)GameController.getInstance().getCard(game.getCommonGoals().get(1))));
                p.addGoalPoint(p.getBoard().calculateGoalPoint((ObjectiveCard)GameController.getInstance().getCard(p.getPersonalGoal())));
            }

            //Find winner/winners of this game
            game.checkMaxPoint();

            game.setGameState(GameState.FINISHED);

            //Remove from list that contains users whose currently playing a match/game
            for (Player p: game.getPlayers()) {
                GameController.getInstance().removeUserInGame(p.getNickname());
            }
        }

    }

}

