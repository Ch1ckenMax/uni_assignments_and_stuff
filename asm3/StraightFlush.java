public class StraightFlush extends Hand{
    //Constructor
    public StraightFlush(CardGamePlayer player, CardList cards){
        super(player, cards);
        sort();
    }

    public boolean isValid(){
        if(size() != 5)
            return false;
        
        //Same Suit
        for(int i = 1; i < 5; i++){
            if(getCard(0).getSuit() != getCard(i).getSuit())
                return false;
        }

        //Consecutive Five Cards
        for(int i = 0; i < 4; i++){
            if(BigTwoCard.rankOrderToBigTwo(getCard(i).getRank()) + 1 != BigTwoCard.rankOrderToBigTwo(getCard(i+1).getRank()))
                return false;
        }

        return true;
    }
    
    public String getType(){
        return "StraightFlush";
    }
    
    public boolean beats(Hand hand){
        if(hand.size() != this.size())
            return false;

        if(hand.getType() != "StraightFlush")
            return true;

        if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
            return true;
        else
            return false;
    }
}
