package it.polimi.ingsw;

import it.polimi.ingsw.enums.Symbols;
import it.polimi.ingsw.enums.Types;

public class InitialCard extends Card{
    private Symbols[] centerResource;

    /**
     * constructor for the InitialCard class which extends the Card constructor
     * @param centerResource defines the optional center resource
     */
    public InitialCard(Symbols kingdom, int x, int y, Symbols topLeftAngle, Symbols topRightAngle, Symbols bottomLeftAngle, Symbols bottomRightAngle, int points, boolean isBackSide, Types type, Symbols[] centerResource) {
        super(kingdom, x, y, topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle, points, isBackSide, type);
        this.centerResource = centerResource;
    }

    /**
     * centerResource are the resources that aren't placed on specific angles of some cards but at the center of them
     */
    public Symbols[] getCenterResource() {
        return centerResource;
    }
}
