package it.polimi.ingsw.controller.client;

import it.polimi.ingsw.message.enums.LocationType;

public interface ClientAction {
    public void access(String nickname, String pwd, boolean isRegistered);
    public void reqLobbies();
    public void joinLobby(Integer idLobby);
    public void createLobby(int numOfPlayer);
    public void playInitCard(Integer idCard, boolean isBackSide);
    public void choosePersonalGoal(Integer idCard);
    public void playCard(Integer idCard, boolean isBackSide, int[] position);
    public void drawCard(LocationType location, Integer idCard);

}
