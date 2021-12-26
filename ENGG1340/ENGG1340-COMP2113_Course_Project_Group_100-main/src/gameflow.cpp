//
//  gameflow.cpp
//  blackjack-ultimate
//
//  Created by eric on 22/4/2021.
//

#include "../include/gameflow.hpp"

void displayPlayerAndBanker(player& p, banker& b, bool bankerShowAll)
{
    std::cout << "\n";
    std::cout << "Your's hand: ";
    p.showHand();
    std::cout << "Banker's hand: ";
    b.showHand(bankerShowAll);
    
    if (!bankerShowAll)
    {
        std::cout << "In numerial form, you have " << p.sumYourHandUp() << " points." << std::endl;
    }
    else
    {
        std::cout << "In numerial form, you have " << p.sumYourHandUp() << " points and dealer has " << b.sumYourHandUp()  << "." << std::endl;
    }
    
}

int getSafeAction(int& maxActions)
{
    int temp = 1;
    
reInput:
    try
    {
        do
        {
            std::cout << "Note that the input must be an integer between 1 and " << maxActions << ": ";
            std::cin >> temp;
            std::cin.clear();
            std::cin.ignore(1000, '\n');
        } while (!RANGE(1, maxActions, temp));
    }
    catch (...)
    {
        std::cout << "Input must be an integer between 1 and " << maxActions << std::endl;
        goto reInput;
    }
    
    return temp;
}

int getBet(player& p)
{
    int temp = 0;
    do
    {
        std::cout << "Your current principal: " << p.changePrincipalBy(0) << std::endl;
        std::cout << "Enter your bet by an integer (Max bet = " << p.changePrincipalBy(0) << "): ";
        std::cin >> temp;
        std::cin.clear();
        std::cin.ignore(1000, '\n');
    } while (!RANGE(1, p.changePrincipalBy(0), temp));
    return temp;
}

int menu(player& p, banker& b, std::vector<std::string>& options, int originalPrincipal)
{
    displayPlayerAndBanker(p, b, false);
    
    int maxActions = static_cast<int>(options.size());
    std::cout << std::endl;
    std::cout << "Your bet: " << originalPrincipal - p.changePrincipalBy(0) << std::endl;
    std::cout << "Your current principal: " << p.changePrincipalBy(0) << std::endl;
    std::cout << "Choose your action by number:" << std::endl;
    for (auto e : options)
    {
        std::cout << e << std::endl;
    }
    
    return getSafeAction(maxActions);
}

int determine(player& p, banker& b)
{
    displayPlayerAndBanker(p, b, true);
    
    
    if (p.sumYourHandUp() > 21)
    {
        std::cout << "Bust! You lose!" << std::endl;
        return -3;
    }
    
    if (b.sumYourHandUp() > 21)
    {
        std::cout << "Banker Bust! You win!" << std::endl;
        return -1;
    }
    
    if ((p.sumYourHandUp() > b.sumYourHandUp()))
    {
        std::cout << "You win!" << std::endl;
        return -1;
    }
    
    if ((p.sumYourHandUp() == b.sumYourHandUp()))
    {
        std::cout << "Draw!" << std::endl;
        return -2;
    }
    
    if ((p.sumYourHandUp() < b.sumYourHandUp()))
    {
        std::cout << "You lose!" << std::endl;
        return -3;
    }
    
    return -1;
}

void displayDieMessage() {
}

int askLoan(player& p) {
    std::cout << "Due after " << DUE << " rounds starting from loan date" << std::endl;
    std::cout << "Interest rate : " << INTEREST << std::endl;
    std::cout << "How much do you want to loan?" << std::endl;
    int maxAmount = 100000;
    int amount = getSafeAction(maxAmount);
    p.retrieveLoan(amount);
    std::cout << "Loan successful !" << std::endl;
    return 0;
}

