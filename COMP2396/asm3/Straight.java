/**
 * Models a hand of straight in a Big Two card game.
 * 
 * @author Li Hoi Kit
 */
public class Straight extends Hand{
    /**
     * Builds a hand of straight with the specified player and list of cards
     * 
     * @param player The specified player
     * @param cards The list of cards
     */
    public Straight(CardGamePlayer player, CardList cards){
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
        return "Straight";
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
            
        if(hand.getType() != "Straight")
            return false;

        if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
            return true;
        else
            return false;
    }
}
