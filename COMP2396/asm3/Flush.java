/**
 * Models a hand of flush in a Big Two card game.
 * 
 * @author Li Hoi Kit
 */
public class Flush extends Hand{
    /**
     * Builds a hand of flush with the specified player and list of cards
     * 
     * @param player The specified player
     * @param cards The list of cards
     */
    public Flush(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    /**
     * Checks if the hand is valid
     * 
     * @return True if valid, False otherwise
     */
    public boolean isValid(){
        if(size() != 5)
            return false;
        
        //Same Suit
        for(int i = 1; i < 5; i++){
            if(getCard(0).getSuit() != getCard(i).getSuit())
                return false;
        }

        return true;
    }
    
    /**
     * Returns the type of the hand (i.e. the class name)
     * 
     * @return The type of hand
     */
    public String getType(){
        return "Flush";
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

        if(hand.getType() == "Straight")
            return true;
        else if(hand.getType() != "Flush")
            return false;

        Card card1 = this.getTopCard();
        Card card2 = hand.getTopCard();

        if(card1.getSuit() > card2.getSuit())
            return true;
        else if(card1.getSuit() < card2.getSuit())
            return false;
        else if(BigTwoCard.rankOrderToBigTwo(card1.getRank()) > BigTwoCard.rankOrderToBigTwo(card2.getRank()))
            return true;
        else 
            return false;
    }
}
