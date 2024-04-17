package it.polimi.ingsw.model;
import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.exceptions.IllegalCoordinateInsertionException;
import it.polimi.ingsw.model.exceptions.InvalidIdCardException;
import it.polimi.ingsw.model.exceptions.InvalidNumOfHandCardsException;

import java.util.*;

import static it.polimi.ingsw.model.enums.Symbol.*;

//lista di coordinate permessi??
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
    private int[] symbolsList;

    /**
     * cardList define list of card use on the board.
     * handList define list of card in hnd of player.
     * x coordinate for the position
     * y coordinate for the position
     * isCoveredTopLeftAngle whether the corner is covered or not
     * isCoveredTopRightAngle whether the corner is covered or not
     * isCoveredBottomLeftAngle whether the corner is covered or not
     * isCoveredBottomRightAngle whether the corner is covered or not
     * cardKingdom defines the color of the card
     * symbolsList define the resources and objects visible in the PlayerBoard.
     * symbolsList[0] indicates the number of ANIMAL,
     * symbolsList[1] indicates the number of PLANT,
     * symbolsList[2] indicates the number of FUNGI,
     * symbolsList[3] indicates the number of INSECT,
     * symbolsList[4] indicates the number of FEATHER,
     * symbolsList[5] indicates the number of INK_BOTTLE,
     * symbolsList[6] indicates the number of PARCHMENT,
     */
    public PlayerBoard() {
        boardCards = new ArrayList<>();
        handCards = new ArrayList<>();
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

    /**
     * adding cards to hand
     * @param idCard card id to add
     * @throws InvalidNumOfHandCardsException I can only add cards if I had 2 in my hand
     */
    public void addcardtoHandcards(int idCard) throws InvalidNumOfHandCardsException {
        if (handCards.size()!=2) {
            throw new InvalidNumOfHandCardsException();
        }
        try {
            handCards.add(idCard);
        }catch (NumberFormatException e){}
    }
    /**
     * represents the placement of a card on the board
     * @param idCard card id to place
     * @param isBackSide if the card is used on the back side
     * @param xx the x position of the card
     * @param yy the y position of the card
     * @throws InvalidIdCardException when Id is not for resource or gold card
     * @throws IllegalCoordinateInsertionException if the coordinates are invalid, or represent a prohibited position
     */

    public int placeCard(int idCard,boolean isBackSide,int xx, int yy) throws InvalidIdCardException, IllegalCoordinateInsertionException {
        if (idCard>80||idCard<1) {throw new InvalidIdCardException();}
        if (){throw new IllegalCoordinateInsertionException();}
        try {
            Card card = server.getC(idCard);
            boardCards.add(idCard);
            handCards.remove(idCard);
            cardKingdom.add(card.getKingdom());
            int index = boardCards.indexOf(idCard);
            x.set(index,xx);
            y.set(index,yy);
            addSymbolsList(card,isBackSide);
            int point = calculatePoint((GoldCard) card, index);
            return point;
        }catch (NumberFormatException e){}
    }

    /**
     * represents the placement of an initial card on the board
     * @param idCard initial card id to place
     * @param isBackSide if the card is used on the back side
     *@throws InvalidIdCardException when id is not for initial card
     */

    public void placeInitCard(int idCard,boolean isBackSide)  throws InvalidIdCardException {
        if (idCard > 86 || idCard < 81) {
            throw new InvalidIdCardException();
        }
        try {
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
                symbolsList[0] = 1;
                symbolsList[1] = 1;
                symbolsList[2] = 1;
                symbolsList[3] = 1;
            } else {
                int[] t = new int[4];
                t = initialcard.getCenterResource();
                symbolsList[0] = t[0];
                symbolsList[1] = t[1];
                symbolsList[2] = t[2];
                symbolsList[3] = t[3];
            }
            symbolsList[4] = 0;
            symbolsList[5] = 0;
            symbolsList[6] = 0;
        }catch (NumberFormatException e){}
    }

    /**
     * add resources to the symbol list
     * @param card card to be analyzed
     * @param isBackSide if the card is used on the back side
     */

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

    /**
     * remove resources to the symbol list
     * @param card card to be analyzed
     * @param i card index in the card list array
     */
    private void removeSymbolsList(Card card, int i) {
        int xx = x.get(i);
        int yy = y.get(i);
        for (int elem : x) {
            int index = x.indexOf(elem);
            if (elem == (xx - 1) & this.y.get(index) == (yy + 1)) {
                Card elemCard = server.getC(index);
                editRemoveSymbolsList(elemCard.getBottomRightAngle());
                isCoveredBottomRightAngle.set(index, true);
                isCoveredTopLeftAngle.set(i, true);
                break;
            }
            if (elem == (xx + 1) & this.y.get(index) == (yy + 1)) {
                Card elemCard = server.getC(index);
                editRemoveSymbolsList(elemCard.getBottomLeftAngle());
                isCoveredBottomLeftAngle.set(index, true);
                isCoveredTopRightAngle.set(i, true);
                break;
            }
            if (elem == (xx + 1) & this.y.get(index) == (yy - 1)) {
                Card elemCard = server.getC(index);
                editRemoveSymbolsList(elemCard.getTopLeftAngle());
                isCoveredTopLeftAngle.set(index, true);
                isCoveredBottomRightAngle.set(i, true);
                break;
            }
            if (elem == (xx - 1) & this.y.get(index) == (yy - 1)) {
                Card elemCard = server.getC(index);
                editRemoveSymbolsList(elemCard.getTopRightAngle());
                isCoveredTopRightAngle.set(index, true);
                isCoveredBottomLeftAngle.set(i, true);
                break;
            }
        }
    }

    /**
     * @return list of board cards
     */
    public List<Integer> getBoardCards() {
        return boardCards;
    }
    /**
     * @return list of Hand cards
     */
    public List<Integer> getHandCards() {
        return handCards;
    }
    /**
     * calculates points of the placed card
     * @param card resource card and gold card to be analyzed
     * @return card points earned
     */
    private int calculatePoint(GoldCard card, int i) {
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
                    point = goalCardPosition(FUNGI, FUNGI, FUNGI, 1, 1, 1);
                    break;
                case BLUEG:
                    point = goalCardPosition(ANIMAL, ANIMAL, ANIMAL, 1, 1, 1);
                    break;
                case VIOLAD:
                    point = goalCardPosition(INSECT, INSECT, INSECT, 1, -1, 1);
                    break;
                case GREEND:
                    point = goalCardPosition(PLANT, PLANT, PLANT, 1, -1, 1);
                    break;
                case GGV:
                    point = goalCardPosition(PLANT, PLANT, INSECT, 0, -1, -1);
                    break;
                case RRG:
                    point = goalCardPosition(FUNGI, FUNGI, PLANT, 0, -1, 1);
                    break;
                case VVB:
                    point = goalCardPosition(INSECT, INSECT, INSECT, 1, -1, 0);
                    break;
                case BBR:
                    point = goalCardPosition(ANIMAL, ANIMAL, FUNGI, -1, -1, 0);
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

        }catch (NumberFormatException e){}

        return point; //domanda guisto cosi??
    }

    /**
     * add resources to the symbol list
     * @param symbol symbol to ADD from the symbol list
     */
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
     * @param i represents the x position of the second card with respect to the first
     * @param ii represents the y position of the second and the third card with respect to the first
     * @param iii represents the x position of the third card with respect to the second
     * @return card points earned
     */
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