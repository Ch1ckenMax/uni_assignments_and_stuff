public class Flush extends Hand{
    //Constructor
    public Flush(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

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
    
    public String getType(){
        return "Flush";
    }
    
    public boolean beats(Hand hand){
        if(hand.size() != this.size())
            return false;

        if(hand.getType() == "Straight")
            return true;
        else if(hand.getType() == "FullHouse" || hand.getType() == "Quad" || hand.getType() == "StraightFlush")
            return false;

        if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
            return true;
        else
            return false;
    }
}
