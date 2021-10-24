public class Straight extends Hand{
    //Constructor
    public Straight(CardGamePlayer player, CardList cards){
        super(player, cards);
        sort();
    }
    
    public boolean isValid(){
        if(size() != 5)
            return false;
        
        for(int i = 0; i < 4; i++){
            if(BigTwoCard.rankOrderToBigTwo(getCard(i).getRank()) + 1 != BigTwoCard.rankOrderToBigTwo(getCard(i+1).getRank()))
                return false;
        }

        return true;
    }
    
    public String getType(){
        return "Straight";
    }
    
    public boolean beats(Hand hand){
        if(hand.size() != this.size())
            return false;
            
        if(hand.getType() == "Flush" || hand.getType() == "FullHouse" || hand.getType() == "Quad" || hand.getType() == "StraightFlush")
            return false;

        if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
            return true;
        else
            return false;
    }
}
