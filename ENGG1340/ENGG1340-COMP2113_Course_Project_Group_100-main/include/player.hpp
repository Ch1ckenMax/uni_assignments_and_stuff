//
//  player.hpp
//  blackjack-ultimate
//
//  Created by eric on 22/4/2021.
//

#ifndef player_hpp
#define player_hpp

//Loan metadata
#define DUE 5
#define INTEREST 1.3

#include "card.hpp"

#include <vector>
#include <iostream>

class player
{
private:
    std::vector<card> hand; //self-initalize with an empty vector
    int principal; //money amount
    bool loanStatus; //bool to determine got loan yet
    int debt, due; //loan debt and due round left
public:
    //Constructor. Parameters : initial values for the member variables.
    player(int, bool, int, int);

    //Display all hands
    void showHand();

    //Adding a card to the hand vector. Parameter : card object
    void appendHand(card);

    //Clearing all elements in the hand vector
    void clearHand();

    //Calculating the sum of cards in the hand vector
    int sumYourHandUp();

    //Change the money amount. Parameter: int manuver value and return remain, enter positive value to ad money, and vice versa
    int changePrincipalBy(int);

    //get functions that returns the memeber variables
    bool getloanStatus();
    int getDebt();
    int getDue();

    //Give loan to player. Returns 0 indicates success.
    int retrieveLoan(int);
    
    //Pay the loan. Returns 0 indicates success.
    int payLoan();
    
    //Calls at the end of every round if loan is given. Counts the due date and debt. Returns 0 indicates success.
    int countDue();

    //Prints loan information
    void displayLoanInformation();

    //Player dead. Reinitialize the member variables with the parameter values.
    void resetCharacter(int, bool, int, int);
};

#endif /* player_hpp */
