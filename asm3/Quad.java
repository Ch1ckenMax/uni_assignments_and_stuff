public class Quad extends Hand{
    //Constructor
    public Quad(CardGamePlayer player, CardList cards){
        super(player, cards);
        sort();
    }

    public boolean isValid(){ //SIMPLER APPROACH???
        if(size() != 5)
            return false;

        int rankOfFirstCard = getCard(0).getRank();
        int rankOfSecondCard = getCard(1).getRank();
        int numOfCards = 2;
        
        if(rankOfFirstCard == rankOfSecondCard){
            for(int i = 2; i < 5; i++){
                if(rankOfFirstCard == getCard(i).getRank())
                    numOfCards++;
            }

            if(numOfCards == 4)
                return true;
            else
                return false;
        }
        else{
            int rankOfThirdCard = getCard(2).getRank();
            if(rankOfFirstCard == rankOfThirdCard || rankOfSecondCard == rankOfThirdCard){
                for(int i = 3; i < 5; i++){
                    if(rankOfThirdCard == getCard(i).getRank())
                        numOfCards++;
                }
                if(numOfCards == 4)
                    return true;
                else
                    return false;
            }else{
                return false;
            }
        }
    }

    public Card getTopCard(){
        if(getCard(0).getRank() != getCard(1).getRank())
            return getCard(4);
        else
            return getCard(3);
    }
    
    public String getType(){
        return "Quad";
    }
    
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
