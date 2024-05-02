package it.polimi.ingsw.modelView;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.Color;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * The {@code PlayerView} class represents the immutable version of the {@link it.polimi.ingsw.model.Player}.
 */

public class PlayerView implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String nickName;
    private final int position;
    private final int point;
    private final int goalPoint;
    private final Color playerColor;
    private final Integer personalGoal;
    private final boolean isConnected;
    private final List<Integer> handCards;

    public PlayerView(Player player){
        this.nickName = player.getNickName();
        this.position = player.getPosition();
        this.point = player.getPoint();
        this.goalPoint = player.getPoint();
        this.playerColor = player.getPlayerColor();
        this.personalGoal = player.getPersonalGoal();
        this.isConnected = player.isConnected();
        this.handCards = player.getHandCards();
    }

    public String getNickName() {
        return nickName;
    }

    public int getPosition() {
        return position;
    }

    public int getPoint() {
        return point;
    }

    public int getGoalPoint() {
        return goalPoint;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public Integer getPersonalGoal() {
        return personalGoal;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public List<Integer> getHandCards() {
        return handCards;
    }
}
