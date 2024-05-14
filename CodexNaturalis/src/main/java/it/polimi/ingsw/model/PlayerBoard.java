package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.exceptions.IllegalCoordinateInsertionException;
import it.polimi.ingsw.model.exceptions.InvalidIdCardException;

import java.util.*;

import static it.polimi.ingsw.model.enums.Symbol.*;

public class PlayerBoard {

    final private List<Integer> boardCards;
    final private List<Symbol> topLeftAngle;
    final private List<Symbol> topRightAngle;
    final private List<Symbol> bottomLeftAngle;
    final private List<Symbol> bottomRightAngle;
    final private List<Symbol> cardKingdom;
    final private List<Integer>  x;
    final private List<Integer> y;
    final private int[] symbolsList;
    final private ArrayList<int[]> availablePosition;
    final private ArrayList<int[]> forbiddenPosition;


    /**
     * cardList define list of card use on the board.
     * x coordinate for the position
     * y coordinate for the position
     * isCoveredTopLeftAngle whether the corner is covered or show the resource
     * isCoveredTopRightAngle whether the corner is covered or show the resource
     * isCoveredBottomLeftAngle whether the corner is covered or show the resource
     * isCoveredBottomRightAngle whether the corner is covered or show the resource
     * cardKingdom defines the color of the card
     * symbolsList define the resources and objects visible in the PlayerBoard.
     * symbolsList[0] indicates the number of ANIMAL,
     * symbolsList[1] indicates the number of PLANT,
     * symbolsList[2] indicates the number of FUNGI,
     * symbolsList[3] indicates the number of INSECT,
     * symbolsList[4] indicates the number of FEATHER,
     * symbolsList[5] indicates the number of INK_BOTTLE,
     * symbolsList[6] indicates the number of PARCHMENT,
     * availablePosition list of Position where player can place a new card
     * forbiddenPosition list of Position where player can't place a new card
     */
    public PlayerBoard() {
        boardCards = new ArrayList<>();
        x = new ArrayList<>();
        y = new ArrayList<>();
        topLeftAngle = new ArrayList<>();
        topRightAngle = new ArrayList<>();
        bottomLeftAngle = new ArrayList<>();
        bottomRightAngle = new ArrayList<>();
        cardKingdom = new ArrayList<>();
        symbolsList = new int[7];
        availablePosition = new ArrayList<>();
        forbiddenPosition= new ArrayList<>();
    }


    /**
     * represents the placement of a card on the board
     * @param card  to place
     * @param isBackSide if the card is used on the back side
     * @param xx the x position of the card
     * @param yy the y position of the card
     * @throws InvalidIdCardException when id is not for resource or gold card
     * @throws IllegalCoordinateInsertionException if the coordinates are invalid, or represent a prohibited position
     */
    public int placeCard(Card card,boolean isBackSide,int xx, int yy) throws InvalidIdCardException {
        int idCard = card.getIdCard();
        if (idCard>80||idCard<1) {throw new InvalidIdCardException();}
        boardCards.add(idCard);
        removeAvailablePosition(new int[]{xx,yy});
        addAvailablePosition(xx,yy);
        cardKingdom.add(card.getKingdom());
        int index = boardCards.indexOf(idCard);

        x.add(xx);
        y.add(yy);
        addSymbolsList(card,isBackSide,xx,yy);
        updateAvailablePosition();
        return calculatePoint(card , index);
    }


    /**
     * represents the placement of an initial card on the board
     * @param initialCard initial card id to place
     * @param isBackSide if the card is used on the back side
     *@throws InvalidIdCardException when id is not for initial card
     */
    public void placeInitCard(InitialCard initialCard,boolean isBackSide)  throws InvalidIdCardException {
        int idCard = initialCard.getIdCard();
        if (idCard > 102 || idCard < 97) {
            throw new InvalidIdCardException();
        }

        boardCards.add(idCard);
        cardKingdom.add(initialCard.getKingdom());
        x.add(0);
        y.add(0);
        addAvailablePosition(0,0);

        if (isBackSide) {
                symbolsList[0] = 1;
                symbolsList[1] = 1;
                symbolsList[2] = 1;
                symbolsList[3] = 1;
                topLeftAngle.add(initialCard.getTopLeftAngle());
                topRightAngle.add(initialCard.getTopRightAngle());
                bottomLeftAngle.add(initialCard.getBottomLeftAngle());
                bottomRightAngle.add(initialCard.getBottomRightAngle());

        } else {

                symbolsList[0] = initialCard.getCenterResource()[0];
                symbolsList[1] = initialCard.getCenterResource()[1];
                symbolsList[2] = initialCard.getCenterResource()[2];
                symbolsList[3] = initialCard.getCenterResource()[3];
                topLeftAngle.add(initialCard.getTopLeftAngleFront());
                topRightAngle.add(initialCard.getTopRightAngleFront());
                bottomLeftAngle.add(initialCard.getBottomLeftAngleFront());
                bottomRightAngle.add(initialCard.getBottomRightAngleFront());
                if(initialCard.getBottomLeftAngleFront()==HIDDEN){
                    removeAvailablePosition(new int[]{-1, -1});
                    removeAvailablePosition(new int[]{1, -1});
                    forbiddenPosition.add(new int[]{-1, -1});
                    forbiddenPosition.add(new int[]{1, -1});
                }

            }
            symbolsList[4] = 0;
            symbolsList[5] = 0;
            symbolsList[6] = 0;

    }


