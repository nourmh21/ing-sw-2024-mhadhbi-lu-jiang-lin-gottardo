package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.ImmutableLobby;
import it.polimi.ingsw.message.general.ConnectedMessage;
import it.polimi.ingsw.model.enums.GameState;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.network.Client;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class TUI {
    private final Scanner in;
    private final PrintStream out;

    private String myNickname;
    private String tryNickname;
    private Optional<ImmutableLobby> lobby;
    private Optional<ImmutableGame>  game;
    private Optional<List<ImmutablePlayer>> players;

    private Optional<List<Integer>> handCards;

    private Optional<ImmutablePlayer> me;


    public TUI() throws IOException {
        in = new Scanner(System.in);
        out = new PrintStream(System.out, true);
        myNickname = null;
        tryNickname = null;
        lobby = Optional.empty();
        game = Optional.empty();
        players = Optional.empty();
        handCards = Optional.empty();
        showWinTitle();
        askServerIP();

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
        out.println("CONNECTION TYPE: ");
        out.println("1- Socket");
        out.println("2- RMI");
        choice = in.nextLine();
        if (choice.equals("1")){
            Client.trySocketConnection(ip);
            //out.println("Connecting...");
            connectionSuccess();
        }else if(choice.equals("2")){
            ////
            connectionSuccess();
        }else {
            invalidChoice();
            askConnectionType(ip);
        }

    }


    public void connectionSuccess(){
        out.println("[Connection successful]");
        askAccessMode();
    }


    public void askAccessMode(){
        String choice;
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
            loginSuccess();
        }else if(choice.equals("2")){
            askAccessMode();
        }else{
            invalidChoice();
            login();
        }

    }


    public void registration() {
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
        out.println("[Login Success]");
        myNickname = tryNickname;
        showGameNameTitle();
        home();
    }


    public void loginFailed(){
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
        ///
    }


    public void askNumOfPlayer(){
        out.println("[No waiting room available in this moment, you're creating one]");
        boolean isValid = false;
        do {
            out.println("How many players do you want in the match?");
            out.println("1- Two");
            out.println("2- Three");
            out.println("3- Four");
            String choice = in.nextLine();
            if (choice.equals("1")){
                ///
                isValid = true;
            }else if (choice.equals("2")){
                ///
                isValid = true;
            }else if (choice.equals("3")){
                ///
                isValid = true;
            }else {
                invalidChoice();
            }

        }while (!isValid);
    }


    public void setLobbyStatus(ImmutableLobby lobby){
        this.lobby = Optional.of(lobby);
        showLobbyStatus();
    }


    public void showLobbyStatus(){
        if (lobby.isPresent()){
            out.println("WAITING ROOM ["+ lobby.get().getNumOfPlayer()+"]");
            for (String player: lobby.get().getPlayers())
                out.println(player);
        }else{
            out.println("You're not in a waiting room");
        }
        if (lobby.get().getPlayers().size() == lobby.get().getNumOfPlayer())
            out.println("Player gathered, game is preparing...]");
    }


    public void addPlayerStatus(ImmutablePlayer player){
        if (players.isEmpty())
            players = Optional.of(new ArrayList<>());
        players.get().add(player);
        if (player.getNickname().equals(myNickname))
            me = Optional.of(player);
    }


    public void updatePlayerStatus(ImmutablePlayer newStatus){
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
        out.println("nickname: " + player.getNickname());
        out.println("initial card: " + player.getInitialCard());
        out.println("kingdoms of the back side of hand cards: " + player.getCardKingdom());
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
            out.println("SCORE BOARD:");
            for (ImmutablePlayer player:players.get()) {
                out.println(player.getNickname()+"["+player.getColor()+"]: "+ player.getPoint());
            }
        }
    }


    public void showInitGameStatus(){
        if (game.isPresent()){
            out.println("GAME SETUP:");
            showScoreBoard();
            showDeskStatus();
            askInitCardPlace();
        }
    }


    public void setGameStatus(ImmutableGame newStatus){

        //the old state
        ImmutableGame old = null;

        if (game.isPresent()) {
            old = game.get();
        }

        //update to the new state
        game = Optional.of(newStatus);

        //realize all players are added to current match
        if (old.getPlayers() == null && newStatus.getPlayers() != null){
            showInitGameStatus();
        }


        //realize common goals are drawn
        if (newStatus.getCommonGoals() != null && old.getCommonGoals() == null){
            showCommonGoals();
        }

        //realize that game is started
        if (old.getCurrentPlayer() == null && newStatus.getCurrentPlayer() != null){
            for (ImmutablePlayer player: players.get()) {
                showPlayerStatus(player);
            }
            out.println("[GAME START]");
        }


        //realize it is the last round
        if (!old.isLastRound() && newStatus.isLastRound()){
            out.println("[Last round begins]");
        }

        //realize change of turn
        if (!Objects.equals(old.getCurrentPlayer(), newStatus.getCurrentPlayer())) {
            out.println("[" + newStatus.getCurrentPlayer() + "is playing]");
            //realize it is my turn to play a card
            if (newStatus.getCurrentPlayer().equals(myNickname)){
                askPlayHandCard();
            }
        }


        //realize that current turn player has played a card
        if (old.getGameState() == GameState.PLAY_CARD && newStatus.getGameState() == GameState.DRAW_CARD ){
            out.println("["+ newStatus.getCurrentPlayer()+ "has played a card]");
            //realize it is my turn to draw a card
            if (newStatus.getCurrentPlayer().equals(myNickname)){
                askDrawCard();
            }
        }

        //realize that current turn player has drawn a card
        if (newStatus.getGameState() == GameState.TURN_MANAGE){
            out.println("["+newStatus.getCurrentPlayer()+" has draw a card]");
        }

        if (newStatus.getGameState() == GameState.ENDING){
            out.println("Final score calculating...");
        }

        if (newStatus.getGameState() == GameState.FINISHED){
            out.println("[GAME ENDED]");
            game = Optional.empty();
            lobby = Optional.empty();
            players = Optional.empty();
            handCards = Optional.empty();
            me = Optional.empty();
            home();
        }
    }


    public void showCommonGoals(){
        out.println("The two common goals of this game are: " + game.get().getCommonGoals());
    }


    public void askInitCardPlace(){
        out.println("This is your initial card: " + me.get().getInitialCard());
        askCardSide(me.get().getInitialCard(), new int[]{0, 0});

    }

    public void askInitialCardSide(Integer id){
        askSide();
        String choice = in.nextLine();
        if (choice.equals("1"))
            ;
        else if(choice.equals("2"))
            ;
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

    public void askCardSide(Integer id, int[] position){
        askSide();
        String choice = in.nextLine();
        if (choice.equals("1"))
            ;
        else if(choice.equals("2"))
            ;
        else {
            invalidChoice();
            askCardSide(id, position);
        }
    }


    public void askPersonalGoalChoose(List<Integer> possiblePersonalGoals){
        out.println("Choose your personal goal: ");
        out.println("1- "+possiblePersonalGoals.get(0));
        out.println("2- "+possiblePersonalGoals.get(1));
        String choice = in.nextLine();
        if (choice.equals("1")){
            //possiblePersonalGoals.get(0)
        }else if (choice.equals("2")){
            //possiblePersonalGoals.get(1)
        }else {
            invalidChoice();
            askPersonalGoalChoose(possiblePersonalGoals);
        }
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
            showHandCards();
            out.println("Which one do you want to play?");
            for (int i = 0; i < handCards.get().size(); i++) {
                if (handCards.get().get(i) != null)
                    out.println(i + "- " + handCards.get().get(i));
            }

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
        }
    }


    public void askPosition(Integer idCard){
        //position that can be chosen
        List<int[]> admittedPositions = me.get().getPermissiblePosition();

        //ask which one
        out.println("Which position do you want to place?");
        for (int i = 0; i < admittedPositions.size(); i++) {
            out.println((i+1)+"- "+ admittedPositions.get(i));
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
            if (game.get().getDisplayedRCards().isPresent()){
                if (game.get().getDisplayedRCards().get().get(0) != null){
                    out.println("1- card: "+ game.get().getDisplayedRCards().get().get(0));
                    validChoices.add("1");
                }else {
                    out.println("1- empty");
                }

                if (game.get().getDisplayedRCards().get().get(1) != null){
                    out.println("2- card: "+ game.get().getDisplayedRCards().get().get(1));
                    validChoices.add("2");
                }else {
                    out.println("2- empty");

                }
            } else {
                out.println("1- empty");
                out.println("2- empty");
            }

            if (game.get().getDisplayedGCards().isPresent()){
                if (game.get().getDisplayedGCards().get().get(0) != null){
                    out.println("3- card: "+ game.get().getDisplayedGCards().get().get(0));
                    validChoices.add("3");
                }else {
                    out.println("3- empty");

                }

                if (game.get().getDisplayedGCards().get().get(1) != null){
                    out.println("4- card: "+ game.get().getDisplayedGCards().get().get(1));
                    validChoices.add("4");
                }else {
                    out.println("4- empty");

                }
            } else {
                out.println("3- empty");
                out.println("4- empty");
            }

            if (game.get().getFirstRCardKingdom().isPresent()){
                out.println("5- resource card deck: the first card kingdom is "+game.get().getFirstRCardKingdom());
                validChoices.add("5");
            }else {
                out.println("5- empty resource card deck");
            }

            if (game.get().getFirstGCardKingdom().isPresent()){
                out.println("6- gold card deck: the first card kingdom is "+game.get().getFirstGCardKingdom());
                validChoices.add("6");
            }else {
                out.println("6- empty gold card deck");
            }

        }
        return validChoices;
    }


    public void askDrawCard(){
        out.println("[Draw a card from desk]");
        List<String> validChoices = showDeskStatus();
        String choice = in.nextLine();
        if (validChoices.contains(choice)){
            switch (choice){
                case "1":

                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":

                    break;
                case "6":

                    break;
            }
        }else{
            invalidChoice();
            askDrawCard();
        }

    }

    public void showFinalResult(ImmutableEndGameInfo info){
        out.println("FINAL SCORE BOARD:");
        for (Map.Entry<String, int[]> entry:info.getFinalResult().entrySet()) {
            out.println(entry.getKey() + ": "+ entry.getValue()[0] + "(include "+ entry.getValue()[1] +"goal points)");
        }
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


}









