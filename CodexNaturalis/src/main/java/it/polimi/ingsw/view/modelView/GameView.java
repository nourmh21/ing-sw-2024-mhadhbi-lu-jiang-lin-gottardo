package it.polimi.ingsw.view.modelView;

import java.util.List;
import java.util.Scanner;
import java.io.PrintStream;
import java.util.InputMismatchException;




public class GameView {
    private PrintStream output;
    private Scanner input;
    private int numOfPlayer;
    private int numOfConnectedPlayer;
    private String currentPlayer;
    private List<PlayerView> players;
    private List<ObjectiveCardView> objectiveCard;
    private DeskView desk;



    /**
     * the player choose the color existing
     * @return the color chose by player
     */
   /* public int chooseYourColor(){
        System.out.println("Which color do you want?");
        output.println("1. Green");
        output.println("2. Yellow");
        output.println("3. Red");
        output.println("4. Blue");
    }*/


    /**
     * it gets the DeskView
     */
    private void getDeskView(){
        System.out.println("Now on desk we have: ");
        output.println("- Resource Card: " + desk.getDisplayedRCards());
        output.println("- Gold Card: " + desk.getDisplayedGCards() );
        output.println("- Resource card deck: " + desk.pickNextRCard());
        output.println("- Gold card deck: " + desk.pickNextGCard());

    }


    /**
     *it prints the three possible card that the player can use in the first time
     */
    private void printHandCard(){
        output.println("The three card that you can play are: ");
        output.println("- Resource Card: " + players.get(0).getHandCards());
        output.println("- Resource Card: " + players.get(1).getHandCards());
        output.println("- Gold Card: " + players.get(2).getHandCards());

    }


    /**
     * it chooses the private Goal Card
     * @param card_1 the first possible private goal card
     * @param card_2 the second possible private goal card
     * @return players choice's
     */
    public int choosePersonalGoalCard(String card_1, String card_2){
        String in;
        int choice = -1;
        while(true){
            System.out.println("Choose your personal GoalCard: ");
            output.println("1 - " + card_1);
            output.println("2 - " + card_2);
            try{
                in = input.nextLine();
            }catch ( InputMismatchException ex){
                output.println("Invalid selection!");
                continue;
            }

            try{
                choice = Integer.parseInt(in);
            } catch (NumberFormatException e) {
                output.println("Invalid selection!");
                continue;
            }
            if(choice != 1 && choice != 2) output.println("Invalid selection!");
            else break;
        }
        return choice;
    }


    /**
     * choose the initial card side's
     * @return player choice's
     */
   public int chooseFrontOrBackInitialCard(String f, String b){
        String in = null;
        int choice = -1;
        while (true){
            System.out.println("Your initial card: ");
            output.println("front: " +  f);
            output.println("back: " + b);
            output.println(" ");
            System.out.println("Which side you want to use: ");
            output.println("1. Front");
            output.println("2. Back");
            try{
                in = input.nextLine();
            }catch (InputMismatchException ex){
                output.println("Invalid selection!!");
            }

            try{
                choice = Integer.parseInt(in);
            } catch (NumberFormatException e) {
                output.println("Invalid selection!");
            }
            if ((choice != 1) && (choice!=2)){
                output.println("Invalid selection!");
            }else break;
        }
       return choice;
   }


    /**
     * @return the list of ObjectiveCardView
     */
   public List<ObjectiveCardView> getObjectiveCard(){
        return objectiveCard;
   }


    /**
     * @return the Nickname of the current player
     */
    public String getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * @return Number of player
     */
    public int getNumOfPlayer(){
        return numOfPlayer;
    }


    /**
     * @return Number of connected player
     */
   public int getNumOfConnectedPlayer() {
       return numOfConnectedPlayer;
   }


    /**
     * @return the players order
     */
   public List<PlayerView> getPlayers(){
       System.out.println("The play order is: ");
       return players;
   }





}