int loan(player& p) { //0 for not dead, 1 for dead after checking loan

    if (p.getloanStatus()) {
        if (p.changePrincipalBy(0) == 0) {
            std::cout << "You lost all your money! And you have already loaned!" << std::endl;
            std::cout << "You are being chased by the gang! Unfortunately, you spent your whole life gambling and your body is not running fast enough for an escape!" << std::endl;
            std::cout << "You got caught by the gang and you got tortured to death! Ouch!" << std::endl;
            std::cout << "YOU DIED! Your career has been reset." << std::endl;
            return 1; //Dead
        }
        else if (p.getDue() == 0) 
        {
            if (p.changePrincipalBy(0) >= p.getDebt()) {
                std::cout << "You have a loan yet to be paid! And it is due!" << std::endl;
                std::cout << "You are paying your debt (You have no choice, you will die otherwise LOLOL)" << std::endl;
                p.payLoan();
                std::cout << "Remaining principal: " << p.changePrincipalBy(0) << std::endl;
                if (p.changePrincipalBy(0) > 0) {
                    std::cout << "You can loan. Do you want to loan? (1 for yes, 2 for no)" << std::endl;
                    int optionAmount = 2;
                    int option = getSafeAction(optionAmount);
                    if (option == 1) {
                        std::cout << std::endl;
                        askLoan(p);
                    }
                    else if (option == 2) {
                        std::cout << "Okay. Not loaning." << std::endl;
                    }
                }
                else
                {
                    std::cout << "You got no money left!" << std::endl;
                    std::cout << "Unfortuately, you are a gambling addict and you need to loan from the gang AGAIN and keep gambling to stay sane." << std::endl;
                    std::cout << std::endl;
                    askLoan(p);
                }
            }
            else {
                std::cout << "The loan is due! But you do not have enough money to pay!" << std::endl;
                std::cout << "You are being chased by the gang! Unfortunately, you spent your whole life gambling and your body is not running fast enough for an escape!" << std::endl;
                std::cout << "You got caught by the gang and you got tortured to death! Ouch!" << std::endl;
                std::cout << "YOU DIED! Your career has been reset." << std::endl;
                return 1; //Dead
            }
        }
        else {
            if (p.changePrincipalBy(0) > p.getDebt()) {
                std::cout << "You have enough money to pay your debt. Do you want to pay? (1 for yes, 2 for no)" << std::endl;
                int optionAmount = 2;
                int option = getSafeAction(optionAmount);
                if (option == 1) {
                    p.payLoan();
                    std::cout << "Payment successful." << std::endl;
                    std::cout << "You can loan. Do you want to loan? (1 for yes, 2 for no)" << std::endl;
                    option = getSafeAction(optionAmount);
                    if (option == 1) {
                        std::cout << std::endl;
                        askLoan(p);
                    }
                    else if (option == 2) {
                        std::cout << "Okay. Not loaning." << std::endl;
                    }
                }
                else if (option == 2) {
                    std::cout << "Okay. Not paying." << std::endl;
                }
            }
            else {
                std::cout << "You do not have enough money to pay your debt at the moment." << std::endl;
            }
        }
    }

    else 
    {
        if (p.changePrincipalBy(0) == 0) {
            std::cout << "You lost all your money!" << std::endl;
            std::cout << "Unfortuately, you are a gambling addict and you need to loan from the gang and keep gambling to stay sane." << std::endl;
            askLoan(p);
        }
        else {
            std::cout << "You can loan. Do you want to loan? (1 for yes, 2 for no)" << std::endl;
            int optionAmount = 2;
            int option = getSafeAction(optionAmount);
            if (option == 1) {
                askLoan(p);
            }
            else if (option == 2) {
                std::cout << "Okay. Not loaning." << std::endl;
            }
        }
    }
    return 0; //Not dead
}

player pregame(std::vector<record> &records)
{
    std::string temp = "";
    std::ifstream file;
    file.open("save.txt");
    if (file.peek() == std::ifstream::traits_type::eof() || file.fail()) {
        std::cout << "Cannot open the file correctly or the file is empty. Starting a new career." << std::endl;
        record newRecord(2000, 0, 0, 0, 0);
        records.push_back(newRecord);

        player p(2000, 0, 0, 0);
        return p;
    }
    //each loop represents one record (one line)
    while (std::getline(file, temp))
    {
        std::stringstream linestream(temp);
        int data[5] = {};
        int count = 0;
        record sample(2000, 0, 0, 0, 0);
        int input;
        int readcount = 0; //Counts how many loops have been done
        while (linestream >> data[count] && count != 5) {
            if (count == 0)  sample.principal = data[0]; 
            if (count == 1)  sample.result = data[1]; 
            if (count == 2)  sample.loanStatus = data[2]; 
            if (count == 3)  sample.debt = data[3]; 
            if (count == 4)  sample.due = data[4];
            count++;
            readcount++;
        }
        if (readcount == 5) { //push the sample to records if exactly 5 loops have been done (i.e. no failed/skipped read) 
            records.push_back(sample);
        }
    }

    int principal, debt, due;
    bool loanStatus;

    //Retrieve latest record and declare p
    principal = records.back().principal;
    loanStatus = records.back().loanStatus;
    debt = records.back().debt;
    due = records.back().due;
    player p(principal, loanStatus, debt, due);

    file.close();
    return p;
}

