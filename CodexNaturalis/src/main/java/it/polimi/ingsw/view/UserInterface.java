package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;

import java.util.List;

public interface UserInterface {
    public void loginSuccess();

    public void loginFailed();
    public void registrationFailed();

    public void connectionSuccess();

    public void askNumOfPlayer();

    public void setPossiblePersonalGoals(List<Integer> possiblePersonalGoals);

    public void personalGoalChooseFailed();

    public void setLobbyStatus(ImmutableLobby lobby);

    public void setGameStatus(ImmutableGame game);

    public void setHandCards(List<Integer> handCards);

    public void setPlayerStatus(ImmutablePlayer player);

    public void playCardFailed();

    public void drawCardFailed();

    public void showFinalResult(ImmutableEndGameInfo info);



}
