public class Test {
    public static void main(String[] args){
        CardGamePlayer p1 = new CardGamePlayer();
        CardGamePlayer p2 = new CardGamePlayer();

        Card card1 = new BigTwoCard(0,0);
        Card card2 = new BigTwoCard(0,4);
        Card card3 = new BigTwoCard(0,7);
        Card card4 = new BigTwoCard(0,2);
        Card card5 = new BigTwoCard(0,9);
        Card card6 = new BigTwoCard(0,8);
        Card card7 = new BigTwoCard(0,1);
        Card card8 = new BigTwoCard(0,12);
        Card card9 = new BigTwoCard(0,3);
        Card card10 = new BigTwoCard(0,5);
        CardList cardlist1 = new CardList();
        cardlist1.addCard(card1);
        cardlist1.addCard(card2);
        cardlist1.addCard(card3);
        cardlist1.addCard(card4);
        cardlist1.addCard(card5);
        CardList cardlist2 = new CardList();
        cardlist2.addCard(card6);
        cardlist2.addCard(card7);
        cardlist2.addCard(card8);
        cardlist2.addCard(card9);
        cardlist2.addCard(card10);


        Hand hand1 = new Flush(p1, cardlist1);
        Hand hand2 = new Flush(p2, cardlist2);

        //System.out.println(hand1.getCard(0).toString());
        //System.out.println(hand2.getCard(0).toString());
        System.out.println(hand1.getTopCard().toString());
        System.out.println(hand2.getTopCard().toString());
        System.out.println(hand1.isValid());
        System.out.println(hand2.isValid());
        System.out.println(hand1.beats(hand2));
        System.out.println(hand2.beats(hand1));
    }
}
