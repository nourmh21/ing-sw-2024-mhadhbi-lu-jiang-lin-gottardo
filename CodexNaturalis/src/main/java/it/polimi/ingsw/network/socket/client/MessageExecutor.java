package it.polimi.ingsw.network.socket.client;

import it.polimi.ingsw.controller.client.ClientController;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.enums.MessageType;
import it.polimi.ingsw.message.enums.NotifyType;
import it.polimi.ingsw.message.error.ErrorMessage;
import it.polimi.ingsw.message.general.ChatMessage;
import it.polimi.ingsw.message.notify.NotifyMessage;
import it.polimi.ingsw.model.immutable.ImmutableEndGameInfo;
import it.polimi.ingsw.model.immutable.ImmutableGame;
import it.polimi.ingsw.model.immutable.ImmutableLobby;
import it.polimi.ingsw.model.immutable.ImmutablePlayer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The MessageExecutor is a class that handles messages
 */
public class MessageExecutor {
    private final ExecutorService normalMsgExecutor;
    private final ExecutorService chatMsgExecutor;

    /**
     * Construct: initialize two Executor
     */
    public MessageExecutor(){
        normalMsgExecutor = Executors.newSingleThreadExecutor();
        chatMsgExecutor = Executors.newSingleThreadExecutor();
    }

    /**
     * Executes tasks for messages handle
     * @param message the {@link Message} received
     */
    public void execute(Message message){
        if (message.getType().equals(MessageType.CHAT)){
            chatMsgExecutor.execute(() -> messageHandler(message));
        }else {
            normalMsgExecutor.execute(()->messageHandler(message));
        }
    }

    /**
     * Handles messages
     * @param message the {@link Message} received
     */
    private void messageHandler(Message message){
        switch (message.getType()) {
            case ERROR:
                ErrorMessage errorMessage = (ErrorMessage) message;
                switch (errorMessage.getError()) {
                    case CREDENTIAL_WRONG:
                        ClientController.getInstance().getView().loginFailed(1);
                        break;
                    case ACCOUNT_ALREADY_LOGGED:
                        ClientController.getInstance().getView().loginFailed(2);
                        break;
                    case NICKNAME_ALREADY_EXIST:
                        ClientController.getInstance().getView().registrationFailed();
                        break;
                    case LOBBY_ID_NOT_FOUND:
                        ClientController.getInstance().getView().lobbyChooseFailed();
                        break;
                    case INVALID_CARD_ID:
                        ClientController.getInstance().getView().drawCardFailed();
                        break;
                    case GOLD_CARD_CONDITION_NOT_RESPECTED:
                        ClientController.getInstance().getView().playCardFailed();
                        break;
                    default:
                        break;
                }
                break;
            case NOTIFY:
                NotifyMessage notifyMessage = (NotifyMessage) message;
                if (notifyMessage.getNotifyType() == NotifyType.CONNECTED) {
                    ClientController.getInstance().getView().connectionSuccess();
                }else if (ClientController.getInstance().isConnected()){
                    switch (notifyMessage.getNotifyType()) {
                        case LOGIN_SUCCESS:
                            ClientController.getInstance().getView().loginSuccess();
                            break;
                        case LOBBY_LIST:
                            ClientController.getInstance().getView().setLobbyList((List<Integer[]>) (notifyMessage.getObject()));
                            break;
                        case LOBBY_STATUS:
                            ClientController.getInstance().getView().setLobbyStatus((ImmutableLobby) (notifyMessage.getObject()));
                            break;
                        case GAME_STATUS:
                            ClientController.getInstance().getView().setGameStatus((ImmutableGame) (notifyMessage.getObject()));
                            break;
                        case PLAYER_STATUS:
                            ClientController.getInstance().getView().setPlayerStatus((ImmutablePlayer) (notifyMessage.getObject()));
                            break;
                        case PERSONAL_GOALS:
                            ClientController.getInstance().getView().setPossiblePersonalGoals((Integer[])(notifyMessage.getObject()));
                            break;
                        case HAND_CARDS:
                            ClientController.getInstance().getView().setHandCards((List<Integer>) (notifyMessage.getObject()));
                            break;
                        case END_GAME_INFO:
                            ClientController.getInstance().getView().showFinalResult((ImmutableEndGameInfo) (notifyMessage.getObject()));
                            break;
                        case PLAYER_DISCONNECTED:
                            ClientController.getInstance().getView().gameInterrupted((String) notifyMessage.getObject());
                            break;
                        default:
                            break;
                    }
                }
                break;
            case CHAT:
                if (ClientController.getInstance().isConnected())
                    ClientController.getInstance().getView().addNewChatMessage((ChatMessage) message);
                break;
            default:
                break;
        }
    }
}
