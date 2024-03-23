package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.Symbol;


public class Card {
    private Symbol kingdom;
    private int x, y;
    private Symbol topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle;
    private int points;
    private boolean isBackSide;
    private CardType type;

    /**
     * @param kingdom defines the gold card's kingdom
     * @param x defines the x-coordinate of the gold card
     * @param y defines the y-coordinate of the gold card
     * @param topLeftAngle defines the optional resource in the upper left angle
     * @param topRightAngle defines the optional resource in the upper right angle
     * @param bottomLeftAngle defines the optional resource in the bottom left angle
     * @param bottomRightAngle defines the optional resource in the bottom right angle
     * @param points defines the optional points given if the
     * @param isBackSide specifies if the card is representing its corresponding back
     * @param type
     */
    public Card(Symbol kingdom, int x, int y, Symbol topLeftAngle, Symbol topRightAngle, Symbol bottomRightAngle,Symbol bottomLeftAngle,  int points, boolean isBackSide, CardType type) {
        this.kingdom = kingdom;
        this.x = x;
        this.y = y;
        this.topLeftAngle = topLeftAngle;
        this.topRightAngle = topRightAngle;
        this.bottomLeftAngle = bottomLeftAngle;
        this.bottomRightAngle = bottomRightAngle;
        this.points = points;
        this.isBackSide = isBackSide;
        this.type = type;
    }
//GETTERS

    /**
     * Type can be POSITION or SET.
     */
    public CardType getType() {
        return type;
    }

    /**
     * X is the x-axis coordinate of the card
     */
    public int getX() {
        return x;
    }

    /**
     * Y is the y-axis coordinate of the card
     */
    public int getY() {
        return y;
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

    //SETTERS

    /**
     * @param x x-axis coordinate of the card that is being placed
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y y-axis coordinate of the card that is being placed
     */
    public void setY(int y) {
        this.y = y;
    }


}
