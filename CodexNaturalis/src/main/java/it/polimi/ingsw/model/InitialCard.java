package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.enums.CardType;

public class InitialCard extends Card{
    private int[] centerResource = new int[4];

    /**
     * constructor for the InitialCard class which extends the Card constructor
     * @param n_animal defines the optional center resource
     */
    public InitialCard(int idCard, Symbol kingdom, Symbol topLeftAngle, Symbol topRightAngle, Symbol bottomLeftAngle, Symbol bottomRightAngle, int points, CardType type, int n_fungi, int n_plant, int n_animal, int n_insect) {
        super(idCard, kingdom, topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle, points, type);
        centerResource[0] = n_animal;
        centerResource[1] = n_plant;
        centerResource[2] = n_fungi;
        centerResource[3] = n_insect;
    }

    /**
     * centerResource are the resources that aren't placed on specific angles of some cards but at the center of them
     */
    public int[] getCenterResource() {
        return centerResource;
    }
}

