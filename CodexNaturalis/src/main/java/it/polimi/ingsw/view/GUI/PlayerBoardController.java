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
    public void chooseSide(MouseEvent event) {

        Bloom bloom = new Bloom();
        if (handCard1.getOnMouseClicked().equals(MouseEvent.MOUSE_CLICKED)){
            if (event.getClickCount() == 2){
                //showBackSide
                //showPossiblePosition
            }else if (event.getClickCount() == 1){
                //showPossiblePosition();
            }
        }
        if (handCard2.getOnMouseClicked().equals(MouseEvent.MOUSE_CLICKED)){
            if (event.getClickCount()==2){
                //show backside
                //showPossiblePosition
            } else if (event.getClickCount() == 1) {
               // showPossiblePosition();
            }
        }
        if (handCard3.getOnMouseClicked().equals(MouseEvent.MOUSE_CLICKED)){
            if (event.getClickCount() == 2){
                //showbackside
                //showPossiblePosition
            } else if (event.getClickCount() == 1) {
                //showPossiblePosition();
            }
        }

    }

    /**
     * it shows back side of hand card selected
     */
    public void showBackSide(boolean back){
        if (back == true){

        }
    }

    /**
     * it shows the possible position of the hand card clicked
     */
    public void showPossiblePositions(ArrayList<Integer[]> availablePositions) {
        //cells are highlighted
        for (Integer[] availablePosition : availablePositions) {
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
        for (Integer[] availablePosition : availablePositions) {
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
