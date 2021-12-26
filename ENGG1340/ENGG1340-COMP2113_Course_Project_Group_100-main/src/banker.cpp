//
//  banker.cpp
//  blackjack-ultimate
//
//  Created by eric on 22/4/2021.
//

#include "../include/banker.hpp"

void banker::showHand(bool showAll)
{
    bool firstElement = true;
    for (auto e : banker::hand)
    {
        if (firstElement && !showAll)
        {
            firstElement = false;
            std::cout << "* ";
            continue;
        }
        std::cout << e.getFaceAndRank() << " ";
    }
    std::cout << '\n';
}

void banker::appendHand(card c)
{
    hand.push_back(c);
}

int banker::sumYourHandUp()
{
    int accumulator = 0;
    for (auto e : banker::hand)
    {
        if (RANGE(1, 9, e.getIndex() % 13))
        {
            accumulator += e.getIndex() % 13 + 1;
        }
        else if(RANGE(10, 12, e.getIndex() % 13))   //J, Q, K as 10
        {
            accumulator += 10;
        }
        else
        {
            (accumulator + 11 > 21) ? accumulator++ : accumulator += 11;    //An ace should be treated as 11 if it will not cause a bust, otherwise 1
        }
    }
    return accumulator;
}

std::vector<card> banker::getRawHand()
{
    std::vector<card> temp = banker::hand;
    return temp;
}

void banker::clearHand()
{
    while(banker::hand.size() != 0)
    {
        banker::hand.pop_back();
    }
}

banker::banker()
{
    if (banker::hand.size() != 0)
    {
        banker::clearHand();
    }
}
