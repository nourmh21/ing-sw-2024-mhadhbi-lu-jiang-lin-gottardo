package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.model.Desk;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

import java.io.ObjectOutputStream;

public class DrawCard  implements Runnable {

    private Game game;
    private String nickname;
    private LocationType locationType;
    private int idCard;
    private ObjectOutputStream oos;

    
    public DrawCard( Game game,String nickname, LocationType locationType, ObjectOutputStream oos){
        this.game = game;
        this.locationType = locationType;
        this.nickname = nickname;
        this.oos = oos;
    }
    public DrawCard(Game game, String nickname, LocationType locationType, Integer idCard, ObjectOutputStream oos){
        this.game = game;
        this.locationType = locationType;
        this.nickname = nickname;
        this.idCard = idCard;
        this.oos = oos;
    }
    
    @Override
    public void run() {

        if (checkDrawCondition()){

            Desk desk = game.getDesk();
            Player p = game.getCurrentPlayer();
            //draw card according locationType given
            switch (locationType){
                case RESOURCE_CARD_DECK:
                    p.addCardToHandCards(desk.pickNextRCard());
                    break;
                case GOLD_CARD_DECK:
                    p.addCardToHandCards(desk.pickNextGCard());
                    break;
                case DISPLAYED_RESOURCE_LIST:
                    if (desk.getDisplayedRCards().contains(idCard)){
                        p.addCardToHandCards(desk.pickOneDisplayedRCard(idCard));
                        desk.updateDisplayedRCard();
                    }else{
                        GameController.writeErrorMessage(oos, ErrorType.INVALID_CARD_ID);
                    }
                    break;
                case DISPLAYED_GOLD_LIST:
                    if (desk.getDisplayedGCards().contains(idCard)){
                        p.addCardToHandCards(desk.pickOneDisplayedGCard(idCard));
                        desk.updateDisplayedGCard();
                    }else{
                        GameController.writeErrorMessage(oos,ErrorType.INVALID_CARD_ID);
                    }
                    break;
            }

            //decide who will play in the next turn
            game.setGameState(GameState.TURN_MANAGE);
            GameController.getInstance().executeTask(new TurnManager(game));
        }
        
        
    }
    private boolean checkDrawCondition(){
        return game.getGameState() == GameState.DRAW_CARD &&
                game.getCurrentPlayer().getNickname().equals(nickname);
    }

}
