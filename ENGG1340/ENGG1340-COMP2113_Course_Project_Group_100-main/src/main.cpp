//
//  main.cpp
//  blackjack-ultimate
//
//  Created by eric on 22/4/2021.
//

#include <iostream>

#include "../include/gameflow.hpp"

int main(int argc, const char * argv[]) {

	//Record
	std::vector<record> records;

	//Load the game save
	player p = pregame(records);

    //Welcome message
    std::cout << std::endl << "Welcome to blackjack! If this is your first time playing, Please check option 3 for the story!" << std::endl << std::endl;

	int choice;
	do {
		choice = mainMenu();
        std::cout << "\033[2J\033[1;1H";
		switch (choice) {
			case 1: {
				round(p,records);
			}break;
			case 2: {
				std::cout << std::endl;
				showRecords(records);
				std::cout << std::endl;
			}break;
			case 3: {
                std::cout << std::endl;
                displayStory();
                std::cout << std::endl;

			}break;
			case 4: {
				std::cout << std::endl << "Credits: " << std::endl;
				std::cout << "Tsoi Tsz Chun | UID : eric2609 | Github ID : Eric's Lab" << std::endl;
				std::cout << "Li Hoi Kit | UID : u3574503 | Github ID : Ch1ckenMax" << std::endl << std::endl;
			}break;
			case 5: {
				std::cout << std::endl << "Saving the game...." << std::endl;
				savegame(records);
				std::cout << std::endl;
			}break;
			case 6: {
				std::cout << "Okay. Quitting the game." << std::endl;
			}break;

		}
	}
	while (choice != 6);

	return 0;
}
