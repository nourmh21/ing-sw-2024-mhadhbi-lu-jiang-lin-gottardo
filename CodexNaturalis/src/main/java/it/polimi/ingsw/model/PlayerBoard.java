package it.polimi.ingsw.model;
import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.model.enums.Symbol;

import java.util.*;

import static it.polimi.ingsw.model.enums.CardType.RESOURCE;
import static it.polimi.ingsw.model.enums.Symbol.*;


public class PlayerBoard {
    private List<Integer> cardList;
    private List<Boolean>coveredTopLeftAngle;
    private List<Boolean>coveredTopRightAngle;
    private List<Boolean>coveredBottomLeftAngle;
    private List<Boolean>coveredBottomRightAngle;
    private List<Symbol> cardcolor;
    private List<Integer>x;
    private List<Integer>y;
    private List<Integer> handList;

    //symbolsList[0] ANIMAL,
    //symbolsList[1] PLANT,
    //symbolsList[2] FUNGI,
    //symbolsList[3] INSECT,
    //symbolsList[4] FEATHER,
    //symbolsList[5] INK_BOTTLE,
    //symbolsList[6] PARCHMENT,
    private int[] symbolsList ;

    /**
     * cardList define list of card use on the board.
     * handList define list of card in hnd of player.
     * symbolsList define the resources and objects visible in the Playerboard.
     */
    public PlayerBoard() {
        handList =new ArrayList<>();
        cardList =new ArrayList<>();
        x=new ArrayList<>();
        y=new ArrayList<>();
        coveredTopLeftAngle = new ArrayList<>();
        coveredTopRightAngle = new ArrayList<>();
        coveredBottomLeftAngle = new ArrayList<>();
        coveredBottomRightAngle = new ArrayList<>();
        cardcolor= new ArrayList<>();
        symbolsList=new int[7];
    }
    Server server= new Server();
    public void placeCard(int idcard){

        Card card = server.getC(idcard);
        cardList.add(idcard);
        handList.remove(idcard);
        cardcolor.add(card.getKingdom());
        int index = cardList.indexOf(idcard);
        addSymbolsList(card);
        calculatepoint((GoldCard) card,index);

    }
    public void placeInitCard(Integer idcard){
        InitialCard initialcard = server.getIC(idcard);
        cardList.add(idcard);
        cardcolor.add(initialcard.getKingdom());
        x.add(0);
        y.add(0);
        coveredTopLeftAngle.add(false);
        coveredTopRightAngle.add(false);
        coveredBottomLeftAngle.add(false);
        coveredBottomRightAngle.add(false);
        if(initialcard.isBackSide()){
            int[] t= new int[4];
            t = initialcard.getCenterResource() ;
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



    private void addSymbolsList(Card  card){
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
    private void removeSymbolsList(Card card,int i){
        int xx =x.get(i);
        int yy =y.get(i);
        for(int elem : x){
            int index =x.indexOf(elem);
            if(elem==(xx-1) & y.get(index)==(yy+1)){
                Card elemcard = server.getC(index);
                switch (elemcard.getBottomRightAngle()){
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
                coveredBottomRightAngle.set(index,true);
                coveredTopLeftAngle.set(i,true);
                break;
            }
            if(elem==(xx+1) & y.get(index)==(yy+1)){
                Card elemcard = server.getC(index);
                switch (elemcard.getBottomLeftAngle()){
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
                coveredBottomLeftAngle.set(index,true);
                coveredTopRightAngle.set(i,true);
                break;
            }
            if(elem==(xx+1) & y.get(index)==(yy-1)){
                Card elemcard = server.getC(index);
                switch (elemcard.getTopLeftAngle()){
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
                coveredTopLeftAngle.set(index,true);
                coveredBottomRightAngle.set(i,true);
                break;
            }
            if(elem==(xx-1) & y.get(index)==(yy-1)){
                Card elemcard = server.getC(index);
                switch (elemcard.getTopRightAngle()){
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
                coveredTopRightAngle.set(index,true);
                coveredBottomLeftAngle.set(i,true);
                break;
            }
        }

    }
    public List<Integer> getCardList() {
        return cardList;
    }
    public List<Integer> getHandList() {
        return handList;
    }

    public int calculatepoint(GoldCard card,int i){
        int x = 0;
        switch (card.getType()) {
            case RESOURCE:
                x = card.getPoints();
                removeSymbolsList(card,i);
                break;
            case GOLD:
                switch (card.getBasicPointCriterion()){
                    case EMPTY :
                        x = card.getPoints();
                        removeSymbolsList(card,i);
                        break;
                    case COVERED_ANGLE:
                        removeSymbolsList(card,i);
                        if (coveredTopRightAngle.get(i)) x+=2;
                        if (coveredTopLeftAngle.get(i)) x+=2;
                        if (coveredBottomRightAngle.get(i)) x+=2;
                        if (coveredBottomLeftAngle.get(i)) x+=2;
                        break;
                    case FEATHER:
                        x = symbolsList[4]*card.getPoints();
                        removeSymbolsList(card,i);
                        break;
                    case PARCHMENT:
                        x = symbolsList[6]*card.getPoints();
                        removeSymbolsList(card,i);
                        break;
                    case INK_BOTTLE:
                        x = symbolsList[5]*card.getPoints();
                        removeSymbolsList(card,i);
                        break;
                }
                break;
        }
        return x;
    }


    public int calculategoalpoint(GoalCard card){
        int point=0;
        switch (card.getType()){
            case REDG:
                for (int elem : cardList){
                    int index1 = cardList.indexOf(elem);
                    if(cardcolor.get(index1)== FUNGI){
                        int x1 = x.get(index1);
                        int y1 = y.get(index1);
                        int x2 = x1+1;
                        int y2 = y1+1;
                        int index2=0;
                        for (int xelem : x){
                            if (xelem == x2 ) {
                                for (int yelem : y){
                                    if (yelem==y2 & x.indexOf(xelem)==y.indexOf(yelem)){
                                        index2 = x.indexOf(xelem);
                                        break;
                                    }
                                }
                            }
                        }
                        if (cardcolor.get(index2)== FUNGI){
                            int index3=0;
                            int x3 = x2+1;
                            int y3 = y2+1;
                            for (int xelem : x){
                                if (xelem == x3 ) {
                                    for (int yelem : y){
                                        if (yelem==y3 & x.indexOf(xelem)==y.indexOf(yelem)){
                                            index3 = x.indexOf(xelem);
                                            break;
                                        }
                                    }
                                }
                            }
                            if (cardcolor.get(index3)== FUNGI){
                                point +=3;
                                cardcolor.set(index1,EMPTY);
                                cardcolor.set(index2,EMPTY);
                                cardcolor.set(index3,EMPTY);
                            }
                        }
                    }
                }
                break;
            case BLUEG:
                for (int elem : cardList){
                    int index1 = cardList.indexOf(elem);
                    if(cardcolor.get(index1)== ANIMAL){
                        int x1 = x.get(index1);
                        int y1 = y.get(index1);
                        int x2 = x1+1;
                        int y2 = y1+1;
                        int index2=0;
                        for (int xelem : x){
                            if (xelem == x2 ) {
                                for (int yelem : y){
                                    if (yelem==y2 & x.indexOf(xelem)==y.indexOf(yelem)){
                                        index2 = x.indexOf(xelem);
                                        break;
                                    }
                                }
                            }
                        }
                        if (cardcolor.get(index2)== ANIMAL){
                            int index3 = 0;
                            int x3 = x2+1;
                            int y3 = y2+1;
                            for (int xelem : x){
                                if (xelem == x3 ) {
                                    for (int yelem : y){
                                        if (yelem==y3 & x.indexOf(xelem)==y.indexOf(yelem)){
                                            index3 = x.indexOf(xelem);
                                            break;
                                        }
                                    }
                                }
                            }
                            if ( cardcolor.get(index3)== ANIMAL){
                                point +=3;
                                cardcolor.set(index1,EMPTY);
                                cardcolor.set(index2,EMPTY);
                                cardcolor.set(index3,EMPTY);
                            }
                        }
                    }
                }
                break;
            case VIOLAD:
                for (int elem : cardList){
                    int index1 = cardList.indexOf(elem);
                    if(cardcolor.get(index1)== INSECT){
                        int x1 = x.get(index1);
                        int y1 = y.get(index1);
                        int x2 = x1+1;
                        int y2 = y1-1;
                        int index2=0;
                        for (int xelem : x){
                            if (xelem == x2 ) {
                                for (int yelem : y){
                                    if (yelem==y2 & x.indexOf(xelem)==y.indexOf(yelem)){
                                        index2 = x.indexOf(xelem);
                                        break;
                                    }
                                }
                            }
                        }
                        if (cardcolor.get(index2)== INSECT){
                            int index3 =0;
                            int x3 = x2+1;
                            int y3 = y2-1;
                            for (int xelem : x){
                                if (xelem == x3 ) {
                                    for (int yelem : y){
                                        if (yelem==y3 & x.indexOf(xelem)==y.indexOf(yelem)){
                                            index3 = x.indexOf(xelem);
                                            break;
                                        }
                                    }
                                }
                            }
                            if ( cardcolor.get(index3)== INSECT){
                                point +=3;
                                cardcolor.set(index1,EMPTY);
                                cardcolor.set(index2,EMPTY);
                                cardcolor.set(index3,EMPTY);
                            }
                        }
                    }
                }

                break;
            case GREEND:
                for (int elem : cardList){
                    int index1 = cardList.indexOf(elem);
                    if(cardcolor.get(index1)== PLANT){
                        int x1 = x.get(index1);
                        int y1 = y.get(index1);
                        int x2 = x1+1;
                        int y2 = y1-1;
                        int index2=0;
                        for (int xelem : x){
                            if (xelem == x2 ) {
                                for (int yelem : y){
                                    if (yelem==y2 & x.indexOf(xelem)==y.indexOf(yelem)){
                                        index2 = x.indexOf(xelem);
                                        break;
                                    }
                                }
                            }
                        }
                        if (cardcolor.get(index2)== PLANT){
                            int index3 = 0;
                            int x3 = x2+1;
                            int y3 = y2-1;
                            for (int xelem : x){
                                if (xelem == x3 ) {
                                    for (int yelem : y){
                                        if (yelem==y3 & x.indexOf(xelem)==y.indexOf(yelem)){
                                            index3 = x.indexOf(xelem);
                                            break;
                                        }
                                    }
                                }
                            }
                            if ( cardcolor.get(index3)== PLANT){
                                point +=3;
                                cardcolor.set(index1,EMPTY);
                                cardcolor.set(index2,EMPTY);
                                cardcolor.set(index3,EMPTY);
                            }
                        }
                    }
                }
                break;
            case GGV:
                for (int elem : cardList){
                    int index1 = cardList.indexOf(elem);
                    if(cardcolor.get(index1)== PLANT){
                        int x1 = x.get(index1);
                        int y1 = y.get(index1);
                        int x2 = x1;
                        int y2 = y1-1;
                        int index2=0;
                        for (int xelem : x){
                            if (xelem == x2 ) {
                                for (int yelem : y){
                                    if (yelem==y2 & x.indexOf(xelem)==y.indexOf(yelem)){
                                        index2 = x.indexOf(xelem);
                                        break;
                                    }
                                }
                            }
                        }
                        if (cardcolor.get(index2)== PLANT){
                            int index3 = 0;
                            int x3 = x2-1;
                            int y3 = y2-1;
                            for (int xelem : x){
                                if (xelem == x3 ) {
                                    for (int yelem : y){
                                        if (yelem==y3 & x.indexOf(xelem)==y.indexOf(yelem)){
                                            index3 = x.indexOf(xelem);
                                            break;
                                        }
                                    }
                                }
                            }
                            if (cardcolor.get(index3)== INSECT){
                                point +=3;
                                cardcolor.set(index1,EMPTY);
                                cardcolor.set(index2,EMPTY);
                                cardcolor.set(index3,EMPTY);
                            }
                        }
                    }
                }
                break;
            case RRG:
                for (int elem : cardList){
                    int index1 = cardList.indexOf(elem);
                    if(cardcolor.get(index1)== FUNGI){
                        int x1 = x.get(index1);
                        int y1 = y.get(index1);
                        int x2 = x1;
                        int y2 = y1-1;
                        int index2=0;
                        for (int xelem : x){
                            if (xelem == x2 ) {
                                for (int yelem : y){
                                    if (yelem==y2 & x.indexOf(xelem)==y.indexOf(yelem)){
                                        index2 = x.indexOf(xelem);
                                        break;
                                    }
                                }
                            }
                        }
                        if ( cardcolor.get(index2)== FUNGI){
                            int index3 = 0;
                            int x3 = x2+1;
                            int y3 = y2-1;
                            for (int xelem : x){
                                if (xelem == x3 ) {
                                    for (int yelem : y){
                                        if (yelem==y3 & x.indexOf(xelem)==y.indexOf(yelem)){
                                            index3 = x.indexOf(xelem);
                                            break;
                                        }
                                    }
                                }
                            }
                            if (cardcolor.get(index3)== PLANT){
                                point +=3;
                                cardcolor.set(index1,EMPTY);
                                cardcolor.set(index2,EMPTY);
                                cardcolor.set(index3,EMPTY);
                            }
                        }
                    }
                }
                break;
            case VVB:
                for (int elem : cardList){
                    int index1 = cardList.indexOf(elem);
                    if(cardcolor.get(index1)== ANIMAL){
                        int x1 = x.get(index1);
                        int y1 = y.get(index1);
                        int x2 = x1+1;
                        int y2 = y1-1;
                        int index2=0;
                        for (int xelem : x){
                            if (xelem == x2 ) {
                                for (int yelem : y){
                                    if (yelem==y2 & x.indexOf(xelem)==y.indexOf(yelem)){
                                        index2 = x.indexOf(xelem);
                                        break;
                                    }
                                }
                            }
                        }
                        if (cardcolor.get(index2)== INSECT){
                            int index3 = 0;
                            int x3 = x2;
                            int y3 = y2-1;
                            for (int xelem : x){
                                if (xelem == x3 ) {
                                    for (int yelem : y){
                                        if (yelem==y3 & x.indexOf(xelem)==y.indexOf(yelem)){
                                            index3 = x.indexOf(xelem);
                                            break;
                                        }
                                    }
                                }
                            }
                            if (cardcolor.get(index3)== INSECT){
                                point +=3;
                                cardcolor.set(index1,EMPTY);
                                cardcolor.set(index2,EMPTY);
                                cardcolor.set(index3,EMPTY);
                            }
                        }
                    }
                }
                break;
            case BBR:
                for (int elem : cardList){
                    int index1 = cardList.indexOf(elem);
                    if(cardcolor.get(index1)== FUNGI){
                        int x1 = x.get(index1);
                        int y1 = y.get(index1);
                        int x2 = x1-1;
                        int y2 = y1-1;
                        int index2=0;
                        for (int xelem : x){
                            if (xelem == x2 ) {
                                for (int yelem : y){
                                    if (yelem==y2 & x.indexOf(xelem)==y.indexOf(yelem)){
                                        index2 = x.indexOf(xelem);
                                        break;
                                    }
                                }
                            }
                        }
                        if ( cardcolor.get(index2)== ANIMAL){
                            int index3 = 0;
                            int x3 = x2;
                            int y3 = y2-1;
                            for (int xelem : x){
                                if (xelem == x3 ) {
                                    for (int yelem : y){
                                        if (yelem==y3 & x.indexOf(xelem)==y.indexOf(yelem)){
                                            index3 = x.indexOf(xelem);
                                            break;
                                        }
                                    }
                                }
                            }
                            if (cardcolor.get(index3)== ANIMAL){
                                point +=3;
                                cardcolor.set(index1,EMPTY);
                                cardcolor.set(index2,EMPTY);
                                cardcolor.set(index3,EMPTY);
                            }
                        }
                    }
                }
                break;
            //symbolsList[0] ANIMAL,
            //symbolsList[1] PLANT,
            //symbolsList[2] FUNGI,
            //symbolsList[3] INSECT,
            //symbolsList[4] FEATHER,
            //symbolsList[5] INK_BOTTLE,
            //symbolsList[6] PARCHMENT,
            case FFF:
                point=2*(symbolsList[2]/3);
                break;
            case AAA:
                point=2*(symbolsList[0]/3);
                break;
            case PPP:
                point=2*(symbolsList[1]/3);
                break;
            case III:
                point=2*(symbolsList[3]/3);
                break;
            case BFP:
                int min = symbolsList[4];
                if (min > symbolsList[5]) min = symbolsList[5];
                if (min > symbolsList[6]) min = symbolsList[6];
                point=min*3;
                break;
            case BB:
                point=2*(symbolsList[5]/2);
                break;
            case FF:
                point=2*(symbolsList[4]/2);
                break;
            case PP:
                point=2*(symbolsList[6]/2);
                break;
        }
        return point;

    }


}
