package it.polimi.ingsw.view.TUI;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.controller.server.ImmutableLobby;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.model.enums.GameState;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.view.UserInterface;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class TUI implements UserInterface, Runnable{
    private final Scanner in;
    private final PrintStream out;

    private String myNickname;
    private String tryNickname;
    private Optional<ImmutableLobby> lobby;
    private Optional<ImmutableGame>  game;
    private Optional<List<ImmutablePlayer>> players;

    private Optional<List<Integer>> handCards;

    private Optional<Integer[]> possiblePersonalGoals;

    private Optional<ImmutablePlayer> me;

    private Optional<Integer> myNumOfPlayerChoose;



    public TUI() throws IOException {
        in = new Scanner(System.in);
        out = new PrintStream(System.out, true);
        myNickname = null;
        tryNickname = null;
        lobby = Optional.empty();
        game = Optional.empty();
        players = Optional.empty();
        handCards = Optional.empty();
        me = Optional.empty();
        possiblePersonalGoals = Optional.empty();
        myNumOfPlayerChoose = Optional.empty();
    }

    @Override
    public void run() {
        askServerIP();
        while (true){}

    }


    public void askServerIP(){
        String serverIP;
        do {
            serverIP = ipRequest();
            if (Client.checkIPValidity(serverIP))
                askConnectionType(serverIP);
            else{
                System.out.println("Invalid IP address, a correct IPv4 address has format x.x.x.x  [x ranges from 0 to 255]");
            }
        }while (!Client.checkIPValidity(serverIP));
    }


    private String ipRequest(){
        out.print("Please insert the server IP address: ");
        return in.nextLine();
    }


    public void askConnectionType(String ip){
        String choice;
        out.println();
        out.println("CONNECTION TYPE: ");
        out.println("1- Socket");
        out.println("2- RMI");
        choice = in.nextLine();
        if (choice.equals("1")){
            Client.trySocketConnection(ip);

        }else if(choice.equals("2")){
            //
        }else {
            invalidChoice();
            askConnectionType(ip);
        }

    }


    public void connectionSuccess(){
        out.println();
        out.println("[Connection successful]");
        askAccessMode();
    }


    public void askAccessMode(){
        String choice;
        out.println();
        out.println("ACCESS MODE ");
        out.println("1- Login");
        out.println("2- Registration");
        choice = in.nextLine();
        if(choice.equals("1")){
            login();
        }else if(choice.equals("2")){
            registration();
        }else{
            invalidChoice();
            askAccessMode();
        }
    }


    public void login(){
        out.println();
        String password;
        out.println("LOGIN");
        out.print("Please enter nickname: ");
        tryNickname = nicknameRequest();
        out.print("Please enter password: ");
        password = pwdRequest();

        out.println("1- login");
        out.println("2- go back");
        String choice = in.nextLine();
        if(choice.equals("1")){
            ///
            //out.println("data checking ...");
            ClientController.getInstance().getClientAction().access(tryNickname,password,true);
        }else if(choice.equals("2")){
            askAccessMode();
        }else{
            invalidChoice();
            login();
        }

    }


    public void registration() {
        out.println();
        String pwd1, pwd2;
        out.println("REGISTRATION");
        out.print("Please enter nickName [at least 1 alphabetic letter]:  ");
        tryNickname = nicknameRequest();
        do {
            out.print("Please enter password [min size: 6]: ");
            pwd1 = pwdRequest();
            out.print("Please enter confirmed password: ");
            pwd2 = in.nextLine();
            if (!pwd1.equals(pwd2))
                out.println("The two password are different.");
        }while (!pwd1.equals(pwd2));

        out.println("1- registration with auto login");
        out.println("2- go back");
        String choice = in.nextLine();
        if(choice.equals("1")){
            ;///
            ClientController.getInstance().getClientAction().access(tryNickname,pwd1,false);
        }else if(choice.equals("2")){
            askAccessMode();
        }else{
            invalidChoice();
            login();
        }

    }


    private String nicknameRequest(){
        String input;
        do {
            input = in.nextLine();
            if(!input.matches(".*[a-zA-Z]+.*"))
                out.print("Nickname should contain at least 1 alphabetic letter: ");
        }while (!input.matches(".*[a-zA-Z]+.*"));
        return input;
    }


    private String pwdRequest(){
        String input;
        do {
            input = in.nextLine();
            if(input.length() < 6)
                out.print("Password min size 6: ");
        }while (input.length() < 6);
        return input;
    }


    public void loginSuccess(){
        out.println();
        out.println("[Login Success]");
        myNickname = tryNickname;
        showGameNameTitle();
        home();
    }


    public void loginFailed(){
        out.println();
        out.println("[Credential errors]");
        tryNickname = null;
        login();
    }


    public void registrationFailed(){
        out.println("[Nickname already exists]");
        registration();
    }


    public void showGameNameTitle() {
        System.out.println("\u001B[38;4;230m");
        System.out.println("\u001B[38;5;78m ██████╗ █████╗ ██████╗ ███████╗██╗   ██╗");
        System.out.println("\u001B[38;5;78m██╔════╝██╔══██║██╔══██║██╔════╝ ██║ ██╔╝");
        System.out.println("\u001B[38;5;79m██║     ██║  ██║██║  ██║█████╗    ████╔╝");
        System.out.println("\u001B[38;5;80m██║     ██║  ██║██║  ██║██╔══╝   ██╔ ██╗");
        System.out.println("\u001B[38;5;80m╚██████╗ █████╔╝██████╔╝███████╗██╔╝  ██╗");
        System.out.println("\u001B[38;5;44m ╚═════╝ ╚════╝ ╚═════╝ ╚══════╝╚═╝   ╚═╝");
        System.out.println("\u001B[38;5;9m███╗  ██╗ █████╗ ████████╗██╗  ██╗██████╗ ███████╗██╗     ██╗ ███████╗");
        System.out.println("\u001B[38;5;1m████║ ██║██║  ██║   ██╔══╝██║  ██║██   ██║██   ██║██║     ██║ ██╔════╝");
        System.out.println("\u001B[38;5;204m██║██║██║███████║   ██║   ██║  ██║██████╔╝███████║██║     ██║ ███████║");
        System.out.println("\u001B[38;5;133m██║ ████║██╔══██║   ██║   ██║  ██║██╔══██╗██╔══██║██║     ██║      ██║");
        System.out.println("\u001B[38;5;134m██║  ███║██║  ██║   ██║   ███████║██║  ██║██║  ██║███████╗██║ ███████║");
        System.out.println("\u001B[38;5;134m╚═╝  ╚══╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝ ╚══════╝");
        System.out.println("\u001B[38;4;230m");
        System.out.println("\u001B[38;4;230m[Welcome to the game]");
    }


    public void home() {
        out.println();
        out.println("HOME");
        out.println("1- Play a match");
        out.println("2- Exit from game");
        String choice = in.nextLine();
        if (choice.equals("1"))
            playGame();
        else if (choice.equals("2"))
            System.exit(0);
        else{
            invalidChoice();
            home();
        }

    }


    public void playGame(){
        ClientController.getInstance().getClientAction().joinGame();
    }


    public void askNumOfPlayer(){
        out.println();
        out.println("[No waiting room available in this moment, you're creating one]");
        boolean isValid = false;
        do {
            out.println("How many players do you want in the match?");
            out.println("1- Two");
            out.println("2- Three");
            out.println("3- Four");
            String choice = in.nextLine();
            if (choice.equals("1")){
                myNumOfPlayerChoose = Optional.of(2);
                ClientController.getInstance().getClientAction().newGame(2);
                isValid = true;
            }else if (choice.equals("2")){
                myNumOfPlayerChoose = Optional.of(3);
                ClientController.getInstance().getClientAction().newGame(3);
                isValid = true;
            }else if (choice.equals("3")){
                myNumOfPlayerChoose = Optional.of(4);
                ClientController.getInstance().getClientAction().newGame(4);
                isValid = true;
            }else {
                invalidChoice();
            }

        }while (!isValid);
    }


    public void setLobbyStatus(ImmutableLobby lobby){
        if (myNumOfPlayerChoose.isPresent() && this.lobby.isEmpty())
            if (myNumOfPlayerChoose.get() != lobby.getNumOfPlayer()){
                out.println();
                out.println("[Someone created a waiting room faster than you, you entered in that one]");
            }
        out.println();
        this.lobby = Optional.of(lobby);
        showLobbyStatus();
        if (lobby.getPlayers().size() == lobby.getNumOfPlayer())
            out.println("[All players gathered, game is preparing...]");
        else
            out.println("[Waiting other player...]");

    }


    public void showLobbyStatus(){

        if (lobby.isPresent()){
            out.println("WAITING ROOM ["+ lobby.get().getNumOfPlayer()+"]");
            for (String player: lobby.get().getPlayers())
                out.println(player);
        }else{
            out.println("You're not in a waiting room");
        }
    }


    //this will call showInitGameStatus() in specific condition
    public void setPlayerStatus(ImmutablePlayer player){

        if (players.isEmpty())
            players = Optional.of(new ArrayList<>());
        //I doubt the correctness of this method
        if (players.get().size() < game.get().getNumOfPlayer()){
            addPlayerStatus(player);
        }else {
            updatePlayerStatus(player);
        }


        if (player.getNickname().equals(myNickname) &&
                game.get().getGameState() == GameState.SETUP_PHASE_1 && me.get().getBoardCards().isEmpty()){
            showInitGameStatus();
        }



    }


    private void addPlayerStatus(ImmutablePlayer player){
        if (player.getNickname().equals(myNickname))
            me = Optional.of(player);
        players.get().add(player);
    }


    private void updatePlayerStatus(ImmutablePlayer newStatus){
        for (ImmutablePlayer oldStatus: players.get()) {
            if (oldStatus.getNickname().equals(newStatus.getNickname())){
                players.get().remove(oldStatus);
                players.get().add(newStatus);
                if (newStatus.getNickname().equals(myNickname)){
                    me = Optional.of(newStatus);
                }
                break;
            }
        }
    }


    public void showPlayerStatus(ImmutablePlayer player){
        out.println("player: " + player.getNickname());
        out.println("initial card: " + player.getInitialCard());
        out.println("kingdoms of the back side of hand cards: " + player.getHandCardKingdoms());
        out.print("board: ");
        if (!player.getBoardCards().isEmpty()){
            for (int i = 0; i < player.getBoardCards().size(); i++) {
                out.print(player.getBoardCards().get(i) + "[" + player.getX().get(i) + ", " + player.getY().get(i) + "]");
                out.print("  ");
            }
        }
        out.println(" ");

    }


    public void showScoreBoard(){
        if (players.isPresent()){
            out.println();
            out.println("SCORE BOARD:");
            for (ImmutablePlayer player:players.get()) {
                out.println(player.getNickname()+"["+player.getColor()+"]: "+ player.getPoint());
            }
        }
    }


    public void showInitGameStatus(){
        if (game.isPresent()){
            out.println();
            out.println("[GAME SETUP]");
            out.println();
            //showScoreBoard();
            showDeskStatus();
            askInitCardPlace();
        }
    }


    public void setGameStatus(ImmutableGame newStatus){

        out.println();
        out.println(newStatus);
        //the oldStatus state
        ImmutableGame oldStatus = null;

        if (game.isPresent()) {
            oldStatus = game.get();
        }


        //update to the new state
        game = Optional.of(newStatus);



        //realize all players are added to current match
        if (oldStatus != null){

            //realize common goals are drawn
            if (oldStatus.getCommonGoals().isEmpty() && !newStatus.getCommonGoals().isEmpty()){
                showCommonGoals();
            }


            //realize that game is started
            if (oldStatus.getCurrentPlayer() == null && newStatus.getCurrentPlayer() != null){
                out.println();
                out.println("[GAME START]");
                showScoreBoard();
                for (ImmutablePlayer player: players.get()) {
                    out.println();
                    showPlayerStatus(player);
                }
                out.println();
                out.println("[Round begins with player " + newStatus.getCurrentPlayer()+"]");
                out.println();
                out.println("[" + newStatus.getCurrentPlayer() + " is playing...]");
                if (newStatus.getCurrentPlayer().equals(myNickname)){
                    askPlayHandCard();
                }
            }


            //realize it is the last round
            if (!oldStatus.isLastRound() && newStatus.isLastRound()){
                out.println();
                out.println("[Last round begins]");
            }


            //realize change of turn
            if (oldStatus.getCurrentPlayer() != null &&
                    !oldStatus.getCurrentPlayer().equals(newStatus.getCurrentPlayer())) {
                out.println();
                out.println("[" + newStatus.getCurrentPlayer() + " is playing...]");
                //realize it is my turn to play a card
                if (newStatus.getCurrentPlayer() != null && newStatus.getCurrentPlayer().equals(myNickname)){
                    askPlayHandCard();
                }
            }


            //realize that current turn player has played a card
            if (oldStatus.getGameState() == GameState.PLAY_CARD && newStatus.getGameState() == GameState.DRAW_CARD ){
                out.println();
                out.println("["+ newStatus.getCurrentPlayer()+ " has played a card]");
                //realize it is my turn to draw a card
                if (newStatus.getCurrentPlayer().equals(myNickname)){
                    askDrawCard();
                }
            }

            //realize that current turn player has drawn a card
            if (oldStatus.getGameState() == GameState.DRAW_CARD && newStatus.getGameState() == GameState.TURN_MANAGE){
                out.println();
                out.println("["+newStatus.getCurrentPlayer()+" has draw a card]");
            }
        }


        if (newStatus.getGameState() == GameState.ENDING){
            out.println();
            out.println("Final score calculating...");
        }

        if (newStatus.getGameState() == GameState.FINISHED){
            out.println("[GAME ENDED]");
            game = Optional.empty();
            lobby = Optional.empty();
            players = Optional.empty();
            handCards = Optional.empty();
            me = Optional.empty();
            myNumOfPlayerChoose = Optional.empty();
            home();
        }
    }


    public void showCommonGoals(){
        out.println();
        out.println("The two common goals of this game are: " + game.get().getCommonGoals());
    }

    private void showWaitingComment(){
        out.println();
        out.println("[Waiting all players take that action...]");
    }

    public void askInitCardPlace(){
        out.println();
        out.println("Your initial card: " + me.get().getInitialCard());
        askInitialCardSide(me.get().getInitialCard());
        showWaitingComment();
    }

    public void askInitialCardSide(Integer id){
        askSide();
        String choice = in.nextLine();
        if (choice.equals("1"))
            ClientController.getInstance().getClientAction().playInitCard(id, false);
        else if(choice.equals("2"))
            ClientController.getInstance().getClientAction().playInitCard(id, true);
        else {
            invalidChoice();
            askInitialCardSide(id);
        }
    }

    private void askSide(){
        out.println("Which side of card do you want to play");
        out.println("1- front");
        out.println("2- back");
    }


    public void setPossiblePersonalGoals(Integer[] possiblePersonalGoals) {
        this.possiblePersonalGoals = Optional.of(possiblePersonalGoals);
        askPersonalGoalChoose();
    }

    public void askPersonalGoalChoose(){
        out.println();
        out.println("Choose your personal goal: ");
        out.println("1- "+possiblePersonalGoals.get()[0]);
        out.println("2- "+possiblePersonalGoals.get()[1]);
        String choice = in.nextLine();
        if (choice.equals("1")){
            ClientController.getInstance().getClientAction().choosePersonalGoal(possiblePersonalGoals.get()[0]);
            showWaitingComment();
        }else if (choice.equals("2")){
            ClientController.getInstance().getClientAction().choosePersonalGoal(possiblePersonalGoals.get()[1]);
            showWaitingComment();
        }else {
            invalidChoice();
            askPersonalGoalChoose();
        }
    }

    public void personalGoalChooseFailed(){
        out.println("[Action failed: invalid goal]");
        askPersonalGoalChoose();
    }


    public void setHandCards(List<Integer> newHandCardsStatus) {
        if (handCards.isEmpty()){
            handCards = Optional.of(newHandCardsStatus);
            showHandCards();
        }else {
            handCards = Optional.of(newHandCardsStatus);
        }

    }

    public void showHandCards(){
        out.println("Your hand card: " + handCards);
    }


    public void askPlayHandCard(){
        if (handCards.isPresent()) {
            out.println();
            showHandCards();
            out.println();
            out.println("Which one do you want to play?");
            for (int i = 0; i < handCards.get().size(); i++) {
                if (handCards.get().get(i) != null)
                    out.println((i+1) + "- " + handCards.get().get(i));
            }

            try{
                int choice = Integer.parseInt(in.nextLine());
                if (choice > 0 && choice <= handCards.get().size()){
                    for (int i = 0; i < handCards.get().size(); i++) {
                        if (choice == i+1){
                            askPosition(handCards.get().get(i));
                            break;
                        }
                    }
                } else {
                    invalidChoice();
                    askPlayHandCard();
                }
            }catch (NumberFormatException e){
                invalidChoice();
                askPlayHandCard();
            }

        }
    }

    public void askCardSide(Integer id, int[] position){
        askSide();
        String choice = in.nextLine();
        if (choice.equals("1"))
            ClientController.getInstance().getClientAction().playCard(id,false,position);
        else if(choice.equals("2"))
            ClientController.getInstance().getClientAction().playCard(id,true,position);
        else {
            invalidChoice();
            askCardSide(id, position);
        }
    }


    public void playCardFailed(){
        out.println("[Action failed: invalid position]");
        askPlayHandCard();
    }

    public void drawCardFailed(){
        out.println();
        out.println("[Action failed: invalid card id]");
        askDrawCard();
    }

    public void askPosition(Integer idCard){
        //position that can be chosen
        List<int[]> admittedPositions = me.get().getPermissiblePosition();

        //ask which one
        out.println("Which position do you want to place?");
        for (int i = 0; i < admittedPositions.size(); i++) {
            out.println((i+1)+"- "+ Arrays.toString(admittedPositions.get(i)));
        }
        //or return back page
        out.println((admittedPositions.size()+1)+"- go back");

        //analyse client choose
        int choice = Integer.parseInt(in.nextLine());

        if (choice > 0 && choice <= admittedPositions.size()+1){
            if (choice == admittedPositions.size()+1){
                askPlayHandCard();
            }else {
                for (int i = 0; i < admittedPositions.size(); i++) {
                    if (choice == i+1){
                        askCardSide(idCard, admittedPositions.get(i));
                        break;
                    }
                }
            }
        }else{
            invalidChoice();
            askPosition(idCard);
        }
    }


    public List<String> showDeskStatus(){
        List<String> validChoices = new ArrayList<>();
        out.println("DESK: ");
        if (game.isPresent()){
            if (game.get().getDisplayedRCards() != null){
                if (game.get().getDisplayedRCards().get(0) != null){
                    out.println("1- card: "+ game.get().getDisplayedRCards().get(0));
                    validChoices.add("1");
                }else {
                    out.println("1- empty");
                }

                if (game.get().getDisplayedRCards().get(1) != null){
                    out.println("2- card: "+ game.get().getDisplayedRCards().get(1));
                    validChoices.add("2");
                }else {
                    out.println("2- empty");

                }
            } else {
                out.println("1- empty");
                out.println("2- empty");
            }

            if (game.get().getDisplayedGCards() != null){
                if (game.get().getDisplayedGCards().get(0) != null){
                    out.println("3- card: "+ game.get().getDisplayedGCards().get(0));
                    validChoices.add("3");
                }else {
                    out.println("3- empty");

                }

                if (game.get().getDisplayedGCards().get(1) != null){
                    out.println("4- card: "+ game.get().getDisplayedGCards().get(1));
                    validChoices.add("4");
                }else {
                    out.println("4- empty");

                }
            } else {
                out.println("3- empty");
                out.println("4- empty");
            }

            if (game.get().getFirstRCardKingdom() != null){
                out.println("5- resource card deck: the first card kingdom is "+game.get().getFirstRCardKingdom());
                validChoices.add("5");
            }else {
                out.println("5- empty resource card deck");
            }

            if (game.get().getFirstGCardKingdom() != null){
                out.println("6- gold card deck: the first card kingdom is "+game.get().getFirstGCardKingdom());
                validChoices.add("6");
            }else {
                out.println("6- empty gold card deck");
            }

        }
        return validChoices;
    }


    public void askDrawCard(){
        out.println();
        out.println("Which card do you want to draw?");
        List<String> validChoices = showDeskStatus();
        String choice = in.nextLine();
        if (validChoices.contains(choice)){
            switch (choice){
                case "1":
                    ClientController.getInstance().getClientAction().drawCard(
                            LocationType.DISPLAYED_RESOURCE_LIST,game.get().getDisplayedRCards().get(0));
                    break;
                case "2":
                    ClientController.getInstance().getClientAction().drawCard(
                            LocationType.DISPLAYED_RESOURCE_LIST,game.get().getDisplayedRCards().get(1));
                    break;
                case "3":
                    ClientController.getInstance().getClientAction().drawCard(
                            LocationType.DISPLAYED_GOLD_LIST,game.get().getDisplayedGCards().get(0));
                    break;
                case "4":
                    ClientController.getInstance().getClientAction().drawCard(
                            LocationType.DISPLAYED_GOLD_LIST,game.get().getDisplayedGCards().get(1));
                    break;
                case "5":
                    ClientController.getInstance().getClientAction().drawCard(
                            LocationType.RESOURCE_CARD_DECK,null);
                    break;
                case "6":
                    ClientController.getInstance().getClientAction().drawCard(
                            LocationType.GOLD_CARD_DECK,null);
                    break;
            }
        }else{
            invalidChoice();
            askDrawCard();
        }

    }

    public void showFinalResult(ImmutableEndGameInfo info){
        out.println();
        out.println("FINAL SCORE BOARD:");
        for (Map.Entry<String, int[]> entry:info.getFinalResult().entrySet()) {
            out.println(entry.getKey() + ": "+ entry.getValue()[0] + " [include "+ entry.getValue()[1] +" goal points]");
        }
        out.println();
        out.println("Game winner: "+ info.getWinners());
        if (info.getWinners().contains(myNickname))
            showWinTitle();

    }

    public void showWinTitle(){
        out.println("\u001B[38;4;230m");
        out.println("\u001B[38;5;78m ██   ██  ███████  ██   ██     ██     ██     ██  ██  ████    ██   ");
        out.println("\u001B[38;5;78m ██   ██  ██   ██  ██   ██      ██   ████   ██   ██  ██ ██   ██   ");
        out.println("\u001B[38;5;79m ███████  ██   ██  ██   ██       ██ ██  ██ ██    ██  ██  ██  ██   ");
        out.println("\u001B[38;5;80m   ███    ███████  ███████        ███    ███     ██  ██   █████   ");
        out.println("\u001B[38;4;230m");
    }







    //methods to be fixed

    /*
    public void askAction(boolean isMyTurn){
        askGeneralOptions();
        if (isMyTurn)
            askTurnOptions();
        String choice = in.nextLine();
        switch (choice){
            case "1":
                askWhichPlayerBoard();
                break;
            case "2":

                break;
            case "3":

                break;
            case "4":

                break;
            default:
                if(isMyTurn){
                    if (choice.equals("5")){

                        break;
                    }
                }
                invalidChoice();
                askAction(isMyTurn);
                break;
        }

    }

    public void askGeneralOptions(){
        out.println("Permitted actions:");
        out.println("1- See a player board");
        out.println("2- See the desk");
        out.println("3- See common goals");
        out.println("4- Send a message");
    }

    public void askTurnOptions(){
        out.println("5- Play a card");
    }

    public void askWhichPlayerBoard(){
        out.println("Whose board do you want to see?");
        for (int i = 0; i < game.get().getNumOfPlayer(); i++) {
            out.println((i+1)+("- ")+players.get().get(i).getNickname());
        }

        String choice = in.nextLine();

        int intChoice = Integer.parseInt(choice);

        if (intChoice >= 0 && intChoice <= game.get().getNumOfPlayer()){
            for (int i = 0; i < game.get().getNumOfPlayer(); i++) {
                if ((i+1) == intChoice){
                    showPlayerStatus(players.get().get(i));
                    break;
                }
            }
        }
    }

     */



    public void invalidChoice(){
        out.println("Invalid choice");
    }

    public void showServerOffline(){
        out.println();
        out.println("[Server offline, please try reconnection]");
        out.println();
        askServerIP();
    }

}









