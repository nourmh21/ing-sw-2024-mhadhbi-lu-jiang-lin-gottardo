package it.polimi.ingsw.controller.task;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.message.ErrorMessage;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

import static it.polimi.ingsw.message.enums.ErrorType.INVALID_PERSONAL_GOAL;
import static it.polimi.ingsw.message.enums.ErrorType.INVALID_POSITION;

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
                    //if that player don't already have a personal goal
                    if (p.getPersonalGoal() == null){
                        if (checkValidity(p))
                            p.setPersonalGoal(idCard);
                        else{
                            //
                            Message message = new ErrorMessage(INVALID_PERSONAL_GOAL);
                            try {
                                oos.writeObject(message);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    //otherwise this illegal new set will be ignored
                }
                //count player who already has a personal goal
            }

            for (Player p: game.getPlayers()) {
                if (p.getPersonalGoal() != null){
                    i++;
                }
            }

            //if all players has already set their goal;
            if (i == game.getNumOfPlayer()-1)
                GameController.getInstance().executeTask(new Start(game));
        }
    }

    private boolean checkValidity(Player p){
        Integer[] goals = p.getInitialPossibleGoals();
        return (Objects.equals(goals[0], idCard) || Objects.equals(goals[1], idCard));
    }

}
