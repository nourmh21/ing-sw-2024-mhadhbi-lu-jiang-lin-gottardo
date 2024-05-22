package it.polimi.ingsw.view.GUI.enums;

public enum SceneType {
    CONNECTION(""),
    LOGIN("/Login.fxml"),
    REGISTRATION("/Registration.fxml"),
    HOME("/Home.fxml"),
    ASK_NUM_OF_PLAYER("/ChoosePlayersNumber.fxml"),
    LOBBY_2("/Lobby2Players.fxml"),
    LOBBY_3("/Lobby3Players.fxml"),
    LOBBY_4("/Lobby4Players.fxml"),
    GAME(""),
    ENDGAME("");

    private final String value;

    SceneType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
