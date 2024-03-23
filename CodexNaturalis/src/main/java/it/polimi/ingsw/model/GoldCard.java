package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.enums.CardType;

import java.util.List;

public class GoldCard extends Card{
    private List<Symbol> condition;
    private Symbol basicPointCriterion;


    /**
     * Constructor for the GoldCard class which extends the Card constructor
     * @param condition
     * @param basicPointCriterion
     */
    public GoldCard(Symbol kingdom, int x, int y, Symbol topLeftAngle, Symbol topRightAngle, Symbol bottomRightAngle, Symbol bottomLeftAngle, int points, boolean isBackSide, CardType type, List<Symbol> condition, Symbol basicPointCriterion) {
        super(kingdom, x, y, topLeftAngle, topRightAngle, bottomLeftAngle, bottomRightAngle, points, isBackSide, type);
        this.condition = condition;
        this.basicPointCriterion = basicPointCriterion;
    }

    /**
     *
     */
    public List<Symbol> getCondition() {
        return condition;
    }

    /**
     *
     */
    public Symbol getBasicPointCriterion() {
        return basicPointCriterion;
    }
}

