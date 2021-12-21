#IMPORTANT NOTE TO TA/PROF THAT MARK MY CODE:
#The idea of my AI is from this paper : https://arxiv.org/pdf/1301.1672v1.pdf?
#And I have referred some implementation ideas from the code from here : https://github.com/kelvin0815/notakto/blob/master/solveTicTacToe.py
#However, I did not directly copy any code from the above websites or any other sources.
#Thank you for your attention :)


#Stuff for AI
#reflect the boards
def reflectBoardVertical(board):
    newboard = [[board[0][2],board[0][1],board[0][0]],[board[1][2],board[1][1],board[1][0]],[board[2][2],board[2][1],board[2][0]]]
    for i in range(len(newboard)):
        for j in range(len(newboard[i])):
            if(newboard[i][j] != 'X'):
                newboard[i][j] = i*3 + j
    return newboard
    
def reflectBoardHorizontal(board):
    newboard = [[board[2][0],board[2][1],board[2][2]],[board[1][0],board[1][1],board[1][2]],[board[0][0],board[0][1],board[0][2]]]
    for i in range(len(newboard)):
        for j in range(len(newboard[i])):
            if(newboard[i][j] != 'X'):
                newboard[i][j] = i*3 + j
    return newboard

#Rotate the boards
def rotateBoard90(board):
    newboard = [[board[2][0],board[1][0],board[0][0]],[board[2][1],board[1][1],board[0][1]],[board[2][2],board[1][2],board[0][2]]]
    for i in range(len(newboard)):
        for j in range(len(newboard[i])):
            if(newboard[i][j] != 'X'):
                newboard[i][j] = i*3 + j
    return newboard
        
def rotateBoard180(board):
    newboard = [[board[2][2],board[2][1],board[2][0]],[board[1][2],board[1][1],board[1][0]],[board[0][2],board[0][1],board[0][0]]]
    for i in range(len(newboard)):
        for j in range(len(newboard[i])):
            if(newboard[i][j] != 'X'):
                newboard[i][j] = i*3 + j
    return newboard
            
def rotateBoard270(board):
    newboard = [[board[0][2],board[1][2],board[2][2]],[board[0][1],board[1][1],board[2][1]],[board[0][0],board[1][0],board[2][0]]]
    for i in range(len(newboard)):
        for j in range(len(newboard[i])):
            if(newboard[i][j] != 'X'):
                newboard[i][j] = i*3 + j
    return newboard
        
        
