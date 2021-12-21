/**
 * Models a hand of triple in a Big Two card game.
 * 
 * @author Li Hoi Kit
 */
public class Triple extends Hand{
    /**
     * Builds a hand of triple with the specified player and list of cards
     * 
     * @param player The specified player
     * @param cards The list of cards
     */
    public Triple(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    
    /**
     * Checks if the hand is valid
     * 
     * @return True if valid, False otherwise
     */
    public boolean isValid(){
        if(size() != 3)
            return false;

        if(getCard(0).getRank() != getCard(1).getRank() || getCard(1).getRank() != getCard(2).getRank())
            return false;

        return true;
    }

    /**
     * Returns the type of the hand (i.e. the class name)
     * 
     * @return The type of hand
     */
    public String getType(){
        return "Triple";
    }
}
