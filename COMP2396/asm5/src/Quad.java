/**
 * Models a hand of quad in a Big Two card game.
 * 
 * @author Li Hoi Kit
 */
public class Quad extends Hand{
    /**
     * Builds a hand of quad with the specified player and list of cards
     * 
     * @param player The specified player
     * @param cards The list of cards
     */
    public Quad(CardGamePlayer player, CardList cards){
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

        boolean oneAndFour = getCard(0).getRank() == getCard(1).getRank() && getCard(1).getRank() == getCard(2).getRank() && getCard(2).getRank() == getCard(3).getRank() && getCard(3).getRank() != getCard(4).getRank();
        boolean fourAndOne = getCard(0).getRank() != getCard(1).getRank() && getCard(1).getRank() == getCard(2).getRank() && getCard(2).getRank() == getCard(3).getRank() && getCard(3).getRank() == getCard(4).getRank();
        if(oneAndFour || fourAndOne)
            return true;
        else
            return false;

    }

    /**
     * Getter method for retrieving the top card of this hand
     * 
     * @return The top card of this hand
     */
    public Card getTopCard(){
        if(getCard(0).getRank() != getCard(1).getRank())
            return getCard(4);
        else
            return getCard(3);
    }
    
    /**
     * Returns the type of the hand (i.e. the class name)
     * 
     * @return The type of hand
     */
    public String getType(){
        return "Quad";
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

        if(hand.getType() == "Straight" || hand.getType() == "Flush" || hand.getType() == "FullHouse")
            return true;
        else if(hand.getType() == "StraightFlush")
            return false;

        if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
            return true;
        else
            return false;
    }
}
