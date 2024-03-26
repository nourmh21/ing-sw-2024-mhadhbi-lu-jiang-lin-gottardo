package it.polimi.ingsw.model;
import java.util.*;

import static it.polimi.ingsw.model.enums.CardType.RESOURCE;
import static it.polimi.ingsw.model.enums.Symbol.*;


public class PlayerBoard {
    private List<Card> cardList;
    private List<Card> handList;
    private int[] symbolsList ;

    /**
     * cardList define list of card use on the board.
     * handList define list of card in hnd of player.
     * symbolsList define the resources and objects visible in the Playerboard.
     */
    public PlayerBoard() {
        handList =new ArrayList<>();
        cardList =new ArrayList<>();
        symbolsList=new int[7];
    }
    
    public void placeInitCard(InitialCard initcard){
        cardList.add(initcard);
        if(initcard.isBackSide()){
            int[] t= new int[4];
            t = initcard.getCenterResource() ;
            symbolsList[0] = t[0];
            symbolsList[1] = t[1];
            symbolsList[2] = t[2];
            symbolsList[3] = t[3];
        }else {
            symbolsList[0] = 1;
            symbolsList[1] = 1;
            symbolsList[2] = 1;
            symbolsList[3] = 1;
        }
        symbolsList[4] = 0;
        symbolsList[5] = 0;
        symbolsList[6] = 0;
    }

    public void placeCard(Card card){
        cardList.add(card);
        handList.remove(card);
        addSymbolsList(card);
        removeSymbolsList(card);

    }

    private void addSymbolsList(Card card){
        if(card.isBackSide()){
            switch (card.getKingdom()){
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
            }
        }else {
            switch (card.getTopLeftAngle()){
                case EMPTY :
                    break;
                case HIDDEN :
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
            switch (card.getTopRightAngle()){
                case EMPTY :
                    break;
                case HIDDEN :
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
            switch (card.getBottomLeftAngle()){
                case EMPTY :
                    break;
                case HIDDEN :
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
            switch (card.getBottomRightAngle()){
                case EMPTY :
                    break;
                case HIDDEN :
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
    }
    private void removeSymbolsList(Card card){
        int x = card.getX();
        for(Card elem : cardList){
            if(elem.getX()==(x-1+100)){
                switch (elem.getBottomRightAngle()){
                    case EMPTY :
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
                elem.setBottomRightAngle();
                break;
            }
            if(elem.getX()==(x+100+1)){
                switch (elem.getBottomLeftAngle()){
                    case EMPTY :
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
                elem.setBottomLeftAngle();
                break;
            }
            if(elem.getX()==(x-100+1)){
                switch (elem.getTopLeftAngle()){
                    case EMPTY :
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
                elem.setTopLeftAngle();
                break;
            }
            if(elem.getX()==(x-100-1)){
                switch (elem.getTopRightAngle()){
                    case EMPTY :
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
                elem.setTopRightAngle();
                break;
            }
        }

    }
    public List<Card> getCardList() {
        return cardList;
    }
    public List<Card> getHandList() {
        return handList;
    }

    public int calculatepoint(GoldCard card){
        int x = 0;
        switch (card.getType()) {
            case RESOURCE:
                x = card.getPoints();
                break;
            case GOLD:
                switch (card.getBasicPointCriterion()){
                    case EMPTY :
                        x = card.getPoints() ;
                        break;
                    case COVERED_ANGLE:
                        int y = card.getX();
                        for(Card elem : cardList) {
                            int p = elem.getX();
                            if (p == (y - 1 + 100)||p == (y - 1 - 100)||p == (y + 1 - 100)||p == (y + 1 + 100))  x+=2;
                        }
                        break;
                    case FEATHER:
                        x = (symbolsList[4]+1)*card.getPoints();
                        break;
                    case PARCHMENT:
                        x = (symbolsList[6]+1)*card.getPoints();
                        break;
                    case INK_BOTTLE:
                        x = (symbolsList[5]+1)*card.getPoints();
                        break;
                }
                break;
        }
        return x;
    }
    public int calculgoalpoint(GoalCard card){
        int x=0;
        switch (card.getType()){
            case SET:

                break;
            case POSITION:
                switch (card.getPositionType()){
                    case REDG:
                        break;
                    case BLUEG:
                        break;
                    case VIOLAD:
                        break;
                    case GREEND:
                        break;
                    case BBR:
                        break;
                    case VVB:
                        break;
                    case RRG:
                        break;
                    case GGV:
                        break;
                }
        }


        return x;

    }


}
