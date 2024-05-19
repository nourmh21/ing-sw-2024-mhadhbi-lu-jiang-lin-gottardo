package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.model.Player;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.*;

public class FinalResultsController {
    @FXML
    private VBox winnersNames;
    @FXML
    private VBox losersNames;

    public void displayPlayers(List<Player> winners, List<Player> players){
        Text[] winnersText = new Text[winners.size()];
        ArrayList<String> playersInOrder = getPlayersInOrder(players);
        for (int i = 0; i < winners.size(); i++) {
            winnersText[i].setText(winners.get(i).getNickname());
            playersInOrder.remove(i);
        }
        for(int i=0; i<winners.size(); i++){
            winnersNames.getChildren().addAll(winnersText);
        }
        for(String losers : playersInOrder){
            losersNames.getChildren().add(new Text(losers));
        }
    }

    public ArrayList<String> getPlayersInOrder(List<Player> playersInGame){
        ArrayList<String> playersInOrder;
        HashMap<Integer, String> playersMap = new HashMap<Integer, String>();
        for(Player p : playersInGame){
            int totalPoints = p.getPoint() + p.getGoalPoint();
            playersMap.put(totalPoints, p.getNickname());
        }
        TreeMap<Integer, String> playersSorted = new TreeMap<>(playersMap);

        playersInOrder = new ArrayList<>(playersSorted.values());

        return playersInOrder;
    }
}
