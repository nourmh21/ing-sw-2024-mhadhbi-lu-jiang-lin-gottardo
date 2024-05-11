package it.polimi.ingsw.model.enums;

public enum GameState {
    SETUP_PHASE_1,  //waiting players place their initial card
    SETUP_PHASE_2,  //waiting players chooses their personal goal
    PLAY_CARD,
    DRAW_CARD,
    TURN_MANAGE,    //decide who's the next one to play
    ENDING,
    FINISHED,
    @Deprecated
    WAITING_RECONNECTION
}
