import java.util.ArrayList;

/**
 * Models a Big Two card game.
 * 
 * @author Li Hoi Kit
 */
public class BigTwo implements CardGame{
    //Constructor

    /**
     * Creates a big two game. Initializes players list and UI.
     */
    public BigTwo(){
        //Create 4 players and add them to playerList
        this.numOfPlayers = 4;
        for(int i = 0; i < 4; i++){
            CardGamePlayer player = new CardGamePlayer();
            playerList.add(player);
        }

        //BigTwoUI
        gui = new BigTwoGUI(this);
    }

    //Private Variables
    private int numOfPlayers;
    private Deck deck;
    private ArrayList<CardGamePlayer> playerList = new ArrayList<CardGamePlayer>();
    private ArrayList<Hand> handsOnTable = new ArrayList<Hand>();
    private int currentPlayerIdx;
    private BigTwoGUI gui;

    //Getter Methods

    /**
     * Getter method for retrieving the number of players
     * 
     * @return Number of players
     */
    public int getNumOfPlayers(){
        return this.numOfPlayers;
    }

    /**
     * Getter method for retrieving the deck
     * 
     * @return The deck of cards being used
     */
    public Deck getDeck(){
        return this.deck;
    }

    /**
     * Getter method for retrieving the list of players
     * 
     * @return The list of players
     */
    public ArrayList<CardGamePlayer> getPlayerList(){
        return this.playerList;
    }
    
    /**
     * Getter method for retrieving the list of hands played on the table
     * 
     * @return The list of hands played on the table
     */
    public ArrayList<Hand> getHandsOnTable(){
        return this.handsOnTable;
    }

    /**
     * Getter method for retrieving the index of the current player
     * 
     * @return The index of the current player
     */
    public int getCurrentPlayerIdx(){
        return this.currentPlayerIdx;
    }

    /**
     * Start the BigTwo game with a given shuffled deck of cards.
     * 
     * @param deck The deck of cards used in the game
     */
    public void start(Deck deck){
        //Remove all cards from players and tables
        for(int i = 0; i < numOfPlayers; i++){
            playerList.get(i).removeAllCards();
        }
        handsOnTable.clear();

        //Distribute cards to players
        int playerIdxToAdd = 0;
        for(int i = 0; i < deck.size(); i++){
            playerList.get(playerIdxToAdd).addCard(deck.getCard(i));
            playerIdxToAdd = (playerIdxToAdd + 1) % numOfPlayers;
        }

        //Identify the player who holds the three of diamonds
        Card threeOfDiamond = new Card(0,2);
        for(int i = 0; i < numOfPlayers; i++){
            if(playerList.get(i).getCardsInHand().contains(threeOfDiamond)){
                currentPlayerIdx = i;
                break;
            }
        }

        gui.setActivePlayer(currentPlayerIdx);
        playerList.get(currentPlayerIdx).sortCardsInHand();
        gui.repaint();
        gui.promptActivePlayer();
    }

    /**
     * Makes a move by a player with specified index using the cards specified by the list of indices.
     * Prints the hand and the type of hand if the move contains cards and it is a legal move.
     * Prints {Pass} if the hand of the move is empty and it is a legal move.
     * Prints "Not a legal move!!!" if the move is illegal.
     * 
     * @param playerIdx The index of the player who made this move
     * @param cardIdx The list of cards in the player's cards list chosen to make the move
     */
    public void makeMove(int playerIdx, int[] cardIdx){
        checkMove(playerIdx, cardIdx);

        if(cardIdx != null){ //If there is input
            //Determine hand
            CardList cards = new CardList();
            for(int i = 0; i < cardIdx.length; i++){
                //Invalid input range
                if(cardIdx[i] >= playerList.get(playerIdx).getNumOfCards() || cardIdx[i] < 0){
                    gui.printMsg("Not a legal move!!!");
                    return;
                }

                Card temp = playerList.get(playerIdx).getCardsInHand().getCard(cardIdx[i]);
                cards.addCard(temp);
            }
            Hand hand = composeHand(playerList.get(playerIdx), cards);

            //If hand is valid
            if(hand != null){
                if(handsOnTable.isEmpty() || hand.beats(handsOnTable.get(handsOnTable.size() - 1)) || handsOnTable.get(handsOnTable.size() - 1).getPlayer() == playerList.get(playerIdx)){ //If its a valid move that throws cards out
                    handsOnTable.add(hand);//Add cards to table
                    playerList.get(playerIdx).removeCards(cards);//Remove cards from player
                    gui.printMsg("{"+hand.getType()+"} "+hand.toString());
                }
                else{ //If this hand cannot beat the previous hand and it is not first round/dead round
                    gui.printMsg("Not a legal move!!!");
                }
            }
            else{ //If hand is invalid
                gui.printMsg("Not a legal move!!!");
            }
        }
        else{ //If input is empty
            if(handsOnTable.isEmpty() || handsOnTable.get(handsOnTable.size() - 1).getPlayer() == playerList.get(playerIdx)){ //First round or dead round
                gui.printMsg("Not a legal move!!!");
            }
            else{
                gui.printMsg("{Pass}");
            }
        }
    }

