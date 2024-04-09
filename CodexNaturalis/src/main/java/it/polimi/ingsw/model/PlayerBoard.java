package it.polimi.ingsw.model;
import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.enums.Symbol;

import java.util.*;

import static it.polimi.ingsw.model.enums.Symbol.*;


public class PlayerBoard {
    private List<Integer> boardCards;
    private List<Boolean> isCoveredTopLeftAngle;
    private List<Boolean> isCoveredTopRightAngle;
    private List<Boolean> isCoveredBottomLeftAngle;
    private List<Boolean> isCoveredBottomRightAngle;
    private List<Symbol> cardKingdom;
    private List<Integer> x;
    private List<Integer> y;
    private List<Integer> handCards;

    //symbolsList[0] ANIMAL,
    //symbolsList[1] PLANT,
    //symbolsList[2] FUNGI,
    //symbolsList[3] INSECT,
    //symbolsList[4] FEATHER,
    //symbolsList[5] INK_BOTTLE,
    //symbolsList[6] PARCHMENT,
    private int[] symbolsList;

    /**
     * cardList define list of card use on the board.
     * handList define list of card in hnd of player.
     * symbolsList define the resources and objects visible in the Playerboard.
     */
    public PlayerBoard() {
        handCards = new ArrayList<>();
        boardCards = new ArrayList<>();
        x = new ArrayList<>();
        y = new ArrayList<>();
        isCoveredTopLeftAngle = new ArrayList<>();
        isCoveredTopRightAngle = new ArrayList<>();
        isCoveredBottomLeftAngle = new ArrayList<>();
        isCoveredBottomRightAngle = new ArrayList<>();
        cardKingdom = new ArrayList<>();
        symbolsList = new int[7];
    }

    Server server = new Server();

    public void placeCard(int idCard,boolean isBackSide) {
        Card card = server.getC(idCard);
        boardCards.add(idCard);
        handCards.remove(idCard);
        cardKingdom.add(card.getKingdom());
        int index = boardCards.indexOf(idCard);
        addSymbolsList(card,isBackSide);
        calculatePoint((GoldCard) card, index);
    }

    public void placeInitCard(int idCard,boolean isBackSide) {
        Card card = server.getC(idCard);
        InitialCard initialcard = null;
        InitialCard initialCard = (InitialCard) card;

        boardCards.add(idCard);
        cardKingdom.add(initialcard.getKingdom());
        x.add(0);
        y.add(0);
        isCoveredTopLeftAngle.add(false);
        isCoveredTopRightAngle.add(false);
        isCoveredBottomLeftAngle.add(false);
        isCoveredBottomRightAngle.add(false);
        if (isBackSide) {
            int[] t = new int[4];
            t = initialcard.getCenterResource();
            symbolsList[0] = t[0];
            symbolsList[1] = t[1];
            symbolsList[2] = t[2];
            symbolsList[3] = t[3];
        } else {
            symbolsList[0] = 1;
            symbolsList[1] = 1;
            symbolsList[2] = 1;
            symbolsList[3] = 1;
        }
        symbolsList[4] = 0;
        symbolsList[5] = 0;
        symbolsList[6] = 0;
    }


    private void addSymbolsList(Card card,boolean isBackSide) {
        if (isBackSide) {
            editAddSymbolsList(card.getKingdom());
        } else {
            editAddSymbolsList(card.getTopLeftAngle());
            editAddSymbolsList(card.getTopRightAngle());
            editAddSymbolsList(card.getBottomLeftAngle());
            editAddSymbolsList(card.getBottomRightAngle());
        }
    }

    private void removeSymbolsList(Card card, int i) {
        int x = this.x.get(i);
        int y = this.y.get(i);
        for (int elem : this.x) {
            int index = this.x.indexOf(elem);
            if (elem == (x - 1) & this.y.get(index) == (y + 1)) {
                Card elemCard = server.getC(index);
                editRemoveSymbolsList(elemCard.getBottomRightAngle());
                isCoveredBottomRightAngle.set(index, true);
                isCoveredTopLeftAngle.set(i, true);
                break;
            }
            if (elem == (x + 1) & this.y.get(index) == (y + 1)) {
                Card elemCard = server.getC(index);
                editRemoveSymbolsList(elemCard.getBottomLeftAngle());
                isCoveredBottomLeftAngle.set(index, true);
                isCoveredTopRightAngle.set(i, true);
                break;
            }
            if (elem == (x + 1) & this.y.get(index) == (y - 1)) {
                Card elemCard = server.getC(index);
                editRemoveSymbolsList(elemCard.getTopLeftAngle());
                isCoveredTopLeftAngle.set(index, true);
                isCoveredBottomRightAngle.set(i, true);
                break;
            }
            if (elem == (x - 1) & this.y.get(index) == (y - 1)) {
                Card elemCard = server.getC(index);
                editRemoveSymbolsList(elemCard.getTopRightAngle());
                isCoveredTopRightAngle.set(index, true);
                isCoveredBottomLeftAngle.set(i, true);
                break;
            }
        }
    }

    public List<Integer> getBoardCards() {
        return boardCards;
    }

    public List<Integer> getHandCards() {
        return handCards;
    }

