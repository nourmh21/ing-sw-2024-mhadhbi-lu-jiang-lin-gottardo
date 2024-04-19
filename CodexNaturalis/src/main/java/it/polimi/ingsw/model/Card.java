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
     * the class constructor
     * @param kingdom the gold card's kingdom
     * @param topLeftAngle the optional resource in the upper left angle
     * @param topRightAngle the optional resource in the upper right angle
     * @param bottomLeftAngle the optional resource in the bottom left angle
     * @param bottomRightAngle the optional resource in the bottom right angle
     * @param points the optional points of the card
     * @param type the type of the card
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


    /**
     * @return id of the card
     */
    public  int getIdCard(){
        return idCard;
    }


    /**
     * @return type of the card
     */
    public CardType getType() {
        return type;
    }


    /**
     * @return kingdom of the card, it can be FUNGI, PLANT, ANIMAL or INSECT
     */
    public Symbol getKingdom(){
        return kingdom;
    }


    /**
     * @return symbol in the top left angle, on the front of the card
     */
    public Symbol getTopLeftAngle() {
        return topLeftAngle;
    }


    /**
     * @return symbol in the top right angle, on the front of the card
     */
    public Symbol getTopRightAngle() {
        return topRightAngle;
    }


    /**
     * @return symbol in the bottom left angle, on the front of the card
     */
    public Symbol getBottomLeftAngle() {
        return bottomLeftAngle;
    }


    /**
     * @return symbol in the bottom right angle, on the front of the card
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
