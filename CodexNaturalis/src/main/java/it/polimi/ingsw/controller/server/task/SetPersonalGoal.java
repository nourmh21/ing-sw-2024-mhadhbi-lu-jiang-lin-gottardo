package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

import java.util.Objects;

/**
 * The SetPersonalGoal is one of Runnable executed by {@link GameController}
 * It manages the choice of the personal objective(goal) card
 */
public class SetPersonalGoal implements Runnable{
    Client client;
    Game game;
    Integer idCard;

    /**
     * Constructor
     * @param client the {@link Client} who made the action
     * @param game the {@link Game} in which client participates
     * @param idCard the id of an objective(goal) card
     */
    public SetPersonalGoal(Client client, Game game, Integer idCard){
        this.client = client;
        this.game = game;
        this.idCard = idCard;
    }

    @Override
    public void run() {
        //basic check
        if (game.getGameState() == GameState.SETUP_PHASE_2){
            int i = 0;
            for (Player p: game.getPlayers()) {
                //find the right player
                if (p.getNickname().equals(client.getNickname())){
                    //if that player has not selected a personal goal yet
                    if (p.getPersonalGoal() == null){
                        if (checkValidity(p))
                            p.setPersonalGoal(idCard);
                    }
                    //otherwise this action will be considered illegal and will be ignored
                    break;
                }
            }
            //count player who has already selected a personal goal
            for (Player p: game.getPlayers()) {
                if (p.getPersonalGoal() != null){
                    i++;
                }
            }
            //if all players have already set their goal;
            if (i == game.getNumOfPlayer())
                GameController.getInstance().executeTask(new Start(game));
        }
    }


    /**
     * @param p the {@link Player}
     * @return true is the id of card is correct, false otherwise
     */
    private boolean checkValidity(Player p){
        Integer[] goals = p.getInitialPossibleGoals();
        return (Objects.equals(goals[0], idCard) || Objects.equals(goals[1], idCard));
    }

}