void savegame(std::vector<record>& records)
{
    
   
    std::ofstream wfile;
    std::string temp = "";
    wfile.open("save.txt");
    if (wfile.fail()) {
        std::cout << "Error : Cannot write to the save file." << std::endl;
        return;
    }
    for (int i = 0; i < records.size(); i++)
    {
        temp = std::to_string(records[i].principal) + ' ' + std::to_string(records[i].result) + ' ' + std::to_string(records[i].loanStatus) + ' ' + std::to_string(records[i].debt) + ' ' + std::to_string(records[i].due);
        wfile << temp << std::endl;
    }

    std::cout << "Saved successfully!" << std::endl;

    wfile.close();
}

int round(player& p, std::vector<record>& book)
{
    std::string goOn = "true";

    do {

        deck casinoCardSuffleMachine;   //already suffle the cards in the initializer
        p.clearHand(); //Clear hand from last round
        banker b;

        p.appendHand(card(casinoCardSuffleMachine.deal()));
        b.appendHand(card(casinoCardSuffleMachine.deal()));
        p.appendHand(card(casinoCardSuffleMachine.deal()));
        b.appendHand(card(casinoCardSuffleMachine.deal()));

        //player and banker each got two cards

        displayPlayerAndBanker(p, b, false);

        int originalPrincipal = p.changePrincipalBy(0);

        int bet = getBet(p);
        p.changePrincipalBy(-bet);

        std::vector<std::string> options = { "1. Hit (get one more card)", "2. Stand (Compare ranks with banker directly)", "3. Double down (double the bet and get ONLY one more card)", "4. Surrender (lose and take back half of the bet)" };

        int result = 0; //-1 means player wins, -2 means draw, -3 means loss, -4 means surrender

        do {
            std::cout << "\033[2J\033[1;1H";
            switch (menu(p, b, options, originalPrincipal))
            {
            case 1:
                std::cout << "\033[2J\033[1;1H";
                p.appendHand(card(casinoCardSuffleMachine.deal()));
                if (p.sumYourHandUp() > 21) { result = determine(p, b); }
                break;
            case 2:
                std::cout << "\033[2J\033[1;1H";
                while (b.sumYourHandUp() < 17)
                {
                    b.appendHand(card(casinoCardSuffleMachine.deal()));
                }
                result = determine(p, b);
                break;
            case 3:
                std::cout << "\033[2J\033[1;1H";
                if (p.changePrincipalBy(0) >= bet) {
                    p.changePrincipalBy(-bet);
                    bet += bet;
                    p.appendHand(card(casinoCardSuffleMachine.deal()));
                    while (b.sumYourHandUp() < 17)
                    {
                        b.appendHand(card(casinoCardSuffleMachine.deal()));
                    }
                    result = determine(p, b);
                }
                else {
                    std::cout << "You cannot do this. You don't have enough money." << std::endl;
                }
                break;
            case 4:
                std::cout << "\033[2J\033[1;1H";
                std::cout << "Don't give up next time!" << std::endl;
                result = -4;
                break;
            default:
                std::cout << "NA" << std::endl;
                result = determine(p, b);
                break;
            }
        } while (result == 0);

        if (result == -3 || result == -2 || result == -1) {
            p.changePrincipalBy((result + 3) * bet);
        }
        else if (result == -4) {
            p.changePrincipalBy(bet / 2);
        }
        std::cout << "Remaining principal: " << p.changePrincipalBy(0) << std::endl;

        if (p.getloanStatus()) p.countDue();
        std::cout << std::endl;
        p.displayLoanInformation();
        std::cout << std::endl;
        int death = loan(p);
        if (death) { // loan(p) outputs 1 represents death of character, resetting career.
            record tempRecord1(p.changePrincipalBy(0),result,p.getloanStatus(),p.getDebt(),p.getDue());
            book.push_back(tempRecord1);
            p.resetCharacter(2000, 0, 0, 0);
            record tempRecord2(p.changePrincipalBy(0), 0, p.getloanStatus(), p.getDebt(), p.getDue());
            book.push_back(tempRecord2);
        }
        else {
            record tempRecord(p.changePrincipalBy(0), result, p.getloanStatus(), p.getDebt(), p.getDue());
            book.push_back(tempRecord);
        }
        std::cout << std::endl;
        
        std::cout << "Continue? [Y/N] (default: Y): ";
        std::cin >> goOn;
        std::cin.clear();
        std::cin.ignore(1000, '\n');
        std::cout << std::endl;

        std::cout << "\033[2J\033[1;1H";

    } while (goOn != "N");

    std::cout << "\033[2J\033[1;1H";
    
    std::cout << std::endl << "Remember to save your progress by option 5 in the main menu!" << std::endl << std::endl;
    
    return 0;
}

