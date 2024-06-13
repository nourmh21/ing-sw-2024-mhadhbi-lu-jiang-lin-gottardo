package it.polimi.ingsw.view.gui.controllers;


import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.model.enums.CardType;
import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.view.gui.GUIApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.List;


public class ForInGame {

    @FXML
    private ImageView im_p1;

    @FXML
    private ImageView im_p2;

    @FXML
    private ImageView im_p3;

    @FXML
    private ImageView im_p4;

    @FXML
    private Button playerOne;

    @FXML
    private Button playerTwo;

    @FXML
    private Button playerThree;

    @FXML
    private Button playerFour;

    @FXML
    private Label nicknameOne;

    @FXML
    private Label nicknameTwo;

    @FXML
    private Label nicknameThree;

    @FXML
    private Label nicknameFour;

    @FXML
    private ImageView personalGoal;

    @FXML
    private ScrollPane playerBoard;

    @FXML
    private HBox handCards;

    @FXML
    private ImageView handCard1;

    @FXML
    private ImageView handCard2;

    @FXML
    private ImageView handCard3;

    @FXML
    private Button hand1;

    @FXML
    private Button hand2;

    @FXML
    private Button hand3;

    @FXML
    private Label name1;

    @FXML
    private Label name2;

    @FXML
    private Label name3;

    @FXML
    private Label name4;

    @FXML
    private Label firstScore;

    @FXML
    private Label secondScore;

    @FXML
    private Label thirdScore;

    @FXML
    private Label fourthScore;

    @FXML
    private GridPane desk;

    @FXML
    private ImageView commonGoal1;

    @FXML
    private ImageView commonGoal2;

    @FXML
    private ImageView resourceCard1;

    @FXML
    private ImageView resourceCard2;

    @FXML
    private ImageView resourceDeck;

    @FXML
    private ImageView goldCard1;

    @FXML
    private ImageView goldCard2;

    @FXML
    private ImageView goldDeck;

    @FXML
    private Button rDeck;

    @FXML
    private Button resourceC1;

    @FXML
    private Button resourceC2;

    @FXML
    private Button gDeck;

    @FXML
    private Button goldC1;

    @FXML
    private Button goldC2;

    @FXML
    private TextArea chatBox;

    @FXML
    private ChoiceBox<String> chatOption;

    @FXML
    private TextField messageToSend;

    @FXML
    private Button sendButton;

    @FXML
    private Pane chatCover;

    private boolean resourceC1Selected = false;

    private boolean resourceC2Selected = false;

    private boolean resourceDeckSelected = false;

    private boolean goldC1Selected = false;
    private boolean goldC2Selected = false;
    private boolean goldDeckSelected = false;

    private boolean handCard1Selected = false;
    private boolean handCard2Selected = false;
    private boolean handCard3Selected = false;
    private boolean isBackSide = false;

    Image bluePlayerIcon = new Image(getClass().getResourceAsStream("/img/playerIcon/blue.png"));
    Image redPlayerIcon = new Image(getClass().getResourceAsStream("/img/playerIcon/red.png"));
    Image yellowPlayerIcon = new Image(getClass().getResourceAsStream("/img/playerIcon/yellow.png"));
    Image greenPlayerIcon = new Image(getClass().getResourceAsStream("/img/playerIcon/green.png"));
    Image blackPlayerICon = new Image(getClass().getResourceAsStream("/img/playerIcon/black.png"));

    AnchorPane boardPlayer1;
    AnchorPane boardPlayer2;
    AnchorPane boardPlayer3;
    AnchorPane boardPlayer4;

    ForPlayerBoard boardPlayer1Controller;
    ForPlayerBoard boardPlayer2Controller;
    ForPlayerBoard boardPlayer3Controller;
    ForPlayerBoard boardPlayer4Controller;
    ForPlayerBoard myBoardController;
    private String current = (((GUIApplication)ClientController.getInstance().getView()).getMyNickname());
    String buttonBorderStyle = "-fx-border-color: white; -fx-border-radius: 7; -fx-border-width: 2; -fx-padding: 0; -fx-background-color: transparent";
    String buttonFirstBorderStyle = "-fx-border-color: #2d2d2a; -fx-border-radius: 7; -fx-border-width: 2; -fx-padding: 0; -fx-background-color: transparent";