    /**
     * Checks if the specified move made by the player is valid.
     * Switches the current player to the next player by modifying currentPlayerIdx if it is valid. Does nothing otherwise.
     * 
     * @param playerIdx The index of the player who made this move
     * @param cardIdx The list of cards in the player's cards list chosen to make the move
     */
    public void checkMove(int playerIdx, int[] cardIdx){
        if(cardIdx == null){ //empty input
            if(handsOnTable.isEmpty() || handsOnTable.get(handsOnTable.size() - 1).getPlayer() == playerList.get(playerIdx)){
                return; //first round or dead round, cannot have empty input
            }
            else{
                currentPlayerIdx = (currentPlayerIdx + 1) % numOfPlayers;
                return; //valid empty input: pass
            }
        }

        //Determine hand
        CardList cards = new CardList();
        for(int i = 0; i < cardIdx.length; i++){
            //Invalid range of input
            if(cardIdx[i] >= playerList.get(playerIdx).getNumOfCards() || cardIdx[i] < 0){
                return;
            }

            Card temp = playerList.get(playerIdx).getCardsInHand().getCard(cardIdx[i]);
            cards.addCard(temp);
        }
        Hand hand = composeHand(playerList.get(playerIdx), cards);

        if(hand == null){ //invalid hand
            return;
        }
        else{
            if(handsOnTable.isEmpty() || hand.beats(handsOnTable.get(handsOnTable.size() - 1)) || handsOnTable.get(handsOnTable.size() - 1).getPlayer() == playerList.get(playerIdx))
                currentPlayerIdx = (currentPlayerIdx + 1) % numOfPlayers;
            return; //first round, or beat the previous hand on table, or dead round
        }
    }

    /**
     * Checks if the game ends
     * 
     * @return Truth value of whether the game ends
     */
    public boolean endOfGame(){
        for(int i = 0; i < numOfPlayers; i++){
            if(playerList.get(i).getNumOfCards() == 0)
                return true; //If one of the player has no card
        }
        return false; //for all players there exist cards
    }

    //Static Methods

    /**
     * Starts a Big Two card game.
     * 
     * @param args Not used in this application.
     */
    public static void main(String[] args){
        CardGame theGame = new BigTwo();

        //Create and shuffle deck of cards
        Deck theDeck = new BigTwoDeck();
        theDeck.shuffle();
    
        theGame.start(theDeck);
    }

    /**
     * Checks if there is a valid hand from the specified list of cards, and return the valid hand if there is one.
     * 
     * @param player The index of the player that chose these cards
     * @param cards The specified list of cards to be determined by the method
     * @return The valid hand, or null if there does not exist a valid hand.
     */
    public static Hand composeHand(CardGamePlayer player, CardList cards){
        //Check by size first
        Hand single = new Single(player, cards);
        Hand pair = new Pair(player, cards);
        Hand triple = new Triple(player, cards);
        if(single.isValid()) return single;
        if(pair.isValid()) return pair;
        if(triple.isValid()) return triple;

        //Check straight and flush first
        Hand straightFlush = new StraightFlush(player, cards);
        if(straightFlush.isValid()) return straightFlush;

        //Not both, check distinct
        Hand straight = new Straight(player, cards);
        Hand flush = new Flush(player, cards);
        if(straight.isValid()) return straight;
        if(flush.isValid()) return flush;

        //Check the remaining
        Hand fullHouse = new FullHouse(player, cards);
        Hand quad = new Quad(player, cards);
        if(fullHouse.isValid()) return fullHouse;
        if(quad.isValid()) return quad;

        //No valid hand
        return null;
    }
}