    /**
     * add resources to the symbol list
     * @param card card to be analyzed
     * @param isBackSide if the card is used on the back side
     */
    private void addSymbolsList(Card card,boolean isBackSide,int xx,int yy) {
        if (isBackSide) {
            editAddSymbolsList(card.getKingdom(),new int[]{0,0});
            bottomLeftAngle.add( EMPTY);
            bottomRightAngle.add(EMPTY);
            topLeftAngle.add( EMPTY);
            topRightAngle.add(EMPTY);


        } else {
            editAddSymbolsList(card.getTopLeftAngle(),new int[]{xx-1,yy+1});
            editAddSymbolsList(card.getTopRightAngle(),new int[]{xx+1,yy+1});
            editAddSymbolsList(card.getBottomLeftAngle(),new int[]{xx-1,yy-1});
            editAddSymbolsList(card.getBottomRightAngle(),new int[]{xx+1,yy-1});

            bottomLeftAngle.add(card.getBottomLeftAngle());
            bottomRightAngle.add( card.getBottomRightAngle());
            topLeftAngle.add( card.getTopLeftAngle());
            topRightAngle.add(card.getTopRightAngle());
        }
    }


    /**
     * remove resources to the symbol list
     * @param i card index in the card list array
     */
    private void removeSymbolsList(int i) {
        int xx = x.get(i);
        int yy = y.get(i);
        int index =0;
        for (int elem : x) {
            //int index = x.indexOf(elem);
            if (elem == (xx - 1) & y.get(index) == (yy + 1)) {
                editRemoveSymbolsList(bottomRightAngle.get(index));
                bottomRightAngle.set(index, COVERED_ANGLE);
                //also the card that I place even if the resource is not covered, I need it to calculate point
                topLeftAngle.set(i, COVERED_ANGLE);
                removeAvailablePosition(new int[]{xx-1,yy+1});

            }
            if (elem == (xx + 1) & y.get(index) == (yy + 1)) {
                editRemoveSymbolsList(bottomLeftAngle.get(index));
                bottomLeftAngle.set(index, COVERED_ANGLE);
                topRightAngle.set(i, COVERED_ANGLE);
                removeAvailablePosition(new int[]{xx+1,yy+1});

            }
            if (elem == (xx + 1) & y.get(index) == (yy - 1)) {
                editRemoveSymbolsList(topLeftAngle.get(index));
                topLeftAngle.set(index, COVERED_ANGLE);
                bottomRightAngle.set(i, COVERED_ANGLE);
                removeAvailablePosition(new int[]{xx+1,yy-1});

            }
            if (elem == (xx - 1) & y.get(index) == (yy - 1)) {
                editRemoveSymbolsList(topRightAngle.get(index));
                topRightAngle.set(index,COVERED_ANGLE);
                bottomLeftAngle.set(i, COVERED_ANGLE);
                removeAvailablePosition(new int[]{xx-1,yy-1});

            }
            index ++;

        }
    }


    /**
     *
     * @return list of AvailablePosition
     */
    public ArrayList<int[]> getAvailablePosition() {

        return availablePosition;
    }


    /**
     * @return SymbolsList
     */
    public int[] getSymbolsList(){
        return symbolsList;
    }


    /**
     * @return list of board cards
     */
    public List<Integer> getBoardCards() {
        return boardCards;
    }


    /**
     * calculates points of the placed card
     * @param card resource card and gold card to be analyzed
     * @return card points earned
     */
    private int calculatePoint(Card card, int i) {
        int x = 0;
        switch (card.getType()) {
            case RESOURCE:
                x = card.getPoint();
                removeSymbolsList(i);
                break;
            case GOLD:
                GoldCard goldCard = (GoldCard)card;
                switch (goldCard.getBasicPointCriterion()) {
                    case EMPTY:
                        x = card.getPoint();
                        removeSymbolsList(i);
                        break;
                    case COVERED_ANGLE:
                        removeSymbolsList(i);
                        if (topRightAngle.get(i)==COVERED_ANGLE) x += 2;
                        if (topLeftAngle.get(i)==COVERED_ANGLE) x += 2;
                        if (bottomRightAngle.get(i)==COVERED_ANGLE) x += 2;
                        if (bottomLeftAngle.get(i)==COVERED_ANGLE) x += 2;
                        break;
                    case FEATHER:
                        x = symbolsList[4] * card.getPoint();
                        removeSymbolsList( i);
                        break;
                    case PARCHMENT:
                        x = symbolsList[6] * card.getPoint();
                        removeSymbolsList( i);
                        break;
                    case INK_BOTTLE:
                        x = symbolsList[5] * card.getPoint();
                        removeSymbolsList(i);
                        break;
                }
                break;
        }
        return x;
    }


