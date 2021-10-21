import java.util.ArrayList;

public class BigTwo implements CardGame{
    //Constructor

    public BigTwo(){
        //Create 4 players and add them to playerList
        this.numOfPlayers = 4;
        for(int i = 0; i < 4; i++){
            CardGamePlayer player = new CardGamePlayer();
            playerList.add(player);
        }

        //BigTwoUI
        ui = new BigTwoUI(this); //DOES THIS WORK AT ALL?
    }
    
    //Private members

    //Variables
    private int numOfPlayers;
    private Deck deck;
    private ArrayList<CardGamePlayer> playerList;
    private ArrayList<Hand> handsOnTable;
    private int currentPlayerIdx;
    private BigTwoUI ui;

    //Public Members

    //Methods
    public int getNumOfPlayers(){
        return this.numOfPlayers;
    }

    public Deck getDeck(){
        return this.deck;
    }

    public ArrayList<CardGamePlayer> getPlayerList(){
        return this.playerList;
    }
    
    public ArrayList<Hand> getHandsOnTable(){
        return this.handsOnTable;
    }

    public void start(Deck deck){

    }

    public void makeMove(int playerIdx, int[] cardIdx){

    }

    public void checkMove(int playerIdx, int[] cardIdx){

    }

    public boolean endOfGame(){

    }

    //Static Methods

    public static void main(String[] args){

    }

    public static Hand composeHand(CardGamePlayer player, CardList cards){

    }

}
