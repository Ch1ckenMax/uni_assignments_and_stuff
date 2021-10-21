public class Straight extends Hand{
    //Constructor
    public Straight(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    
    public boolean isValid(){
        if(size() != 5)
            return false;

        sort();
        for(int i = 0; i < 4; i++){
            if(BigTwoCard.rankOrderToBigTwo(getCard(i).getRank()) != BigTwoCard.rankOrderToBigTwo(getCard(i+1).getRank()) + 1)
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
