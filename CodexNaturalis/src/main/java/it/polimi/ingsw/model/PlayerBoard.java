package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.exceptions.IllegalCoordinateInsertionException;
import it.polimi.ingsw.model.exceptions.InvalidIdCardException;

import static it.polimi.ingsw.model.enums.Symbol.*;

import java.util.*;


public class PlayerBoard {

    final private List<Integer> boardCards;
    final private List<Symbol> topLeftAngle;
    final private List<Symbol> topRightAngle;
    final private List<Symbol> bottomLeftAngle;
    final private List<Symbol> bottomRightAngle;
    final private List<Symbol> cardKingdom;
    final private List<Boolean> cardSide;


    final private List<Integer> x;
    final private List<Integer> y;
    final private int[] symbolList;
    private final List<int[]> availablePosition;
    final private ArrayList<int[]> forbiddenPosition;
    private boolean isInitCardPlaced;

    /**
     * cardList define list of card use on the board.
     * x coordinate for the position
     * y coordinate for the position
     * isCoveredTopLeftAngle whether the corner is covered or show the resource
     * isCoveredTopRightAngle whether the corner is covered or show the resource
     * isCoveredBottomLeftAngle whether the corner is covered or show the resource
     * isCoveredBottomRightAngle whether the corner is covered or show the resource
     * cardKingdom defines the color of the card
     * symbolList define the resources and objects visible in the PlayerBoard.
     * symbolList[0] indicates the number of ANIMAL,
     * symbolList[1] indicates the number of PLANT,
     * symbolList[2] indicates the number of FUNGI,
     * symbolList[3] indicates the number of INSECT,
     * symbolList[4] indicates the number of FEATHER,
     * symbolList[5] indicates the number of INK_BOTTLE,
     * symbolList[6] indicates the number of PARCHMENT,
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
        cardSide = new ArrayList<>();
        cardKingdom = new ArrayList<>();
        symbolList = new int[7];
        availablePosition = new ArrayList<>();
        forbiddenPosition = new ArrayList<>();
        isInitCardPlaced = false;
    }

    public List<Boolean> getCardSide() {
        return cardSide;
    }

    public boolean getIsInitCardPlaced() {
        return isInitCardPlaced;
    }

    /**
     * represents the placement of a card on the board
     *
     * @param card       to place
     * @param isBackSide if the card is used on the back side
     * @param xx         the x position of the card
     * @param yy         the y position of the card
     * @throws InvalidIdCardException              when id is not for resource or gold card
     * @throws IllegalCoordinateInsertionException if the coordinates are invalid, or represent a prohibited position
     */
    public int placeCard(Card card, boolean isBackSide, int xx, int yy) throws InvalidIdCardException {
        int idCard = card.getIdCard();
        if (idCard > 80 || idCard < 1) {
            throw new InvalidIdCardException();
        }
        boardCards.add(idCard);
        cardSide.add(isBackSide);
        removeAvailablePosition(new int[]{xx, yy});
        addAvailablePosition(xx, yy);
        cardKingdom.add(card.getKingdom());
        int index = boardCards.indexOf(idCard);

        x.add(xx);
        y.add(yy);
        addSymbolsList(card, isBackSide, xx, yy);
        updateAvailablePosition();
        if (isBackSide) {
            removeSymbolsList(index);
            return 0;
        } else return calculatePoint(card, index);
    }


