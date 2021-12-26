//
//  gameflow.hpp
//  blackjack-ultimate
//
//  Created by eric on 22/4/2021.
//

#ifndef gameflow_hpp
#define gameflow_hpp

#include <fstream>
#include <sstream>
#include <iomanip>

#include "card.hpp"
#include "deck.hpp"
#include "banker.hpp"
#include "player.hpp"

//Represent the record of a round
struct record {
    int principal, debt, due;
    bool loanStatus;
    int result;

    record(int principal, int result, bool loanStatus, int debt, int due) {
        this->principal = principal;
        this->result = result;
        this->loanStatus = loanStatus;
        this->debt = debt;
        this->due = due;
    }
};

//Display menu for each round and ask for input. Returns input value. Parameters : player, banker objects, string vector for option, and original principal for displaying the bet
int menu(player&, banker&, std::vector<std::string>&, int);

//Perform input validation for integer inputs: Parameter : integer that represents range for input [1,maxAction]. Return input value
int getSafeAction(int& maxActions);

//Ask the player how much to loan and return the loan amount. Parameter: plauyer object
int askLoan(player& p);

//Checking loan condition, perform loan payment or death after each round. 
//Returns 0 indicates player not dead after checking, returns 1 indicates player dead after checking. Parameter : player object.
int loan(player& p);

//Function that represents a round. Returns 0 to indicate normal operation. Parameters : player object and record vector
int round(player&, std::vector<record>&);

//Ask for bet input and return the input. Parameter : player object
int getBet(player&);

//Determine the winner of the round. 
//Return value -1 represents player wins, -2 represents draw, -3 represents player lose. Parameters : player object and banker object
int determine(player&, banker&);

//Display player and banker. 
//Parameters : player object, banker object, and a bool value that if true shows all cards, else hides first card and shows remaining cards
void displayPlayerAndBanker(player&, banker&, bool);

//Load the save file and initialize variables.  Parameter : record vector
player pregame(std::vector<record> &);

//Save the game by writing to file. Parameter : record vector
void savegame(std::vector<record>&);

//Show record function for the main menu.  Parameter : record vector
void showRecords(std::vector<record>&);

//Show the main menu and ask for input. Return the input value
int mainMenu();

//Prints story of the game
void displayStory();

#endif
