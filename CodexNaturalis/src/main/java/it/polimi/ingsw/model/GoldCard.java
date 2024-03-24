package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.enums.CardType;

import java.util.List;

public class GoldCard extends Card{
    private int condition[] = new int[4];
    private Symbol basicPointCriterion;


    /**
     * Constructor for the GoldCard class which extends the Card constructor
     * @param //condition
     * @param basicPointCriterion
     */
    public GoldCard(Symbol kingdom, int x, int y, Symbol topLeftAngle, Symbol topRightAngle, Symbol bottomRightAngle, Symbol bottomLeftAngle, int points,
                    boolean isBackSide, CardType type,  Symbol basicPointCriterion,int n_fungi, int n_plant, int n_animal, int n_insect) {
        super(kingdom, x, y, topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle, points, isBackSide, type);
        this.basicPointCriterion = basicPointCriterion;
        condition[0] = n_fungi;
        condition[1] = n_plant;
        condition[2] = n_animal;
        condition[3] = n_insect;

    }




    /**
     *
     */
    public int[] getCondition() {
        return condition;
    }

    /**
     *
     */
    public Symbol getBasicPointCriterion() {
        return basicPointCriterion;
    }
}

