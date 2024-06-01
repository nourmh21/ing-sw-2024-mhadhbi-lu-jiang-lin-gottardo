package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.model.enums.GameState;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.view.UserInterface;
import it.polimi.ingsw.view.gui.controllers.*;
import it.polimi.ingsw.view.gui.enums.SceneType;
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
import java.util.Optional;

import static it.polimi.ingsw.view.gui.enums.SceneType.*;

public class GUIApplication extends Application implements UserInterface {

    Stage stage;
    For2PlayersLobby lobby2Controller;
    For3PlayersLobby lobby3Controller;
    For4PlayersLobby lobby4Controller;
    ForInGame inGameController;
    String myNickname;
    String tryNickname;
    Stage initCardAskStage;
    Stage goalAskStage;
    Stage endGameInfoStage;
    Optional<ImmutableLobby> lobby = Optional.empty();
    Optional<ImmutableGame> game = Optional.empty();
    Optional<List<ImmutablePlayer>> players = Optional.empty();
    Optional<List<Integer>> handCards = Optional.empty();
    Optional<Integer> personalGoal = Optional.empty();


    public void launch() {
        Application.launch();
    }


    @Override
    public void start(Stage stage) throws IOException {

        ((GUIApplication) ClientController.getInstance().getView()).setStage(stage);
        this.stage = stage;

        //incon
        Stage initStage = new Stage();
        Image image = new Image(getClass().getResourceAsStream("/img/utils/icon.png"));
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
                        //stage.setMinWidth(1115);
                        //stage.setMinHeight(785);
                        stage.setResizable(false);
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/utils/icon.png")));
                        stage.setTitle("Codex Naturalis");
                        stage.setScene(scene);
                        stage.setOnCloseRequest(event -> {stop();});
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
        if (initCardAskStage != null)
            initCardAskStage.close();
        if (goalAskStage != null && goalAskStage.isShowing())
            goalAskStage.close();
        if (endGameInfoStage != null && endGameInfoStage.isShowing())
            endGameInfoStage.close();
        Platform.exit();
        System.exit(0);
    }


