package it.polimi.ingsw.controller.task;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.error.ErrorMessage;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.model.Desk;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class DrawCard  implements Runnable {

    private Game game;
    private String nickname;
    private LocationType locationType;
    private int idCard;
    private ObjectOutputStream oos;

    
    public DrawCard(String nickname, Game game, LocationType locationType, ObjectOutputStream oos){
        this.game = game;
        this.locationType = locationType;
        this.nickname = nickname;
        this.oos = oos;
    }
    public DrawCard(Game game, String nickname, LocationType locationType, int idCard, ObjectOutputStream oos){
        this.game = game;
        this.locationType = locationType;
        this.nickname = nickname;
        this.idCard = idCard;
        this.oos = oos;
    }
    
    @Override
    public void run() {


        if (!checkDrawCondition()){
            //
        }else{
            Desk desk = game.getDesk();
            Player p = game.getCurrentPlayer();
            //draw card according locationType given
            switch (locationType){
                case RESOURCE_CARD_DECK:
                    p.addCardToHandCards(desk.pickNextRCard());
                    desk.updateNextRCard();
                    break;
                case GOLD_CARD_DECK:
                    p.addCardToHandCards(desk.pickNextGCard());
                    desk.updateNextGCard();
                    break;
                case DISPLAYED_RESOURCE_LIST:
                    if (desk.getDisplayedRCards().contains(idCard)){
                        p.addCardToHandCards(desk.pickOneDisplayedRCard(idCard));
                        desk.updateDisplayedRCard();
                    }else{
                        //
                        Message message = new ErrorMessage(ErrorType.INVALID_CARD_ID);
                        try {
                            oos.writeObject(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case DISPLAYED_GOLD_LIST:
                    if (desk.getDisplayedGCards().contains(idCard)){
                        p.addCardToHandCards(desk.pickOneDisplayedGCard(idCard));
                        desk.updateDisplayedGCard();
                    }else{
                        //
                        Message message = new ErrorMessage(ErrorType.INVALID_CARD_ID);
                        try {
                            oos.writeObject(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                //or maybe we can decide to give that one player a card from deck
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
