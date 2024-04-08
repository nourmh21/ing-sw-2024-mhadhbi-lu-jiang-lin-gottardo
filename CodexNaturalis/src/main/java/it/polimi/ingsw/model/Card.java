package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.Symbol;

import static it.polimi.ingsw.model.enums.Symbol.COVERED_ANGLE;


public class Card {
    private int idCard;
    private Symbol kingdom;
    private Symbol topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle;
    private int points;
    private boolean isBackSide;
    private CardType type;

    /**
     * @param kingdom defines the gold card's kingdom
     * @param topLeftAngle defines the optional resource in the upper left angle
     * @param topRightAngle defines the optional resource in the upper right angle
     * @param bottomLeftAngle defines the optional resource in the bottom left angle
     * @param bottomRightAngle defines the optional resource in the bottom right angle
     * @param points defines the optional points given if the
     * @param isBackSide specifies if the card is representing its corresponding back
     * @param type
     */
    public Card(int idCard, Symbol kingdom, Symbol topLeftAngle, Symbol topRightAngle, Symbol bottomRightAngle,Symbol bottomLeftAngle,  int points, boolean isBackSide, CardType type) {
        this.idCard = idCard;
        this.kingdom = kingdom;
        this.topLeftAngle = topLeftAngle;
        this.topRightAngle = topRightAngle;
        this.bottomLeftAngle = bottomLeftAngle;
        this.bottomRightAngle = bottomRightAngle;
        this.points = points;
        this.isBackSide = isBackSide;
        this.type = type;
    }
//GETTERS

    public  int getIdCard(){
        return idCard;
    }

    /**
     * Type can be RESOURCE or GOLD or INITIAL or GOAL.
     */
    public CardType getType() {
        return type;
    }


    /**
     * topLeftAngle defines the resource, if there is one, that is contained in the upper left corner
     */
    public Symbol getTopLeftAngle() {
        return topLeftAngle;
    }

    /**
     * topRightAngle defines the resource, if there is one, that is contained in the upper right corner
     */
    public Symbol getTopRightAngle() {
        return topRightAngle;
    }

    /**
     * bottomLeftAngle defines the resource, if there is one, that is contained in the bottom left corner
     */
    public Symbol getBottomLeftAngle() {
        return bottomLeftAngle;
    }

    /**
     * bottomRightAngle defines the resource, if there is one, that is contained in the bottom right corner
     */
    public Symbol getBottomRightAngle() {
        return bottomRightAngle;
    }

    public int getPoints() {
        return points;
    }

    //SETTERS



    public boolean isBackSide() {
        return isBackSide;
    }
    public Symbol getKingdom(){
        return kingdom;
    }



}
