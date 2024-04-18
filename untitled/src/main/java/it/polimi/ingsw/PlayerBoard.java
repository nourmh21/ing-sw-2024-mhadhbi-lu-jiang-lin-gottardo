package it.polimi.ingsw;
import java.util.List;

//+ addInitialCard(initialCardId: Card): void -->nel costruttore
//+ setSymbolList(): void
//+ addCardList(handCard: Card): void
//+ addHandCard(card: Card): void
//+ getHandCard(): Card[]

public class PlayerBoard {
    private List<Card> cardList;
    private List<Card> handList;
    private int[] symbolsList = new int[7];

    /**
     * @param cardList define list of card use on the board.
     * @param handList define list of card in hnd of player.
     * @param symbolsList define the resources and objects visible in the Playerboard.
     */
    public PlayerBoard(List<Card> handList,List<Card> cardList,int[] symbolsList) {
        this.handList = handList;
        this.cardList= cardList;
        this.symbolsList=symbolsList;
    }
    /**
     * @return
     */
    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    /**
     * @return
     */
    public List<Card> getCardList() {
        return cardList;
    }
    /**
     * @return
     */
    public List<Card> addcardList(Card card){

    }
}
