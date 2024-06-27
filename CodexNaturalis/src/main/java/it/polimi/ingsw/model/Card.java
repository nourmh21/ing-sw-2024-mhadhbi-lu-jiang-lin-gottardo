package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.Symbol;

/**
 * The Card class represents a card. It is the parent class for the GoldCard and InitialCard classes
 */
public class Card {
    private final int idCard;
    private final Symbol kingdom;
    private final Symbol topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle;
    private final int points;
    private final CardType type;

    /**
     * Card class constructor
     *
     * @param idCard           the card's id
     * @param kingdom          the gold card's kingdom
     * @param topLeftAngle     the optional resource in the upper left angle
     * @param topRightAngle    the optional resource in the upper right angle
     * @param bottomLeftAngle  the optional resource in the bottom left angle
     * @param bottomRightAngle the optional resource in the bottom right angle
     * @param points           the optional points of the card
     * @param type             the type of the card
     */
    public Card(int idCard, Symbol kingdom, Symbol topLeftAngle, Symbol topRightAngle, Symbol bottomRightAngle, Symbol bottomLeftAngle, int points, CardType type) {
        this.idCard = idCard;
        this.type = type;
        this.kingdom = kingdom;
        this.topLeftAngle = topLeftAngle;
        this.topRightAngle = topRightAngle;
        this.bottomLeftAngle = bottomLeftAngle;
        this.bottomRightAngle = bottomRightAngle;
        this.points = points;
    }

    /**
     * get method for int idCard
     *
     * @return id of the card
     */
    public int getIdCard() {
        return idCard;
    }

    /**
     * getter for type
     *
     * @return type of the card
     */
    public CardType getType() {
        return type;
    }

    /**
     * getter for kingdom
     *
     * @return kingdom of the card, it can be FUNGI, PLANT, ANIMAL or INSECT
     */
    public Symbol getKingdom() {
        return kingdom;
    }

    /**
     * getter for topLeftAngle
     *
     * @return symbol in the top left angle, on the front of the card
     */
    public Symbol getTopLeftAngle() {
        return topLeftAngle;
    }

    /**
     * getter for topRightAngle
     *
     * @return symbol in the top right angle, on the front of the card
     */
    public Symbol getTopRightAngle() {
        return topRightAngle;
    }

    /**
     * getter for bottomLeftAngle
     *
     * @return symbol in the bottom left angle, on the front of the card
     */
    public Symbol getBottomLeftAngle() {
        return bottomLeftAngle;
    }

    /**
     * getter for bottomRightAngle
     *
     * @return symbol in the bottom right angle, on the front of the card
     */
    public Symbol getBottomRightAngle() {
        return bottomRightAngle;
    }

    /**
     * getter for points
     *
     * @return points given by the card
     */
    public int getPoint() {
        return points;
    }

}
