package it.polimi.ingsw.model;
import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.enums.Symbol;

import java.util.*;

import static it.polimi.ingsw.model.enums.Symbol.*;


public class PlayerBoard {
    private List<Integer> CardList;
    private List<Boolean> CoveredTopLeftAngle;
    private List<Boolean> CoveredTopRightAngle;
    private List<Boolean> CoveredBottomLeftAngle;
    private List<Boolean> CoveredBottomRightAngle;
    private List<Symbol> CardColor;
    private List<Integer> X;
    private List<Integer> Y;
    private List<Integer> HandList;

    //symbolsList[0] ANIMAL,
    //symbolsList[1] PLANT,
    //symbolsList[2] FUNGI,
    //symbolsList[3] INSECT,
    //symbolsList[4] FEATHER,
    //symbolsList[5] INK_BOTTLE,
    //symbolsList[6] PARCHMENT,
    private int[] SymbolsList;

    /**
     * cardList define list of card use on the board.
     * handList define list of card in hnd of player.
     * symbolsList define the resources and objects visible in the Playerboard.
     */
    public PlayerBoard() {
        HandList = new ArrayList<>();
        CardList = new ArrayList<>();
        X = new ArrayList<>();
        Y = new ArrayList<>();
        CoveredTopLeftAngle = new ArrayList<>();
        CoveredTopRightAngle = new ArrayList<>();
        CoveredBottomLeftAngle = new ArrayList<>();
        CoveredBottomRightAngle = new ArrayList<>();
        CardColor = new ArrayList<>();
        SymbolsList = new int[7];
    }

    Server server = new Server();

    public void placeCard(int IdCard) {
        Card card = server.getC(IdCard);
        CardList.add(IdCard);
        HandList.remove(IdCard);
        CardColor.add(card.getKingdom());
        int index = CardList.indexOf(IdCard);
        addSymbolsList(card);
        calculatepoint((GoldCard) card, index);
    }

    public void placeInitCard(Integer idcard) {
        Card card = server.getC(idcard);
        InitialCard initialcard = null;
        InitialCard initialCard = (InitialCard) card;

        CardList.add(idcard);
        CardColor.add(initialcard.getKingdom());
        X.add(0);
        Y.add(0);
        CoveredTopLeftAngle.add(false);
        CoveredTopRightAngle.add(false);
        CoveredBottomLeftAngle.add(false);
        CoveredBottomRightAngle.add(false);
        if (initialcard.isBackSide()) {
            int[] t = new int[4];
            t = initialcard.getCenterResource();
            SymbolsList[0] = t[0];
            SymbolsList[1] = t[1];
            SymbolsList[2] = t[2];
            SymbolsList[3] = t[3];
        } else {
            SymbolsList[0] = 1;
            SymbolsList[1] = 1;
            SymbolsList[2] = 1;
            SymbolsList[3] = 1;
        }
        SymbolsList[4] = 0;
        SymbolsList[5] = 0;
        SymbolsList[6] = 0;
    }


    private void addSymbolsList(Card card) {
        if (card.isBackSide()) {
            editaddSymbolsList(card.getKingdom());
        } else {
            editaddSymbolsList(card.getTopLeftAngle());
            editaddSymbolsList(card.getTopRightAngle());
            editaddSymbolsList(card.getBottomLeftAngle());
            editaddSymbolsList(card.getBottomRightAngle());
        }
    }

    private void removeSymbolsList(Card card, int i) {
        int x = X.get(i);
        int y = Y.get(i);
        for (int elem : X) {
            int index = X.indexOf(elem);
            if (elem == (x - 1) & Y.get(index) == (y + 1)) {
                Card elemcard = server.getC(index);
                editremoveSymbolsList(elemcard.getBottomRightAngle());
                CoveredBottomRightAngle.set(index, true);
                CoveredTopLeftAngle.set(i, true);
                break;
            }
            if (elem == (x + 1) & Y.get(index) == (y + 1)) {
                Card elemcard = server.getC(index);
                editremoveSymbolsList(elemcard.getBottomLeftAngle());
                CoveredBottomLeftAngle.set(index, true);
                CoveredTopRightAngle.set(i, true);
                break;
            }
            if (elem == (x + 1) & Y.get(index) == (y - 1)) {
                Card elemcard = server.getC(index);
                editremoveSymbolsList(elemcard.getTopLeftAngle());
                CoveredTopLeftAngle.set(index, true);
                CoveredBottomRightAngle.set(i, true);
                break;
            }
            if (elem == (x - 1) & Y.get(index) == (y - 1)) {
                Card elemcard = server.getC(index);
                editremoveSymbolsList(elemcard.getTopRightAngle());
                CoveredTopRightAngle.set(index, true);
                CoveredBottomLeftAngle.set(i, true);
                break;
            }
        }
    }

    public List<Integer> getCardList() {
        return CardList;
    }

    public List<Integer> getHandList() {
        return HandList;
    }

