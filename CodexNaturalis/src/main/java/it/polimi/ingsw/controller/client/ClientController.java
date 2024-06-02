package it.polimi.ingsw.controller.client;

import it.polimi.ingsw.message.Message;

import it.polimi.ingsw.message.error.ErrorMessage;
import it.polimi.ingsw.message.notify.NotifyMessage;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;
import it.polimi.ingsw.view.UserInterface;

import java.util.List;

public class ClientController {
    private static ClientController instance;
    private UserInterface view;
    private ClientAction clientAction;

    public ClientController(){
        view = null;
        clientAction = null;
    }


    public static ClientController getInstance(){
        if (instance == null)
            instance = new ClientController();
        return instance;
    }

    public void messageHandler(Message message){
        switch (message.getType()) {
            case ERROR:
                ErrorMessage errorMessage = (ErrorMessage) message;
                switch (errorMessage.getError()) {
                    case CREDENTIAL_WRONG:
                        view.loginFailed(1);
                        break;
                    case ACCOUNT_ALREADY_LOGGED:
                        view.loginFailed(2);
                        break;
                    case NICKNAME_ALREADY_EXIST:
                        view.registrationFailed();
                        break;
                    case LOBBY_ID_NOT_FOUND:
                        view.lobbyChooseFailed();
                        break;
                    case INVALID_CARD_ID:
                        view.drawCardFailed();
                        break;
                    case GOLD_CARD_CONDITION_NOT_RESPECTED:
                        view.playCardFailed();
                        break;
                    default:
                        break;
                }
                break;
            case NOTIFY:
                NotifyMessage notifyMessage = (NotifyMessage) message;
                switch (notifyMessage.getNotifyType()) {
                    case CONNECTED:
                        view.connectionSuccess();
                        break;
                    case LOGIN_SUCCESS:
                        view.loginSuccess();
                        break;
                    case LOBBY_LIST:
                        view.setLobbyList((List<Integer[]>) (notifyMessage.getObject()));
                        break;
                    case LOBBY_STATUS:
                        view.setLobbyStatus((ImmutableLobby) (notifyMessage.getObject()));
                        break;
                    case GAME_STATUS:
                        view.setGameStatus((ImmutableGame) (notifyMessage.getObject()));
                        break;
                    case PLAYER_STATUS:
                        view.setPlayerStatus((ImmutablePlayer) (notifyMessage.getObject()));
                        break;
                    case PERSONAL_GOALS:
                        view.setPossiblePersonalGoals((Integer[])(notifyMessage.getObject()));
                        break;
                    case HAND_CARDS:
                        view.setHandCards((List<Integer>) (notifyMessage.getObject()));
                        break;
                    case END_GAME_INFO:
                        view.showFinalResult((ImmutableEndGameInfo) (notifyMessage.getObject()));
                        break;
                    case PLAYER_DISCONNECTED:
                        view.gameInterrupted((String) notifyMessage.getObject());
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    public void setView(UserInterface view) {
        this.view = view;
    }

    public UserInterface getView() {
        return view;
    }

    public void setClientAction(ClientAction clientAction) {
        this.clientAction = clientAction;
    }

    public ClientAction getClientAction() {
        return clientAction;
    }



}
