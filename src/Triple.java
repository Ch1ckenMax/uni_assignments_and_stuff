public class Triple extends Hand{
    //Constructor
    public Triple(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    
    //Methods
    public boolean isValid(){
        if(size() != 3)
            return false;
        
        Card firstCard = getCard(0);
        Card secondCard = getCard(1);
        Card thirdCard = getCard(2);

        if(firstCard.getRank() != secondCard.getRank() || secondCard.getRank() != thirdCard.getRank())
            return false;

        return true;
    }

    public String getType(){
        return "Triple";
    }
}
