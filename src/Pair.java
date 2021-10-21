public class Pair extends Hand{
    //Constructor
    public Pair(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    
    //Methods
    public boolean isValid(){
        if(size() != 2)
            return false;
        
        Card firstCard = getCard(0);
        Card secondCard = getCard(1);

        if(firstCard.getRank() != secondCard.getRank())
            return false;

        return true;
    }

    public String getType(){
        return "Pair";
    }
}
