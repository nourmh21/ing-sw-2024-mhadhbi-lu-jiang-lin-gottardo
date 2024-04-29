package it.polimi.ingsw.view;

import it.polimi.ingsw.modelView.ObjectiveCardView;
import it.polimi.ingsw.modelView.PlayerBoardView;

import java.io.PrintStream;
import java.util.Scanner;

public class UserInterfaceText {


        private Scanner input;
        private PrintStream output;

        //private DeskView deskView;

        //private PlayerView players;
        private PlayerBoardView playerBoard;

        private ObjectiveCardView objectiveCard;
        String Nickname;



        private void init(){
            Nickname = null;
       /* players = null;
        deskView = null;
        objectiveCard = null;*/
            playerBoard = null;
            input = new Scanner(System.in);
            output = new PrintStream(System.out);
            chooseTypeConnection();
            getUserNickname();
            createOrJoinGame();

        }

        private void chooseTypeConnection() {
            int in;
            String ip;

            System.out.println("Choose the connection type: ");
            output.println("1. SOCKET");
            output.println("2. RMI");
            in = Integer.parseInt(input.nextLine());

            if ((in == 1) || (in ==2)) {
                output.println("Insert the IP address: ");
                ip = input.nextLine();

            }
            if (in == 1){
                output.println("Connecting with SOCKET...");
                //....
            }else {
                output.println("Connecting with RMI...");
                //....
            }
        }

        private void getUserNickname(){
            do {
                output.println("Insert your Nickname: ");
                this.Nickname = input.nextLine();
            }while (this.Nickname.equals(""));

            //do connection!!!
        }

        private void createOrJoinGame(){
            output.println("Choose to create or join game: ");
            output.println("1. CREATE A GAME");
            output.println("2. JOIN A GAME");
            int in = input.nextInt();

            if (in == 1){
                chooseNumOfPlayer();
            }else  {
                //do connection for RMI...
            }

        }

        private void chooseNumOfPlayer(){
            int num;
            output.println("Choose the number of player for the game (from 2 to 4 players): ");
            num = input.nextInt();
            //do connection for SOCKET....
        }

        private void showObjectiveCard(){
            output.println("Common Objective Card: ");
            //....
        }

        public void exitGame(){
            //when the player do disconnection
        }






}