    /**
     * represents the placement of an initial card on the board
     *
     * @param initialCard initial card id to place
     * @param isBackSide  if the card is used on the back side
     * @throws InvalidIdCardException when id is not for initial card
     */
    public int placeInitCard(InitialCard initialCard, boolean isBackSide) throws InvalidIdCardException {
        int idCard = initialCard.getIdCard();
        if (idCard > 102 || idCard < 97) {
            throw new InvalidIdCardException();
        }

        boardCards.add(idCard);
        cardKingdom.add(initialCard.getKingdom());
        cardSide.add(isBackSide);
        x.add(0);
        y.add(0);
        addAvailablePosition(0, 0);

        if (isBackSide) {
            symbolList[0] = initialCard.getCenterResource()[0];
            symbolList[1] = initialCard.getCenterResource()[1];
            symbolList[2] = initialCard.getCenterResource()[2];
            symbolList[3] = initialCard.getCenterResource()[3];
            topLeftAngle.add(initialCard.getTopLeftAngleFront());
            topRightAngle.add(initialCard.getTopRightAngleFront());
            bottomLeftAngle.add(initialCard.getBottomLeftAngleFront());
            bottomRightAngle.add(initialCard.getBottomRightAngleFront());
            if (initialCard.getBottomLeftAngleFront() == HIDDEN) {
                removeAvailablePosition(new int[]{-1, -1});
                removeAvailablePosition(new int[]{1, -1});
                forbiddenPosition.add(new int[]{-1, -1});
                forbiddenPosition.add(new int[]{1, -1});
            }

        } else {
            symbolList[0] = 1;
            symbolList[1] = 1;
            symbolList[2] = 1;
            symbolList[3] = 1;
            topLeftAngle.add(initialCard.getTopLeftAngle());
            topRightAngle.add(initialCard.getTopRightAngle());
            bottomLeftAngle.add(initialCard.getBottomLeftAngle());
            bottomRightAngle.add(initialCard.getBottomRightAngle());


        }
        symbolList[4] = 0;
        symbolList[5] = 0;
        symbolList[6] = 0;
        isInitCardPlaced = true;
        return 0;
    }


    public List<Integer> getY() {
        return y;
    }

    public List<Integer> getX() {
        return x;
    }


    /**
     * add resources to the symbol list
     *
     * @param card       card to be analyzed
     * @param isBackSide if the card is used on the back side
     */
    private void addSymbolsList(Card card, boolean isBackSide, int xx, int yy) {
        if (isBackSide) {
            editAddSymbolsList(card.getKingdom(), new int[]{0, 0});
            bottomLeftAngle.add(EMPTY);
            bottomRightAngle.add(EMPTY);
            topLeftAngle.add(EMPTY);
            topRightAngle.add(EMPTY);

        } else {
            editAddSymbolsList(card.getTopLeftAngle(), new int[]{xx - 1, yy + 1});
            editAddSymbolsList(card.getTopRightAngle(), new int[]{xx + 1, yy + 1});
            editAddSymbolsList(card.getBottomLeftAngle(), new int[]{xx - 1, yy - 1});
            editAddSymbolsList(card.getBottomRightAngle(), new int[]{xx + 1, yy - 1});

            bottomLeftAngle.add(card.getBottomLeftAngle());
            bottomRightAngle.add(card.getBottomRightAngle());
            topLeftAngle.add(card.getTopLeftAngle());
            topRightAngle.add(card.getTopRightAngle());
        }
    }


    /**
     * remove resources to the symbol list
     *
     * @param i card index in the card list array
     */
    private void removeSymbolsList(int i) {
        int xx = x.get(i);
        int yy = y.get(i);
        int index = 0;
        for (int elem : x) {
            //int index = x.indexOf(elem);
            if (elem == (xx - 1) & y.get(index) == (yy + 1)) {
                editRemoveSymbolList(bottomRightAngle.get(index));
                bottomRightAngle.set(index, COVERED_ANGLE);
                //also the card that I place even if the resource is not covered, I need it to calculate point
                topLeftAngle.set(i, COVERED_ANGLE);
                removeAvailablePosition(new int[]{xx - 1, yy + 1});

            }
            if (elem == (xx + 1) & y.get(index) == (yy + 1)) {
                editRemoveSymbolList(bottomLeftAngle.get(index));
                bottomLeftAngle.set(index, COVERED_ANGLE);
                topRightAngle.set(i, COVERED_ANGLE);
                removeAvailablePosition(new int[]{xx + 1, yy + 1});

            }
            if (elem == (xx + 1) & y.get(index) == (yy - 1)) {
                editRemoveSymbolList(topLeftAngle.get(index));
                topLeftAngle.set(index, COVERED_ANGLE);
                bottomRightAngle.set(i, COVERED_ANGLE);
                removeAvailablePosition(new int[]{xx + 1, yy - 1});

            }
            if (elem == (xx - 1) & y.get(index) == (yy - 1)) {
                editRemoveSymbolList(topRightAngle.get(index));
                topRightAngle.set(index, COVERED_ANGLE);
                bottomLeftAngle.set(i, COVERED_ANGLE);
                removeAvailablePosition(new int[]{xx - 1, yy - 1});

            }
            index++;

        }
    }


    /**
     * @return list of AvailablePosition
     */
    public List<int[]> getAvailablePosition() {
        return availablePosition;
    }


