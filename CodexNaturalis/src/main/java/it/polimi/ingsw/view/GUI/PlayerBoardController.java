package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerBoard;
import javafx.fxml.FXML;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    public void showPossiblePosition(ArrayList<Integer[]> availablePosition) {


    }


    /**
     * player choose position for the card
     */
    public void choosePosition(){

    }




}
