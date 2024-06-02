package it.polimi.ingsw.view.tui;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.model.enums.GameState;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.view.UserInterface;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class TUI extends Thread implements UserInterface{
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

    private Optional<Integer> myPersonalGoal;

    private Optional<HashMap<ChatMessage, Boolean>> chatMessages;



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
        myPersonalGoal = Optional.empty();
        chatMessages = Optional.empty();
    }

    @Override
    public void run() {
        askServerIP();
        while (!this.isInterrupted()){}

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

    @Override
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


    @Override
    public void loginSuccess(){
        out.println();
        out.println("[Login Success]");
        myNickname = tryNickname;
        showGameNameTitle();
        home();
    }

    @Override
    public void loginFailed(int type){
        out.println();
        if (type == 1){
            out.println("[Credential errors]");
        }else if (type == 2){
            out.println("[Account already logged in]");
        }
        tryNickname = null;
        login();
    }


    @Override
    public void registrationFailed(){
        out.println("[Nickname already exists]");
        registration();
    }


    private void showGameNameTitle() {
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


    private void home() {
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


    private void playGame(){
        out.println();
        out.println("Make your choice:");
        out.println("1- JOIN an existing waiting room");
        out.println("2- CREATE one waiting room yourself");
        String choice = in.nextLine();
        if (choice.equals("1")){
            ClientController.getInstance().getClientAction().reqLobbies();
        }else if (choice.equals("2")) {
            askNumOfPlayer();
        } else {
            invalidChoice();
            playGame();
        }
    }

    @Override
    public void setLobbyList(List<Integer[]> lobbyList) {
        out.println();
        out.println("EXISTING WAITING ROOMS:");
        if (lobbyList.isEmpty())
            out.println("[No waiting room exists]");
        for (int i = 0; i < lobbyList.size(); i++) {
            out.println((i+1) + "- "+ lobbyList.get(i)[0] + " [" + lobbyList.get(i)[1] + "] currently in: " + lobbyList.get(i)[2]);
        }
        out.println((lobbyList.size()+1) + "- REFRESH");
        out.println((lobbyList.size()+2) + "- go back");
        String choice = in.nextLine();
        try {
            int intChoice = Integer.parseInt(choice);
            if (intChoice > 0 && intChoice <= lobbyList.size()){
                ClientController.getInstance().getClientAction().joinLobby(lobbyList.get(intChoice-1)[0]);
            }else if (intChoice == (lobbyList.size()+1)) {
                ClientController.getInstance().getClientAction().reqLobbies();
            }else if (intChoice == (lobbyList.size()+2)){
                playGame();
            }else {
                invalidChoice();
                setLobbyList(lobbyList);
            }
        }catch (NumberFormatException e){
            invalidChoice();
            setLobbyList(lobbyList);
        }
    }

    @Override
    public void lobbyChooseFailed() {
        out.println();
        out.println("[The room you selected is already full, probably someone joined before you]");
    }


    @Override
    public void askNumOfPlayer(){
        out.println();
        boolean isValid = false;
        do {
            out.println("How many players do you want in the match?");
            out.println("1- Two");
            out.println("2- Three");
            out.println("3- Four");
            String choice = in.nextLine();
            if (choice.equals("1")){
                ClientController.getInstance().getClientAction().createLobby(2);
                isValid = true;
            }else if (choice.equals("2")){
                ClientController.getInstance().getClientAction().createLobby(3);
                isValid = true;
            }else if (choice.equals("3")){
                ClientController.getInstance().getClientAction().createLobby(4);
                isValid = true;
            }else {
                invalidChoice();
            }

        }while (!isValid);
    }


    @Override
    public void setLobbyStatus(ImmutableLobby lobby){
        out.println();
        this.lobby = Optional.of(lobby);
        showLobbyStatus();
        if (lobby.getPlayers().size() == lobby.getNumOfPlayer())
            out.println("[All players gathered, game is preparing...]");
        else
            out.println("[Waiting other player...]");

    }


    private void showLobbyStatus(){
        if (lobby.isPresent()){
            out.println("WAITING ROOM ["+ lobby.get().getNumOfPlayer()+"]");
            for (String player: lobby.get().getPlayers())
                out.println(player);
        }else{
            out.println("You're not in a waiting room");
        }
    }


    //this will call showInitGameStatus() in specific condition
    @Override
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
        ImmutablePlayer old = null;
        for (ImmutablePlayer oldStatus: players.get()) {
            if (oldStatus.getNickname().equals(newStatus.getNickname())){
                old = oldStatus;
                break;
            }
        }
        players.get().set(players.get().indexOf(old),newStatus);
        if (newStatus.getNickname().equals(myNickname)){
            me = Optional.of(newStatus);
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

    @Override
    public void setGameStatus(ImmutableGame newStatus){
        /*used for CmdLaunchTest:
        out.println();
        out.println(newStatus);
         */
        //the oldStatus state
        ImmutableGame oldStatus = null;

        if (chatMessages.isEmpty())
            chatMessages = Optional.of(new HashMap<>());

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
                out.println("["+newStatus.getCurrentPlayer()+" has drew a card]");
            }
        }


        if (newStatus.getGameState() == GameState.ENDING){
            out.println();
            out.println("Final score calculating...");
        }

        if (newStatus.getGameState() == GameState.FINISHED){
            out.println("[GAME ENDED]");
            removeLastGameInfo();
            home();
        }
    }

    private void removeLastGameInfo(){
        game = Optional.empty();
        lobby = Optional.empty();
        players = Optional.empty();
        handCards = Optional.empty();
        me = Optional.empty();
        myPersonalGoal = Optional.empty();
        chatMessages = Optional.empty();
    }


    private void showCommonGoals(){
        out.println();
        out.println("The two common goals of this game are: " + game.get().getCommonGoals());
    }

    private void showWaitingComment(){
        out.println();
        out.println("[Waiting all players take that action...]");
    }

    private void askInitCardPlace(){
        out.println();
        out.println("Your initial card: " + me.get().getInitialCard());
        askInitialCardSide(me.get().getInitialCard());
        showWaitingComment();
    }

    private void askInitialCardSide(Integer id){
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


    @Override
    public void setPossiblePersonalGoals(Integer[] possiblePersonalGoals) {
        this.possiblePersonalGoals = Optional.of(possiblePersonalGoals);
        askPersonalGoalChoose();
    }

    private void askPersonalGoalChoose(){
        out.println();
        out.println("Choose your personal goal: ");
        out.println("1- "+possiblePersonalGoals.get()[0]);
        out.println("2- "+possiblePersonalGoals.get()[1]);
        String choice = in.nextLine();
        if (choice.equals("1")){
            ClientController.getInstance().getClientAction().choosePersonalGoal(possiblePersonalGoals.get()[0]);
            myPersonalGoal = Optional.of(possiblePersonalGoals.get()[0]);
            showWaitingComment();
        }else if (choice.equals("2")){
            ClientController.getInstance().getClientAction().choosePersonalGoal(possiblePersonalGoals.get()[1]);
            myPersonalGoal = Optional.of(possiblePersonalGoals.get()[1]);
            showWaitingComment();
        }else {
            invalidChoice();
            askPersonalGoalChoose();
        }
    }

    @Override
    public void setHandCards(List<Integer> newHandCardsStatus) {
        if (handCards.isEmpty()){
            handCards = Optional.of(newHandCardsStatus);
            showHandCards();
        }else {
            handCards = Optional.of(newHandCardsStatus);
        }

    }

    private void showHandCards(){
        out.println();
        out.println("Your hand card: " + handCards);
    }


    private void askPlayHandCard(){
        if (handCards.isPresent()) {
            showHandCards();
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

    private void askCardSide(Integer id, int[] position){
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


    @Override
    public void playCardFailed(){
        out.println("[Action failed: gold card's condition not fulfilled]");
        askPlayHandCard();
    }


    @Override
    public void drawCardFailed(){
        out.println();
        out.println("[Action failed: invalid card id]");
        askDrawCard();
    }

    private void askPosition(Integer idCard){
        //position that can be chosen
        List<int[]> admittedPositions = me.get().getPermissiblePosition();

        //ask which one
        out.println("Which position do you want to place?");
        for (int i = 0; i < admittedPositions.size(); i++) {
            out.println((i+1)+"- "+ Arrays.toString(admittedPositions.get(i)));
        }
        //or return back page
        out.println((admittedPositions.size()+1)+"- go back");

        try {
            int choice = Integer.parseInt(in.nextLine());
            //analyse client choose
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
        }catch (NumberFormatException e){
            invalidChoice();
            askPosition(idCard);
        }
    }


    private List<String> showDeskStatus(){
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


    private void askDrawCard(){
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


    @Override
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

    @Override
    public void gameInterrupted(String nickname) {
        out.println();
        out.println("[Player " + nickname + " leave the game]");
        out.println("GAME INTERRUPTED");
        removeLastGameInfo();
        home();
    }


    private void showWinTitle(){
        out.println("\u001B[38;4;230m");
        out.println("\u001B[38;5;78m ██   ██  ███████  ██   ██     ██     ██     ██  ██  ████    ██   ");
        out.println("\u001B[38;5;78m ██   ██  ██   ██  ██   ██      ██   ████   ██   ██  ██ ██   ██   ");
        out.println("\u001B[38;5;79m ███████  ██   ██  ██   ██       ██ ██  ██ ██    ██  ██  ██  ██   ");
        out.println("\u001B[38;5;80m   ███    ███████  ███████        ███    ███     ██  ██   █████   ");
        out.println("\u001B[38;4;230m");
    }


    public void showChatService(){
        out.println("CHAT SERVICE:");
        out.println("1- Send a public message");
        out.println("2- Send a private message");
        out.println("3- View read messages");
        out.println("4- View unread messages");
        out.println("5- EXIT from chat");
        String choice = in.nextLine();
        switch (choice){
            case "1":
                sendPublicMessage();
                break;
            case "2":
                sendPrivateMessage();
                break;
            case "3":
                viewReadChatMessages();
                break;
            case "4":
                viewUnReadChatMessages();
                break;
            case "5":
                break;
            default:
                invalidChoice();
                showChatService();
                break;
        }
    }

    public void addNewMessage(ChatMessage message){
        chatMessages.ifPresent(chatMessageBooleanHashMap -> chatMessageBooleanHashMap.put(message, false));
    }

    private void sendPublicMessage(){
        out.print("Please enter the message content: ");
        String message = in.nextLine();
        //

        showChatService();
    }

    private void sendPrivateMessage(){
        out.println("Who do you want to send the message to?");
        List<String> nicknames = new ArrayList<>(players.get().stream()
                .map(ImmutablePlayer::getNickname).toList());
        nicknames.remove(myNickname);
        for (int i = 0; i < nicknames.size(); i++) {
            out.println((i+1) + "- " + nicknames.get(i));
        }
        out.println((nicknames.size()+1) + "- back to chat service");
        String choice = in.nextLine();
        try {
            int intChoice = Integer.parseInt(choice);
            if (intChoice > 0 && intChoice <= nicknames.size()-1){
                out.print("Please enter the message content: ");
                String message = in.nextLine();
                //

                showChatService();
            }else if (intChoice == nicknames.size()){
                showChatService();
            }else {
                invalidChoice();
                sendPrivateMessage();
            }
        }catch (NumberFormatException e){
            invalidChoice();
            sendPrivateMessage();
        }
    }


    public void viewUnReadChatMessages(){
        Iterator<ChatMessage> iterator = chatMessages.get().keySet().iterator();
        if (!iterator.hasNext()){
            out.println("You haven't received any new messages");
            showChatService();
        }else {
            while (iterator.hasNext()){
                ChatMessage message = iterator.next();
                if (!chatMessages.get().get(message)){
                    out.println(message.getSender() + ": " + message.getContent());
                    chatMessages.get().replace(message,true);
                }
            }
            showChatService();
        }

    }

    public void viewReadChatMessages(){
        Iterator<ChatMessage> iterator = chatMessages.get().keySet().iterator();
        if (!iterator.hasNext()){
            out.println("You never received any messages");
            showChatService();
        }else {
            while (iterator.hasNext()){
                ChatMessage message = iterator.next();
                if (chatMessages.get().get(message)){
                    out.println(message.getSender() + ": " + message.getContent());
                }
            }
            showChatService();
        }

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

    public void askTurnOptions_1(){
        out.println("5- Play a card");
    }

    public void askTurnOptions_2(){
        out.println("5- Draw a card from desk");
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

    @Override
    public void showServerOffline(){
        removeLastGameInfo();
        myNickname = null;
        tryNickname = null;
        out.println();
        out.println("[Server offline, please try reconnection]");
        out.println();
        askServerIP();
    }

    public void showPersonalGoal(){
        if (myPersonalGoal.isPresent()){
            out.println();
            out.println("My personal goal: " + myPersonalGoal.get());
        }
    }
}









