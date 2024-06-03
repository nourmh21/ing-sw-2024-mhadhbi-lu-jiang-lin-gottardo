package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.view.gui.GUIApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.List;

public class ForPlayerBoard extends Node{
    @FXML
    private GridPane board;

    List<int[]> lastVersion;
    int[] lastChoice;

    HashMap<Region,int[]> relations = new HashMap<>();


    /**
     * it places the card
     * @param id is id of the card
     * @param isBackSide indicates if player chose the back side
     * @param x coordinate for place the card
     * @param y coorfinate for player the card
     */
    public void placeCard(Integer id, Boolean isBackSide, Integer x, Integer y){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Image im;
                if(isBackSide){
                    im = new Image(getClass().getResourceAsStream("/img/cards/back/" + id + ".png"));
                } else {
                    im = new Image(getClass().getResourceAsStream("/img/cards/front/" + id + ".png"));
                }
                ImageView imageView = new ImageView(im);
                imageView.setFitWidth(104);
                imageView.setFitHeight(66.66);
                board.add(imageView, 25+x, 25-y);
            }
        });

    }

    /**
     * it shows on the player board the possible position
     * @param availablePositions list of available position
     */
    public void showPossiblePositions(List<int[]> availablePositions) {
        lastVersion = availablePositions;
        for (int[] position : availablePositions) {
            Region r = new Region();
            relations.put(r,position);
            r.setStyle("-fx-background-color: #b5af5a; -fx-opacity: 0.5");
            r.setCursor(Cursor.HAND);
            r.setOnMouseClicked(event -> {
                tryPlace(position);
            });
            board.add(r, 25+position[0], 25-position[1]);
        }
    }


    /**
     * try place the card
     * @param position indicate possible position for the card
     */
    private void tryPlace(int[] position){
        GUIApplication gui = ((GUIApplication) ClientController.getInstance().getView());
        ForInGame inGameController = gui.getInGameController();
        if (inGameController.isHandCard1Selected() || inGameController.isHandCard2Selected() || inGameController.isHandCard3Selected()){
            lastChoice = position;
            if (inGameController.isHandCard1Selected()) {
                ClientController.getInstance().getClientAction().playCard(gui.getHandCards().get(0),inGameController.getIsBackSide(),position);
                deactivateMouseClick();
            }else if (inGameController.isHandCard2Selected()) {
                ClientController.getInstance().getClientAction().playCard(gui.getHandCards().get(1),inGameController.getIsBackSide(),position);
                deactivateMouseClick();
            }else if (inGameController.isHandCard3Selected()) {
                ClientController.getInstance().getClientAction().playCard(gui.getHandCards().get(2),inGameController.getIsBackSide(),position);
                deactivateMouseClick();
            }
            inGameController.refreshHandClickState();
        }else {
            gui.showGameInformation("Please, select one card from your hand cards");
        }
    }


    private void deactivateMouseClick(){
        for (Region r: relations.keySet()) {
            r.setCursor(Cursor.DEFAULT);
            r.setOnMouseClicked(event -> {});
        }

    }


    public void reactiveMouseClick(){
        for (Region r: relations.keySet()) {
            r.setCursor(Cursor.HAND);
            r.setOnMouseClicked(event -> tryPlace(relations.get(r)));
        }
    }


    public void removeRegion(){
        lastVersion.remove(lastChoice);
        relations.clear();
        for (int[] position : lastVersion) {
            board.getChildren().removeIf( node -> GridPane.getColumnIndex(node).equals(25+position[0]) && GridPane.getRowIndex(node).equals(25-position[1]));
        }
    }

}
