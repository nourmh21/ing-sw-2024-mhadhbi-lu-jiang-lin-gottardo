package it.polimi.ingsw.view.gui.enums;

public enum PageType {
    CONNECTION("/pages/Connection.fxml"),
    LOGIN("/pages/Login.fxml"),
    REGISTRATION("/pages/Registration.fxml"),
    HOME("/pages/Home.fxml"),
    JOIN_MODE("/pages/JoinModeAsk.fxml"),
    LOBBY_CHOOSE("/pages/LobbyChoose.fxml"),
    LOBBY_CREATION("/pages/LobbyCreation.fxml"),
    LOBBY_2("/pages/Lobby2Players.fxml"),
    LOBBY_3("/pages/Lobby3Players.fxml"),
    LOBBY_4("/pages/Lobby4Players.fxml"),
    INIT_CARD_CHOOSE("/pages/InitialCardPlay.fxml"),
    PERSONAL_GOAL_CHOOSE("/pages/PersonalGoalChoose.fxml"),
    GAME("/pages/InGamePage.fxml"),
    BOARD("/pages/PlayerBoard.fxml"),
    ENDGAME("/pages/FinalResults.fxml");

    private final String value;

    PageType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
