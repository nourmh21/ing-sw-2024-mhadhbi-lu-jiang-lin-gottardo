package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.GoalType;

public class ObjectiveCard {
    private int idCard;
    private int points;
    private GoalType type;

    /**
     * @param points       defines the points given.
     * @param type         defines the Type of GoalCard POSITION or SET.
     * @param idCard       defines ID of the card
     */


    public ObjectiveCard(int idCard, int points, GoalType type) {
        this.idCard = idCard;
        this.points = points;
        this.type = type;
    }

    public int getIdCard() {
        return idCard;
    }

    /**
     * @return points given bye the GoalCard.
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



