/**
 * Models a hand of cards.
 * 
 * @author Li Hoi Kit
 */
public abstract class Hand extends CardList{
    /**
     * Builds a hand with the specified player and list of cards
     * 
     * @param player The specified player
     * @param cards The list of cards
     */
    public Hand(CardGamePlayer player, CardList cards){
        this.player = player;
        
        //Move the cards
        int numOfCards = cards.size();
        for(int i = 0; i < numOfCards; i++){
            Card currentCard = cards.getCard(i);
            this.addCard(currentCard);
        }

        sort();
    }

    //Private members
    private CardGamePlayer player;

    //Public Methods
    /**
     * Getter method for retrieving the player of this hand
     * 
     * @return The player of this hand
     */
    public CardGamePlayer getPlayer(){
        return this.player;
    }

    /**
     * Getter method for retrieving the top card of this hand
     * 
     * @return The top card of this hand
     */
    public Card getTopCard(){
        Card topCard = getCard(0);
        for(int i = 1; i < size(); i++){
            if(getCard(i).compareTo(topCard) == 1)
                topCard = getCard(i);
        }
        return topCard;
    }

    /**
     * Checks if this hand beats a specified hand
     * 
     * @param hand The specified hand
     * @return True if beats, false otherwise
     */
    public boolean beats(Hand hand){
        if(hand.size() != this.size())
            return false;

        if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
            return true;
        else
            return false;
    }

    //Abstract Methods
    public abstract boolean isValid();
    public abstract String getType();
}
