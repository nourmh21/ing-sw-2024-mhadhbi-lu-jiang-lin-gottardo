package it.polimi.ingsw.controller.client;

import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.general.ChatMessage;

//to be completed by Nourhane
public class RMIAction implements ClientAction{
    @Override
    public void access(String nickname, String pwd, boolean isRegistered) {

    }

    @Override
    public void reqLobbies() {

    }

    @Override
    public void joinLobby(Integer idLobby) {

    }

    @Override
    public void createLobby(int numOfPlayer) {

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

    @Override
    public void chat(ChatMessage message) {

    }

}
