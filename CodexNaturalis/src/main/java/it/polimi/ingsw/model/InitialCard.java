package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.enums.CardType;

public class InitialCard extends Card{
    private int[] centerResource = new int[4];

    /**
     * constructor for the InitialCard class which extends the Card constructor
     * @param centerResource defines the optional center resource
     */
    public InitialCard(int idCard, Symbol kingdom, Symbol topLeftAngle, Symbol topRightAngle, Symbol bottomLeftAngle, Symbol bottomRightAngle, int points, boolean isBackSide, CardType type, int[] centerResource) {
        super(idCard, kingdom, topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle, points, isBackSide, type);
        this.centerResource = centerResource;
    }

    /**
     * centerResource are the resources that aren't placed on specific angles of some cards but at the center of them
     */
    public int[] getCenterResource() {
        return centerResource;
    }
}

