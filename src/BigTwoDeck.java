/**
 * Models a deck of cards used in a Big Two card game
 * 
 * @author Li Hoi Kit
 */
public class BigTwoDeck extends Deck{

    /**
     * Initializes the deck of cards (called implicitly inside the superclass's constructor).
    */
    public void initialize(){
        removeAllCards();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				Card card = new BigTwoCard(i, j);
				addCard(card);
			}
		}
    }
}