    public int calculatePoint(GoldCard card, int i) {
        int x = 0;
        switch (card.getType()) {
            case RESOURCE:
                x = card.getPoint();
                removeSymbolsList(card, i);
                break;
            case GOLD:
                switch (card.getBasicPointCriterion()) {
                    case EMPTY:
                        x = card.getPoint();
                        removeSymbolsList(card, i);
                        break;
                    case COVERED_ANGLE:
                        removeSymbolsList(card, i);
                        if (isCoveredTopRightAngle.get(i)) x += 2;
                        if (isCoveredTopLeftAngle.get(i)) x += 2;
                        if (isCoveredBottomRightAngle.get(i)) x += 2;
                        if (isCoveredBottomLeftAngle.get(i)) x += 2;
                        break;
                    case FEATHER:
                        x = symbolsList[4] * card.getPoint();
                        removeSymbolsList(card, i);
                        break;
                    case PARCHMENT:
                        x = symbolsList[6] * card.getPoint();
                        removeSymbolsList(card, i);
                        break;
                    case INK_BOTTLE:
                        x = symbolsList[5] * card.getPoint();
                        removeSymbolsList(card, i);
                        break;
                }
                break;
        }
        return x;
    }


    public int calculateGoalPoint(ObjectiveCard card) {
        int point = 0;
        switch (card.getType()) {
            case REDG:
                point = goalCardPosition(FUNGI,FUNGI,FUNGI,1,1,1);
                break;
            case BLUEG:
                point = goalCardPosition(ANIMAL,ANIMAL,ANIMAL,1,1,1);
                break;
            case VIOLAD:
                point = goalCardPosition(INSECT,INSECT,INSECT,1,-1,1);
                break;
            case GREEND:
                point = goalCardPosition(PLANT,PLANT,PLANT,1,-1,1);
                break;
            case GGV:
                point = goalCardPosition(PLANT,PLANT,INSECT,0,-1,-1);
                break;
            case RRG:
                point = goalCardPosition(FUNGI,FUNGI,PLANT,0,-1,1);
                break;
            case VVB:
                point = goalCardPosition(INSECT,INSECT,INSECT,1,-1,0);
                break;
            case BBR:
                point = goalCardPosition(ANIMAL,ANIMAL,FUNGI,-1,-1,0);
                break;
            case FFF:
                point = 2 * (symbolsList[2] / 3);
                break;
            case AAA:
                point = 2 * (symbolsList[0] / 3);
                break;
            case PPP:
                point = 2 * (symbolsList[1] / 3);
                break;
            case III:
                point = 2 * (symbolsList[3] / 3);
                break;
            case BFP:
                int min = symbolsList[4];
                if (min > symbolsList[5]) min = symbolsList[5];
                if (min > symbolsList[6]) min = symbolsList[6];
                point = min * 3;
                break;
            case BB:
                point = 2 * (symbolsList[5] / 2);
                break;
            case FF:
                point = 2 * (symbolsList[4] / 2);
                break;
            case PP:
                point = 2 * (symbolsList[6] / 2);
                break;
        }
        return point;

    }

    private void editAddSymbolsList(Symbol symbol) {
        switch (symbol) {
            case EMPTY:
                break;
            case HIDDEN:
                break;
            case ANIMAL:
                symbolsList[0] += 1;
                break;
            case PLANT:
                symbolsList[1] += 1;
                break;
            case FUNGI:
                symbolsList[2] += 1;
                break;
            case INSECT:
                symbolsList[3] += 1;
                break;
            case FEATHER:
                symbolsList[4] += 1;
                break;
            case INK_BOTTLE:
                symbolsList[5] += 1;
                break;
            case PARCHMENT:
                symbolsList[6] += 1;
                break;


        }

    }

    private void editRemoveSymbolsList(Symbol symbol) {
        switch (symbol) {
            case EMPTY:
                break;
            case ANIMAL:
                symbolsList[0] -= 1;
                break;
            case PLANT:
                symbolsList[1] -= 1;
                break;
            case FUNGI:
                symbolsList[2] -= 1;
                break;
            case INSECT:
                symbolsList[3] -= 1;
                break;
            case FEATHER:
                symbolsList[4] -= 1;
                break;
            case INK_BOTTLE:
                symbolsList[5] -= 1;
                break;
            case PARCHMENT:
                symbolsList[6] -= 1;
                break;

        }
    }

    private int goalCardPosition(Symbol symbol1, Symbol symbol2, Symbol symbol3 , int i , int ii, int iii){
        int point = 0 ;
        for (int elem : boardCards) {
            int index1 = boardCards.indexOf(elem);
            if (cardKingdom.get(index1) == symbol1) {
                int x1 = x.get(index1);
                int y1 = y.get(index1);
                int x2 = x1+i;
                int y2 = y1 + ii;
                int index2 = 0;
                for (int xelem : x) {
                    if (xelem == x2) {
                        for (int yelem : y) {
                            if (yelem == y2 & x.indexOf(xelem) == y.indexOf(yelem)) {
                                index2 = x.indexOf(xelem);
                                break;
                            }
                        }
                    }
                }
                if (cardKingdom.get(index2) == symbol2) {
                    int index3 = 0;
                    int x3 = x2 +iii;
                    int y3 = y2 +ii;
                    for (int xelem : x) {
                        if (xelem == x3) {
                            for (int yelem : y) {
                                if (yelem == y3 & x.indexOf(xelem) == y.indexOf(yelem)) {
                                    index3 = x.indexOf(xelem);
                                    break;
                                }
                            }
                        }
                    }
                    if (cardKingdom.get(index3) ==symbol3) {
                        point += 3;
                        cardKingdom.set(index1, EMPTY);
                        cardKingdom.set(index2, EMPTY);
                        cardKingdom.set(index3, EMPTY);
                    }
                }
            }
        }
        return point;
    }
}