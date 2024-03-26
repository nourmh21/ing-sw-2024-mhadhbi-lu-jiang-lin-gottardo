package it.polimi.ingsw.model;
import it.polimi.ingsw.model.enums.Color;


public class Player{
    private String nickName;
    private int position;
    private int point;
    private Color playerColor;
    private GoalCard personalGoal;
    private boolean isConnected;

    public Player(String nickName){
        this.nickName = nickName;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    public int getPosition(){
        return position;
    }
    public void setPosition(int position){
        this.position = position;
    }

    public int getPoint(){
        return point;
    }
    public void setPoint(int point){
        this.point=point;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public GoalCard getPersonalGoal() {
        return personalGoal;
    }

    public boolean isConnected() {
        return isConnected;
    }
}
