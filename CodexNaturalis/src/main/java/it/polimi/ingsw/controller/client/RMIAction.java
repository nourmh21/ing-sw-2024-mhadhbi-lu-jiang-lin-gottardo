package it.polimi.ingsw.controller.client;

import it.polimi.ingsw.message.enums.LocationType;

//to be completed by Nourhane
public class RMIAction implements ClientAction{
    @Override
    public void access(String nickname, String pwd, boolean isRegistered) {

    }

    @Override
    public void joinGame() {

    }

    @Override
    public void newGame(int numOfPlayer) {

    }

    @Override
    public void playInitCard(Integer idCard, boolean isBackSide) {

    }

    @Override
    public void choosePersonalGoal(Integer idCard) {

    }

    @Override
    public void playCard(Integer idCard, boolean isBackSide, int[] position) {

    }

    @Override
    public void drawCard(LocationType location, Integer idCard) {

    }
}
