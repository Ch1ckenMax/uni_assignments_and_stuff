//
//  card.hpp
//  blackjack-ultimate
//
//  Created by eric on 22/4/2021.
//

#ifndef card_hpp
#define card_hpp

#include <string>

#define SPADE   "\xE2\x99\xA0"  //define suits
#define CLUB    "\xE2\x99\xA3"
#define HEART   "\xE2\x99\xA5"
#define DIAMOND "\xE2\x99\xA6"

#define RANGE(min, max, x) (((min <= x) && (x <= max)) ? 1 : 0)

class card
{
private:
    //Ranges from 0 to 103
    //0 to 51 represents the first set of cards, 52 to 103 represents the second set of cards
    int value;
public:
    //Constructor. Parameter : card value
    card(int);

    //Get function that returns the card value
    int getIndex();

    //Get function that returns the rank of a card. Parameter : card value
    std::string getRank(int);

    //Get function that determine if a card's face is spade, club, heart, or diamond and return the value
    std::string getFaceAndRank();
};

#endif /* card_hpp */
