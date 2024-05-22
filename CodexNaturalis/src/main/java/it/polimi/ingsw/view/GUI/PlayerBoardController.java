package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerBoard;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerBoardController {
    @FXML
    private GridPane board;

    @FXML
    private ImageView handCard1;

    @FXML
    private ImageView handCard2;

    @FXML
    private ImageView handCard3;

    @FXML
    private HBox handCards;

    @FXML
    private ImageView initialCards;

    @FXML
    private AnchorPane pb;

    @FXML
    private ImageView personal_goal;

    @FXML
    private ColumnConstraints eleven;

    @FXML
    private RowConstraints zero;

    private int idChosenCard;
    private boolean backHandCard1 = false;
    private boolean backHandCard2 = false;
    private boolean backHandCard3 = false;

    /**
     * show personal goals in the pb
     * @param id is the id of the personal card chosed
     */
    public void showPersonalGoal(Integer id){
        Image im = new Image(getClass().getResourceAsStream("/img/cards/front/" + id + ".png"));
        personal_goal.setImage(im);
    }

    /**
     * show initial cards on player board
     * @param id it's id of the initial card
     * @param back rappresents the side of the card
     */
    public void showInitialcard(Integer id, boolean back){
        if (back == true){
            Image im = new Image(getClass().getResourceAsStream("/img/cards/back" + id + ".png")) ;
            initialCards.setImage(im);
        }else{
            Image im = new Image(getClass().getResourceAsStream("/img/cards/front" + id + ".png"));
            initialCards.setImage(im);
        }
    }

    /**
     * it gets PlayerBoard of player
     * @param p player of the game
     * @return player board
     */
    public PlayerBoard getPlayerBoard(Player p){
        return p.getBoard();
    }


    /**
     * Deactivates click of the hand cards
     */
    public void deactivateClicksHandCard(){
        handCards.setOnMouseClicked(null);
    }


    /**
     * player choose side of hand cards (one click front side and two click back side)
     * @param event mouse event
     */
    public void chooseSide(MouseEvent event, Player player, PlayerBoard playerBoard) {

        Bloom bloom = new Bloom();
        if (handCard1.getOnMouseClicked().equals(MouseEvent.MOUSE_CLICKED)){
            if (event.getClickCount() == 2){
                showBackSide(backHandCard1, player.getHandCards().get(0), handCard1);
                showPossiblePositions(playerBoard.getAvailablePosition());
                backHandCard1 = true;
            }else if (event.getClickCount() == 1){
                showPossiblePositions(playerBoard.getAvailablePosition());
            }
        }
        if (handCard2.getOnMouseClicked().equals(MouseEvent.MOUSE_CLICKED)){
            if (event.getClickCount()==2){
                showBackSide(backHandCard2, player.getHandCards().get(1), handCard2);
                showPossiblePositions(playerBoard.getAvailablePosition());
                backHandCard1 = true;
            } else if (event.getClickCount() == 1) {
                showPossiblePositions(playerBoard.getAvailablePosition());
            }
        }
        if (handCard3.getOnMouseClicked().equals(MouseEvent.MOUSE_CLICKED)){
            if (event.getClickCount() == 2){
                showBackSide(backHandCard3, player.getHandCards().get(2), handCard3);
                showPossiblePositions(playerBoard.getAvailablePosition());
                backHandCard3 = true;
            } else if (event.getClickCount() == 1) {
                showPossiblePositions(playerBoard.getAvailablePosition());
            }
        }

    }

    /**
     * it shows back side of hand card selected
     */
    public void showBackSide(boolean back, int cardId, ImageView image){
        image.setImage(null);
        if (!back){
            image.setImage(new Image(getClass().getResourceAsStream("/img/cards/back" + cardId + ".png")));
        } else {
            image.setImage(new Image(getClass().getResourceAsStream("/img/cards/front" + cardId + ".png")));
        }
    }

    /**
     * it shows the possible position of the hand card clicked
     */
    public void showPossiblePositions(ArrayList<int[]> availablePositions) {
        //cells are highlighted
        for (int[] availablePosition : availablePositions) {
            Region r = new Region();
            r.setStyle("-fx-background-color: #b0faac;");
            board.add(r, availablePosition[1], availablePosition[0]);
        }

        //timer to wait for cells to stop being highlighted
        try {
            int SECONDS_TO_SLEEP = 2;
            TimeUnit.SECONDS.sleep(SECONDS_TO_SLEEP);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        //cells' highlight is removed
        for (int[] availablePosition : availablePositions) {
            board.getChildren().removeIf( node -> GridPane.getColumnIndex(node).equals(availablePosition[1]) && GridPane.getRowIndex(node).equals(availablePosition[0]));
        }
    }



    /**
     * player chooses position for the card
     */
    private int[] choosePosition(MouseEvent e) {
        Node source = (Node)e.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        return new int[]{colIndex, rowIndex};
        //send info to the server(?)
    }

    private void placeCard(int xx, int yy, int cardID, boolean isBackSide){
        Image im;
        if(isBackSide){
            im = new Image(getClass().getResourceAsStream("/img/cards/back/" + cardID + ".png"));
        } else {
            im = new Image(getClass().getResourceAsStream("/img/cards/front/" + cardID + ".png"));
        }
        board.add(new ImageView(im), yy, xx);
        //send info to the server
    }
}
