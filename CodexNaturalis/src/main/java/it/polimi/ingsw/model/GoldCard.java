package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.Symbols;
import it.polimi.ingsw.model.enums.Types;

import java.util.List;

public class GoldCard extends Card{
    private List<Symbols> condition;
    private String basicPointCriterion;


    /**
     * Constructor for the GoldCard class which extends the Card constructor
     * @param condition
     * @param basicPointCriterion
     */
    public GoldCard(Symbols kingdom, int x, int y, Symbols topLeftAngle, Symbols topRightAngle, Symbols bottomLeftAngle, Symbols bottomRightAngle, int points, boolean isBackSide, Types type, List<Symbols> condition, String basicPointCriterion) {
        super(kingdom, x, y, topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle, points, isBackSide, type);
        this.condition = condition;
        this.basicPointCriterion = basicPointCriterion;
    }

    /**
     *
     */
    public List<Symbols> getCondition() {
        return condition;
    }

    /**
     *
     */
    public String getBasicPointCriterion() {
        return basicPointCriterion;
    }
}