    /**
     * @return SymbolsList
     */
    public int[] getSymbolList() {
        return symbolList;
    }


    /**
     * @return list of board cards
     */
    public List<Integer> getBoardCards() {
        return boardCards;
    }


    /**
     * calculates points of the placed card
     *
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
                GoldCard goldCard = (GoldCard) card;
                switch (goldCard.getBasicPointCriterion()) {
                    case EMPTY:
                        x = card.getPoint();
                        removeSymbolsList(i);
                        break;
                    case COVERED_ANGLE:
                        removeSymbolsList(i);
                        if (topRightAngle.get(i) == COVERED_ANGLE) x += 2;
                        if (topLeftAngle.get(i) == COVERED_ANGLE) x += 2;
                        if (bottomRightAngle.get(i) == COVERED_ANGLE) x += 2;
                        if (bottomLeftAngle.get(i) == COVERED_ANGLE) x += 2;
                        break;
                    case FEATHER:
                        x = symbolList[4] * card.getPoint();
                        removeSymbolsList(i);
                        break;
                    case PARCHMENT:
                        x = symbolList[6] * card.getPoint();
                        removeSymbolsList(i);
                        break;
                    case INK_BOTTLE:
                        x = symbolList[5] * card.getPoint();
                        removeSymbolsList(i);
                        break;
                }
                break;
        }

        return x;
    }


    /**
     * calculates goal card points
     *
     * @param card objective card to be analyzed
     * @return card points earned
     */
    public int calculateGoalPoint(ObjectiveCard card) {
        int point = 0;
        try {
            switch (card.getType()) {
                case REDG:
                    point = goalCardPosition(FUNGI, FUNGI, FUNGI, 1, 1, 1, 1);
                    break;
                case BLUEG:
                    point = goalCardPosition(ANIMAL, ANIMAL, ANIMAL, 1, 1, 1, 1);
                    break;
                case VIOLAD:
                    point = goalCardPosition(INSECT, INSECT, INSECT, 1, -1, 1, -1);
                    break;
                case GREEND:
                    point = goalCardPosition(PLANT, PLANT, PLANT, 1, -1, 1, -1);
                    break;
                case GGV:
                    point = goalCardPosition(PLANT, PLANT, INSECT, 0, -2, -1, -1);
                    break;
                case RRG:
                    point = goalCardPosition(FUNGI, FUNGI, PLANT, 0, -2, 1, -1);
                    break;
                case VVB:
                    point = goalCardPosition(ANIMAL, INSECT, INSECT, 1, -1, 0, -2);
                    break;
                case BBR:
                    point = goalCardPosition(FUNGI, ANIMAL, ANIMAL, -1, -1, 0, -2);
                    break;
                case FFF:
                    point = 2 * (symbolList[2] / 3);
                    break;
                case AAA:
                    point = 2 * (symbolList[0] / 3);
                    break;
                case PPP:
                    point = 2 * (symbolList[1] / 3);
                    break;
                case III:
                    point = 2 * (symbolList[3] / 3);
                    break;
                case BFP:
                    int min = symbolList[4];
                    if (min > symbolList[5]) min = symbolList[5];
                    if (min > symbolList[6]) min = symbolList[6];
                    point = min * 3;
                    break;
                case BB:
                    point = 2 * (symbolList[5] / 2);
                    break;
                case FF:
                    point = 2 * (symbolList[4] / 2);
                    break;
                case PP:
                    point = 2 * (symbolList[6] / 2);
                    break;
            }

        } catch (NumberFormatException e) {
            return -1;
        }

        return point;
    }


    /**
     * add resources to the symbol list
     *
     * @param symbol symbol to ADD from the symbol list
     */
    private void editAddSymbolsList(Symbol symbol, int[] position) {
        switch (symbol) {
            case EMPTY:
                break;
            case HIDDEN:
                removeAvailablePosition(position);
                forbiddenPosition.add(position);
                break;
            case ANIMAL:
                symbolList[0] += 1;
                break;
            case PLANT:
                symbolList[1] += 1;
                break;
            case FUNGI:
                symbolList[2] += 1;
                break;
            case INSECT:
                symbolList[3] += 1;
                break;
            case FEATHER:
                symbolList[4] += 1;
                break;
            case INK_BOTTLE:
                symbolList[5] += 1;
                break;
            case PARCHMENT:
                symbolList[6] += 1;
                break;


        }

    }


