/**
 * Models a hand of straight flush in a Big Two card game.
 * 
 * @author Li Hoi Kit
 */
public class StraightFlush extends Hand{
    /**
     * Builds a hand of straight flush with the specified player and list of cards
     * 
     * @param player The specified player
     * @param cards The list of cards
     */
    public StraightFlush(CardGamePlayer player, CardList cards){
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

        //Consecutive Five Cards
        for(int i = 0; i < 4; i++){
            if(BigTwoCard.rankOrderToBigTwo(getCard(i).getRank()) + 1 != BigTwoCard.rankOrderToBigTwo(getCard(i+1).getRank()))
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
        return "StraightFlush";
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

        if(hand.getType() != "StraightFlush")
            return true;

        if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
            return true;
        else
            return false;
    }
}