#unique pattern keys(without permutation of reflections and rotations)
patternkey = {'1':([['X', 1, 2], [3, 4, 5], [6, 7, 8]],[[0, 'X', 2], [3, 4, 5], [6, 7, 8]],[['X', 1, 2], [3, 4, 'X'], [6, 'X', 8]]),
              'a':([['X', 1, 2], [3, 4, 5], [6, 7, 'X']],[[0, 'X', 2], ['X', 4, 5], [6, 7 , 8]],[[0, 'X', 2], [3, 4, 5], [6, 'X' , 8]],[['X', 'X', 2], [3, 4, 5], ['X', 7, 8]],[['X', 1, 'X'], [3, 'X', 5], [6, 7, 8]],[['X', 1, 'X'], [3, 4, 5], [6, 'X', 8]],[['X', 1, 2], [3, 'X', 'X'], [6, 7, 8]],[['X', 'X', 2], ['X', 'X', 5], [6, 7, 8]],[['X', 'X', 2], ['X', 4, 'X'], [6, 7, 8]],[['X', 'X', 2], ['X', 4, 5], [6, 7, 'X']],[['X', 'X', 2], [3, 4, 5], [6, 'X', 'X']],[['X', 1, 'X'], [3, 4, 5], ['X', 7, 'X']],[[0, 'X', 2], ['X', 4, 'X'], [6, 'X', 8]],[['X', 'X', 2], [3, 'X', 'X'], ['X', 7, 8]],[['X', 'X', 2], [3, 4, 'X'], ['X', 'X', 8]],[['X', 'X', 2], [3, 4, 'X'], ['X', 7, 'X']],[['X', 'X', 2], ['X', 4, 'X'], [6, 'X', 'X']]),
              'b':([['X', 1, 'X'], [3, 4, 5], [6, 7, 8]],[['X', 1, 2], [3, 'X', 5], [6, 7, 8]],[['X', 1, 2], [3, 4, 'X'], [6, 7, 8]],[[0, 'X', 2], [3, 'X', 5], [6, 7, 8]],[['X', 'X', 2], ['X', 4, 5], [6, 7, 8]],[[0, 'X', 2], ['X', 4, 'X'], [6, 7, 8]],[['X', 'X', 2], [3, 'X', 'X'], [6, 7, 8]],[['X', 'X', 2], [3, 'X', 5], ['X', 7, 8]],[['X', 'X', 2], [3, 4, 'X'], ['X', 7, 8]],[['X', 'X', 2], [3, 4, 5], ['X', 'X', 8]],[['X', 'X', 2], [3, 4, 5], ['X', 7, 'X']],[['X', 1, 'X'], [3, 'X', 5], [6, 'X', 8]],[['X', 1, 2], [3, 'X', 'X'], [6, 'X', 8]],[['X', 'X', 2], ['X', 4, 'X'], [6, 'X', 8]],[['X', 'X', 2], ['X', 4, 'X'], [6, 7, 'X']]),
              'c':([[0, 1, 2], [3, 4, 5], [6, 7, 8]],),
              'd':([['X', 'X', 2], [3, 4, 'X'], [6, 7, 8]],[['X', 'X', 2], [3, 4, 5], [6, 'X', 8]],[['X', 'X', 2], [3, 4, 5], [6, 7, 'X']]),
              'ab':([['X', 'X', 2], [3, 'X', 5], [6, 7, 8]],[['X', 1, 'X'], [3, 4, 5], ['X', 7, 8]],[[0, 'X', 2], ['X', 'X', 5], [6, 7, 8]],[['X', 'X', 2], [3, 4, 'X'], [6, 'X', 8]],[['X', 'X', 2], [3, 4, 'X'], [6, 7, 'X']]),
              'ad':([['X', 'X', 2], [3, 4, 5], [6, 7, 8]],),
              'cc':([[0, 1, 2], [3, 'X', 5], [6, 7, 8]],)}

#Generating the dictionary of full pattern keys
for key in patternkey:
    for eachcombination in patternkey[key]:
        if(key != 'c'):
            result = reflectBoardHorizontal(eachcombination), reflectBoardVertical(eachcombination), rotateBoard90(eachcombination), rotateBoard180(eachcombination), rotateBoard270(eachcombination)
            patternkey[key] = patternkey[key] + result

#Determine the key of the board
def findBoardkey(board):
    for key in patternkey:
        for eachboard in patternkey[key]:
            if(eachboard == board):
                return key
    print("fuck")
    return 'n' #cant find the board

#Determine whether the key is in winning condition
def determineGoodKey(boardskey):
    count = [0,0,0,0] #subscript 0 as a, 1 as b, 2 as c, 3 as d
    for char in boardskey:
        if(char == 'a'):
            count[0] += 1
        elif(char == 'b'):
            count[1] += 1
        elif(char == 'c'):
            count[2] += 1
        elif(char == 'd'):
            count[3] += 1
    if(count == [1,0,0,0] or count == [0,2,0,0] or count == [0,1,1,0] or count == [0,0,2,0]): #if key = a or bb or bc or cc then its good
        return True
    else:
        return False

board = [ [[0,1,2],[3,4,5],[6,7,8]] , [[0,1,2],[3,4,5],[6,7,8]] , [[0,1,2],[3,4,5],[6,7,8]] ] #Initialize the board

def printBoard(a, b, c): #function that prints the board, boolean parameters denote print or not
    for row in range(4):
        #First row
        if(row == 0):
            if(a):
                print("A",end='')
            if(b):
                if(a):
                    print("      ",end='')
                print("B",end='')
            if(c):
                if(a or b):
                    print("      ",end='')
                print("C",end='')
            print()

        #Remaining rows
        else:
            if(a):
                for col in range(3):
                    print(str(board[0][row-1][col]),end='')
                    if(col != 2):
                        print(' ',end='')
            if(b):
                if(a):
                    print('  ',end='')
                for col in range(3):
                    print(str(board[1][row-1][col]),end='')
                    if(col != 2):
                        print(' ',end='')
            if(c):
                if(a or b):
                    print('  ',end='')
                for col in range(3):
                    print(str(board[2][row-1][col]),end='')
                    if(col != 2):
                        print(' ',end='')
            print()

