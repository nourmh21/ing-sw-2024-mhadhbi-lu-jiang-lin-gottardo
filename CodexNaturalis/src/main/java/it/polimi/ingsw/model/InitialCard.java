package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.enums.CardType;

public class InitialCard extends Card{
    private Symbol[] centerResource;

    /**
     * constructor for the InitialCard class which extends the Card constructor
     * @param centerResource defines the optional center resource
     */
    public InitialCard(Symbol kingdom, int x, int y, Symbol topLeftAngle, Symbol topRightAngle, Symbol bottomLeftAngle, Symbol bottomRightAngle, int points, boolean isBackSide, CardType type, Symbol[] centerResource) {
        super(kingdom, x, y, topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle, points, isBackSide, type);
        this.centerResource = centerResource;
    }

    /**
     * centerResource are the resources that aren't placed on specific angles of some cards but at the center of them
     */
    public Symbol[] getCenterResource() {
        return centerResource;
    }
}
