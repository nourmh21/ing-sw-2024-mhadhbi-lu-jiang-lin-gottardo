package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.controller.server.ImmutableLobby;
import it.polimi.ingsw.model.enums.GameState;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.view.UserInterface;
import it.polimi.ingsw.view.gui.controllers.*;
import it.polimi.ingsw.view.gui.enums.SceneType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
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

import static it.polimi.ingsw.view.gui.enums.SceneType.INIT_CARD_CHOOSE;
import static it.polimi.ingsw.view.gui.enums.SceneType.PERSONAL_GOAL_CHOOSE;

public class GUIApplication extends Application implements UserInterface {

    Stage stage;

    For2PlayersLobby lobby2Controller;
    For3PlayersLobby lobby3Controller;
    For4PlayersLobby lobby4Controller;

    ForInGame inGameController;


    public void launch() {
        Application.launch();
    }


    @Override
    public void start(Stage stage) throws IOException {

        ((GUIApplication) ClientController.getInstance().getView()).setStage(stage);
        this.stage = stage;

        Stage initStage = new Stage();

        Image image = new Image(getClass().getResourceAsStream("/img/utils/icon.png"));
        ImageView view = new ImageView(image);
        view.setFitHeight(500);
        view.setFitWidth(500);
        AnchorPane pane = new AnchorPane(view);
        initStage.setScene(new Scene(pane));
        initStage.centerOnScreen();
        initStage.initStyle(StageStyle.UNDECORATED);
        initStage.show();

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                ae -> {
                    try {
                        initStage.close();
                        AnchorPane root = FXMLLoader.load(getClass().getResource("/Connection.fxml"));
                        Scene scene = new Scene(root);
                        setBackgroundImage(root);
                        stage.setMinWidth(1115);
                        stage.setMinHeight(785);
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/utils/icon.png")));
                        stage.setTitle("Codex Naturalis");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        ));
        timeline.setCycleCount(1);
        timeline.play();

        //askInitCardPlace();

        /*
        AnchorPane root = FXMLLoader.load(getClass().getResource("/Connection.fxml"));
        Scene scene = new Scene(root);

        setBackgroundImage(root)

        stage.setMinWidth(1115);
        stage.setMinHeight(785);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/utils/icon.png")));
        stage.setTitle("Codex Naturalis");
        stage.setScene(scene);

        stage.show();
        */


    }


