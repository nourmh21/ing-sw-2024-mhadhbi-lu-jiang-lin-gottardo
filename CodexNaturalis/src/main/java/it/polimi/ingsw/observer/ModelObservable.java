
package it.polimi.ingsw.observer;

import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;

import java.util.ArrayList;
import java.util.List;


/**
 * A model class can extends ModelObservable when it wants to be observable
 */
public abstract class ModelObservable {
    protected List<ModelObserver> observers = new ArrayList<>();

    public void addObserver(ModelObserver modelObserver){
        observers.add(modelObserver);
    }

    public void removeObserver(ModelObserver modelObserver){
        observers.remove(modelObserver);
    }


    /**
     * Notifies all observers of the lobby status
     * @param lobby {@link ImmutableLobby}
     */
    public void notify_lobby_status(ImmutableLobby lobby){
        for (ModelObserver o: observers) {
            o.send_lobby_status(lobby);
        }
    }


    /**
     * Notifies all observers of the game status
     * @param game {@link ImmutableGame}
     */
    public void notify_game_status(ImmutableGame game){
        for (ModelObserver o: observers) {
            o.send_game_status(game);
        }
    }


    /**
     * Notifies all observers of the final result
     * @param info {@link ImmutableEndGameInfo}
     */
    public void notify_final_result(ImmutableEndGameInfo info){
        for (ModelObserver o: observers) {
            o.send_end_game_info(info);
        }
    }


    /**
     * Notifies all observers of a player's status
     * @param player {@link Player}
     */
    public void notify_player_status(ImmutablePlayer player){
        for (ModelObserver o: observers) {
            o.send_player_status(player);
        }
    }


    /**
     * Notifies the specific observer of two objective card ids
     * @param player the target {@link Player}
     */
    public void notify_two_personal_goals(Player player){
        for (ModelObserver o: observers) {
            if (o.getNickname().equals(player.getNickname())){
                o.send_two_personal_goal(player.getInitialPossibleGoals());
                break;
            }
        }
    }


    /**
     * Notifies the specific observer of his/her hand cards
     * @param player the target {@link Player}
     */
    public void notify_hand_cards(Player player){
        for (ModelObserver o: observers) {
            if ((o.getNickname().equals(player.getNickname()))){
                o.send_player_handCard(player.getHandCards());
                break;
            }
        }
    }


    /**
     * Notifies all observers that a player has left the game
     * @param nickname nickname of player
     */
    public void notify_game_interrupted(String nickname){
        for (ModelObserver o: observers){
            if (!o.getNickname().equals(nickname))
                o.send_player_disconnected(nickname);
        }
    }


    /**
     * Notifies the specific observer of the chat message
     * @param message {@link ChatMessage}
     */
    public void notify_private_chat(ChatMessage message){
        for (ModelObserver o: observers){
            if (o.getNickname().equals(message.getRecipient())){
                o.send_chat(message);
                break;
            }
        }
    }


    /**
     * Notifies observers of a public chat message
     * @param message {@link ChatMessage}
     */
    public void notify_public_chat(ChatMessage message){
        for (ModelObserver o: observers){
            if (!o.getNickname().equals(message.getSender())){
                o.send_chat(message);
            }
        }
    }


    public List<ModelObserver> getObservers(){
        return observers;
    }


}
