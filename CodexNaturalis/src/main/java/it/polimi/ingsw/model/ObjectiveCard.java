package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.GoalType;

public class ObjectiveCard{
    private final int idCard;
    private final GoalType type;


    /**
     * constructor
     * @param type defines the Type of GoalCard POSITION or SET.
     * @param idCard defines ID of the card
     */
    public ObjectiveCard(int idCard, int points, GoalType type) {
        this.idCard = idCard;
        this.type = type;
    }


    /**
     * @return id of the card
     */
    public int getIdCard() {
        return idCard;
    }


    /**
     * @return Type POSITION or SET.
     */
    public GoalType getType() {
        return type;
    }


}



