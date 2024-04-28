package it.polimi.ingsw.modelView;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.enums.Symbol;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code PlayerBoardView} class represents the immutable version of the {@link it.polimi.ingsw.model.PlayerBoard}.
 * It provides a snapshot of the player's board in a serializable format.
 */
public class PlayerBoardView implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

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

    public PlayerBoardView(PlayerBoard playerBoard) {
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

    }

    public ArrayList<int[]> getAvailablePosition() {

        return availablePosition;
    }
    public int[] getSymbolsList(){
        return symbolsList;
    }
    public List<Integer> getBoardCards() {
        return boardCards;
    }





}
