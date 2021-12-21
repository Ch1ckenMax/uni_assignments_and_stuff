/**
 * Models a hand of full house in a Big Two card game.
 * 
 * @author Li Hoi Kit
 */
public class FullHouse extends Hand{
    /**
     * Builds a hand of full house with the specified player and list of cards
     * 
     * @param player The specified player
     * @param cards The list of cards
     */
    public FullHouse(CardGamePlayer player, CardList cards){
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

        //The cards are sorted in the constructor
        boolean twoAndThree = getCard(0).getRank() == getCard(1).getRank() && getCard(1).getRank() != getCard(2).getRank() && getCard(2).getRank() == getCard(3).getRank() && getCard(3).getRank() == getCard(4).getRank();
        boolean threeAndTwo = getCard(0).getRank() == getCard(1).getRank() && getCard(1).getRank() == getCard(2).getRank() && getCard(2).getRank() != getCard(3).getRank() && getCard(3).getRank() == getCard(4).getRank();
        if(twoAndThree || threeAndTwo)
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
        if(getCard(1).getRank() != getCard(2).getRank()){
            return getCard(4);
        }
        else{
            return getCard(2);
        }
    }
    
    /**
     * Returns the type of the hand (i.e. the class name)
     * 
     * @return The type of hand
     */
    public String getType(){
        return "FullHouse";
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

        if(hand.getType() == "Straight" || hand.getType() == "Flush")
            return true;
        else if(hand.getType() != "FullHouse")
            return false;

        if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
            return true;
        else
            return false;
    }
}
