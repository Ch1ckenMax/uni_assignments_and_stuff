/**
 * Models a hand of single in a Big Two card game.
 * 
 * @author Li Hoi Kit
 */
public class Single extends Hand{
    /**
     * Builds a hand of single with the specified player and list of cards
     * 
     * @param player The specified player
     * @param cards The list of cards
     */
    public Single(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    
    /**
     * Checks if the hand is valid
     * 
     * @return True if valid, False otherwise
     */
    public boolean isValid(){
        if(size() != 1)
            return false;
        else
            return true;
    }

    /**
     * Returns the type of the hand (i.e. the class name)
     * 
     * @return The type of hand
     */
    public String getType(){
        return "Single";
    }
}
