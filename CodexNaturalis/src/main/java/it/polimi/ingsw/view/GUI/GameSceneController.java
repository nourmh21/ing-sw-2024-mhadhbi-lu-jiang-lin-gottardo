package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Desk;
import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.model.enums.Symbol;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GameSceneController {

    @FXML
    private ScrollPane playerBoard;

    @FXML
    private ImageView commonGoal1;

    @FXML
    private ImageView commonGoal2;


    @FXML
    private ImageView goldCard1;

    @FXML
    private ImageView goldCard2;

    @FXML
    private ImageView goldDeck;

    @FXML
    private ImageView im_p1;

    @FXML
    private ImageView im_p2;

    @FXML
    private ImageView im_p3;

    @FXML
    private ImageView im_p4;

    @FXML
    private GridPane nineteenToTwentyseven;

    @FXML
    private Button playerFour;

    @FXML
    private Button playerOne;

    @FXML
    private Button playerTree;

    @FXML
    private Button playerTwo;

    @FXML
    private ImageView resourceCard1;

    @FXML
    private ImageView resourceCard2;

    @FXML
    private ImageView resourceDeck;

    @FXML
    private GridPane tewntyOneToTree;

    @FXML
    private GridPane treeToFifteen;

    @FXML
    private GridPane twentyAndTwentynine;

    @FXML
    private GridPane twentyfourToTwentysix;

    @FXML
    private GridPane zeroToTwo;

    @FXML
    private Text nicknameFour;

    @FXML
    private Text nicknameOne;

    @FXML
    private Text nicknameTree;

    @FXML
    private Text nicknameTwo;

    @FXML
    private GridPane desk;

    @FXML
    private PlayerBoardController playerBoardController;



    /**
     * it shows common goals of the game
     * @param goals contains id of two common goals for the game
     */
    public void showObjectiveCard(List<Integer> goals){
        Image im1 = new Image(getClass().getResourceAsStream("/img/cards/front/" + goals.get(0) + ".png"));
        Image im2 = new Image(getClass().getResourceAsStream("img/cards/front/" + goals.get(1) +".png"));

        commonGoal1.setImage(im1);
        commonGoal2.setImage(im2);
    }


    /**
     * set the first's card image of deck on resourceDeck
     * @param symbol it contains symbol of the card
     */
    public void showResourceDeck(Symbol symbol){
        Image im = null;
        if (resourceDeck == null) {
            if (symbol == Symbol.INSECT){
                im = new Image(getClass().getResourceAsStream("/img/kingdom/Insect.png"));
            } else if (symbol == Symbol.ANIMAL) {
                im = new Image(getClass().getResourceAsStream("/img/kingdom/Animal.png"));
            } else if (symbol == Symbol.FUNGI) {
                im = new Image(getClass().getResourceAsStream("/img/kingdom/Fungi.png"));
            }else if (symbol == Symbol.PLANT){
                im =new Image(getClass().getResourceAsStream("/img/kingdom/Plant.png"));
            }
        }
        resourceDeck.setImage(im);
    }


    /**
     * set the first's card image on goldDeck
     * @param symbol it contains symbol of the card
     */
    public void showGoldDeck(Symbol symbol) {
        Image im = null;
        if (goldDeck == null) {
            if (symbol == Symbol.INSECT){
                im = new Image(getClass().getResourceAsStream("/img/kingdom/Insect.png"));
            } else if (symbol == Symbol.ANIMAL) {
                im = new Image(getClass().getResourceAsStream("/img/kingdom/Animal.png"));
            } else if (symbol == Symbol.FUNGI) {
                im = new Image(getClass().getResourceAsStream("/img/kingdom/Fungi.png"));
            }else if (symbol == Symbol.PLANT){
                im =new Image(getClass().getResourceAsStream("/img/kingdom/Plant.png"));
            }
        }
        goldDeck.setImage(im);
    }


    /**
     * update two card image of displayed resource card (after used)
     */
    public void updateDisplayRCard(){
        if ((resourceCard1 == null) && (resourceCard2 == null)) {
            resourceCard1.setImage(resourceDeck.getImage());
            resourceCard1.setImage(resourceDeck.getImage());
        }else {
            if (resourceCard1 == null){
                resourceCard1.setImage(resourceDeck.getImage());
            }
            else if (resourceCard2 == null){
                resourceCard2.setImage(resourceDeck.getImage());
            }
        }

    }


    /**
     * update two card image of displayed gold card (after used)
     */
    public void updateDisplayGCard(){
        if ((goldCard1 == null) && (goldCard2 == null)){
            goldCard1.setImage(goldDeck.getImage());
            goldCard2.setImage(goldDeck.getImage());
        }else
            if (goldCard1 == null){
                goldCard1.setImage(goldDeck.getImage());
            }else if (goldCard2 == null){
                    goldCard2.setImage(goldDeck.getImage());
                }
    }


    /**
     * it shows the two first displayed resource card
     * @param displayRCard contains id of the fist two resource cards
     */
    public void showDisplayRCard(List<Integer> displayRCard){
        Image im1 = new Image(getClass().getResourceAsStream(displayRCard.get(0) + ".png"));
        Image im2 = new Image(getClass().getResourceAsStream(displayRCard.get(1) + ".png"));

        resourceCard1.setImage(im1);
        resourceCard2.setImage(im2);
    }


    /**
     * it shows the two first displayed gold card
     * @param displayGCard contains id of the first two gold card
     */
    public void showDisplayGCard(List<Integer> displayGCard){
        Image im1 = new Image(getClass().getResourceAsStream(displayGCard.get(0) + ".png"));
        Image im2 = new Image(getClass().getResourceAsStream(displayGCard.get(0)+ ".png"));

        goldCard1.setImage(im1);
        goldCard2.setImage(im2);
    }


    /**
     * Deactivates clicks on the desk
     */
    public void deactivateClicksDesk(){
        desk.setOnMouseClicked(null);
    }


    /**
     * It prints nickname of Player in the text
     * @param players is the list of player in the game
     */
    public void printNickname(List<Player> players){
        int size = players.size();
        if (size == 2){
            nicknameOne.setText(players.get(0).getNickname());
            nicknameTwo.setText(players.get(1).getNickname());
            nicknameTree.setText(null);
            nicknameFour.setText(null);
        }
        if (size == 3){
            nicknameOne.setText(players.get(0).getNickname());
            nicknameTwo.setText(players.get(1).getNickname());
            nicknameTree.setText(players.get(2).getNickname());
            nicknameFour.setText(null);
        }
        if (size == 4){
            nicknameOne.setText(players.get(0).getNickname());
            nicknameTwo.setText(players.get(1).getNickname());
            nicknameTree.setText(players.get(2).getNickname());
            nicknameFour.setText(players.get(3).getNickname());
        }

    }


    public void getPlayerBoard(){
        playerBoard.getScene();
    }


    /**
     * it makes image picked bloom
     * @param event mouse click event
     */
    public void cardPicked(MouseEvent event) {
        Bloom bloom = new Bloom();
        String url = null;

        if (event.getSource().equals(resourceCard1)){
            resourceCard1.setEffect(bloom);
            url =  resourceCard1.getImage().getUrl();
        }else if (event.getSource().equals(resourceCard2)){
            resourceCard2.setEffect(bloom);
            url = resourceCard2.getImage().getUrl();
        } else if (event.getSource().equals(resourceDeck)) {
            resourceDeck.setEffect(bloom);
            url = resourceDeck.getImage().getUrl();
        } else if (event.getSource().equals(goldCard1)) {
            goldCard1.setEffect(bloom);
            url = goldCard1.getImage().getUrl();
        } else if (event.getSource().equals(goldCard2)) {
            goldCard2.setEffect(bloom);
            url = goldCard2.getImage().getUrl();
        } else if (event.getSource().equals(goldDeck)) {
            goldDeck.setEffect(bloom);
            url = goldDeck.getImage().getUrl();
        }
    }


    /*public void InitializePlayerBoard() throws IOException {

        playerBoard.setContent();
    }*/


    /*public void printPlayerColor(List<Player> players){

        Color B = Color.BLUE;
        Color R = Color.RED;
        Color G = Color.GREEN;
        Color Y = Color.YELLOW;

        if (players.size() == 2){
            if (players.get(0).getPlayerColor() == B){
                Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                playerOne.setGraphic(new ImageView(im));
            } else {
                if (players.get(0).getPlayerColor() == R){
                    Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-RED.png"));
                    playerOne.setGraphic(new ImageView(im));
                }else {
                    if (players.get(0).getPlayerColor() == G){
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-GREEN.png"));
                        playerOne.setGraphic(new ImageView(im));
                    }else {
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-YELLOW.png"));
                        playerOne.setGraphic(new ImageView(im));
                    }
                }

            }

            if (players.get(1).getPlayerColor() == B){
                Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                playerTwo.setGraphic(new ImageView(im));
            } else {
                if (players.get(1).getPlayerColor() == R){
                    Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                    playerTwo.setGraphic(new ImageView(im));
                }else {
                    if (players.get(1).getPlayerColor() == G){
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                        playerTwo.setGraphic(new ImageView(im));
                    }else {
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                        playerTwo.setGraphic(new ImageView(im));
                    }
                }

            }
            playerTree.setGraphic(null);
            playerFour.setGraphic(null);

        }else if (players.size() == 3){

            if (players.get(0).getPlayerColor() == B){
                Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                playerOne.setGraphic(new ImageView(im));
            } else {
                if (players.get(0).getPlayerColor() == R){
                    Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-RED.png"));
                    playerOne.setGraphic(new ImageView(im));
                }else {
                    if (players.get(0).getPlayerColor() == G){
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-GREEN.png"));
                        playerOne.setGraphic(new ImageView(im));
                    }else {
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-YELLOW.png"));
                        playerOne.setGraphic(new ImageView(im));
                    }
                }

            }

            if (players.get(1).getPlayerColor() == B){
                Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                playerTwo.setGraphic(new ImageView(im));
            } else {
                if (players.get(1).getPlayerColor() == R){
                    Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                    playerTwo.setGraphic(new ImageView(im));
                }else {
                    if (players.get(1).getPlayerColor() == G){
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                        playerTwo.setGraphic(new ImageView(im));
                    }else {
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                        playerTwo.setGraphic(new ImageView(im));
                    }
                }

            }

            if (players.get(2).getPlayerColor() == B){
                Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                playerTree.setGraphic(new ImageView(im));
            } else {
                if (players.get(2).getPlayerColor() == R){
                    Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                    playerTree.setGraphic(new ImageView(im));
                }else {
                    if (players.get(2).getPlayerColor() == G){
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                        playerTree.setGraphic(new ImageView(im));
                    }else {
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                        playerTree.setGraphic(new ImageView(im));
                    }
                }

            }
            playerFour.setGraphic(null);



        }else {
            if (players.get(0).getPlayerColor() == B){
                Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                playerOne.setGraphic(new ImageView(im));
            } else {
                if (players.get(0).getPlayerColor() == R){
                    Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-RED.png"));
                    playerOne.setGraphic(new ImageView(im));
                }else {
                    if (players.get(0).getPlayerColor() == G){
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-GREEN.png"));
                        playerOne.setGraphic(new ImageView(im));
                    }else {
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-YELLOW.png"));
                        playerOne.setGraphic(new ImageView(im));
                    }
                }

            }

            if (players.get(1).getPlayerColor() == B){
                Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                playerTwo.setGraphic(new ImageView(im));
            } else {
                if (players.get(1).getPlayerColor() == R){
                    Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                    playerTwo.setGraphic(new ImageView(im));
                }else {
                    if (players.get(1).getPlayerColor() == G){
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                        playerTwo.setGraphic(new ImageView(im));
                    }else {
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                        playerTwo.setGraphic(new ImageView(im));
                    }
                }

            }

            if (players.get(2).getPlayerColor() == B){
                Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                playerTree.setGraphic(new ImageView(im));
            } else {
                if (players.get(2).getPlayerColor() == R){
                    Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                    playerTree.setGraphic(new ImageView(im));
                }else {
                    if (players.get(2).getPlayerColor() == G){
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                        playerTree.setGraphic(new ImageView(im));
                    }else {
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                        playerTree.setGraphic(new ImageView(im));
                    }
                }

            }

            if (players.get(3).getPlayerColor() == B){
                Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                playerFour.setGraphic(new ImageView(im));
            } else {
                if (players.get(3).getPlayerColor() == R){
                    Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                    playerFour.setGraphic(new ImageView(im));
                }else {
                    if (players.get(3).getPlayerColor() == G){
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                        playerFour.setGraphic(new ImageView(im));
                    }else {
                        Image im = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
                        playerFour.setGraphic(new ImageView(im));
                    }
                }

            }
        }

        }*/



}
