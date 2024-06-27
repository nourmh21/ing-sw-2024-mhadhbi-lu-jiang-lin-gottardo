package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.model.enums.Color;
import it.polimi.ingsw.model.enums.GameState;
import it.polimi.ingsw.model.enums.Symbol;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.observer.ModelObservable;
import it.polimi.ingsw.observer.ModelObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Game extends ModelObservable {
    private final int idGame;
    private GameState gameState;
    private final int numOfPlayer;
    private List<Player> players;
    private final List<Integer> commonGoals;
    private Desk desk;
    private List<Player> winners;
    public List<Player> possibleWinners;
    private boolean isLastRound;
    private Player currentPlayer;
    private ChatHistory chatHistory;
    ArrayList<Color> colors;


    /***
     * the class constructor
     * @param numOfPlayer defines number of player for the game
     */
    public Game(int numOfPlayer) {
        desk = new Desk(this);
        players = new ArrayList<>();
        winners = new ArrayList<>();
        possibleWinners = new ArrayList<>();
        commonGoals = new ArrayList<>();
        this.numOfPlayer = numOfPlayer;
        Random random = new Random();
        idGame = random.nextInt(1000000);
        gameState = GameState.SETUP_PHASE_1;
        isLastRound = false;
        colors = new ArrayList<>(List.of((Color.values())));
        chatHistory = new ChatHistory();
    }


    public void setGameObservers(List<ModelObserver> observers) {
        this.observers = observers;
        notify_game_status(generateImmutableGame());
        for (ModelObserver o : observers) {
            chatHistory.addObserver(o);
        }
    }


    /**
     * it sets common goal for the game
     *
     * @param idCard indicates id of the common goal
     */
    public void setCommonGoals(Integer idCard) {
        if (commonGoals.size() < 2)
            commonGoals.add(idCard);
        //
        if (commonGoals.size() == 2)
            notify_game_status(generateImmutableGame());
    }


    /**
     * @return the unique code of game generated random
     */
    public int getIdGame() {
        return idGame;
    }


    /**
     * @return gameState that indicates what state the game is in
     */
    public GameState getGameState() {
        return gameState;
    }


    /**
     * sets game state
     *
     * @param gameState indicate the present state of the game
     */
    public synchronized void setGameState(GameState gameState) {
        this.gameState = gameState;
        //
        notify_game_status(generateImmutableGame());
    }


    /**
     * add the new player in the player list
     *
     * @param nickname is the new player's name
     */
    public void addPlayers(String nickname) {
        players.add(new Player(nickname, this, randomColor()));
        if (players.size() == numOfPlayer) {
            notify_game_status(generateImmutableGame());
        }
    }


    /**
     * @return player's list of the game
     */
    public List<Player> getPlayers() {
        return players;
    }


    /**
     * @return the size of player list
     */
    public int getPlayersSize() {
        return players.size();
    }


    /**
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    /**
     * @param p defines the next current player
     */
    public void setCurrentPlayer(Player p) {
        this.currentPlayer = p;
        //
        notify_game_status(generateImmutableGame());
    }


    /**
     * set isLastRound true
     */
    public void setIsLastRound() {
        isLastRound = true;
        //
        notify_game_status(generateImmutableGame());
    }


    /**
     * @return if is last turn
     */
    public boolean getIsLastRound() {
        return isLastRound;
    }


    /**
     * find players with maximum points and return the list of possible winner
     */
    public void checkMaxPoint() {
        int max = 20;
        for (Player p : players) {
            if ((p.getPoint() >= max))
                max = p.getPoint();
        }

        for (Player p : players) {
            if ((p.getPoint() == max)) {
                possibleWinners.add(p);
            }
        }
        if (possibleWinners.size() > 1)
            checkExtraPoint();
        else
            winners = possibleWinners;
        //
        notify_final_result(new ImmutableEndGameInfo(getPlayersPoint(), getWinnersNickname()));
    }


    /**
     * find final winners in the game and return the final winners list
     */
    public void checkExtraPoint() {
        int maxExtraPoint = 0;
        for (Player p : possibleWinners) {
            if (p.getPoint() >= maxExtraPoint)
                maxExtraPoint = p.getPoint();
        }
        for (Player p : possibleWinners) {
            if (p.getPoint() == maxExtraPoint)
                winners.add(p);
        }

    }


    /**
     * it gets points of player
     *
     * @return point
     */
    private HashMap<String, int[]> getPlayersPoint() {
        HashMap<String, int[]> finalResult = new HashMap<>();
        for (Player p : getPlayers()) {
            finalResult.put(p.getNickname(), new int[]{p.getPoint(), p.getGoalPoint()});
        }
        return finalResult;
    }


    /**
     * @return winners' nickname
     */
    public List<String> getWinnersNickname() {
        return getWinners().stream()
                .map(Player::getNickname)
                .toList();
    }


    /**
     * @return players' nickname
     */
    public List<String> getPlayersNickname() {
        return getPlayers().stream()
                .map(Player::getNickname)
                .toList();
    }


    /**
     * @return List of winners
     */
    public List<Player> getWinners() {
        return winners;
    }


    /**
     * @return the common Goal Cards id
     */
    public List<Integer> getCommonGoals() {
        return commonGoals;
    }


    /**
     * @return the number of player in the game
     */
    public int getNumOfPlayer() {
        return numOfPlayer;
    }


    /**
     * @return desk of the game
     */
    public Desk getDesk() {
        return desk;
    }


    /**
     * it randoms color (without repeat)
     *
     * @return the color get by random
     */
    public Color randomColor() {
        Random random = new Random();
        int randomIndex = random.nextInt(colors.size());
        Color randomColor = colors.get(randomIndex);
        colors.remove(randomIndex);

        return randomColor;
    }

    public void addNewChat(ChatMessage message) {
        chatHistory.addNewMessage(message);
    }


    /**
     * Visible for GameTest
     *
     * @return chatHistory
     */
    public ChatHistory getChatHistory() {
        return chatHistory;
    }


    /**
     * @return a new {@link ImmutableGame}
     */
    public ImmutableGame generateImmutableGame() {
        List<String> playerNicknames = null;
        String current = null;
        Symbol firstRCardKingdom = null;
        Symbol firstGCardKingdom = null;
        List<Integer> displayedRCards = null;
        List<Integer> displayedGCards = null;
        if (players != null)
            playerNicknames = getPlayersNickname();
        if (currentPlayer != null)
            current = currentPlayer.getNickname();
        if (desk.getNextResourceCard() != null)
            firstRCardKingdom = ((Card) (GameController.getInstance().getCard(desk.getNextResourceCard()))).getKingdom();
        if (desk.getNextGoldCard() != null)
            firstGCardKingdom = ((Card) GameController.getInstance().getCard(desk.getNextGoldCard())).getKingdom();
        if (desk.getDisplayedRCards() != null)
            displayedRCards = desk.getDisplayedRCards();
        if (desk.getDisplayedGCards() != null)
            displayedGCards = desk.getDisplayedGCards();
        return new ImmutableGame(numOfPlayer, gameState, commonGoals, isLastRound, playerNicknames, current,
                firstRCardKingdom, firstGCardKingdom, displayedRCards, displayedGCards);
    }
}
