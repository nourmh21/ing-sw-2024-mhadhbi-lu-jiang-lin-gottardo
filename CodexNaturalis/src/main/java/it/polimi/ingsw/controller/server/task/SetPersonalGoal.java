package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

import java.io.ObjectOutputStream;
import java.util.Objects;

public class SetPersonalGoal implements Runnable{
    Game game;
    String nickname;
    Integer idCard;
    ObjectOutputStream oos;

    public SetPersonalGoal(Game game, String nickname, Integer idCard, ObjectOutputStream oos){
        this.game = game;
        this.nickname = nickname;
        this.idCard = idCard;
        this.oos = oos;
    }

    @Override
    public void run() {
        //basic check
        if (game.getGameState() == GameState.SETUP_PHASE_2){
            int i = 0;
            for (Player p: game.getPlayers()) {
                //find the right player
                if (p.getNickname().equals(nickname)){
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

    private boolean checkValidity(Player p){
        Integer[] goals = p.getInitialPossibleGoals();
        return (Objects.equals(goals[0], idCard) || Objects.equals(goals[1], idCard));
    }

}