    public int calculatepoint(GoldCard card, int i) {
        int x = 0;
        switch (card.getType()) {
            case RESOURCE:
                x = card.getPoints();
                removeSymbolsList(card, i);
                break;
            case GOLD:
                switch (card.getBasicPointCriterion()) {
                    case EMPTY:
                        x = card.getPoints();
                        removeSymbolsList(card, i);
                        break;
                    case COVERED_ANGLE:
                        removeSymbolsList(card, i);
                        if (CoveredTopRightAngle.get(i)) x += 2;
                        if (CoveredTopLeftAngle.get(i)) x += 2;
                        if (CoveredBottomRightAngle.get(i)) x += 2;
                        if (CoveredBottomLeftAngle.get(i)) x += 2;
                        break;
                    case FEATHER:
                        x = SymbolsList[4] * card.getPoints();
                        removeSymbolsList(card, i);
                        break;
                    case PARCHMENT:
                        x = SymbolsList[6] * card.getPoints();
                        removeSymbolsList(card, i);
                        break;
                    case INK_BOTTLE:
                        x = SymbolsList[5] * card.getPoints();
                        removeSymbolsList(card, i);
                        break;
                }
                break;
        }
        return x;
    }


    public int calculategoalpoint(GoalCard card) {
        int point = 0;
        switch (card.getType()) {
            case REDG:
                point = goalcartL(FUNGI,FUNGI,FUNGI,1,1,1);
                break;
            case BLUEG:
                point = goalcartL(ANIMAL,ANIMAL,ANIMAL,1,1,1);
                break;
            case VIOLAD:
                point = goalcartL(INSECT,INSECT,INSECT,1,-1,1);
                break;
            case GREEND:
                point = goalcartL(PLANT,PLANT,PLANT,1,-1,1);
                break;
            case GGV:
                point = goalcartL(PLANT,PLANT,INSECT,0,-1,-1);
                break;
            case RRG:
                point = goalcartL(FUNGI,FUNGI,PLANT,0,-1,1);
                break;
            case VVB:
                point = goalcartL(INSECT,INSECT,INSECT,1,-1,0);
                break;
            case BBR:
                point = goalcartL(ANIMAL,ANIMAL,FUNGI,-1,-1,0);
                break;
            case FFF:
                point = 2 * (SymbolsList[2] / 3);
                break;
            case AAA:
                point = 2 * (SymbolsList[0] / 3);
                break;
            case PPP:
                point = 2 * (SymbolsList[1] / 3);
                break;
            case III:
                point = 2 * (SymbolsList[3] / 3);
                break;
            case BFP:
                int min = SymbolsList[4];
                if (min > SymbolsList[5]) min = SymbolsList[5];
                if (min > SymbolsList[6]) min = SymbolsList[6];
                point = min * 3;
                break;
            case BB:
                point = 2 * (SymbolsList[5] / 2);
                break;
            case FF:
                point = 2 * (SymbolsList[4] / 2);
                break;
            case PP:
                point = 2 * (SymbolsList[6] / 2);
                break;
        }
        return point;

    }

    private void editaddSymbolsList(Symbol symbol) {
        switch (symbol) {
            case EMPTY:
                break;
            case HIDDEN:
                break;
            case ANIMAL:
                SymbolsList[0] += 1;
                break;
            case PLANT:
                SymbolsList[1] += 1;
                break;
            case FUNGI:
                SymbolsList[2] += 1;
                break;
            case INSECT:
                SymbolsList[3] += 1;
                break;
            case FEATHER:
                SymbolsList[4] += 1;
                break;
            case INK_BOTTLE:
                SymbolsList[5] += 1;
                break;
            case PARCHMENT:
                SymbolsList[6] += 1;
                break;


        }

    }

    private void editremoveSymbolsList(Symbol symbol) {
        switch (symbol) {
            case EMPTY:
                break;
            case ANIMAL:
                SymbolsList[0] -= 1;
                break;
            case PLANT:
                SymbolsList[1] -= 1;
                break;
            case FUNGI:
                SymbolsList[2] -= 1;
                break;
            case INSECT:
                SymbolsList[3] -= 1;
                break;
            case FEATHER:
                SymbolsList[4] -= 1;
                break;
            case INK_BOTTLE:
                SymbolsList[5] -= 1;
                break;
            case PARCHMENT:
                SymbolsList[6] -= 1;
                break;

        }
    }

    private int goalcartL (Symbol symbol1,Symbol symbol2,Symbol symbol3 , int i , int ii,int iii){
        int point =0 ;
        for (int elem : CardList) {
            int index1 = CardList.indexOf(elem);
            if (CardColor.get(index1) == symbol1) {
                int x1 = X.get(index1);
                int y1 = Y.get(index1);
                int x2 = x1+i;
                int y2 = y1 + ii;
                int index2 = 0;
                for (int xelem : X) {
                    if (xelem == x2) {
                        for (int yelem : Y) {
                            if (yelem == y2 & X.indexOf(xelem) == Y.indexOf(yelem)) {
                                index2 = X.indexOf(xelem);
                                break;
                            }
                        }
                    }
                }
                if (CardColor.get(index2) == symbol2) {
                    int index3 = 0;
                    int x3 = x2 +iii;
                    int y3 = y2 +ii;
                    for (int xelem : X) {
                        if (xelem == x3) {
                            for (int yelem : Y) {
                                if (yelem == y3 & X.indexOf(xelem) == Y.indexOf(yelem)) {
                                    index3 = X.indexOf(xelem);
                                    break;
                                }
                            }
                        }
                    }
                    if (CardColor.get(index3) == symbol3) {
                        point += 3;
                        CardColor.set(index1, EMPTY);
                        CardColor.set(index2, EMPTY);
                        CardColor.set(index3, EMPTY);
                    }
                }
            }
        }
        return point;
    }

}