package it.polimi.ingsw.message.enums;

/**
 * The ErrorType enum represents the different negative results of client actions
 * - CREDENTIALS_WRONG: the login credentials entered by the used are incorrect
 * - NICKNAME_ALREADY_EXISTS: the nickname used for registration is already in use by another user
 * - ACCOUNT_ALREADY_LOGGED: the player is already logged in
 * - INVALID_CARD_ID: the card id is invalid
 * - GOLD_CARD_CONDITION_NOT_RESPECTED: the condition imposed by the gold card is not fulfilled
 * - LOBBY_ID_NOT_FOUND: the lobby id entered by the player is not found
 */
public enum ErrorType {
    CREDENTIAL_WRONG,
    NICKNAME_ALREADY_EXIST,
    ACCOUNT_ALREADY_LOGGED,
    INVALID_CARD_ID,
    GOLD_CARD_CONDITION_NOT_RESPECTED,
    LOBBY_ID_NOT_FOUND

}
