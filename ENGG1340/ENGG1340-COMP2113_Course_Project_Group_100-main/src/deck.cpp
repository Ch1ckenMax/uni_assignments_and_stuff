//
//  deck.cpp
//  blackjack-ultimate
//
//  Created by eric on 22/4/2021.
//

#include "../include/deck.hpp"

deck::deck()
{
    deck::suffle();
}

int deck::deal()
{
    int temp = 0;
    do
    {
        temp = rand() % 104;
    }while (!deck::cards[temp]);
    return temp;
}

void deck::suffle()
{
    srand(static_cast<unsigned int>(time(nullptr)));
    for (int i = 0; i < 104; i++)
    {
        deck::cards[i] = true;
    }
}
