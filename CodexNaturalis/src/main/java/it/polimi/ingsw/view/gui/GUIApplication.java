package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.model.enums.GameState;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.view.UserInterface;
import it.polimi.ingsw.view.gui.controllers.*;
import it.polimi.ingsw.view.gui.enums.PageType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.view.gui.enums.PageType.*;

/**
 * The GUI class represents Graphical User Interface
 * It implements {@link UserInterface}
 */
public class GUIApplication extends Application implements UserInterface {

    Stage stage;
    For2PlayersLobby lobby2Controller;
    For3PlayersLobby lobby3Controller;
    For4PlayersLobby lobby4Controller;
    ForInGame inGameController;
    ForLobbyChoose lobbyChooseController;
    String myNickname;
    String tryNickname;
    Stage initCardAskStage;
    Stage goalAskStage;
    Stage joinModeStage;
    Stage endGameInfoStage;
    ImmutableLobby lobby;
    ImmutableGame game;
    List<ImmutablePlayer> players;
    List<Integer> handCards;
    Integer personalGoal;


    public void launch() {
        Application.launch();
    }


    @Override
    public void start(Stage stage) throws IOException {

        ((GUIApplication) ClientController.getInstance().getView()).setStage(stage);
        this.stage = stage;

        //incon
        Stage initStage = new Stage();
        Image image = new Image(getClass().getResourceAsStream("/img/utils/game_icon_square.png"));
        ImageView view = new ImageView(image);
        view.setFitHeight(440);
        view.setFitWidth(450);
        AnchorPane pane = new AnchorPane(view);
        initStage.setScene(new Scene(pane));
        initStage.centerOnScreen();
        initStage.setAlwaysOnTop(true);
        initStage.initStyle(StageStyle.UNDECORATED);
        initStage.show();

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                ae -> {
                    try {
                        initStage.close();
                        AnchorPane root = FXMLLoader.load(getClass().getResource(CONNECTION.getValue()));
                        Scene scene = new Scene(root);
                        setBackgroundImage(root);
                        stage.setMinWidth(1115);
                        stage.setMinHeight(785);
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/utils/game_icon_square.png")));
                        stage.setTitle("Codex Naturalis");
                        stage.setScene(scene);
                        stage.setOnCloseRequest(event -> {stop();});
                        stage.setResizable(false);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        ));
        timeline.setCycleCount(1);
        timeline.play();
    }


    @Override
    public void stop(){
        closeOpenedAskStage();
        Platform.exit();
        System.exit(0);
    }


