package it.polimi.ingsw.view;

import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;

import java.util.List;

public interface UserInterface {
    public void loginSuccess();

    public void loginFailed(int type);   //type: 1 if wrong credentials, 2 if account already logged in

    public void registrationFailed();

    public void connectionSuccess();

    public void setLobbyList(List<Integer[]> lobbies);

    public void lobbyChooseFailed();

    public void askNumOfPlayer();

    public void setPossiblePersonalGoals(Integer[] possiblePersonalGoals);

    public void setLobbyStatus(ImmutableLobby lobby);

    public void setGameStatus(ImmutableGame game);

    public void setHandCards(List<Integer> handCards);

    public void setPlayerStatus(ImmutablePlayer player);

    public void playCardFailed();

    public void drawCardFailed();

    public void showFinalResult(ImmutableEndGameInfo info);

    public void gameInterrupted(String nickname);

    public void showServerOffline();

}
