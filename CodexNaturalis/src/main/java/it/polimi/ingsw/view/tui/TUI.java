package it.polimi.ingsw.view.tui;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.main.ClientApp;
import it.polimi.ingsw.message.enums.LocationType;
import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.model.enums.GameState;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.view.UserInterface;

import java.io.PrintStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * The TUI class provides all methods needed for a Textual User Interface
 * It implements {@link UserInterface}
 */
public class TUI implements UserInterface{
    private final Scanner in;
    private final PrintStream out;
    private String myNickname;
    private String tryNickname;
    private ImmutableLobby lobby;
    private ImmutableGame game;
    private List<ImmutablePlayer> players;
    private List<Integer> handCards;
    private Integer[] possiblePersonalGoals;
    private ImmutablePlayer me;
    private Integer myPersonalGoal;
    private HashMap<ChatMessage, Boolean> chatMessages;



    public TUI() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out, true);
        myNickname = null;
        tryNickname = null;
        lobby = null;
        game = null;
        players = null;
        handCards = null;
        me = null;
        possiblePersonalGoals = null;
        myPersonalGoal = null;
        chatMessages = null;
    }

// SHOW CARD IN BOARD

    /**
     * Asks the client to insert the server ip
     */
    public void askServerIP(){
        String serverIP;
        do {
            serverIP = ipRequest();
            if (ClientApp.checkIPValidity(serverIP))
                askConnectionType(serverIP);
            else{
                out.println();
                System.out.println("Invalid IP address, a correct IPv4 address has format x.x.x.x  [x ranges from 0 to 255]");
                out.println();
            }
        }while (!ClientApp.checkIPValidity(serverIP));
    }


    private String ipRequest(){
        out.print("Please insert the server IP address: ");
        return in.nextLine();
    }


    /**
     * Asks the client to choose the connection type
     * @param ip server ip
     */
    public void askConnectionType(String ip){
        String choice;
        out.println();
        out.println("CONNECTION TYPE: ");
        out.println("1- Socket");
        out.println("2- RMI");
        choice = in.nextLine();
        if (choice.equals("1")){
            ClientApp.trySocketConnection(ip);
        }else if(choice.equals("2")){
            try {
                ClientApp.tryRMIConnection(ip);
            } catch (RemoteException | NotBoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            invalidChoice();
            askConnectionType(ip);
        }

    }

    @Override
    public synchronized void connectionSuccess(){
        out.println();
        out.println("[Connection successful]");
        ClientController.getInstance().setConnected();
        askAccessMode();
    }

    /**
     * Asks the client to choose between login or registration
     */
    private void askAccessMode(){
        String choice;
        out.println();
        out.println("ACCESS MODE ");
        out.println("1- Login");
        out.println("2- Registration");
        choice = in.nextLine();
        if (!ClientController.getInstance().isConnected())
            return;
        if(choice.equals("1")){
            login();
        }else if(choice.equals("2")){
            registration();
        }else{
            invalidChoice();
            askAccessMode();
        }
    }

    /**
     * Asks the client to enter credentials
     */
    private void login(){
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


    /**
     * Asks the client to enter nickname and password for registration
     */
    private void registration() {
        out.println();
        String pwd1, pwd2;
        out.println("REGISTRATION");
        out.print("Please enter nickname [at least 1 alphabetic letter]:  ");
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
            ClientController.getInstance().getClientAction().access(tryNickname,pwd1,false);
        }else if(choice.equals("2")){
            askAccessMode();
        }else{
            invalidChoice();
            login();
        }

    }


    /**
     * @return a string contains al least 1 alphabetic character and no space in
     */
    private String nicknameRequest(){
        String input;
        do {
            input = in.nextLine();
            if(!input.matches(".*[a-zA-Z]+.*") || input.matches(".*\\s+.*")) {
                out.println("[Nickname should contain at least 1 alphabetic letter and no space]");
                out.println();
                out.print("Please enter nickname: ");
            }
        }while (!input.matches(".*[a-zA-Z]+.*") || input.matches(".*\\s+.*"));
        return input;
    }


    /**
     * @return a string with min size: 6
     */
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
    public synchronized void loginSuccess(){
        out.println();
        out.println("[Login Success]");
        myNickname = tryNickname;
        showGameNameTitle();
        home();
    }

    @Override
     public synchronized void loginFailed(int type){
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
    public synchronized void registrationFailed(){
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


    /**
     * Show home page options
     */
    private void home() {
        out.println();
        out.println("HOME");
        out.println("1- Play a match");
        out.println("2- Exit from game");
        String choice = in.nextLine();
        if (!ClientController.getInstance().isConnected())
            return;
        if (choice.equals("1"))
            playGame();
        else if (choice.equals("2"))
            System.exit(0);
        else{
            invalidChoice();
            home();
        }

    }

    /**
     * Asks the client to choose between join a lobby and create a lobby
     */
    private void playGame(){
        out.println();
        out.println("Make your choice:");
        out.println("1- JOIN an existing waiting room");
        out.println("2- CREATE one waiting room yourself");
        String choice = in.nextLine();
        if (!ClientController.getInstance().isConnected())
            return;
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
    public synchronized void setLobbyList(List<Integer[]> lobbyList) {
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
    public synchronized void lobbyChooseFailed() {
        out.println();
        out.println("[The room you selected is already full, probably someone joined before you]");
    }


    /**
     * Asks the client to choose the number of new lobby
     */
    public synchronized void askNumOfPlayer(){
        out.println();
        boolean isValid = false;
        do {
            out.println("How many players do you want in the match?");
            out.println("1- Two");
            out.println("2- Three");
            out.println("3- Four");
            String choice = in.nextLine();
            switch (choice) {
                case "1" -> {
                    ClientController.getInstance().getClientAction().createLobby(2);
                    isValid = true;
                }
                case "2" -> {
                    ClientController.getInstance().getClientAction().createLobby(3);
                    isValid = true;
                }
                case "3" -> {
                    ClientController.getInstance().getClientAction().createLobby(4);
                    isValid = true;
                }
                default -> invalidChoice();
            }

        }while (!isValid);
    }


    @Override
    public synchronized void setLobbyStatus(ImmutableLobby lobby){
        out.println();
        this.lobby = lobby;
        showLobbyStatus();
        if (lobby.getPlayers().size() == lobby.getNumOfPlayer())
            out.println("[All players gathered, game is preparing...]");
        else
            out.println("[Waiting other player...]");

    }


    private void showLobbyStatus(){
        if (lobby == null)
            return;
        out.println("WAITING ROOM ["+ lobby.getNumOfPlayer()+"]");
        for (String player: lobby.getPlayers())
            out.println(player);
    }


    @Override
    public synchronized void setPlayerStatus(ImmutablePlayer player){
        if (players == null)
            players = new ArrayList<>();

        if (players.size() < game.getNumOfPlayer()){
            addPlayerStatus(player);
            if (player.getNickname().equals(myNickname)){
                showInitGameStatus();
            }
        }else {
            updatePlayerStatus(player);
        }

    }


    private void addPlayerStatus(ImmutablePlayer player){
        if (player.getNickname().equals(myNickname))
            me = player;
        players.add(player);
    }


    private void updatePlayerStatus(ImmutablePlayer newStatus){
        ImmutablePlayer old = null;
        for (ImmutablePlayer oldStatus: players) {
            if (oldStatus.getNickname().equals(newStatus.getNickname())){
                old = oldStatus;
                break;
            }
        }
        players.set(players.indexOf(old),newStatus);
        if (newStatus.getNickname().equals(myNickname)){
            me = newStatus;
        }
    }


    /**
     * show Symbols List
     */
    private void showSymbolsList(int[] symbolsList){
        System.out.println("\nVisible objects and resources:");
        System.out.println(AnsiColor.BLUE.getCode() + symbolsList[0] + "  Animal "+ AnsiColor.RESET.getCode());
        System.out.println(AnsiColor.GREEN.getCode() + symbolsList[1] + "  Plant "+ AnsiColor.RESET.getCode());
        System.out.println(AnsiColor.RED.getCode() + symbolsList[2] + "  Fungi "+ AnsiColor.RESET.getCode());
        System.out.println(AnsiColor.MAGENTA.getCode() + symbolsList[3] + "  Insect "+ AnsiColor.RESET.getCode());
        System.out.println(AnsiColor.BRIGHT_YELLOW.getCode() + symbolsList[4] + "  Quill"+ AnsiColor.RESET.getCode());
        System.out.println(AnsiColor.BRIGHT_YELLOW.getCode() + symbolsList[5] + "  Inkwell"+ AnsiColor.RESET.getCode());
        System.out.println(AnsiColor.BRIGHT_YELLOW.getCode() + symbolsList[6] + "  Manuscript"+ AnsiColor.RESET.getCode());
    }

    /**
     * Show player board
     */
    private void showPlayerBoard(ImmutablePlayer player) {

        int minX = player.getX().stream().min(Integer::compareTo).orElse(Integer.MIN_VALUE)-1;
        int maxX = player.getX().stream().max(Integer::compareTo).orElse(Integer.MAX_VALUE)+1;
        int minY = player.getY().stream().min(Integer::compareTo).orElse(Integer.MIN_VALUE)-1;
        int maxY = player.getY().stream().max(Integer::compareTo).orElse(Integer.MAX_VALUE)+1;

        // Create a matrix of size rows y cols x and fill it with default values "   "
        String[][] matrix = new String[maxY - minY + 1][maxX - minX + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = "   "; // default values
            }
        }

        // Populate the matrix with the given coordinates, coloring the card ███ according to the kingdom
        for (int i =0 ;i< player.getBoardCards().size() ;i++) {
            int x = player.getX().get(i);
            int y = player.getY().get(i);
            int number = player.getBoardCards().get(i);
            String color = getColor(number);

            if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
                if ( number < 10) matrix[y - minY][x - minX] = color + AnsiColor.BLACK.getCode() +" "+ number +" " + AnsiColor.RESET.getCode();
                if ( 9 < number && number < 100) matrix[y - minY][x - minX] = color + AnsiColor.BLACK.getCode()  +" "+ number  + AnsiColor.RESET.getCode();
                if ( number > 99) matrix[y - minY][x - minX] = color + AnsiColor.BLACK.getCode() + number  + AnsiColor.RESET.getCode();
            }
        }

        // Populate the matrix with the permissible coordinates, coloring it in white
        for (int[] coord : player.getPermissiblePosition()) {
            int x = coord[0];
            int y = coord[1];
            if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
                matrix[y - minY][x - minX] =  AnsiColor.WHITE.getCode() + "███" +  AnsiColor.RESET.getCode();
            }
        }

        //Printing the matrix
        System.out.print("  ");
        for (int j = 0; j < matrix[0].length; j++) {
            int n= j+minX;
            if(n<0) System.out.print(" "+(j + minX) + " ");
            else  System.out.print("  "+(j + minX) + " ");
        }
        System.out.println();
        for (int i = matrix.length - 1; i >= 0; i--) {
            int n= i+minY;
            if((n<0)||(n>9)) System.out.print((n) + " ");
            else  System.out.print(" "+(n) +" ");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static String getColor(int number) {
        String color = AnsiColor.YELLOW_BACKGROUND.getCode();

        if (( 0 < number && number < 11) || (40 < number && number < 51) ) { color = AnsiColor.RED_BACKGROUND.getCode();}
        if ((10 < number && number < 21) || (50 < number && number < 61) ) { color = AnsiColor.GREEN_BACKGROUND.getCode();}
        if ((20 < number && number < 31) || (60 < number && number < 71) ) { color = AnsiColor.BLUE_BACKGROUND.getCode();}
        if ((30 < number && number < 41) || (70 < number && number < 81) ) { color = AnsiColor.MAGENTA_BACKGROUND.getCode();}

        return color;
    }


    public void showPlayerStatus(ImmutablePlayer player){
        out.println();
        out.println("Player: " + player.getNickname());
        out.println("Kingdoms of the back side of hand cards: " + player.getHandCardKingdoms());
        out.println("Board: ");
        showPlayerBoard(player);
        showSymbolsList(player.getSymbolList());
        out.println(" ");

    }


    public void showScoreBoard(){
        if (players == null)
            return;
        out.println();
        out.println("SCORE BOARD:");
        for (ImmutablePlayer player:players) {
            out.println(player.getNickname()+"["+player.getColor()+"]: "+ player.getPoint());
        }
    }


    /**
     * Shows game status in phase of setup
     */
    public void showInitGameStatus(){
        if (game == null)
            return;
        out.println();
        out.println("[GAME SETUP]");
        showScoreBoard();
        out.println();
        showDeskStatus();
        askInitCardPlace();
    }


    @Override
    public synchronized void setGameStatus(ImmutableGame newStatus){
        //the oldStatus state
        ImmutableGame oldStatus = null;

        if (chatMessages == null)
            chatMessages = new LinkedHashMap<>();

        if (game != null) {
            oldStatus = game;
        }

        //update to the new state
        game = newStatus;

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
                for (ImmutablePlayer player: players) {
                    showPlayerStatus(player);
                }
                out.println();
                out.println("[Round begins with player " + newStatus.getCurrentPlayer()+"]");
                out.println();
                out.println("[" + newStatus.getCurrentPlayer() + " is playing...]");
                if (newStatus.getCurrentPlayer().equals(myNickname)){
                    askAction();
                }else {
                    showChatService();
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
                    askAction();
                }else {
                    showChatService();
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


    /**
     * Removes all information about the game
     */
    private void removeLastGameInfo(){
        game = null;
        lobby = null;
        players = null;
        handCards = null;
        me = null;
        myPersonalGoal = null;
        chatMessages = null;
    }


    private void showCommonGoals(){
        out.println();
        out.println("The two common goals: \n" + Resource.getCard(game.getCommonGoals().get(0),false)+"\n"+ Resource.getCard(game.getCommonGoals().get(1),false));
    }


    private void showPersonalGoal(){
        if (myPersonalGoal == null)
            return;
        out.println();
        out.println("Your personal goal: \n" +  Resource.getCard(myPersonalGoal,false));
    }


    /**
     * Shows comments when waiting other players play initial card or choose personal goal
     */
    private void showWaitingComment(){
        out.println();
        out.println("[Waiting all players take that action...]");
    }


    private void askInitCardPlace(){
        out.println("\n[Initial card play]\n");
       // out.println("Your initial card: " + me.getInitialCard());
        askInitialCardSide(me.getInitialCard());
        showWaitingComment();
    }


    private void askInitialCardSide(Integer id){
        askSide(id);
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


    private void askSide(Integer id){
        out.println("Which side of card do you want to play?");
        out.println("1- [front] "+ Resource.getCard(id,false));
        out.println("2- [back] "+ Resource.getCard(id,true));
    }


    @Override
    public synchronized void setPossiblePersonalGoals(Integer[] possiblePersonalGoals) {
        this.possiblePersonalGoals = possiblePersonalGoals;
        askPersonalGoalChoose();
    }

    private void askPersonalGoalChoose(){
        out.println("\n[Personal goal choose]\n");
        out.println("Choose your personal goal: ");
        out.println("1- "+ Resource.getCard(possiblePersonalGoals[0],false));
        out.println("2- "+ Resource.getCard(possiblePersonalGoals[1],false));
        String choice = in.nextLine();
        if (choice.equals("1")){
            ClientController.getInstance().getClientAction().choosePersonalGoal(possiblePersonalGoals[0]);
            myPersonalGoal = possiblePersonalGoals[0];
            showWaitingComment();
        }else if (choice.equals("2")){
            ClientController.getInstance().getClientAction().choosePersonalGoal(possiblePersonalGoals[1]);
            myPersonalGoal = possiblePersonalGoals[1];
            showWaitingComment();
        }else {
            invalidChoice();
            askPersonalGoalChoose();
        }
    }

    @Override
    public synchronized void setHandCards(List<Integer> newHandCardsStatus) {
        if (handCards == null){
            handCards = newHandCardsStatus;
            showHandCards();
        }else {
            handCards = newHandCardsStatus;
        }

    }

    private void showHandCards(){
        out.println();
        out.println("Your hand card: " );
        for (int i = 0; i < handCards.size(); i++) {
            if (handCards.get(i) != null)
                out.println((i+1) + "- " + Resource.getCard(handCards.get(i),false));
        }
    }


    /**
     * Asks the client to play a card
     */
    private void askPlayHandCard(){
        if (handCards == null)
            return;

        showHandCards();
        out.println("Which one do you want to play?");

        try{
            int choice = Integer.parseInt(in.nextLine());
            if (!ClientController.getInstance().isConnected())
                return;
            if (choice > 0 && choice <= handCards.size()){
                for (int i = 0; i < handCards.size(); i++) {
                    if (choice == i+1){
                        askPosition(handCards.get(i));
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


    /**
     * Asks the side of the card that client wants to play
     * @param id id of card
     * @param position position that client wants to play
     */
    private void askCardSide(Integer id, int[] position){
        askSide(id);
        String choice = in.nextLine();
        if (!ClientController.getInstance().isConnected())
            return;
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
    public synchronized void playCardFailed(){
        out.println("[Action failed: gold card's condition not fulfilled]");
        askPlayHandCard();
    }


    @Override
    public synchronized void drawCardFailed(){
        out.println();
        out.println("[Action failed: invalid card id]");
        askDrawCard();
    }


    /**
     * Asks the client to choose the position that he/she wants to play
     * @param idCard id of card already chosen
     */
    private void askPosition(Integer idCard){
        //position that can be chosen
        List<int[]> admittedPositions = me.getPermissiblePosition();

        //ask which one
        out.println("Which position do you want to place?");
        for (int i = 0; i < admittedPositions.size(); i++) {
            out.println((i+1)+"- "+ Arrays.toString(admittedPositions.get(i)));
        }
        //or return back page
        out.println((admittedPositions.size()+1)+"- go back");

        try {
            int choice = Integer.parseInt(in.nextLine());
            if (!ClientController.getInstance().isConnected())
                return;
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
        if (game.getDisplayedRCards() != null){
            if (game.getDisplayedRCards().get(0) != null){
                out.println("1- [card] "+ Resource.getCard(game.getDisplayedRCards().get(0),false));
                validChoices.add("1");
            }else {
                out.println("1- empty");
            }

            if (game.getDisplayedRCards().get(1) != null){
                out.println("2- [card] "+ Resource.getCard(game.getDisplayedRCards().get(1),false));
                validChoices.add("2");
            }else {
                out.println("2- empty");

            }
        } else {
            out.println("1- empty");
            out.println("2- empty");
        }

        if (game.getDisplayedGCards() != null){
            if (game.getDisplayedGCards().get(0) != null){
                out.println("3- [card] "+ Resource.getCard(game.getDisplayedGCards().get(0),false));
                validChoices.add("3");
            }else {
                out.println("3- empty");

            }

            if (game.getDisplayedGCards().get(1) != null){
                out.println("4- [card] "+ Resource.getCard(game.getDisplayedGCards().get(1),false));
                validChoices.add("4");
            }else {
                out.println("4- empty");

            }
        } else {
            out.println("3- empty");
            out.println("4- empty");
        }

        if (game.getFirstRCardKingdom() != null){
            out.println("5- resource card deck: the first card kingdom is "+game.getFirstRCardKingdom());
            validChoices.add("5");
        }else {
            out.println("5- empty resource card deck");
        }

        if (game.getFirstGCardKingdom() != null){
            out.println("6- gold card deck: the first card kingdom is "+game.getFirstGCardKingdom());
            validChoices.add("6");
        }else {
            out.println("6- empty gold card deck");
        }
        return validChoices;
    }


    /**
     * Asks the client to draw a card from desk
     */
    private void askDrawCard(){
        out.println();
        out.println("Which card do you want to draw?");
        List<String> validChoices = showDeskStatus();
        String choice = in.nextLine();
        if (validChoices.contains(choice)){
            if (!ClientController.getInstance().isConnected())
                return;
            switch (choice){
                case "1":
                    ClientController.getInstance().getClientAction().drawCard(
                            LocationType.DISPLAYED_RESOURCE_LIST,game.getDisplayedRCards().get(0));
                    break;
                case "2":
                    ClientController.getInstance().getClientAction().drawCard(
                            LocationType.DISPLAYED_RESOURCE_LIST,game.getDisplayedRCards().get(1));
                    break;
                case "3":
                    ClientController.getInstance().getClientAction().drawCard(
                            LocationType.DISPLAYED_GOLD_LIST,game.getDisplayedGCards().get(0));
                    break;
                case "4":
                    ClientController.getInstance().getClientAction().drawCard(
                            LocationType.DISPLAYED_GOLD_LIST,game.getDisplayedGCards().get(1));
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
    public synchronized void showFinalResult(ImmutableEndGameInfo info){
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
    public synchronized void gameInterrupted(String nickname) {
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


    /**
     * Shows chat service
     */
    public void showChatService(){
        out.println();
        out.println("CHAT SERVICE:");
        out.println("1- Send a public message");
        out.println("2- Send a private message");
        out.println("3- View history");
        out.println("4- View unread messages");
        out.println("5- EXIT from chat");
        String choice = in.nextLine();
        if (!ClientController.getInstance().isConnected())
            return;
        switch (choice) {
            case "1":
                sendPublicMessage();
                break;
            case "2":
                sendPrivateMessage();
                break;
            case "3":
                viewChatHistory();
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


    private void sendPublicMessage(){
        out.print("Please enter the message content: ");
        String content = in.nextLine();
        if (!ClientController.getInstance().isConnected())
            return;
        ChatMessage message = new ChatMessage(myNickname,content);
        ClientController.getInstance().getClientAction().chat(message);
        chatMessages.put(message,true);
        showChatService();
    }


    private void sendPrivateMessage(){
        out.println("Who do you want to send the message to?");
        List<String> nicknames = new ArrayList<>(
                players.stream()
                .map(ImmutablePlayer::getNickname).toList());
        nicknames.remove(myNickname);

        for (int i = 0; i < nicknames.size(); i++) {
            out.println((i+1) + "- " + nicknames.get(i));
        }
        out.println((nicknames.size()+1) + "- back to chat service");

        String choice = in.nextLine();
        try {
            int intChoice = Integer.parseInt(choice);
            if (!ClientController.getInstance().isConnected())
                return;
            if (intChoice > 0 && intChoice <= nicknames.size()){
                out.print("Please enter the message content: ");
                String content = in.nextLine();
                ChatMessage message = new ChatMessage(myNickname, nicknames.get(intChoice-1), content);
                ClientController.getInstance().getClientAction().chat(message);
                chatMessages.put(message,true);
                showChatService();
            }else if (intChoice == nicknames.size()+1){
                showChatService();
            }else{
                invalidChoice();
                sendPrivateMessage();
            }
        }catch (NumberFormatException e){
            invalidChoice();
            sendPrivateMessage();
        }
    }


    @Override
    public void addNewChatMessage(ChatMessage message) {
        if (chatMessages == null)
            return;
        chatMessages.put(message,false);
    }


    public void viewUnReadChatMessages(){
        out.println();
        boolean exists = false;
        for (ChatMessage message : chatMessages.keySet()) {
            if (!chatMessages.get(message)) {
                showChat(message);
                chatMessages.replace(message, true);
                exists = true;
            }
        }
        if (!exists)
            out.println("[No new messages have arrived]");
        if (!ClientController.getInstance().isConnected())
            return;
        showChatService();
    }


    /**
     * Shows the message content
     * @param message {@link ChatMessage}
     */
    private void showChat(ChatMessage message){
        if (message.isPublic())
            out.println("[public]  " +message.getSender() + ": " + message.getContent());
        else
            out.println("[private] " +message.getSender() + ": " + message.getContent());
    }


    private void viewChatHistory(){
        out.println();
        boolean exists = false;
        for (ChatMessage message : chatMessages.keySet()) {
            if (chatMessages.get(message)) {
                showChat(message);
                exists = true;
            }
        }
        if (!exists)
            out.println("[No history is present. Let's start chatting!]");
        if (!ClientController.getInstance().isConnected())
            return;
        showChatService();
    }


    /**
     * Asks the client doing an action when it is his/her turn
     */
    private void askAction(){
        askActionOptions();
        String choice = in.nextLine();
        if (!ClientController.getInstance().isConnected())
            return;
        switch (choice){
            case "1":
                askWhichPlayerBoard();
                askAction();
                break;
            case "2":
                showDeskStatus();
                askAction();
                break;
            case "3":
                showCommonGoals();
                showPersonalGoal();
                askAction();
                break;
            case "4":
                showChatService();
                askAction();
                break;
            case "5":
                askPlayHandCard();
                break;
            default:
                invalidChoice();
                askAction();
                break;
        }

    }


    private void askActionOptions(){
        out.println();
        out.println("PERMITTED ACTIONS:");
        out.println("1- See a player board");
        out.println("2- See the desk");
        out.println("3- See all goals");
        out.println("4- Use chat service");
        out.println("5- PLAY YOUR TURN");
    }


    private void askWhichPlayerBoard(){
        out.println("Whose board do you want to see?");
        for (int i = 0; i < game.getNumOfPlayer(); i++) {
            out.println((i+1)+("- ")+players.get(i).getNickname());
        }
        String choice = in.nextLine();
        try {
            int intChoice = Integer.parseInt(choice);
            if (intChoice > 0 && intChoice <= game.getNumOfPlayer()){
                for (int i = 0; i < game.getNumOfPlayer(); i++) {
                    if ((i+1) == intChoice){
                        showPlayerStatus(players.get(i));
                        break;
                    }
                }
            }else {
                invalidChoice();
                askWhichPlayerBoard();
            }
        }catch (NumberFormatException e){
            invalidChoice();
            askWhichPlayerBoard();
        }
    }


    private void invalidChoice(){
        out.println("Invalid choice");
    }


    @Override
    public synchronized void showConnectionError(){
        removeLastGameInfo();
        myNickname = null;
        tryNickname = null;
        out.println();
        out.println("[CONNECTION ERROR, please try reconnection]");
        out.println();
        askServerIP();
    }

}