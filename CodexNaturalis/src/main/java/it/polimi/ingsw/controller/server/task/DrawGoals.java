package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.model.Desk;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.exceptions.EmptyDeckException;

public class DrawGoals implements Runnable{

    private Game game;
    private String nickname;


    public DrawGoals(Game game){
        this.game = game;
    }


    @Override
    public void run() {

        Desk desk = game.getDesk();

        try {
            //draw common game's common goals
            game.setCommonGoals(desk.pickOneCard(CardType.OBJECTIVE));
            game.setCommonGoals(desk.pickOneCard(CardType.OBJECTIVE));

            //draw two personal goals for each player
            for (Player p: game.getPlayers()){
                Integer[] personalGoals = {game.getDesk().pickOneCard(CardType.OBJECTIVE),
                        game.getDesk().pickOneCard(CardType.OBJECTIVE)};
                p.setInitialPossibleGoals(personalGoals);
            }
        } catch (EmptyDeckException e) {
            //Ignored, because it won't happen at this point of game
        }
    }
}
