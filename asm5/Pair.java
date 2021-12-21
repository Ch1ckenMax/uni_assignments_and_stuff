/**
 * Models a hand of pair in a Big Two card game.
 * 
 * @author Li Hoi Kit
 */
public class Pair extends Hand{
    /**
     * Builds a hand of pair with the specified player and list of cards
     * 
     * @param player The specified player
     * @param cards The list of cards
     */
    public Pair(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    
    /**
     * Checks if the hand is valid
     * 
     * @return True if valid, False otherwise
     */
    public boolean isValid(){
        if(size() != 2)
            return false;
        
        if(getCard(0).getRank() != getCard(1).getRank())
            return false;

        return true;
    }

    /**
     * Returns the type of the hand (i.e. the class name)
     * 
     * @return The type of hand
     */
    public String getType(){
        return "Pair";
    }
}