void showRecords(std::vector<record>& records) {
    std::cout << "Game Records : " << std::endl;
    std::cout << "----------------------------------------------------------------------" << std::endl;
    std::cout << "|  Round   | Principal |  Result  |  Got Loan  |   Debt   |  Due In  |" << std::endl;
    std::cout << "----------------------------------------------------------------------" << std::endl;

    int counter = 0; //Round counter
    for (int i = 0; i < records.size(); i++) {
        
        //Round
        if (records[i].result == 0) { //Indicates new life
            std::cout << "|Game Start|";
            counter = 0;
        }
        else {
            std::cout << "|Round " << std::setw(4) << std::right << counter << "|";
        }

        //Principal
        std::cout << std::setw(10) << std::right << records[i].principal << " |";

        //Result
        std::cout << std::setw(10) << std::right;
        if (records[i].result == 0) {
            std::cout << "N/A";
        }
        else if (records[i].result == -1) {
           std::cout << "WIN";
        }
        else if (records[i].result == -2) {
            std::cout << "DRAW";
        }
        else if (records[i].result == -3) {
            std::cout << "LOSE";
        }
        else if (records[i].result == -4) {
            std::cout << "SURRENDER";
        }
        std::cout << "|";

        //Got Loan
        std::cout << std::setw(12) << std::right;
        if (records[i].loanStatus) {
            std::cout << "YES";
        }
        else {
            std::cout << "NO";
        }
        std::cout << "|";

        //Debt
        std::cout << std::setw(10) << std::right;
        if (records[i].loanStatus) {
            std::cout << records[i].debt;
        }
        else {
            std::cout << "N/A";
        }
        std::cout << "|";

        //Due
        std::cout << std::setw(10) << std::right;
        if (records[i].loanStatus) {
            std::cout << records[i].due;
        }
        else {
            std::cout << "N/A";
        }
        std::cout << "|";

        std::cout << std::endl;
        std::cout << "----------------------------------------------------------------------" << std::endl;

        //Indicates death
        if (i != records.size() - 1 && records[i + 1].result == 0) {
            std::cout << "|  DEATH. Character reset.                                           |" << std::endl;
            std::cout << "----------------------------------------------------------------------" << std::endl;
        }

        counter++;
    }
}

int mainMenu() {
    std::cout << "Main Menu :" << std::endl;
    std::cout << "[1]Start The Game" << std::endl;
    std::cout << "[2]Show Records" << std::endl;
    std::cout << "[3]Story" << std::endl;
    std::cout << "[4]Show Credits" << std::endl;
    std::cout << "[5]Save the game" << std::endl;
    std::cout << "[6]Quit the game" << std::endl;
    int size = 6, choice = 0;
    choice = getSafeAction(size);
    return choice;
}

void displayStory() {
    std::cout << "Story :" << std::endl << std::endl;
    std::cout << "Welcome to Las Vegas! As a gambling addict, playing blackjack in las vegas is your full time job now!" << std::endl;
    std::cout << "You will have $2000 to start your gambling career." << std::endl;
    std::cout << "If you lose all your money, you will have to get a loan from the gang to keep gambling." << std::endl;
    std::cout << "And since you are an addict. You will have no choice but to loan, because gambling is YOUR LIFE! And you can't live without it." << std::endl;
    std::cout << "But please make sure that you pay the debt before due if you loan. Gangs are violent and you are gonna get killed if you can't pay on time!" << std::endl << std::endl;
}
