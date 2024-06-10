package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.message.enums.ErrorType;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.model.Desk;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

public class DrawCard  implements Runnable {
    private Client client;
    private Game game;
    private LocationType locationType;
    private int idCard;

    
    public DrawCard(Client client, Game game, LocationType locationType){
        this.client = client;
        this.game = game;
        this.locationType = locationType;
    }
    public DrawCard(Client client, Game game, LocationType locationType, Integer idCard){
        this.client = client;
        this.game = game;
        this.locationType = locationType;
        this.idCard = idCard;
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
                        client.informError(ErrorType.INVALID_CARD_ID);
                    }
                    break;
                case DISPLAYED_GOLD_LIST:
                    if (desk.getDisplayedGCards().contains(idCard)){
                        p.addCardToHandCards(desk.pickOneDisplayedGCard(idCard));
                        desk.updateDisplayedGCard();
                    }else{
                        client.informError(ErrorType.INVALID_CARD_ID);
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
                game.getCurrentPlayer().getNickname().equals(client.getNickname());
    }

}
