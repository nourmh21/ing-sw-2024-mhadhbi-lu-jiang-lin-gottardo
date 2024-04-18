package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.enums.CardType;

public class InitialCard extends Card{
    private int[] centerResource = new int[4];
    private Symbol topLeftAngleFront, topRightAngleFront, bottomLeftAngleFront, bottomRightAngleFront;


    /**
     *
     * constructor for the InitialCard class which extends the Card constructor
     * @param n_animal defines the optional center resource
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
     * centerResource are the resources that aren't placed on specific angles of some cards but at the center of them
     */
    public int[] getCenterResource() {
        return centerResource;
    }

    /**
     * @return topLeftAngleFront defines the resource, if there is one, that is contained in the upper left corner (type: enums.Symbol)
     */
    public Symbol getTopLeftAngleFront() {
        return topLeftAngleFront;
    }

    /**
     * @return topRightAngleFront defines the resource, if there is one, that is contained in the upper right corner (type: enums.Symbol)
     */
    public Symbol getTopRightAngleFront() {
        return topRightAngleFront;
    }

    /**
     * @return bottomLeftAngleFront defines the resource, if there is one, that is contained in the bottom left corner (type: enums.Symbol)
     */
    public Symbol getBottomLeftAngleFront() {
        return bottomLeftAngleFront;
    }

    /**
     *
     * @return bottomRightAngleFront defines the resource, if there is one, that is contained in the bottom right corner (type: enums.Symbol)
     */
    public Symbol getBottomRightAngleFront() {
        return bottomRightAngleFront;
    }
}