    /**
     * initializes all information about player 1
     * @param player is one of players
     */
    public void initPlayer1(ImmutablePlayer player) {
        im_p1.setImage(getImageByColor(player.getColor()));
        playerOne.setStyle(buttonBorderStyle);
        nicknameOne.setText(player.getNickname());
        name1.setText(player.getNickname());
        firstScore.setText(String.valueOf(player.getPoint()));
        initBoard(1);
        playerOne.setOnMouseClicked(event -> showPlayerBoard1());
        playerOne.setCursor(Cursor.HAND);
        if (checkNickname(player.getNickname())) {
            myBoardController = boardPlayer1Controller;
            playerBoard.setContent(boardPlayer1);
        }
    }


    /**
     * initializes all information about player 2
     * @param player is one of players
     */
    public void initPlayer2(ImmutablePlayer player) {
        im_p2.setImage(getImageByColor(player.getColor()));
        playerTwo.setStyle(buttonBorderStyle);
        nicknameTwo.setText(player.getNickname());
        name2.setText(player.getNickname());
        secondScore.setText(String.valueOf(player.getPoint()));
        initBoard(2);
        playerTwo.setOnMouseClicked(event -> showPlayerBoard2());
        playerTwo.setCursor(Cursor.HAND);
        if (checkNickname(player.getNickname())) {
            myBoardController = boardPlayer2Controller;
            playerBoard.setContent(boardPlayer2);
        }
    }


    /**
     * initializes all information about player 3
     * @param player is one of players
     */
    public void initPlayer3(ImmutablePlayer player) {
        im_p3.setImage(getImageByColor(player.getColor()));
        playerThree.setStyle(buttonBorderStyle);
        nicknameThree.setText(player.getNickname());
        name3.setText(player.getNickname());
        thirdScore.setText(String.valueOf(player.getPoint()));
        initBoard(3);
        playerThree.setOnMouseClicked(event -> showPlayerBoard3());
        playerThree.setCursor(Cursor.HAND);
        if (checkNickname(player.getNickname())) {
            myBoardController = boardPlayer3Controller;
            playerBoard.setContent(boardPlayer3);
        }
    }


    /**
     * initializes all information about player 4
     * @param player is one of players
     */
    public void initPlayer4(ImmutablePlayer player) {
        im_p4.setImage(getImageByColor(player.getColor()));
        playerFour.setStyle(buttonBorderStyle);
        nicknameFour.setText(player.getNickname());
        name4.setText(player.getNickname());
        fourthScore.setText(String.valueOf(player.getPoint()));
        initBoard(4);
        playerFour.setOnMouseClicked(event -> showPlayerBoard4());
        playerFour.setCursor(Cursor.HAND);
        if (checkNickname(player.getNickname())){
            myBoardController = boardPlayer4Controller;
            playerBoard.setContent(boardPlayer4);
        }

    }


    /**
     * sets the border of the player who starts playing first
     * @param nickname is the nickname of the first player
     */
    public void changeFirstStartPlayerIcon(String nickname){
        if (nicknameOne.getText().equals(nickname))
            playerOne.setStyle(buttonFirstBorderStyle);
        else if (nicknameTwo.getText().equals(nickname))
            playerTwo.setStyle(buttonFirstBorderStyle);
        else if (nicknameThree.getText() != null && nicknameThree.getText().equals(nickname))
            playerThree.setStyle(buttonFirstBorderStyle);
        else if (nicknameFour.getText() != null && nicknameFour.getText().equals(nickname))
            playerFour.setStyle(buttonFirstBorderStyle);
    }


    /**
     * sets player's icon by their color
     * @param color
     * @return
     */
    private Image getImageByColor(Color color) {
        switch (color) {
            case RED:
                return redPlayerIcon;
            case BLUE:
                return bluePlayerIcon;
            case YELLOW:
                return yellowPlayerIcon;
            case GREEN:
                return greenPlayerIcon;
            default:
                return (new Image(getClass().getResourceAsStream("/img/playerIcon/basic.png")));
        }
    }