    private void setBackgroundImage(AnchorPane root){
        Image background = new Image(getClass().getResourceAsStream("/img/utils/background.png"));
        BackgroundImage backgroundImage = new BackgroundImage(
                background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));
    }


    public void switchPage(SceneType type){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource(type.getValue()));

                    AnchorPane root = loader.load();
                    setBackgroundImage(root);
                    //switch the root and not the scene
                    stage.getScene().setRoot(root);

                    switch (type){
                        case HOME, CONNECTION:
                            stage.setMaximized(false);
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
                            inGameController = loader.getController();
                            ((GUIApplication) ClientController.getInstance().getView()).setInGameController(inGameController);
                            inGameController.setDeskBackground();
                            stage.setMaximized(true);
                            stage.setResizable(false);
                            break;

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    @Override
    public void connectionSuccess() {
        switchPage(SceneType.LOGIN);
    }


    @Override
    public void loginSuccess() {
        switchPage(SceneType.HOME);
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
    }

    @Override
    public void lobbyChooseFailed() {
    }

    @Override
    public void askNumOfPlayer() {
        switchPage(SceneType.ASK_NUM_OF_PLAYER);
    }


    @Override
    public void setLobbyStatus(ImmutableLobby newLobbyStatus) {
        try {
            if (lobby.isEmpty()) {
                switch (newLobbyStatus.getNumOfPlayer()) {
                    case 2:
                        switchPage(SceneType.LOBBY_2);
                        break;
                    case 3:
                        switchPage(SceneType.LOBBY_3);
                        break;
                    case 4:
                        switchPage(SceneType.LOBBY_4);
                        break;
                }
            }
            lobby = Optional.of(newLobbyStatus);
            updateLobby(newLobbyStatus.getNumOfPlayer(), newLobbyStatus.getPlayers());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void updateLobby(int numOfPlayer, List<String> players) throws IOException {
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
        showUsefulStage(PERSONAL_GOAL_CHOOSE,null,possibleGoals,null);
    }


    @Override
    public void setGameStatus(ImmutableGame newStatus) {
        if (game.isEmpty()){
            switchPage(SceneType.GAME);
            game = Optional.of(newStatus);
            initDesk(newStatus);
        }else {
            ImmutableGame oldStatus = game.get();

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

            game = Optional.of(newStatus);
        }
    }


    private boolean checkIfChanged(List<Integer> oldStatus, List<Integer> newStatus){
        if (oldStatus.size() != newStatus.size())
            return true;
        for (Integer oldId: oldStatus) {
            if (!newStatus.contains(oldId))
                return true;
        }
        return false;
    }


    private void checkMyTurn(String nickname){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (nickname.equals(myNickname))
                    inGameController.askPlayHandCard();
            }
        });

    }


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
        if (players.isEmpty()){
            players = Optional.of(new ArrayList<>());
        }
        if (!checkPlayerExistence(player.getNickname())) {
            switch (players.get().size()){
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
            players.get().add(player);
            if (player.getNickname().equals(myNickname))
                askInitCardPlace(player.getInitialCard());
        } else{
            update(player);
        }

    }


    private boolean checkPlayerExistence(String nickname){
        return (players.get().stream()
                .parallel()
                .anyMatch(player1 -> player1.getNickname().equals(nickname)));
    }


    private void update(ImmutablePlayer newStatus) {
        ImmutablePlayer oldStatus = null;
        //find player who needs to be updated
        for (ImmutablePlayer player: players.get()) {
            if (player.getNickname().equals(newStatus.getNickname())){
                oldStatus = player;
                break;
            }
        }

        //if that player's board has been changed, update
        if (checkPlayerBoardChange(oldStatus.getBoardCards(),newStatus.getBoardCards())) {

            if (newStatus.getNickname().equals(myNickname) && initCardAskStage.isShowing())
                closeInitCardAskStage();
            switch (players.get().indexOf(oldStatus)) {
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
            inGameController.showPersonalGoal(personalGoal.get());
            closePersonalGoalAskStage();
        }

        //check if others hand card change and show changes if this client are looking that one
        if (oldStatus.getHandCardKingdoms().size() != newStatus.getHandCardKingdoms().size() &&
        inGameController.getCurrent().equals(newStatus.getNickname()) && !newStatus.getNickname().equals(myNickname)){
            inGameController.showOthersHandCard(newStatus.getHandCardTypes(),newStatus.getHandCardKingdoms());
        }

        //check if that player's score is updated
        if (oldStatus.getPoint() != newStatus.getPoint()){
            inGameController.updateScore(players.get().indexOf(oldStatus), newStatus.getPoint());
        }

        players.get().set(players.get().indexOf(oldStatus), newStatus);

    }


    public void askInitCardPlace(Integer idCard){
        showUsefulStage(INIT_CARD_CHOOSE, idCard, null, null);
    }


    private boolean checkPlayerBoardChange(List<Integer> lastStatus, List<Integer> newStatus){
        return newStatus.size() > lastStatus.size();
    }


    @Override
    public void setHandCards(List<Integer> newStatus) {
        if (handCards.isEmpty()) {
            handCards = Optional.of(newStatus);
            inGameController.showMineHandCards();
        }else {
            handCards = Optional.of(newStatus);
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
        initDesk(game.get());
        //reactive click
        inGameController.activateDeskClick();
    }


    @Override
    public void showFinalResult(ImmutableEndGameInfo info) {
        showUsefulStage(ENDGAME,null,null, info);
    }


    @Override
    public void showServerOffline() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showGameInformation("Server offline, please try to reconnect");
                tryNickname = null;
                myNickname = null;
                removeAllLastGameInfo();
                switchPage(CONNECTION);
            }
        });
    }


    public void removeAllLastGameInfo(){
        lobby = Optional.empty();
        personalGoal = Optional.empty();
        handCards = Optional.empty();
        players = Optional.empty();
        game = Optional.empty();
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
        this.personalGoal = Optional.of(personalGoal);
    }


    public String getMyNickname() {
        return myNickname;
    }


    public List<ImmutablePlayer> getPlayers() {
        return players.orElse(null);
    }


    public ImmutableGame getGame() {
        return game.get();
    }


    public List<Integer> getHandCards() {
        return handCards.orElse(null);
    }


    public ForInGame getInGameController() {
        return inGameController;
    }


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


    private void showUsefulStage(SceneType type, Integer idCard, Integer[] goals, ImmutableEndGameInfo info){
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


    private void closeInitCardAskStage(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initCardAskStage.close();
            }
        });
    }


    private void closePersonalGoalAskStage(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                goalAskStage.close();
            }
        });
    }

}
