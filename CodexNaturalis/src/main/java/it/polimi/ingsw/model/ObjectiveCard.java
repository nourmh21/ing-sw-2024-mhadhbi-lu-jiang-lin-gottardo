package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.GoalType;
import it.polimi.ingsw.modelView.ObjectiveCardView;

public class ObjectiveCard{
    private final int idCard;
    private final int points;
    private final GoalType type;


    /**
     * constructor
     * @param points       defines the points given.
     * @param type         defines the Type of GoalCard POSITION or SET.
     * @param idCard       defines ID of the card
     */
    public ObjectiveCard(int idCard, int points, GoalType type) {
        this.idCard = idCard;
        this.points = points;
        this.type = type;
    }

    /**
     * Gets the view representation of the global goal.
     *
     * @return the view representation of the global goal.
     */
    public ObjectiveCardView getView(){
        return new ObjectiveCardView(this);
    }


    /**
     * @return id of the card
     */
    public int getIdCard() {
        return idCard;
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



