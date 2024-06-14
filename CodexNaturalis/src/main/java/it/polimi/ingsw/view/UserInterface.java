package it.polimi.ingsw.view;

import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;

import java.util.List;

/**
 * The UserInterface interface provides methods that determine what will be shown on the user screen
 */
public interface UserInterface {

    /**
     * Determines what will be shown in case of a successful login
     */
    public void loginSuccess();

    /**
     * Determines what will be shown in case of a failed login
     * @param type 1 if wrong credentials, 2 if account already logged in
     */
    public void loginFailed(int type);

    /**
     * Determines what will be shown in case of a failed registration
     */
    public void registrationFailed();

    /**
     * Determines what will be shown in case of a successful connection try
     */
    public void connectionSuccess();

    /**
     * Determines what will be shown in case of receiving a lobby list
     * @param lobbies list of lobbies
     */
    public void setLobbyList(List<Integer[]> lobbies);

    /**
     * Determines what will be shown in case of a failed lobby join
     */
    public void lobbyChooseFailed();

    /**
     * Determines what will be shown in case of receiving two personal objective(goal) cards
     * @param possiblePersonalGoals two objective card ids
     */
    public void setPossiblePersonalGoals(Integer[] possiblePersonalGoals);

    /**
     * Determines what will be shown in case of receiving a new lobby status
     * @param lobby {@link  ImmutableLobby}
     */
    public void setLobbyStatus(ImmutableLobby lobby);

    /**
     * Determines what will be shown in case of receiving a new game status
     * @param game {@link ImmutableGame}
     */
    public void setGameStatus(ImmutableGame game);

    /**
     * Determines what will be shown in case of receiving a new hand cards status
     * @param handCards list of card ids
     */
    public void setHandCards(List<Integer> handCards);

    /**
     * Determines what will be shown in case of receiving a new player status
     * @param player {@link ImmutablePlayer}
     */
    public void setPlayerStatus(ImmutablePlayer player);

    /**
     * Determines what will be shown in case of a failed play card action
     */
    public void playCardFailed();

    /**
     * Determines what will be shown in case of a failed draw card action
     */
    public void drawCardFailed();

    /**
     * Determines what will be shown in case of receiving a final results of a game
     * @param info {@link ImmutableEndGameInfo}
     */
    public void showFinalResult(ImmutableEndGameInfo info);

    /**
     * Determines what will be shown in case of receiving nickname of player who left the game
     * @param nickname nickname of player
     */
    public void gameInterrupted(String nickname);

    /**
     * Determines what will happen in case of receiving a chat message
     * @param message {@link ChatMessage}
     */
    public void addNewChatMessage(ChatMessage message);

    /**
     * Determines what will happen in case of connection lost
     */
    public void showConnectionError();

}