    /**
     * remove resources to the symbol list
     *
     * @param symbol to remove from the SymbolList
     */
    private void editRemoveSymbolList(Symbol symbol) {
        switch (symbol) {
            case EMPTY:
                break;
            case ANIMAL:
                symbolList[0] -= 1;
                break;
            case PLANT:
                symbolList[1] -= 1;
                break;
            case FUNGI:
                symbolList[2] -= 1;
                break;
            case INSECT:
                symbolList[3] -= 1;
                break;
            case FEATHER:
                symbolList[4] -= 1;
                break;
            case INK_BOTTLE:
                symbolList[5] -= 1;
                break;
            case PARCHMENT:
                symbolList[6] -= 1;
                break;

        }
    }

    /**
     * calculates positional goal card points
     *
     * @param symbol1 indicates the color of the first card
     * @param symbol2 indicates the color of the second card
     * @param symbol3 indicates the color of the third card
     * @param i1      represents the x position of the second card with respect to the first
     * @param i2      represents the y position of the second and the third card with respect to the first
     * @param i3      represents the x position of the third card with respect to the second
     * @return card points earned
     */
    private int goalCardPosition(Symbol symbol1, Symbol symbol2, Symbol symbol3, int i1, int i2, int i3, int i4) {
        int point = 0;
        for (int elem : boardCards) {
            int index1 = boardCards.indexOf(elem);
            if (cardKingdom.get(index1) == symbol1) {
                int x1 = x.get(index1);
                int y1 = y.get(index1);
                int x2 = x1 + i1;
                int y2 = y1 + i2;
                int index2 = 0;
                for (int xx : x) {
                    if (xx == x2 & y.get(index2) == y2) break;
                    index2++;
                }
                if (index2 == x.size()) index2 = 0;
                if (cardKingdom.get(index2) == symbol2) {
                    int index3 = 0;
                    int x3 = x2 + i3;
                    int y3 = y2 + i4;
                    for (int xx : x) {
                        if (xx == x3 & y.get(index3) == y3) break;
                        index3++;
                    }
                    if (index3 == x.size()) index3 = 0;
                    if (cardKingdom.get(index3) == symbol3) {
                        if (i2 == -2 || i4 == -2) point += 3;
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
     *
     * @param xx refers to x position
     * @param yy refers to y position
     */
    private void addAvailablePosition(int xx, int yy) {
        boolean is1 = false;
        boolean is2 = false;
        boolean is3 = false;
        boolean is4 = false;
        for (int[] position : availablePosition) {
            if (Arrays.equals(position, new int[]{xx - 1, yy + 1})) is1 = true;
            if (Arrays.equals(position, new int[]{xx + 1, yy + 1})) is2 = true;
            if (Arrays.equals(position, new int[]{xx + 1, yy - 1})) is3 = true;
            if (Arrays.equals(position, new int[]{xx - 1, yy - 1})) is4 = true;
        }
        if (!is1) availablePosition.add(new int[]{xx - 1, yy + 1});
        if (!is2) availablePosition.add(new int[]{xx + 1, yy + 1});
        if (!is3) availablePosition.add(new int[]{xx + 1, yy - 1});
        if (!is4) availablePosition.add(new int[]{xx - 1, yy - 1});
    }


    /**
     * remove forbidden position from available position list
     */
    private void updateAvailablePosition() {
        for (int[] elementToControlled : forbiddenPosition) {
            for (int j = 0; j < availablePosition.size(); j++) {
                int[] arrayAvailablePosition = availablePosition.get(j);
                if (Arrays.equals(elementToControlled, arrayAvailablePosition)) {
                    availablePosition.remove(j);
                    j--;
                }
            }
        }
    }


    /**
     * remove position to AvailablePosition list
     *
     * @param position to be removed
     */
    private void removeAvailablePosition(int[] position) {
        for (int i = 0; i < availablePosition.size(); i++) {
            int[] array = availablePosition.get(i);
            if (Arrays.equals(array, position)) {
                availablePosition.remove(i);
                i--;
            }
        }
    }

    public boolean checkGoldCardCondition(GoldCard card) {
        return card.getCondition()[0] <= symbolList[0] & card.getCondition()[1] <= symbolList[1] & card.getCondition()[2] <= symbolList[2] & card.getCondition()[3] <= symbolList[3];
    }

}