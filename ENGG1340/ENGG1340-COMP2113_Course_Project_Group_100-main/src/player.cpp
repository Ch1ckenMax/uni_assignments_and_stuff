//
//  player.cpp
//  blackjack-ultimate
//
//  Created by eric on 22/4/2021.
//

#include "../include/player.hpp"

void player::showHand()
{
    for (auto e : player::hand)
    {
        std::cout << e.getFaceAndRank() << " ";
    }
    std::cout << '\n';
}

void player::appendHand(card c)
{
    hand.push_back(c);
}

int player::sumYourHandUp()
{
    int accumulator = 0;
    for (auto e : player::hand)
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

int player::changePrincipalBy(int manuver)
{
    player::principal += manuver;
    return player::principal;
}

player::player(int principal, bool loanStatus, int debt, int due)
{
    if (player::hand.size() != 0)
    {
        player::clearHand();
    }
    player::principal = principal;
    player::loanStatus = loanStatus;
    player::debt = debt;
    player::due = due;
}

void player::clearHand()
{
    while(player::hand.size() != 0)
    {
        player::hand.pop_back();
    }
}

bool player::getloanStatus(){
    return player::loanStatus;
}
int player::getDebt() {
    return player::debt;
}
int player::getDue() {
    return player::due;
}

int player::retrieveLoan(int amount) {
    player::loanStatus = true;
    player::due = DUE;
    player::debt = amount;
    player::principal = player::principal + amount;
    return 0;
}
int player::payLoan() {
    player::loanStatus = false;
    player::principal = player::principal - player::debt;
    player::debt = 0;
    player::due = 0;
    return 0;
}
int player::countDue() {
    player::due--;
    player::debt = int(player::debt * INTEREST);
    return 0;
}

void player::displayLoanInformation() {
    std::cout << "Your Loan Information :" << std::endl;
    std::cout << "Got a loan : "; if (player::loanStatus) std::cout << "Yes"; else std::cout << "No"; std::cout << std::endl;
    if (player::loanStatus) {
        std::cout << "Due in : " << player::due << " rounds" << std::endl;
        std::cout << "Debt : " << player::debt << std::endl;
        std::cout << "Interest rate (per round) : " << INTEREST << std::endl;
    }
}

void player::resetCharacter(int p, bool lS, int de, int du) {
    if (player::hand.size() != 0)
    {
        player::clearHand();
    }
    player::principal = p;
    player::loanStatus = lS;
    player::debt = de;
    player::due = du;
}
