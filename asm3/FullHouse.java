public class FullHouse extends Hand{
    //Constructor
    public FullHouse(CardGamePlayer player, CardList cards){
        super(player, cards);
        sort();
    }

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

    public Card getTopCard(){
        boolean twoAndThree = getCard(0).getRank() == getCard(1).getRank() && getCard(1).getRank() != getCard(2).getRank() && getCard(2).getRank() == getCard(3).getRank() && getCard(3).getRank() == getCard(4).getRank();
        if(twoAndThree){
            return getCard(4);
        }
        else{
            return getCard(2);
        }
    }
    
    public String getType(){
        return "FullHouse";
    }
    
    public boolean beats(Hand hand){
        if(hand.size() != this.size())
            return false;

        if(hand.getType() == "Straight" || hand.getType() == "Flush")
            return true;
        else if(hand.getType() == "StraightFlush" || hand.getType() == "Quad" )
            return false;

        if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
            return true;
        else
            return false;
    }
}
