package it.polimi.ingsw.controller.client;

import it.polimi.ingsw.message.enums.LocationType;

public interface ClientAction {
    public void access(String nickname, String pwd, boolean isRegistered);
    public void joinGame();
    public void newGame(int numOfPlayer);
    public void playInitCard(Integer idCard, boolean isBackSide);
    public void choosePersonalGoal(Integer idCard);
    public void playCard(Integer idCard, boolean isBackSide, int[] position);
    public void drawCard(LocationType location, Integer idCard);

}
