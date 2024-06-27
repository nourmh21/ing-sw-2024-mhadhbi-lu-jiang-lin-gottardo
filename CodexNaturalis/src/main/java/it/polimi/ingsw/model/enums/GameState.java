package it.polimi.ingsw.model.enums;

/**
 * indicates the state of the game, it can be one of the following
 * - SETUP_PHASE_1: waiting for players to place their initial card
 * - SETUP_PHASE_2: waiting for players to choose their personal goal
 * - PLAY_CARD: current player is placing a card
 * - DRAW_CARD: current player is drawing a card
 * - TURN_MANAGE: the next player is being chosen
 * - ENDING: game is ending
 * - FINISHED: game has ended
 */
public enum GameState {
    SETUP_PHASE_1,  //waiting players place their initial card
    SETUP_PHASE_2,  //waiting players choose their personal goal
    PLAY_CARD,
    DRAW_CARD,
    TURN_MANAGE,    //decide who's the next one to play
    ENDING,
    FINISHED
}
