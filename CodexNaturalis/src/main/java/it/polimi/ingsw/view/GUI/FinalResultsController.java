package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.*;

public class FinalResultsController {

    @FXML
    private Label firstWinnGoalScore;

    @FXML
    private Label firstWinnNick;

    @FXML
    private Label firstWinnTotScore;

    @FXML
    private Label four;

    @FXML
    private Label fourthWinnNick;

    @FXML
    private Label fourthWinnTotScore;

    @FXML
    private Label fourthWinnGoalScore;

    @FXML
    private Label one;

    @FXML
    private Label secondWinnGoalScore;

    @FXML
    private Label secondWinnNick;

    @FXML
    private Label secondWinnTotScore;

    @FXML
    private Label thirdWinnGoalScore;

    @FXML
    private Label thirdWinnNick;

    @FXML
    private Label thirdWinnTotScore;

    @FXML
    private Label three;

    @FXML
    private Label two;

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

    @FXML
    void switchToStartFromFinalResult(ActionEvent event) {

    }
}
