# ENGG1340-COMP2113_Course_Project_Group_100

# Team members, Topic, and Description

Group No.: 100  <br/>
Group members: Tsoi, Tsz Chun (GitHub username: Eric's Lab), Li, Hoi Kit (GitHub username: Ch1ckenMax)<br/>
Topic: Blackjack  <br/>
Description: The project will be a single-player Player-versus-environment (PVE) blackjack game, in which the player can experience a gambling career (as a blackjack player) and bet with a built-in virtual currency.<br/>

# Detailed Game Rules:
Each game save represents the player's career, the flow of a player's career is as follow:

1. Player starts with $2000.
2. Player plays a round of blackjack to win/lose money.
3. If money > 0, Player can choose to loan or not loan. If loan, player receieve money of [1-100000], The player will have to pay the debt after 5 blackjack rounds, GOTO 5. If not loan, GOTO 2. (Bascially, the game will never end if the player doesn't lose all the money)
4. If money == 0 (i.e. loses all the money), the player has to receive a loan from the gang [1-100000] to continue the career. The player will have to pay the debt after 5 blackjack rounds.
5. Player plays a round of blackjack to win/lose money.
6. After each round, calculate the debt and due date according to the interest rate (1.3).
7. The player can choose to pay up the debt if the player still have money left for betting after paying, GOTO 2 and subtract the debt from money.
8. If due date is present, and if the player can pay up the debt and still have money left for betting after paying, GOTO 2 and subtract the debt from money. Otherwise, the game ENDS as the player cannot pay up the debt and is tortured and killed by the gang.
9. If money > 0, GOTO 5
10. If money == 0, the game ENDS as the player cannot pay up the debt and is tortured and killed by the gang.

We follow one of the most popular versions of Blackjack. The flow of one round of blackjack lies as follow:

1. Shuffle the cards
2. The player and the computer (dealer) get one card
3. The player gets one more card
4. The computer gets one hidden card
5. Player are asked to choose one of the following actions: <br/>
   A. Hit (get one more card, the only action that can be chosen repetitively)<br/>
   B. Stand (Stop getting cards) <br/>
   C. Double down (double the bet and get one more card)<br/>
   D. Surrender (lose the bet and take back half of the bet, the round ends here, GOTO step 1)<br/>
6. If the computer do not has cards bigger than 17, it will draw card until it do so.<br/>
7. Compare the cards, Player > Computer means player get twice the bet. Player < Computer means player lose the bet. Player = Computer means player lose and gain nothing (a draw).<br/>
   If either party gets card greater than 21, other side wins automatically.
   
# Workload distribution
Li, Hoi Kit -> The main menu, gang loans, comments in the code<br/>
Tsoi, Tsz Chun -> The implementation of Blackjack game, make file

# List of features:
1. Shuffling poker cards - Generation of random game sets or events
2. Storing player's information and card availability in runtime - Data structures (Struct, Array) for storing game status 
3. Storing player's and computer's hand in run timewith Dynamic memory management (Use of STL Vectors)
4. Save and retrieve bet history (e.g., Amount of money left, Win/Lost, loan information) in the disk - File I/O
5. OOP with the creation of Player and Dealer class - Program codes in multiple files (Using .hpp and .cpp)
6. Indentation style: Allman style
7. Naming style: Lower camel case 

# Testing Environment
The program is tested in academy11 server. <br>
The sample input output files are put in the sampleio folder.

# Installation Tutorial
1. Download the source files.
2. Unzip the folder `ENGG1340-COMP2113_Course_Project_Group_100-main` into any directory of your choice
3. Set the current directory to `ENGG1340-COMP2113_Course_Project_Group_100-main`
4. Type the command `make` to build the program
6. Type the command `./bin/blackjack` to run the game
7. Enjoy!

You can uninstall the game by typing `make clean` command.
