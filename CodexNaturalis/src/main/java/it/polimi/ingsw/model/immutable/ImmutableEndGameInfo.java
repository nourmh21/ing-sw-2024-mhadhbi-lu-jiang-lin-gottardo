package it.polimi.ingsw.model.immutable;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enums.GameState;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

public class ImmutableEndGameInfo implements Serializable {
    private final HashMap<String, int[]> finalResult;
    private final List<String> winners;
    public ImmutableEndGameInfo(Game game){
        finalResult = new HashMap<>();
        for (Player p: game.getPlayers()) {
            finalResult.put(p.getNickname(), new int[]{p.getPoint(), p.getGoalPoint()});
        }
        winners = game.getWinners().stream()
                .map(Player::getNickname)
                .toList();
    }

    public HashMap<String, int[]> getFinalResult() {
        return finalResult;
    }

    public List<String> getWinners() {
        return winners;
    }

}
