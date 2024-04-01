package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.enums.PCorner;
import it.polimi.ingsw.model.enums.GoalType;

import java.util.List;

public class GoalCard {
    private  int idCard;
    private int points;
    private GoalType type;
    private PCorner positionType;
    private List<Symbol> setlist;

    /**
     * @param points defines the points given.
     * @param type defines the Type of GoalCard POSITION or SET.
     * @param positionType defines the structure of the cards with the relative color.
     * @param setlist defines the set of resources or objects.
     */


    public GoalCard(int idCard, int points,GoalType type,PCorner positionType,List<Symbol> setlist){
        this.idCard = idCard;
        this.points=points;
        this.type=type;
        this.positionType=positionType;
        this.setlist=setlist;

    }
    public  int getIdCard(){
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

    /**
     *
     * @return returns the structure of the cards with the relative color.
     */
    public PCorner getPositionType() {
        return positionType;
    }

    /**
     * @return returns the set of resources or objects.
     */
    public List<Symbol> getSetlist() {
        return setlist;
    }
}
