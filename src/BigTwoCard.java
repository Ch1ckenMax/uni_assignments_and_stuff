/**
 * Models a card used in Big Two card game
 * 
 * @author Li Hoi Kit
 */
public class BigTwoCard extends Card{
    /**
	 * Creates and returns an instance of the Card class.
	 * 
	 * @param suit an int value between 0 and 3 representing the suit of a card:
	 *             <p>
	 *             0 = Diamond, 1 = Club, 2 = Heart, 3 = Spade
	 * @param rank an int value between 0 and 12 representing the rank of a card:
	 *             <p>
	 *             0 = 'A', 1 = '2', 2 = '3', ..., 8 = '9', 9 = '0', 10 = 'J', 11 =
	 *             'Q', 12 = 'K'
     */
    BigTwoCard(int suit, int rank){
        super(suit, rank);
    }

	/**
	 * Compares this card with the specified card for order in Big Two game rules.
	 * 
	 * @param card the card to be compared
	 * @return a negative integer, zero, or a positive integer as this card is less
	 *         than, equal to, or greater than the specified card
	 */
    public int compareTo(Card card){
        //Translate to bigTwo order for comparison
        int thisCardRank = rankOrderToBigTwo(this.getRank());
        int anotherCardRank = rankOrderToBigTwo(card.getRank());

        //Copied from the superclass
        if (thisCardRank > anotherCardRank) {
			return 1;
		} else if (thisCardRank < anotherCardRank) {
			return -1;
		} else if (this.suit > card.suit) {
			return 1;
		} else if (this.suit < card.suit) {
			return -1;
		} else {
			return 0;
		}
    }

    //This function translate the orignal rank order to BigTwo order
    public static int rankOrderToBigTwo(int rank){
        if(rank >= 2 && rank <= 12)
            return (rank - 2);
        else if(rank == 0)
            return 11;
        else
            return 12;
    }
}
