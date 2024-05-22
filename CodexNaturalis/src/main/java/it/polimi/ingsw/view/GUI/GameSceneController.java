package it.polimi.ingsw.view.GUI;


import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.model.enums.Symbol;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;



import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    private Label nicknameFour;

    @FXML
    private Label nicknameOne;

    @FXML
    private Label nicknameTree;

    @FXML
    private Label nicknameTwo;

    @FXML
    private GridPane desk;

    @FXML
    private ImageView handCard1;

    @FXML
    private ImageView handCard2;

    @FXML
    private ImageView handCard3;

    @FXML
    private HBox handCards;


    @FXML
    private ImageView personalGoal;


    @FXML
    private Label firstScore;

    @FXML
    private Label fourthName;

    @FXML
    private Label fourthScore;

    @FXML
    private Label name1;

    @FXML
    private Label name2;

    @FXML
    private Label name3;

    @FXML
    private Label name4;

    @FXML
    private Label secondScore;

    @FXML
    private Label thirdScore;


    @FXML
    private Button resourceD;

    @FXML
    private Button resourceD1;

    @FXML
    private Button resourceD2;


    @FXML
    private Button goldD;

    @FXML
    private Button goldD1;

    @FXML
    private Button goldD2;

    @FXML
    private Button hand1;

    @FXML
    private Button hand2;

    @FXML
    private Button hand3;

    private boolean resourceD1Selected = false;

    private boolean resourceD2Selected = false;

    private boolean resourceDSelected = false;

    private boolean goldD1Selected = false;
    private boolean goldD2Selected = false;
    private boolean goldDSelected = false;

    private boolean handCard1Selected = false;
    private boolean handCard2Selected = false;
    private boolean handCard3Selected = false;

    LocationType loc = null;

    Integer id;
    private List<PlayerBoardController> playerBoardControllers;



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
                im = new Image(getClass().getResourceAsStream("/img/kingdom/RInsect.png"));
            } else if (symbol == Symbol.ANIMAL) {
                im = new Image(getClass().getResourceAsStream("/img/kingdom/RAnimal.png"));
            } else if (symbol == Symbol.FUNGI) {
                im = new Image(getClass().getResourceAsStream("/img/kingdom/GFungi.png"));
            }else if (symbol == Symbol.PLANT){
                im =new Image(getClass().getResourceAsStream("/img/kingdom/GPlant.png"));
            }
        }
        resourceDeck.setImage(im);
    }


    /**
     * set the first's card image on goldDeck
     * @param symbol it contains symbol of the card
     * @param type indicates type of the card
     */
    public void showGoldDeck(Symbol symbol, CardType type) {
        Image im = null;
        if ((goldDeck == null) && (symbol != null)) {
            if (type == CardType.GOLD) {
                if ((symbol == Symbol.INSECT)) {
                    im = new Image(getClass().getResourceAsStream("/img/kingdom/GInsect.png"));
                } else if (symbol == Symbol.ANIMAL) {
                    im = new Image(getClass().getResourceAsStream("/img/kingdom/GAnimal.png"));
                } else if (symbol == Symbol.FUNGI) {
                    im = new Image(getClass().getResourceAsStream("/img/kingdom/GFungi.png"));
                } else if (symbol == Symbol.PLANT) {
                    im = new Image(getClass().getResourceAsStream("/img/kingdom/GPlant.png"));
                }
            } else if (type == CardType.RESOURCE) {
                if ((symbol == Symbol.INSECT)) {
                    im = new Image(getClass().getResourceAsStream("/img/kingdom/GInsect.png"));
                } else if (symbol == Symbol.ANIMAL) {
                    im = new Image(getClass().getResourceAsStream("/img/kingdom/GAnimal.png"));
                } else if (symbol == Symbol.FUNGI) {
                    im = new Image(getClass().getResourceAsStream("/img/kingdom/GFungi.png"));
                } else if (symbol == Symbol.PLANT) {
                    im = new Image(getClass().getResourceAsStream("/img/kingdom/GPlant.png"));
                }
            }
            goldDeck.setImage(im);
        } else if ((goldDeck == null) && (symbol == null)) {
            goldDeck.setImage(null);
        }

    }


    /**
     * update two card image of displayed resource card (after used)
     */
    public void updateDisplayRCard(){
        if ((resourceCard1 == null) && (resourceCard2 == null) && (resourceDeck == null)) {
            resourceCard1.setImage(null);
            resourceCard1.setImage(null);
            resourceDeck.setImage(null);
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
    public void updateDisplayGCard() {
        if ((goldCard1 == null) && (goldCard2 == null) && (goldDeck == null)) {
            goldCard1.setImage(null);
            goldCard2.setImage(null);
            goldDeck.setImage(null);
        } else {
            if ((goldCard1 == null) && (goldCard2 != null) && (goldDeck != null)) {
                goldCard1.setImage(goldDeck.getImage());
            } else if ((goldCard2 == null) && (goldCard1 != null) && (goldDeck != null)){
                goldCard2.setImage(goldDeck.getImage());
            }
        }
    }


    /**
     * it shows the two first displayed resource card
     * @param displayRCard contains id of the fist two resource cards
     */
    public void showDisplayRCard(List<Integer> displayRCard){
        Image im1 = new Image(getClass().getResourceAsStream("/img/cards/front" + displayRCard.get(0) + ".png"));
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
     * Deactivates click on the Hand Cards
     */
    public void deactivateClickHandCards(){
        handCards.setOnMouseClicked(null);
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


    /**
     * it makes image picked bloom and get card url (from desk and hand card)
     */
    public void pickDeskCard() {
        String url = null;

       resourceD1.setOnMouseClicked(event -> {
           if(event.getClickCount()==2){
           resourceD1Selected = true;
           resourceD2Selected = false;
           resourceDSelected = false;
           goldD1Selected = false;
           goldD2Selected = false;
           goldDSelected = false;
       }});

       resourceD2.setOnMouseClicked(event->{
           if (event.getClickCount()==2){
           resourceD1Selected = false;
           resourceD2Selected = true;
           resourceDSelected = false;
           goldD1Selected = false;
           goldD2Selected = false;
           goldDSelected = false;
       }});

       resourceD.setOnMouseClicked(event->{
           if (event.getClickCount() == 2) {
               resourceD1Selected = false;
               resourceD2Selected = false;
               resourceDSelected = true;
               goldD1Selected = false;
               goldD2Selected = false;
               goldDSelected = false;
           }});

       goldD1.setOnMouseClicked(event ->{
           if (event.getClickCount()==2) {
               resourceD1Selected = true;
               resourceD2Selected = false;
               resourceDSelected = false;
               goldD1Selected = true;
               goldD2Selected = false;
               goldDSelected = false;
           }});

       goldD2.setOnMouseClicked(event ->{
           if (event.getClickCount()==2) {
               resourceD1Selected = false;
               resourceD2Selected = false;
               resourceDSelected = false;
               goldD1Selected = false;
               goldD2Selected = true;
               goldDSelected = false;
           }});

       goldD.setOnMouseClicked(event->{
           if (event.getClickCount()==2) {
               resourceD1Selected = false;
               resourceD2Selected = false;
               resourceDSelected = false;
               goldD1Selected = false;
               goldD2Selected = false;
               goldDSelected = true;
           }});

    }

    public void pickHandCard(){

        hand1.setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                handCard1Selected = true;
                handCard2Selected = false;
                handCard3Selected = false;
            }
        });

        hand2.setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                handCard1Selected = false;
                handCard2Selected = true;
                handCard3Selected = false;
            }
        });

        hand3.setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                handCard1Selected = false;
                handCard2Selected = false;
                handCard3Selected = true;
            }
        });
    }


    /**
     * it takes id of selected card
     * @param displayGCard it contains id of display gold card
     * @param displayRCard it contains id of display resource card
     */
    public void printId(List<Integer> displayGCard, List<Integer> displayRCard){
       if (resourceD1Selected || resourceD2Selected){
           loc = LocationType.DISPLAYED_RESOURCE_LIST;
           if ((resourceD1Selected)){
               id = displayRCard.get(0);
               resourceCard1.setImage(null);
           } else if (resourceD2Selected) {
               id = displayRCard.get(1);
               resourceCard2.setImage(null);
           }
       } else if (goldD1Selected || goldD2Selected) {
           loc = LocationType.DISPLAYED_GOLD_LIST;
           if (goldD1Selected){
               id = displayGCard.get(0);
               goldCard1.setImage(null);
           } else if (goldD2Selected) {
               id = displayGCard.get(1);
               goldCard2.setImage(null);
           }
       } else if (goldDSelected) {
           loc = LocationType.GOLD_CARD_DECK;
       } else if (resourceDSelected) {
           loc = LocationType.RESOURCE_CARD_DECK;
       }
    }


    /**
     * show personal goals in the pb
     * @param id is the id of the personal card chosed
     */
    public void showPersonalGoal(int id){
        Image im = new Image(getClass().getResourceAsStream("/img/cards/front/" + id + ".png"));
        personalGoal.setImage(im);
    }


    /**
     * it shows first tree hand cards
     * @param handCards contains id of hand cards
     */
    public void showHandCards(List<Integer> handCards){
        Image im1 = new Image(getClass().getResourceAsStream("/img/cards/front/" + handCards.get(0) + ".png"));
        Image im2 = new Image(getClass().getResourceAsStream("/img/cards/front/" + handCards.get(1) + ".png" ));
        Image im3 = new Image(getClass().getResourceAsStream("/img/cards/front/" + handCards.get(2) + ".png"));

        handCard1.setImage(im1);
        handCard2.setImage(im2);
        handCard3.setImage(im3);
    }


    /**
     * it updates hand cards
     * @param id is the card's id chosed from desk
     */
    public void updateHandCard(Integer id){
        Image im = new Image(getClass().getResourceAsStream("/img/cards/front/" + id + ".png"));
        if (handCard1 == null){
            handCard1.setImage(im);
        } else if (handCard2 == null) {
            handCard2.setImage(im);
        } else if (handCard3 == null) {
            handCard3.setImage(im);
        }
    }


    /**
     * it prints player color in the button
     * @param players is the player in the game
     */
    public void printPlayerColorImage(List<Player> players){

        int i=0;
        Color c = null;
        im_p1.setImage(null);
        im_p2.setImage(null);
        im_p3.setImage(null);
        im_p4.setImage(null);

        Image blue = new Image(getClass().getResourceAsStream("/img/utils/pion-BLUE.png"));
        Image red = new Image(getClass().getResourceAsStream("/img/utils/pion-RED.png"));
        Image yellow = new Image(getClass().getResourceAsStream("/img/utils/pion-YELLOW.png"));
        Image green = new Image(getClass().getResourceAsStream("/img/utils/pion-GREEN.png"));

        do {
            c = players.get(i).getPlayerColor();
            if (i==0){
                if (c == Color.GREEN){
                    im_p1.setImage(green);
                } else if (c == Color.BLUE) {
                    im_p1.setImage(blue);
                }else if (c == Color.RED){
                    im_p1.setImage(red);
                }else if (c == Color.YELLOW){
                    im_p1.setImage(yellow);
                }
            }
            if (i==1){
                if (c == Color.GREEN){
                    im_p2.setImage(green);
                } else if (c == Color.BLUE) {
                    im_p2.setImage(blue);
                }else if (c == Color.RED){
                    im_p2.setImage(red);
                }else if (c == Color.YELLOW){
                    im_p2.setImage(yellow);
                }
            }

            if (i==2){
                if (c == Color.GREEN){
                    im_p3.setImage(green);
                } else if (c == Color.BLUE) {
                    im_p3.setImage(blue);
                }else if (c == Color.RED){
                    im_p3.setImage(red);
                }else if (c == Color.YELLOW){
                    im_p3.setImage(yellow);
                }
            }

            if (i == 3){
                if (c == Color.GREEN){
                    im_p4.setImage(green);
                } else if (c == Color.BLUE) {
                    im_p4.setImage(green);
                }else if (c == Color.RED){
                    im_p4.setImage(green);
                }else if (c == Color.YELLOW){
                    im_p4.setImage(green);
                }
            }

            i++;
        }while (i<players.size());
    }


    /**
     * it shows first player board
     */
    public void choosePlayerBoard1() {
        playerBoard.setContent(playerBoardControllers.get(0));
    }


    /**
     * it shows second player boards
     */
    public void choosePlayerBoard2() {
        playerBoard.setContent(playerBoardControllers.get(1));
    }


    /**
     * it shows third player board
     */
    public void choosePlayerBoard3() {
        playerBoard.setContent(playerBoardControllers.get(2));
    }


    /**
     * it shows fourth player board
     */
    public void choosePlayerBoard4() {
        playerBoard.setContent(playerBoardControllers.get(3));
    }


    public void printTableName(List<Player> players){
        firstScore.setText(null);
        secondScore.setText(null);
        thirdScore.setText(null);
        fourthScore.setText(null);
        name1.setText(null);
        name2.setText(null);
        name3.setText(null);
        name4.setText(null);

        if (players.size() == 2){
            name1.setText(players.get(0).getNickname());
            name2.setText(players.get(1).getNickname());
        }
        if (players.size() == 3){
            name1.setText(players.get(0).getNickname());
            name2.setText(players.get(1).getNickname());
            name3.setText(players.get(2).getNickname());
        }
        if (players.size() == 4){
            name1.setText(players.get(0).getNickname());
            name2.setText(players.get(1).getNickname());
            name3.setText(players.get(2).getNickname());
            name4.setText(players.get(3).getNickname());
        }
    }

    public void updateScore(Player players){

        if (players.getNickname() == name1.getText()){
            firstScore.setText(String.valueOf(players.getPoint()));
        } else if (players.getNickname() == name2.getText()) {
            secondScore.setText(String.valueOf(players.getPoint()));
        } else if (players.getNickname() == name3.getText()) {
            thirdScore.setText(String.valueOf(players.getPoint()));
        } else if (players.getNickname() == name4.getText()) {
            fourthScore.setText(String.valueOf(players.getPoint()));
        }

    }


}
