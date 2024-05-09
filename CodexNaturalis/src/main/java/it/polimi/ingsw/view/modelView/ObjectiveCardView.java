package it.polimi.ingsw.view.modelView;

import it.polimi.ingsw.model.ObjectiveCard;
import it.polimi.ingsw.model.enums.GoalType;

import java.io.Serializable;



/**
 * The {@code ObjectiveCardView} class represents the immutable version of the {@link it.polimi.ingsw.model.ObjectiveCard}.
 * It provides a snapshot of the player's ObjectiveCard in a serializable format.
 */
public class ObjectiveCardView implements Serializable{

    /**
     * id of the ObjectiveCard.
     */
    private final int id;
    private final int points;
    private final GoalType type;




    public ObjectiveCardView (ObjectiveCard card){
        id = card.getIdCard();
        points = card.getPoints();
        type= card.getType();
    }

    /**
     * @return id of the card
     */
    public int getIdCard() {
        return id;
    }


    /**
     * @return points given by the GoalCard.
     */
    public int getPoints() {
        return points;
    }


    /**
     * @return Type POSITION or SET.
     */
    public GoalType getType() {
        return type;
    }






}
