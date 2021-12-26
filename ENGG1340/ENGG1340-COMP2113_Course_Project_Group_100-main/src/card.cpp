//
//  card.cpp
//  blackjack-ultimate
//
//  Created by eric on 22/4/2021.
//

#include "../include/card.hpp"

card::card(int val)
{
    card::value = val;
}

int card::getIndex()
{
    return card::value;
}

std::string card::getRank(int _)
{
    _ %= 13;
    
    if (RANGE(1, 9, _))
    {
        return std::to_string(++_);
    }
    else if (_ == 0)
    {
        return "A";
    }
    else if (_ == 10)
    {
        return "J";
    }
    else if (_ == 11)
    {
        return "Q";
    }
    else
    {
        return "K";
    }
}

std::string card::getFaceAndRank()
{
    if (RANGE(0, 12, card::value) || RANGE(52, 63, card::value))
    {
        return card::getRank(card::value).append(SPADE);
    }
    else if (RANGE(13, 25, card::value) || RANGE(64, 76, card::value))
    {
        return card::getRank(card::value).append(CLUB);
    }
    else if (RANGE(26, 38, card::value) || RANGE(77, 89, card::value))
    {
        return card::getRank(card::value).append(HEART);
    }
    else
    {
        return card::getRank(card::value).append(DIAMOND);
    }
}
