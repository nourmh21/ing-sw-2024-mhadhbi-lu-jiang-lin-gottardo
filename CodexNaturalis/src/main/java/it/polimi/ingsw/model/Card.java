package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.Symbol;


public class Card {
    private int idCard;
    private Symbol kingdom;
    private Symbol topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle;
    private int points;
    private CardType type;

    /**
     * @param kingdom defines the gold card's kingdom
     * @param topLeftAngle defines the optional resource in the upper left angle
     * @param topRightAngle defines the optional resource in the upper right angle
     * @param bottomLeftAngle defines the optional resource in the bottom left angle
     * @param bottomRightAngle defines the optional resource in the bottom right angle
     * @param points defines the optional points given if the
     * @param type
     */
    public Card(int idCard, Symbol kingdom, Symbol topLeftAngle, Symbol topRightAngle, Symbol bottomRightAngle,Symbol bottomLeftAngle, int points, CardType type) {
        this.idCard = idCard;
        this.type = type;
        this.kingdom = kingdom;
        this.topLeftAngle = topLeftAngle;
        this.topRightAngle = topRightAngle;
        this.bottomLeftAngle = bottomLeftAngle;
        this.bottomRightAngle = bottomRightAngle;
        this.points = points;
    }
//GETTERS

    /**
     * @return id of the card
     */
    public  int getIdCard(){
        return idCard;
    }

    /**
     * @return type can be RESOURCE, GOLD, INITIAL or GOAL (type: enums.CardType)
     */
    public CardType getType() {
        return type;
    }

    /**
     * @return kingdom can be FUNGI, PLANT, ANIMAL or INSECT (type: enums.Symbol)
     */
    public Symbol getKingdom(){
        return kingdom;
    }


    /**
     * @return topLeftAngle defines the resource, if there is one, that is contained in the upper left corner (type: enums.Symbol)
     */
    public Symbol getTopLeftAngle() {
        return topLeftAngle;
    }

    /**
     * @return topRightAngle defines the resource, if there is one, that is contained in the upper right corner (type: enums.Symbol)
     */
    public Symbol getTopRightAngle() {
        return topRightAngle;
    }

    /**
     * @return bottomLeftAngle defines the resource, if there is one, that is contained in the bottom left corner (type: enums.Symbol)
     */
    public Symbol getBottomLeftAngle() {
        return bottomLeftAngle;
    }

    /**
     *
     * @return bottomRightAngle defines the resource, if there is one, that is contained in the bottom right corner (type: enums.Symbol)
     */
    public Symbol getBottomRightAngle() {
        return bottomRightAngle;
    }

    /**
     * @return points given by the card
     */
    public int getPoint() {
        return points;
    }




}
