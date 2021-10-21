public abstract class Hand extends CardList{
    //Constructor
    public Hand(CardGamePlayer player, CardList cards){
        this.player = player;
        
        //Move the cards
        int numOfCards = cards.size();
        for(int i = 0; i < numOfCards; i++){
            Card currentCard = cards.getCard(i);
            this.addCard(currentCard);
        }
    }

    //Private members
    private CardGamePlayer player;

    //Public Methods
    public CardGamePlayer getPlayer(){
        return this.player;
    }

    public Card getTopCard(){
        Card topCard = getCard(0);
        for(int i = 1; i < size(); i++){
            if(getCard(i).compareTo(topCard) == 1)
                topCard = getCard(i);
        }
        return topCard;
    }

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
