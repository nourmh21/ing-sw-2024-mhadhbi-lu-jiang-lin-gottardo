package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.model.Desk;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.exceptions.EmptyDeckException;


/**
 * The DrawCard is one of Runnable executed for the progression of a game
 * It is called to execute by {@link PlayInitialCard} in specific condition
 * It manages the draw of all public or private objective(goal) cards
 */
public class DrawGoals implements Runnable{

    private Game game;


    /**
     * Constructor
     * @param game the {@link Game} that wants to do draw cards
     */
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
        } catch (EmptyDeckException ignored) {
            //Ignored, because it won't happen at this point of game
        }
    }
}