def checkBoardDone(boardnum): #check if the board is finished, return True if done, return false if not done
    for i in range(3):
        #Check Row
        if(board[boardnum][i] == ['X','X','X']):
            return True

        #Check Column
        if([str(board[boardnum][0][i]), str(board[boardnum][1][i]), str(board[boardnum][2][i])] == ['X','X','X']):
            return True
    
    #Check Diagonal
    if([str(board[boardnum][0][0]), str(board[boardnum][1][1]), str(board[boardnum][2][2])] == ['X','X','X'] or [str(board[boardnum][0][2]), str(board[boardnum][1][1]), str(board[boardnum][2][0])] == ['X','X','X']):
        return True
    
    return False #Not done yet

#Another function to check the potential moves for AI. I am avoiding to alter the above function to prevent error
def checkNewBoardDone(board):
    for i in range(3):
        if(board[i] == ['X','X','X']):
            return True
        if([str(board[0][i]), str(board[1][i]), str(board[2][i])] == ['X','X','X']):
            return True
    if([str(board[0][0]), str(board[1][1]), str(board[2][2])] == ['X','X','X'] or [str(board[0][2]), str(board[1][1]), str(board[2][0])] == ['X','X','X']):
        return True
    
    return False
    

count = 0 #count of round of game. even as player 1, odd as player 2
boardStatus = [True, True, True] #Status of each board
boarddict = {'A':0, 'B':1, 'C':2}

printBoard(*boardStatus)

while(True):

    player = count % 2 + 1

    #AI Part
    if(player == 1):

        #First move
        if(count == 0): 
            move = "A0"
        else:
            #nextmove:
            found = False
            for boardnum in range(3):
                if(found):
                    break
                for row in range(3):
                    if(found):
                        break
                    for column in range(3):
                        if(board[boardnum][row][column] != 'X' and boardStatus[boardnum] == True): #only consider moves that are valid
                            newboard = [[board[0][0][:],board[0][1][:],board[0][2][:]],[board[1][0][:],board[1][1][:],board[1][2][:]],[board[2][0][:],board[2][1][:],board[2][2][:]]]
                            newboard[boardnum][row][column] = 'X'
                            boardskey = ""
                            newboardStatus = [not checkNewBoardDone(newboard[0]),not checkNewBoardDone(newboard[1]),not checkNewBoardDone(newboard[2])]
                            for i in range(3):                                    
                               if(newboardStatus[i]): #Dead board are not considered
                                    boardskey += findBoardkey(newboard[i])
                            if(determineGoodKey(boardskey)):
                                if(boardnum == 0):
                                    move = 'A'+str(column+row*3)
                                    found = True
                                    break
                                if(boardnum == 1):
                                    move = 'B'+str(column+row*3)
                                    found = True
                                    break
                                if(boardnum == 2):
                                    move = 'C'+str(column+row*3)
                                    found = True
                                    break

        print(f"Player 1: {move}")

    #Player Part
    else:
    
        move = input(f"Player {player}: ")

        #input validation
        if(len(move) != 2):#Length of input
            print("Invalid move, please input again")
            continue
        else:
            if( (move[0] != 'A' and move[0] != 'B' and move[0] != 'C') or (int(move[1]) < 0 or int(move[1]) > 8) ):
                print("Invalid move, please input again")
                continue

        #Check if the board is still available
        if(not boardStatus[boarddict[move[0]]]):
            print("Invalid move, please input again")
            continue

        #check if the slot is occupied
        if(board[boarddict[move[0]]][int(move[1])//3][int(move[1])%3] == 'X'):
            print("Invalid move, please input again")
            continue
    
    board[boarddict[move[0]]][int(move[1])//3][int(move[1])%3] = 'X'

    boardStatus[boarddict[move[0]]] = not checkBoardDone(boarddict[move[0]])

    count += 1

    #Game ends
    if(boardStatus == [False,False,False]):
        print(f"Player {count % 2 + 1} wins game")
        break

    printBoard(*boardStatus)