    private void setBackgroundImage(AnchorPane root){
        Image background = new Image(getClass().getResourceAsStream("/img/utils/background.png"));
        BackgroundImage backgroundImage = new BackgroundImage(
                background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));
    }


    public void switchScene(SceneType type) throws IOException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    //URL url = getClass().getResource(fileFXML);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(type.getValue()));
                    AnchorPane root = loader.load();

                    switch (type){
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
                            //stage.setMaximized(true);
                            inGameController = loader.getController();
                            ((GUIApplication) ClientController.getInstance().getView()).setInGameController(inGameController);
                            inGameController.setDeskBackground();
                            break;
                    }
                    setBackgroundImage(root);
                    Scene scene = new Scene(root);
                    stage.centerOnScreen();
                    stage.setScene(scene);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void setInGameController(ForInGame controller){
        inGameController= controller;
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



    public void setStage(Stage stage) {
        this.stage = stage;
    }



    public Stage getStage() {
        return stage;
    }

    public ForInGame getInGameController() {
        return inGameController;
    }

    @Override
    public void stop(){
        System.exit(0);
    }


    String myNickname;
    String tryNickname;
    Stage initCardAskStage;
    Stage goalAskStage;
    Optional<ImmutableLobby> lobby = Optional.empty();
    Optional<ImmutableGame> game = Optional.empty();
    Optional<List<ImmutablePlayer>> players = Optional.empty();
    Optional<List<Integer>> handCards = Optional.empty();

    Integer personalGoal;

    public List<ImmutablePlayer> getPlayers() {
        if (players.isPresent())
            return players.get();
        else
            return null;
    }

    public List<Integer> getHandCards() {
        if (handCards.isPresent())
            return handCards.get();
        else
            return null;
    }

    public void setPersonalGoal(Integer personalGoal) {
        this.personalGoal = personalGoal;
    }


    public void setTryNickname(String nickname){
        tryNickname = nickname;
    }

    public String getMyNickname() {
        return myNickname;
    }

    public ImmutableGame getGame() {
        return game.get();
    }

    // OK!
    @Override
    public void connectionSuccess() {
        try {
            switchScene(SceneType.LOGIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // OK!
    @Override
    public void loginSuccess() {
        try {
            switchScene(SceneType.HOME);
            myNickname = tryNickname;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 待做
    @Override
    public void loginFailed(int type) {

    }

    // 待做
    @Override
    public void registrationFailed() {

    }

    // OK!
    @Override
    public void askNumOfPlayer() {
        try {
            switchScene(SceneType.ASK_NUM_OF_PLAYER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // OK!
    @Override
    public void setLobbyStatus(ImmutableLobby newLobbyStatus) {
        try {
            if (lobby.isEmpty()) {
                switch (newLobbyStatus.getNumOfPlayer()) {
                    case 2:
                        switchScene(SceneType.LOBBY_2);
                        break;
                    case 3:
                        switchScene(SceneType.LOBBY_3);
                        break;
                    case 4:
                        switchScene(SceneType.LOBBY_4);
                        break;
                }
            }
            lobby = Optional.of(newLobbyStatus);
            updateLobby(newLobbyStatus.getNumOfPlayer(), newLobbyStatus.getPlayers());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // OK!
    @Override
    public void setPossiblePersonalGoals(Integer[] possibleGoals) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Stage newStage = new Stage(StageStyle.TRANSPARENT);
                    goalAskStage = newStage;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(PERSONAL_GOAL_CHOOSE.getValue()));
                    AnchorPane root = loader.load();

                    ((ForPersonalGoal)loader.getController()).showPersonalGoal(possibleGoals);;
                    root.setStyle("-fx-background-color: transparent;");

                    Scene scene = new Scene(root);
                    scene.setFill(Color.rgb(0, 0, 0, 0.5));

                    newStage.centerOnScreen();
                    newStage.setAlwaysOnTop(true);
                    newStage.setResizable(false);
                    newStage.setScene(scene);
                    newStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void personalGoalChooseFailed() {

    }


    //--
    @Override
    public void setGameStatus(ImmutableGame newStatus) {
        if (game.isEmpty()){
            try {
                switchScene(SceneType.GAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                        "Round begins with " +newStatus.getCurrentPlayer() + "\n" +
                        newStatus.getCurrentPlayer() + " is playing");
                checkMyTurn(newStatus.getCurrentPlayer());

            }

            //check if the turn is changed
            if (oldStatus.getCurrentPlayer() != null && !oldStatus.getCurrentPlayer().equals(newStatus.getCurrentPlayer())) {
                showGameInformation(newStatus.getCurrentPlayer() + " is playing");
                checkMyTurn(newStatus.getCurrentPlayer());
            }


            //check if I(this client)have really placed the card
            if(oldStatus.getGameState() == GameState.PLAY_CARD && newStatus.getCurrentPlayer().equals(myNickname) && newStatus.getGameState() == GameState.DRAW_CARD){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        inGameController.getMyBoardController().removeRegion();
                        inGameController.activateDeskClick();
                    }
                });
            }


            //check if desk has just changed
            if (oldStatus.getGameState() == GameState.DRAW_CARD && newStatus.getGameState() == GameState.TURN_MANAGE){
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

    public void showGameInformation(String information){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage infoStage = new Stage(StageStyle.TRANSPARENT);
                //AnchorPane root = new AnchorPane(new Label(information));
                Label label = new Label(information);
                label.setStyle("-fx-font-size:20; -fx-label-padding: 40; -fx-font-family:Verdana;" +
                        "-fx-background-color: transparent; -fx-text-alignment: center");

                Scene scene = new Scene(label);
                scene.setFill(Color.rgb(0, 0, 0, 0.1));

                //infoStage.centerOnScreen();
                infoStage.setAlwaysOnTop(true);
                infoStage.setScene(scene);
                infoStage.show();
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.seconds(4),
                        ae -> {
                            infoStage.close();
                        }
                ));
                timeline.setCycleCount(1);
                timeline.play();
            }
        });

    }




    // OK!
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

    // OK!
    private boolean checkPlayerExistence(String nickname){
        return (players.get().stream()
                .parallel()
                .anyMatch(player1 -> player1.getNickname().equals(nickname)));
    }


    // --
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
            inGameController.showPersonalGoal(personalGoal);
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

    // OK!
    private void closeInitCardAskStage(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initCardAskStage.close();
            }
        });
    }

    // OK!
    private void closePersonalGoalAskStage(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                goalAskStage.close();
            }
        });
    }

    // OK!
    public void askInitCardPlace(Integer idCard){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Stage newStage = new Stage(StageStyle.TRANSPARENT);
                    initCardAskStage = newStage;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(INIT_CARD_CHOOSE.getValue()));
                    AnchorPane root = loader.load();

                    ((ForInitialCard)loader.getController()).showInitialCards(idCard);;
                    root.setStyle("-fx-background-color: transparent;");

                    Scene scene = new Scene(root);
                    scene.setFill(Color.rgb(0, 0, 0, 0.5));

                    newStage.centerOnScreen();
                    newStage.setAlwaysOnTop(true);
                    newStage.setResizable(false);
                    newStage.setScene(scene);
                    newStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

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
            if (inGameController.getCurrent().equals(myNickname)){
                handCards = Optional.of(newStatus);
                inGameController.showMineHandCards();

                /*
                List<Integer> oldStatus = handCards.get();
                for (int i = 0; i < newStatus.size(); i ++) {
                    if (oldStatus.get(i) != newStatus.get(i))
                        updateHandCard(i, newStatus.get(i));
                }
                if (newStatus.size() <= 2){
                    updateHandCard(2,null);
                }
                if(newStatus.size() <= 1){
                    updateHandCard(1,null);
                }
                if (newStatus.isEmpty()){
                    updateHandCard(0,null);
                }
                handCards = Optional.of(newStatus);*/
            }

        }

    }


    private void updateHandCard(int i, Integer idCard){
        if (i == 0)
            inGameController.updateHandCard1(idCard);
        else if (i == 1)
            inGameController.updateHandCard2(idCard);
        else if (i == 2)
            inGameController.updateHandCard3(idCard);
    }



    @Override
    public void playCardFailed() {

    }

    @Override
    public void drawCardFailed() {

    }

    @Override
    public void showFinalResult(ImmutableEndGameInfo info) {

    }

    @Override
    public void showServerOffline() {

    }

    public List<int[]> getPossiblePosition(){
        List<int[]> positions = null;
        for (ImmutablePlayer p: players.get()) {
            if (p.getNickname().equals(myNickname)) {
                positions = p.getPermissiblePosition();
                break;
            }
        }
        return positions;
    }

}
