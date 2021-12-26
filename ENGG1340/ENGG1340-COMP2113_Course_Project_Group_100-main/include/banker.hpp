//
//  banker.hpp
//  blackjack-ultimate
//
//  Created by eric on 22/4/2021.
//

#ifndef banker_hpp
#define banker_hpp

#include "card.hpp"

#include <vector>
#include <iostream>

class banker
{
private:
    std::vector<card> hand;
public:

    //Constructor for banker. Clear all the cards in hand, just in case.
    banker();
    
    //Printing hand of cards
    //Parameter : True = print all cards to console, False = hide the first card and display the remaining cards to console
    void showHand(bool);

    //Adding a card to the hand vector. Parameter : card object
    void appendHand(card);

    //Clearing all elements in the hand vector
    void clearHand();

    //Calculating the sum of cards in the hand vector
    int sumYourHandUp();

    //Get function that returns hand vector
    std::vector<card> getRawHand();
};

#endif /* banker_hpp */
