package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.enums.CardType;

public class InitialCard extends Card{
    private final int[] centerResource = new int[4];
    private final Symbol topLeftAngleFront, topRightAngleFront, bottomLeftAngleFront, bottomRightAngleFront;


    /**
     * constructor of the InitialCard class which extends the Card constructor
     * @param n_fungi
     * @param n_plant
     * @param n_animal
     * @param n_insect
     * @param topLeftAngleFront
     * @param topRightAngleFront
     * @param bottomRightAngleFront
     * @param bottomLeftAngleFront
     */
    public InitialCard(int idCard, Symbol kingdom, Symbol topLeftAngle, Symbol topRightAngle, Symbol bottomLeftAngle, Symbol bottomRightAngle, int points, CardType type, int n_fungi, int n_plant, int n_animal, int n_insect,Symbol topLeftAngleFront,Symbol topRightAngleFront,Symbol bottomRightAngleFront,Symbol bottomLeftAngleFront ) {
        super(idCard, kingdom, topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle, points, type);
        centerResource[0] = n_animal;
        centerResource[1] = n_plant;
        centerResource[2] = n_fungi;
        centerResource[3] = n_insect;
        this.topLeftAngleFront = topLeftAngleFront;
        this.topRightAngleFront = topRightAngleFront;
        this.bottomLeftAngleFront = bottomLeftAngleFront;
        this.bottomRightAngleFront = bottomRightAngleFront;
    }


    /**
     * @return resources at center of the card
     */
    public int[] getCenterResource() {
        return centerResource;
    }


    /**
     * @return symbol in the top left angle, on the front of the card
     */
    public Symbol getTopLeftAngleFront() {
        return topLeftAngleFront;
    }


    /**
     * @return symbol in the top right angle, on the front of the card
     */
    public Symbol getTopRightAngleFront() {
        return topRightAngleFront;
    }


    /**
     * @return symbol in the bottom left angle, on the front of the card
     */
    public Symbol getBottomLeftAngleFront() {
        return bottomLeftAngleFront;
    }


    /**
     * @return symbol in the bottom right angle, on the front of the card
     */
    public Symbol getBottomRightAngleFront() {
        return bottomRightAngleFront;
    }


}

