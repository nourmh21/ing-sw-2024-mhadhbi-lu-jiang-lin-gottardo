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
    public GoldCard(int idCard, Symbol kingdom, Symbol topLeftAngle, Symbol topRightAngle, Symbol bottomRightAngle, Symbol bottomLeftAngle, int points,
                     CardType type, Symbol basicPointCriterion,int n_fungi, int n_plant, int n_animal, int n_insect) {
        super(idCard, kingdom, topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle, points, type);
        this.basicPointCriterion = basicPointCriterion;
        condition[0] = n_animal;
        condition[1] = n_plant;
        condition[2] = n_fungi;
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