    /**
     * initializes player's board
     * @param i indicate the position of player
     */
    private void initBoard(int i) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/PlayerBoard.fxml"));
            if (i == 1) {
                boardPlayer1 = loader.load();
                boardPlayer1Controller = (ForPlayerBoard) loader.getController();
            } else if (i == 2) {
                boardPlayer2 = loader.load();
                boardPlayer2Controller = (ForPlayerBoard) loader.getController();
            } else if (i == 3) {
                boardPlayer3 = loader.load();
                boardPlayer3Controller = (ForPlayerBoard) loader.getController();
            } else if (i == 4) {
                boardPlayer4 = loader.load();
                boardPlayer4Controller = (ForPlayerBoard) loader.getController();
            }
        } catch (IOException ignored) {}
    }


    private boolean checkNickname(String nickname1) {
        return nickname1.equals(((GUIApplication) (ClientController.getInstance().getView())).getMyNickname());
    }


    /**
     * shows board of the player 1
     */
    public void showPlayerBoard1() {
        playerBoard.setContent(boardPlayer1);
        if (!checkNickname(nicknameOne.getText())){
            current = nicknameOne.getText();
            showOthers(0);
        } else {
            showMineHandCards();
        }
    }


    /**
     * shows board of the player 2
     */
    public void showPlayerBoard2() {
        playerBoard.setContent(boardPlayer2);
        if (!checkNickname(nicknameTwo.getText())){
            current = nicknameTwo.getText();
            showOthers(1);
        } else {
            showMineHandCards();
        }
    }


    /**
     * shows board of player 3
     */
    public void showPlayerBoard3() {
        playerBoard.setContent(boardPlayer3);
        if (!checkNickname(nicknameThree.getText())) {
            current = nicknameThree.getText();
            showOthers(2);
        }else{
            showMineHandCards();
        }
    }


    /**
     * shows board of player 4
     */
    public void showPlayerBoard4() {
        playerBoard.setContent(boardPlayer4);
        if (!checkNickname(nicknameFour.getText())) {
            current = nicknameFour.getText();
            showOthers(3);
        }else {
            showMineHandCards();
        }
    }


    private void showOthers(int i){
        showOthersHandCard(((GUIApplication) (ClientController.getInstance().getView())).getPlayers().get(i).getHandCardTypes(),
                ((GUIApplication) (ClientController.getInstance().getView())).getPlayers().get(i).getHandCardKingdoms());
        deactivateHandCardsClick();
    }


    /**
     * shows hand cards this client
     */
    public void showMineHandCards(){
        current = ((GUIApplication) (ClientController.getInstance().getView())).getMyNickname();
        List<Integer> cards = ((GUIApplication) (ClientController.getInstance().getView())).getHandCards();
        if (cards != null){
            if(cards.size() == 3){
                updateHandCard1(cards.get(0));
                updateHandCard2(cards.get(1));
                updateHandCard3(cards.get(2));
            }else if (cards.size() == 2){
                updateHandCard1(cards.get(0));
                updateHandCard2(cards.get(1));
                updateHandCard3(null);
            }else if (cards.size() == 1){
                updateHandCard1(cards.get(0));
                updateHandCard2(null);
                updateHandCard3(null);
            }else if (cards.isEmpty()){
                updateHandCard1(null);
                updateHandCard2(null);
                updateHandCard3(null);
            }
            activateHandCardsClick();
        }
    }


    public String getCurrent(){
        return current;
    }


    public void showResourceDeck(Symbol symbol){
        resourceDeck.setImage(getRCardBackImage(symbol));
    }


    /**
     * gets the back image of resource card
     * @param symbol is the symbol of the card
     * @return image of the resource card
     */
    private Image getRCardBackImage(Symbol symbol){
        if (symbol != null)
            switch (symbol){
                case INSECT:
                    return new Image(getClass().getResourceAsStream("/img/cards/kingdom/RInsect.png"));
                case ANIMAL:
                    return new Image(getClass().getResourceAsStream("/img/cards/kingdom/RAnimal.png"));
                case FUNGI:
                    return new Image(getClass().getResourceAsStream("/img/cards/kingdom/RFungi.png"));
                case PLANT:
                    return new Image(getClass().getResourceAsStream("/img/cards/kingdom/RPlant.png"));
                default:
                    return null;
        }else {
            return null;
        }
    }


    public void showGoldDeck(Symbol symbol) {
        goldDeck.setImage(getGCardBackImage(symbol));
    }


    /**
     * gets the back image of gold card
     * @param symbol is the symbol of the card
     * @return image of the gold card
     */
    private Image getGCardBackImage(Symbol symbol){
        if (symbol != null){
            switch (symbol){
                case INSECT:
                    return new Image(getClass().getResourceAsStream("/img/cards/kingdom/GInsect.png"));
                case ANIMAL:
                    return new Image(getClass().getResourceAsStream("/img/cards/kingdom/GAnimal.png"));
                case FUNGI:
                    return new Image(getClass().getResourceAsStream("/img/cards/kingdom/GFungi.png"));
                case PLANT:
                    return new Image(getClass().getResourceAsStream("/img/cards/kingdom/GPlant.png"));
                default:
                    return null;
            }
        }else {
            return null;
        }
    }


    /**
     * shows resource cards displayed
     * @param displayRCard is a list of resource card displayed
     */
    public void showDisplayRCard(List<Integer> displayRCard){
        Image im1 = null;
        Image im2 = null;
        if (!displayRCard.isEmpty()){
            im1 = new Image(getClass().getResourceAsStream("/img/cards/front/" + displayRCard.get(0) + ".png"));
            if (displayRCard.size() == 2)
                im2 = new Image(getClass().getResourceAsStream("/img/cards/front/" + displayRCard.get(1) + ".png"));
        }
        resourceCard1.setImage(im1);
        resourceCard2.setImage(im2);
    }


    /**
     * shows gold cards displayed
     * @param displayGCard is a list of gold card displayed
     */
    public void showDisplayGCard(List<Integer> displayGCard){
        Image im1 = null;
        Image im2 = null;
        if (!displayGCard.isEmpty()){
            im1 = new Image(getClass().getResourceAsStream("/img/cards/front/" + displayGCard.get(0) + ".png"));
            if (displayGCard.size() == 2)
                im2 = new Image(getClass().getResourceAsStream("/img/cards/front/" + displayGCard.get(1)+ ".png"));;
        }
        goldCard1.setImage(im1);
        goldCard2.setImage(im2);
    }


    /**
     * shows personal goal card
     * @param id is the id of the personal card
     */
    public void showPersonalGoal(Integer id){
        Image im = new Image(getClass().getResourceAsStream("/img/cards/front/" + id + ".png"));
        personalGoal.setImage(im);
    }


    /**
     * shows common goals cards
     * @param goals is the list of common goals card's id
     */
    public void showCommonGoals(List<Integer> goals){
        Image im_goal1 = new Image(getClass().getResourceAsStream("/img/cards/front/" + goals.get(0) + ".png"));
        Image im_goal2 = new Image(getClass().getResourceAsStream("/img/cards/front/" + goals.get(1) + ".png"));
        commonGoal1.setImage(im_goal1);
        commonGoal2.setImage(im_goal2);
    }


    /**
     * updates the first card in the hand
     * @param idCard is the card's id
     */
    public void updateHandCard1(Integer idCard){
        Image im = null;
        if (idCard != null)
             im = new Image(getClass().getResourceAsStream("/img/cards/front/" + idCard + ".png"));
        handCard1.setImage(im);
    }


    /**
     * updates the second card in the hand
     * @param idCard is the card's id
     */
    public void updateHandCard2(Integer idCard){
        Image im = null;
        if (idCard != null)
            im = new Image(getClass().getResourceAsStream("/img/cards/front/" + idCard + ".png"));
        handCard2.setImage(im);
    }


    /**
     * updates the third card in the hand
     * @param idCard is the card's id
     */
    public void updateHandCard3(Integer idCard){
        Image im = null;
        if (idCard != null)
            im = new Image(getClass().getResourceAsStream("/img/cards/front/" + idCard + ".png"));
        handCard3.setImage(im);
    }


    /**
     * shows back side of the other players' hand card
     * @param cardTypes indicates type of the card
     * @param cardKingdoms indicates the kingdom of the card
     */
    public void showOthersHandCard( List<CardType> cardTypes, List<Symbol> cardKingdoms){
        Image im_1 = null;
        Image im_2 = null;
        Image im_3 = null;
        if (cardKingdoms != null){
            if (!cardKingdoms.isEmpty() && cardKingdoms.get(0)!= null)
                im_1 = getImageByCardType(cardTypes.get(0), cardKingdoms.get(0));
            if (cardKingdoms.size() >= 2 && cardKingdoms.get(1)!= null)
                im_2 = getImageByCardType(cardTypes.get(1), cardKingdoms.get(1));
            if (cardKingdoms.size() == 3 && cardKingdoms.get(2)!= null)
                im_3 =getImageByCardType(cardTypes.get(2), cardKingdoms.get(2));
        }
        handCard1.setImage(im_1);
        handCard2.setImage(im_2);
        handCard3.setImage(im_3);
    }


    /**
     * get back card image by card type and symbol
     * @param type indicates the type of card
     * @param symbol is the symbol of card
     * @return image
     */
    private Image getImageByCardType(CardType type, Symbol symbol){
        if (type == CardType.RESOURCE)
            return getRCardBackImage(symbol);
        else
            return getGCardBackImage(symbol);
    }


    public ForPlayerBoard getBoardPlayer1Controller(){
        return boardPlayer1Controller;
    }


    public ForPlayerBoard getBoardPlayer2Controller(){
        return boardPlayer2Controller;
    }


    public ForPlayerBoard getBoardPlayer3Controller(){
        return boardPlayer3Controller;
    }


    public ForPlayerBoard getBoardPlayer4Controller(){
        return boardPlayer4Controller;
    }


    /**
     * shows positions that can be placed in the client board
     */
    public void askPlayHandCard(){
        ImmutablePlayer me = null;
        for (ImmutablePlayer p: ((GUIApplication)(ClientController.getInstance().getView())).getPlayers()) {
            if (p.getNickname().equals(((GUIApplication)(ClientController.getInstance().getView())).getMyNickname())){
                myBoardController.showPossiblePositions(p.getPermissiblePosition());
                break;
            }

        }
    }


    /**
     * deactivates hand cards click
     */
    public void deactivateHandCardsClick(){
        hand1.setCursor(Cursor.DEFAULT);
        hand2.setCursor(Cursor.DEFAULT);
        hand3.setCursor(Cursor.DEFAULT);
        hand1.setOnMouseClicked(event -> {});
        hand2.setOnMouseClicked(event -> {});
        hand3.setOnMouseClicked(event -> {});
    }


    /**
     * activates hand cards click
     */
    public void activateHandCardsClick(){
        hand1.setCursor(Cursor.HAND);
        hand2.setCursor(Cursor.HAND);
        hand3.setCursor(Cursor.HAND);
        hand1.setOnMouseClicked(event -> {
            if (handCard1.getImage() != null){
                handCard1Selected = true;
                handCard2Selected = false;
                handCard3Selected = false;
                Integer id = ((GUIApplication)ClientController.getInstance().getView()).getHandCards().get(0);
                if(event.getClickCount() == 2){
                    isBackSide = true;
                    handCard1.setImage(new Image(getClass().getResourceAsStream("/img/cards/back/" + id + ".png")));
                }else {
                    isBackSide = false;
                    handCard1.setImage(new Image(getClass().getResourceAsStream("/img/cards/front/" + id + ".png")));
                }
            }else {
                hand1.setCursor(Cursor.DEFAULT);
            }

        });
        hand2.setOnMouseClicked(event -> {
            if (handCard2.getImage() != null){
                handCard1Selected = false;
                handCard2Selected = true;
                handCard3Selected = false;
                Integer id = ((GUIApplication)ClientController.getInstance().getView()).getHandCards().get(1);
                if(event.getClickCount()==2){
                    isBackSide = true;
                    handCard2.setImage(new Image(getClass().getResourceAsStream("/img/cards/back/" + id + ".png")));
                }else {
                    isBackSide = false;
                    handCard2.setImage(new Image(getClass().getResourceAsStream("/img/cards/front/" + id + ".png")));
                }
            }else {
                hand2.setCursor(Cursor.DEFAULT);
            }
        });
        hand3.setOnMouseClicked(event -> {
            if (handCard3.getImage() != null){
                handCard1Selected = false;
                handCard2Selected = false;
                handCard3Selected = true;
                Integer id = ((GUIApplication)ClientController.getInstance().getView()).getHandCards().get(2);
                if(event.getClickCount()==2){
                    isBackSide = true;
                    handCard3.setImage(new Image(getClass().getResourceAsStream("/img/cards/back/" + id + ".png")));
                }else {
                    isBackSide = false;
                    handCard3.setImage(new Image(getClass().getResourceAsStream("/img/cards/front/" + id + ".png")));
                }
            }else {
                hand3.setCursor(Cursor.DEFAULT);
            }
        });
    }


    public boolean isHandCard1Selected(){
        return handCard1Selected;
    }


    public boolean isHandCard2Selected(){
        return handCard2Selected;
    }


    public boolean isHandCard3Selected(){
        return handCard3Selected;
    }


    public boolean getIsBackSide(){
        return isBackSide;
    }


    /**
     * updates a player's score
     * @param i indicates the players
     * @param newPoint indicates the new point get by player
     */
    public void updateScore(int i, int newPoint){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (i){
                    case 0:
                        firstScore.setText(String.valueOf(newPoint));
                        break;
                    case 1:
                        secondScore.setText(String.valueOf(newPoint));
                        break;
                    case 2:
                        thirdScore.setText(String.valueOf(newPoint));
                        break;
                    case 3:
                        fourthScore.setText(String.valueOf(newPoint));
                        break;
                }
            }
        });

    }


    /**
     * activates desk click
     */
    public void activateDeskClick() {
        if(resourceCard1.getImage() != null){
            resourceC1.setCursor(Cursor.HAND);
            resourceC1.setOnMouseClicked(event -> {
                resourceC1Selected = true;
                resourceC2Selected = false;
                resourceDeckSelected = false;
                goldC1Selected = false;
                goldC2Selected = false;
                goldDeckSelected = false;
                if(event.getClickCount()==2){
                    Integer id = (((GUIApplication)ClientController.getInstance().getView()).getGame().getDisplayedRCards().get(0));
                    ClientController.getInstance().getClientAction().drawCard(LocationType.DISPLAYED_RESOURCE_LIST,id);
                    deactivateDeskClick();
                }});
        }

        if (resourceCard2.getImage() != null){
            resourceC2.setCursor(Cursor.HAND);
            resourceC2.setOnMouseClicked(event->{
                resourceC1Selected = false;
                resourceC2Selected = true;
                resourceDeckSelected = false;
                goldC1Selected = false;
                goldC2Selected = false;
                goldDeckSelected = false;
                if (event.getClickCount()==2){
                    Integer id = (((GUIApplication)ClientController.getInstance().getView()).getGame().getDisplayedRCards().get(1));
                    ClientController.getInstance().getClientAction().drawCard(LocationType.DISPLAYED_RESOURCE_LIST,id);
                    deactivateDeskClick();
                }});
        }

        if (resourceDeck.getImage() != null){
            rDeck.setCursor(Cursor.HAND);
            rDeck.setOnMouseClicked(event->{
                resourceC1Selected = false;
                resourceC2Selected = false;
                resourceDeckSelected = true;
                goldC1Selected = false;
                goldC2Selected = false;
                goldDeckSelected = false;
                if (event.getClickCount() == 2) {
                    ClientController.getInstance().getClientAction().drawCard(LocationType.RESOURCE_CARD_DECK,null);
                    deactivateDeskClick();
                }});
        }

        if (goldCard1.getImage() != null){
            goldC1.setCursor(Cursor.HAND);
            goldC1.setOnMouseClicked(event ->{
                resourceC1Selected = true;
                resourceC2Selected = false;
                resourceDeckSelected = false;
                goldC1Selected = true;
                goldC2Selected = false;
                goldDeckSelected = false;
                if (event.getClickCount()==2) {
                    Integer id = (((GUIApplication)ClientController.getInstance().getView()).getGame().getDisplayedGCards().get(0));
                    ClientController.getInstance().getClientAction().drawCard(LocationType.DISPLAYED_GOLD_LIST,id);
                    deactivateDeskClick();
                }});
        }

        if (goldCard2.getImage() != null){
            goldC2.setCursor(Cursor.HAND);
            goldC2.setOnMouseClicked(event ->{
                resourceC1Selected = false;
                resourceC2Selected = false;
                resourceDeckSelected = false;
                goldC1Selected = false;
                goldC2Selected = true;
                goldDeckSelected = false;
                if (event.getClickCount()==2) {
                    Integer id = (((GUIApplication)ClientController.getInstance().getView()).getGame().getDisplayedGCards().get(1));
                    ClientController.getInstance().getClientAction().drawCard(LocationType.DISPLAYED_GOLD_LIST,id);
                    deactivateDeskClick();
                }});
        }

        if (goldDeck.getImage() != null){
            gDeck.setCursor(Cursor.HAND);
            gDeck.setOnMouseClicked(event->{
                resourceC1Selected = false;
                resourceC2Selected = false;
                resourceDeckSelected = false;
                goldC1Selected = false;
                goldC2Selected = false;
                goldDeckSelected = true;
                if (event.getClickCount()==2) {
                    ClientController.getInstance().getClientAction().drawCard(LocationType.GOLD_CARD_DECK,null);
                    deactivateDeskClick();
                }});
        }
    }


    /**
     * deactivates desk click
     */
    public void deactivateDeskClick(){
        resourceC1Selected = false;
        resourceC2Selected = false;
        resourceDeckSelected = false;
        goldC1Selected = false;
        goldC2Selected = false;
        goldDeckSelected = false;
        resourceC1.setCursor(Cursor.DEFAULT);
        resourceC2.setCursor(Cursor.DEFAULT);
        rDeck.setCursor(Cursor.DEFAULT);
        goldC1.setCursor(Cursor.DEFAULT);
        goldC2.setCursor(Cursor.DEFAULT);
        gDeck.setCursor(Cursor.DEFAULT);
        resourceC1.setOnMouseClicked(event -> {});
        resourceC2.setOnMouseClicked(event -> {});
        rDeck.setOnMouseClicked(event -> {});
        goldC1.setOnMouseClicked(event -> {});
        goldC2.setOnMouseClicked(event -> {});
        gDeck.setOnMouseClicked(event -> {});
    }


    public void refreshHandClickState(){
        handCard1Selected = false;
        handCard2Selected = false;
        handCard3Selected = false;
    }


    public ForPlayerBoard getMyBoardController(){
        return myBoardController;
    }


    public void insertNewChat(ChatMessage message){
        if (message.isPublic())
            chatBox.setText(chatBox.getText() + "[public]  " + message.getSender() + ": " + message.getContent() + "\n");
        else
            chatBox.setText(chatBox.getText() + "[private] " + message.getSender() + ": " + message.getContent() + "\n");
        chatBox.requestFocus();
    }


    public void initializeChat(List<String> players){
        chatOption.getItems().add("All players");
        for (String name: players) {
            chatOption.getItems().add(name);
        }
        chatCover.setVisible(false);
        chatBox.setText("");
        sendButton.setOnMouseClicked(event -> {
            if (chatOption.getValue() != null && !messageToSend.getText().isEmpty()){
                ChatMessage message;
                if (chatOption.getValue().equals("All players"))
                     message = new ChatMessage((((GUIApplication)ClientController.getInstance().getView()).getMyNickname()),
                            messageToSend.getText());
                else
                    message = new ChatMessage((((GUIApplication)ClientController.getInstance().getView()).getMyNickname()),
                            chatOption.getValue(),
                            messageToSend.getText());
                ClientController.getInstance().getClientAction().chat(message);
                insertNewChat(message);
                messageToSend.setText("");
            }else {
                if (chatOption.getValue() == null)
                    chatOption.requestFocus();
                if (messageToSend.getText().isEmpty())
                    messageToSend.requestFocus();
            }
        });
    }

}