    /**
     * Set background image for a root(page)
     * @param root
     */
    private void setBackgroundImage(AnchorPane root){
        Image background = new Image(getClass().getResourceAsStream("/img/utils/background.png"));
        BackgroundImage backgroundImage = new BackgroundImage(
                background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));
    }


    /**
     * Switch the page on the screen
     * @param type {@link PageType}
     */
    public void switchPage(PageType type){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource(type.getValue()));
                    AnchorPane root = loader.load();
                    setBackgroundImage(root);

                    //switch the root and not the scene
                    switch (type){
                        case HOME, CONNECTION:
                            stage.setMinWidth(1115);
                            stage.setMinHeight(785);
                            stage.setWidth(1115);
                            stage.setHeight(785);
                            stage.setMaximized(false);
                            stage.setResizable(false);
                            break;
                        case LOBBY_2:
                            lobby2Controller = loader.getController();
                            break;
                        case LOBBY_3:
                            lobby3Controller = loader.getController();
                            break;
                        case LOBBY_4:
                            lobby4Controller = loader.getController();
                            break;
                        case GAME:
                            stage.setResizable(true);
                            stage.setMaximized(true);
                            stage.setMinWidth(1300);
                            stage.setMinHeight(850);
                            inGameController = loader.getController();
                            ((GUIApplication) ClientController.getInstance().getView()).setInGameController(inGameController);
                            break;
                        case LOBBY_CHOOSE:
                            lobbyChooseController = loader.getController();
                            lobbyChooseController.setLobbyListStyle();
                            break;

                    }
                    stage.getScene().setRoot(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    @Override
    public void connectionSuccess() {
        ClientController.getInstance().setConnected();
        switchPage(PageType.LOGIN);
    }


    @Override
    public void loginSuccess() {
        switchPage(PageType.HOME);
        myNickname = tryNickname;
    }


    @Override
    public void loginFailed(int type) {
        if (type == 1){
            showGameInformation("Credentials wrong");
        }else{
            showGameInformation("Account already logged in, please try another one");
        }
    }


    @Override
    public void registrationFailed() {
        showGameInformation("Nickname already exists, please try another one");
    }

    @Override
    public void setLobbyList(List<Integer[]> lobbyList) {
        if (joinModeStage.isShowing()) {
            switchPage(LOBBY_CHOOSE);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    joinModeStage.close();
                }
            });
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lobbyChooseController.showLobbies(lobbyList);
            }
        });

    }

    @Override
    public void lobbyChooseFailed() {
        showGameInformation("selected room already full, probably someone/s joined before you");
        lobbyChooseController.reactivateMouseClick();
    }


    @Override
    public void setLobbyStatus(ImmutableLobby newLobbyStatus) {
        if (lobby == null) {
            switch (newLobbyStatus.getNumOfPlayer()) {
                case 2:
                    switchPage(PageType.LOBBY_2);
                    break;
                case 3:
                    switchPage(PageType.LOBBY_3);
                    break;
                case 4:
                    switchPage(PageType.LOBBY_4);
                    break;
            }
        }
        lobby = newLobbyStatus;
        updateLobby(newLobbyStatus.getNumOfPlayer(), newLobbyStatus.getPlayers());
    }


    /**
     * Update waiting room page
     * @param numOfPlayer number of player in lobby
     * @param players list of nicknames
     */
    public void updateLobby(int numOfPlayer, List<String> players){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (numOfPlayer == 2) {
                    lobby2Controller.updateStatus(players);
                }else if (numOfPlayer == 3){
                    lobby3Controller.updateStatus(players);
                }else if (numOfPlayer == 4){
                    lobby4Controller.updateStatus(players);
                }
            }
        });
    }


    @Override
    public void setPossiblePersonalGoals(Integer[] possibleGoals) {
        showAskStage(PERSONAL_GOAL_CHOOSE,null,possibleGoals,null);
    }


    @Override
    public void setGameStatus(ImmutableGame newStatus) {
        if (game == null){
            switchPage(PageType.GAME);
            game = newStatus;
            initDesk(newStatus);
        }else {
            ImmutableGame oldStatus = game;

            //check if the common goals cards are drawn
            if (oldStatus.getCommonGoals().isEmpty() && !newStatus.getCommonGoals().isEmpty()){
                inGameController.showCommonGoals(newStatus.getCommonGoals());
            }

            //check if the game is started
            if (oldStatus.getCurrentPlayer() == null && newStatus.getCurrentPlayer() != null){
                showGameInformation("GAME START\n" +
                        "Round begins with " + newStatus.getCurrentPlayer() + "\n" +
                        newStatus.getCurrentPlayer() + " is playing");
                inGameController.changeFirstStartPlayerIcon(newStatus.getCurrentPlayer());
                inGameController.initializeChat(getOtherPlayersNickname());
                checkMyTurn(newStatus.getCurrentPlayer());
            }

            //check if the turn is changed
            if (oldStatus.getCurrentPlayer() != null && !oldStatus.getCurrentPlayer().equals(newStatus.getCurrentPlayer())) {
                if (newStatus.isLastRound())
                    showGameInformation("LAST ROUND\n" + newStatus.getCurrentPlayer() + " is playing");
                else {
                    showGameInformation(newStatus.getCurrentPlayer() + " is playing");
                }
                checkMyTurn(newStatus.getCurrentPlayer());
            }


            //check if this client have really placed the card
            if(oldStatus.getGameState() == GameState.PLAY_CARD && newStatus.getCurrentPlayer().equals(myNickname) && newStatus.getGameState() == GameState.DRAW_CARD){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        inGameController.getMyBoardController().removeRegion();
                        inGameController.activateDeskClick();
                        showGameInformation("Now, please draw a card from desk");
                    }
                });
            }


            //check if desk has just changed
            if ((oldStatus.getGameState() == GameState.DRAW_CARD && newStatus.getGameState() == GameState.TURN_MANAGE) ||
                    (oldStatus.getGameState() == GameState.SETUP_PHASE_1 && newStatus.getGameState() == GameState.SETUP_PHASE_2)){
                if (checkIfChanged(oldStatus.getDisplayedRCards(),newStatus.getDisplayedRCards()))
                    inGameController.showDisplayRCard(newStatus.getDisplayedRCards());

                if (checkIfChanged(oldStatus.getDisplayedGCards(), newStatus.getDisplayedGCards()))
                    inGameController.showDisplayGCard(newStatus.getDisplayedGCards());

                if (oldStatus.getFirstRCardKingdom() != newStatus.getFirstRCardKingdom())
                    inGameController.showResourceDeck(newStatus.getFirstRCardKingdom());

                if (oldStatus.getFirstGCardKingdom() != newStatus.getFirstGCardKingdom())
                    inGameController.showGoldDeck(newStatus.getFirstGCardKingdom());
            }

            game = newStatus;
        }
    }

    /**
     * @return all players nickname except client itself
     */
    private List<String> getOtherPlayersNickname(){
        List<String> names = new ArrayList<>(players.stream()
                .parallel()
                .map(ImmutablePlayer::getNickname)
                .toList());
        names.remove(myNickname);
        return names;
    }


    /**
     * @param oldStatus old list of card ids
     * @param newStatus new list of card ids
     * @return true the list of card ids is changed or not, false otherwise
     */
    private boolean checkIfChanged(List<Integer> oldStatus, List<Integer> newStatus){
        if (oldStatus.size() != newStatus.size())
            return true;
        for (Integer oldId: oldStatus) {
            if (!newStatus.contains(oldId))
                return true;
        }
        return false;
    }


    /**
     * Checks if is client turn, in case of true, let client play
     * @param nickname nickname of the current turn player
     */
    private void checkMyTurn(String nickname){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (nickname.equals(myNickname))
                    inGameController.askPlayHandCard();
            }
        });

    }


    /**
     * Initializes desk
     * @param newStatus {@link ImmutableGame}
     */
    private void initDesk(ImmutableGame newStatus){
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                inGameController.showDisplayRCard(newStatus.getDisplayedRCards());
                inGameController.showDisplayGCard(newStatus.getDisplayedGCards());
                inGameController.showResourceDeck(newStatus.getFirstRCardKingdom());
                inGameController.showGoldDeck(newStatus.getFirstGCardKingdom());
                inGameController.deactivateDeskClick();
            }
        });
    }


    @Override
    public void setPlayerStatus(ImmutablePlayer player) {
        if (players == null){
            players = new ArrayList<>();
        }
        if (!checkPlayerExistence(player.getNickname())) {
            switch (players.size()){
                case 0:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            inGameController.initPlayer1(player);
                        }
                    });

                    break;
                case 1:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            inGameController.initPlayer2(player);
                        }
                    });
                    break;
                case 2:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            inGameController.initPlayer3(player);
                        }
                    });
                    break;
                case 3:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            inGameController.initPlayer4(player);
                        }
                    });
                    break;
            }
            players.add(player);
            if (player.getNickname().equals(myNickname))
                askInitCardPlace(player.getInitialCard());
        } else{
            update(player);
        }

    }


    /**
     * @param nickname nickname of player
     * @return true a player in contained in the players, false otherwise
     */
    private boolean checkPlayerExistence(String nickname){
        return (players.stream()
                .parallel()
                .anyMatch(player1 -> player1.getNickname().equals(nickname)));
    }


    /**
     * Update a player status
     * @param newStatus {@link ImmutablePlayer}, the new status
     */
    private void update(ImmutablePlayer newStatus) {
        ImmutablePlayer oldStatus = null;
        //find player who needs to be updated
        for (ImmutablePlayer player: players) {
            if (player.getNickname().equals(newStatus.getNickname())){
                oldStatus = player;
                break;
            }
        }

        //if that player's board has been changed, update
        if (checkPlayerBoardChange(oldStatus.getBoardCards(),newStatus.getBoardCards())) {

            if (newStatus.getNickname().equals(myNickname) && initCardAskStage.isShowing())
                closeAskStage(initCardAskStage);
            switch (players.indexOf(oldStatus)) {
                case 0:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            inGameController.getBoardPlayer1Controller().placeCard(newStatus.getBoardCards().getLast(), newStatus.getIsBackSide().getLast(),
                                    newStatus.getX().getLast(), newStatus.getY().getLast());
                        }
                    });

                    break;
                case 1:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            inGameController.getBoardPlayer2Controller().placeCard(newStatus.getBoardCards().getLast(), newStatus.getIsBackSide().getLast(),
                                    newStatus.getX().getLast(), newStatus.getY().getLast());
                        }
                    });

                    break;
                case 2:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            inGameController.getBoardPlayer3Controller().placeCard(newStatus.getBoardCards().getLast(), newStatus.getIsBackSide().getLast(),
                                    newStatus.getX().getLast(), newStatus.getY().getLast());
                        }
                    });
                    break;
                case 3:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            inGameController.getBoardPlayer4Controller().placeCard(newStatus.getBoardCards().getLast(), newStatus.getIsBackSide().getLast(),
                                    newStatus.getX().getLast(), newStatus.getY().getLast());
                        }
                    });
                    break;
            }
        }

        //check if this client personal goal choose is correctly accepted
        if (oldStatus.getNickname().equals(myNickname) && !oldStatus.isPersonalGoalChosen() && newStatus.isPersonalGoalChosen()) {
            inGameController.showPersonalGoal(personalGoal);
            closeAskStage(goalAskStage);
        }

        //check if others hand card change and show changes if this client are looking that one
        if (oldStatus.getHandCardKingdoms().size() != newStatus.getHandCardKingdoms().size() &&
        inGameController.getCurrent().equals(newStatus.getNickname()) && !newStatus.getNickname().equals(myNickname)){
            inGameController.showOthersHandCard(newStatus.getHandCardTypes(),newStatus.getHandCardKingdoms());
        }

        //check if that player's score is updated
        if (oldStatus.getPoint() != newStatus.getPoint()){
            inGameController.updateScore(players.indexOf(oldStatus), newStatus.getPoint());
        }

        players.set(players.indexOf(oldStatus), newStatus);

    }


    public void askInitCardPlace(Integer idCard){
        showAskStage(INIT_CARD_CHOOSE, idCard, null, null);
    }


    /**
     * @param lastStatus old list of board card ids
     * @param newStatus new list of board card ids
     * @return true if list of board card ids is changed, false otherwise
     */
    private boolean checkPlayerBoardChange(List<Integer> lastStatus, List<Integer> newStatus){
        return newStatus.size() > lastStatus.size();
    }


    @Override
    public void setHandCards(List<Integer> newStatus) {
        if (handCards == null) {
            handCards = newStatus;
            inGameController.showMineHandCards();
        }else {
            handCards = newStatus;
            if (inGameController.getCurrent().equals(myNickname)){
                inGameController.showMineHandCards();
            }
        }

    }


    @Override
    public void playCardFailed() {
        showGameInformation("Gold card's condition not fulfilled");
        inGameController.getMyBoardController().reactiveMouseClick();
    }


    @Override
    public void drawCardFailed() {
        showGameInformation("Please click a valid card on the desk");
        //refresh desk situation
        initDesk(game);
        //reactive click
        inGameController.activateDeskClick();
    }


    @Override
    public void showFinalResult(ImmutableEndGameInfo info) {
        showAskStage(ENDGAME,null,null, info);
    }

    @Override
    public void gameInterrupted(String nickname) {
        showGameInformation("Player " + nickname + " leave the game \n GAME INTERRUPTED");
        closeOpenedAskStage();
        removeAllLastGameInfo();
        switchPage(HOME);
    }

    @Override
    public void addNewChatMessage(ChatMessage message) {
        Platform.runLater(()->{
            inGameController.insertNewChat(message);
        });
    }

    @Override
    public void showConnectionError() {
        showGameInformation("CONNECTION ERROR\nPlease try to reconnect");
        tryNickname = null;
        myNickname = null;
        removeAllLastGameInfo();
        closeOpenedAskStage();
        switchPage(CONNECTION);
    }

    public void removeAllLastGameInfo(){
        lobby = null;
        personalGoal = null;
        handCards = null;
        players = null;
        game = null;
    }


    public void setInGameController(ForInGame controller){
        inGameController= controller;
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setTryNickname(String nickname){
        tryNickname = nickname;
    }


    public void setPersonalGoal(Integer personalGoal) {
        this.personalGoal = personalGoal;
    }


    public String getMyNickname() {
        return myNickname;
    }


    public List<ImmutablePlayer> getPlayers() {
        return players;
    }


    public ImmutableGame getGame() {
        return game;
    }


    public List<Integer> getHandCards() {
        return handCards;
    }


    public ForInGame getInGameController() {
        return inGameController;
    }

    /**
     * Shows on another stage a notice
     * @param information notice
     */
    public void showGameInformation(String information){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage infoStage = new Stage(StageStyle.TRANSPARENT);
                //AnchorPane root = new AnchorPane(new Label(information));
                Label label = new Label(information);
                label.setTextFill(Color.WHITE);
                label.setStyle("-fx-font-size:20; -fx-label-padding: 20; -fx-font-family:Verdana;" +
                        "-fx-background-color: transparent; -fx-text-alignment: center; -fx-background-radius: 10");

                Scene scene = new Scene(label);
                scene.setFill(Color.rgb(0, 0, 0, 0.6));

                //infoStage.centerOnScreen();
                infoStage.setAlwaysOnTop(true);
                infoStage.setScene(scene);
                infoStage.show();
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.seconds(2),
                        ae -> {
                            infoStage.close();
                        }
                ));
                timeline.setCycleCount(1);
                timeline.play();
            }
        });

    }


    /**
     * Shows a stage to ask client make choice
     * @param type {@link PageType}
     * @param idCard if of card
     * @param goals id of objective card
     * @param info {@link ImmutableEndGameInfo}
     */
    public void showAskStage(PageType type, Integer idCard, Integer[] goals, ImmutableEndGameInfo info){
        Platform.runLater(new Runnable() {
            double initX;
            double initY;
            @Override
            public void run() {
                try {
                    Stage newStage = new Stage(StageStyle.TRANSPARENT);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource(type.getValue()));
                    AnchorPane root = loader.load();
                    if (type == INIT_CARD_CHOOSE){
                        initCardAskStage = newStage;
                        ((ForInitialCard)loader.getController()).showInitialCards(idCard);
                    }else if (type == PERSONAL_GOAL_CHOOSE){
                        goalAskStage = newStage;
                        ((ForPersonalGoal)loader.getController()).showPersonalGoal(goals);

                    }else if (type == ENDGAME){

                        root.setOnMousePressed(event -> {
                            initX = event.getSceneX();
                            initY = event.getSceneY();
                            root.setCursor(Cursor.MOVE);
                        });

                        root.setOnMouseDragged(event -> {
                            newStage.setX(event.getScreenX() - initY);
                            newStage.setY(event.getScreenY() - initY);
                        });

                        endGameInfoStage = newStage;
                        root.setOnMouseReleased(event -> root.setCursor(Cursor.DEFAULT));
                        ((ForFinalResult)loader.getController()).fillFinalResult(info);
                    }else if (type == JOIN_MODE){
                        joinModeStage = newStage;
                    }

                    newStage.setAlwaysOnTop(true);
                    root.setStyle("-fx-background-color: transparent;");

                    Scene scene = new Scene(root);
                    scene.setFill(Color.rgb(0, 0, 0, 0.5));

                    newStage.centerOnScreen();
                    newStage.setResizable(false);
                    newStage.setScene(scene);
                    newStage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }


    private boolean checkIsShowingStage(Stage stage){
        return (stage != null && stage.isShowing());
    }


    private void closeAskStage(Stage stage){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage.close();
            }
        });
    }


    /**
     * Closes all currently showing ask stages
     */
    private void closeOpenedAskStage(){
        if (checkIsShowingStage(initCardAskStage))
            closeAskStage(initCardAskStage);
        if (checkIsShowingStage(goalAskStage))
            closeAskStage(goalAskStage);
        if (checkIsShowingStage(joinModeStage))
            closeAskStage(joinModeStage);
        if (checkIsShowingStage(endGameInfoStage))
            closeAskStage(endGameInfoStage);
    }

}