    /**
     * calculates goal card points
     * @param card objective card to be analyzed
     * @return card points earned
     */
    public int calculateGoalPoint(ObjectiveCard card) {
        int point = 0;
        try {
            switch (card.getType()) {
                case REDG:
                    point = goalCardPosition(FUNGI, FUNGI, FUNGI, 1, 1, 1,1);
                    break;
                case BLUEG:
                    point = goalCardPosition(ANIMAL, ANIMAL, ANIMAL, 1, 1, 1,1);
                    break;
                case VIOLAD:
                    point = goalCardPosition(INSECT, INSECT, INSECT, 1, -1, 1,-1);
                    break;
                case GREEND:
                    point = goalCardPosition(PLANT, PLANT, PLANT, 1, -1, 1,-1);
                    break;
                case GGV:
                    point = goalCardPosition(PLANT, PLANT, INSECT, 0, -2, -1,-1);
                    break;
                case RRG:
                    point = goalCardPosition(FUNGI, FUNGI, PLANT, 0, -2, 1,-1);
                    break;
                case VVB:
                    point = goalCardPosition(ANIMAL, INSECT, INSECT, 1, -1, 0,-2);
                    break;
                case BBR:
                    point = goalCardPosition(FUNGI,ANIMAL, ANIMAL, -1, -1, 0,-2);
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

        }catch (NumberFormatException e){
            return -1;
        }

        return point;
    }


    /**
     * add resources to the symbol list
     * @param symbol symbol to ADD from the symbol list
     */
    private void editAddSymbolsList(Symbol symbol,int[] position) {
        switch (symbol) {
            case EMPTY:
                break;
            case HIDDEN:
                removeAvailablePosition(position);
                forbiddenPosition.add(position);
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


    /**
     * remove resources to the symbol list
     * @param symbol to remove from the SymbolList
     */
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


    /**
     * calculates positional goal card points
     * @param symbol1 indicates the color of the first card
     * @param symbol2 indicates the color of the second card
     * @param symbol3 indicates the color of the third card
     * @param i1 represents the x position of the second card with respect to the first
     * @param i2 represents the y position of the second and the third card with respect to the first
     * @param i3 represents the x position of the third card with respect to the second
     * @return card points earned
     */
    private int goalCardPosition(Symbol symbol1, Symbol symbol2, Symbol symbol3 , int i1 , int i2, int i3,int i4){
        int point = 0 ;
        for (int elem : boardCards) {
            int index1 = boardCards.indexOf(elem);
            if (cardKingdom.get(index1) == symbol1) {
                int x1 = x.get(index1);
                int y1 = y.get(index1);
                int x2 = x1+i1;
                int y2 = y1 + i2;
                int index2 = 0;
                for (int xx : x) {
                    if (xx == x2 & y.get(index2)==y2) break;
                    index2++;
                }
                if (index2 == x.size()) index2=0;
                if ( cardKingdom.get(index2) == symbol2) {
                    int index3 = 0;
                    int x3 = x2 +i3;
                    int y3 = y2 +i4;
                    for (int xx : x) {
                        if (xx == x3 & y.get(index3)==y3) break;
                        index3++;
                    }
                    if (index3 == x.size()) index3=0;
                    if (cardKingdom.get(index3) ==symbol3) {
                        if (i2==-2||i4==-2) point += 3;
                        else point += 2;
                        cardKingdom.set(index1, EMPTY);
                        cardKingdom.set(index2, EMPTY);
                        cardKingdom.set(index3, EMPTY);
                    }
                }
            }
        }
        return point;
    }


    /**
     * add position to AvailablePosition list
     * @param xx refers to x position
     * @param yy refers to y position
     */
    private void addAvailablePosition(int xx ,int yy){
        availablePosition.add(new int[]{xx-1, yy+1});
        availablePosition.add(new int[]{xx+1, yy+1});
        availablePosition.add(new int[]{xx+1, yy-1});
        availablePosition.add(new int[]{xx-1, yy-1});
    }


    /**
     * remove forbidden position from available position list
     */
    private void updateAvailablePosition(){
        for (int[] elementToControlled : forbiddenPosition) {
            for (int j = 0; j < availablePosition.size(); j++) {
                int[] arrayAvailablePosition = availablePosition.get(j);
                if (Arrays.equals(elementToControlled, arrayAvailablePosition)) {
                    availablePosition.remove(j);
                    break;
                }
            }
        }
    }

    /**
     * remove position to AvailablePosition list
     * @param position to be removed
     */
    private void removeAvailablePosition(int[] position){
        for (int i = 0; i < availablePosition.size(); i++) {
            int[] array = availablePosition.get(i);
            if (Arrays.equals(array, position)) {
                availablePosition.remove(i);
                break;
            }
        }
    }

}