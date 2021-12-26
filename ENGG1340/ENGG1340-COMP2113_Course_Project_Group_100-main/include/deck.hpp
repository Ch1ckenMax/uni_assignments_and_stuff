//
//  deck.hpp
//  blackjack-ultimate
//
//  Created by eric on 22/4/2021.
//

#ifndef deck_hpp
#define deck_hpp

#include <ctime>
#include <random>

class deck
{
private:
    //the game uses double decks, that is in total 104 cards
    //Storing true means the card is available, and storing alse means the card is unavailable
    bool cards[104];
public:
    //Constructor, set all card available value to true by suffle() function
    deck();

    //Randomly generate a card value, check if the card is available and returns the value
    int deal();

    //"collect" all distributed cards by setting every card available value to true
    void suffle();
};

#endif /* deck_hpp */
