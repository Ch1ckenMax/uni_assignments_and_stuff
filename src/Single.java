public class Single extends Hand{
    //Constructor
    public Single(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    
    //Methods
    public boolean isValid(){
        if(size() != 1)
            return false;
        else
            return true;
    }

    public String getType(){
        return "Single";
    }
}
