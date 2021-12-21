import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Used for building a GUI for the Big Two card game and handle all user actions.
 * 
 * @author Li Hoi Kit
 */
public class BigTwoGUI implements CardGameUI{
    //Colors
    private static final Color TITLE_COLOR = new Color(98, 0, 238);
    private static final Color BG_COLOR = new Color(35, 39, 42);
    private static final Color TEXT_COLOR_WHITE = new Color(246, 246, 246);

    //Private members
    private BigTwo game;
    private BigTwoClient client;
    private boolean[] selected = new boolean[13];
    private int activePlayer = -1;
    private JFrame frame;
    private JMenuItem connect;
    private JMenuItem quit;
    private ArrayList<BigTwoPanel> bigTwoPanels = new ArrayList<BigTwoPanel>();
    private JButton playButton;
    private JButton passButton;
    private JScrollPane msgAreaContainer;
    private JTextArea msgArea;
    private JScrollPane chatAreaContainer;
    private JTextArea chatArea;
    private JTextField chatInput;

    /**
     * Checks the game conditions after a move. For client's use.
     */
    public void actionsAfterMove(){
        if(activePlayer != game.getCurrentPlayerIdx()){ //Valid move. Game proceed
            if(game.endOfGame()){ //End of the game.
                
                activePlayer = -1;
                repaint();
                String msgToBePrint = "Game ends\n";
                for(int i = 0; i < game.getNumOfPlayers(); i++){
                    CardGamePlayer player = game.getPlayerList().get(i);
                    if(player.getNumOfCards() == 0)
                        msgToBePrint += player.getName()+" wins the game.\n";
                    else
                        msgToBePrint += player.getName()+" has "+player.getNumOfCards()+" cards in hand.\n";
                }
                JOptionPane.showMessageDialog(null, msgToBePrint, "Game ends", JOptionPane.INFORMATION_MESSAGE);
                client.sendMessage(new CardGameMessage(CardGameMessage.READY,-1,null));
                disable();
            }else{ //Not the end of the game. Continue
                setActivePlayer(game.getCurrentPlayerIdx());
                game.getPlayerList().get(activePlayer).sortCardsInHand();
                repaint();
                promptActivePlayer();
                resetSelected();
            }
        }
    }

    /**
	 * Prints the specified string to the chat area of the card game user
	 * interface.
	 * 
	 * @param msg the string to be printed to the chat area of the card game user
	 *            interface
	 */
    public void printMsgToChat(String msg){
        chatArea.append(msg+"\n");
        chatAreaContainer.getVerticalScrollBar().setValue(chatAreaContainer.getVerticalScrollBar().getMaximum()); //Auto scroll to bottom
    }

    /**
	 * Sets the index of the active player (i.e., the current player).
	 * 
	 * @param activePlayer an int value representing the index of the active player
	 */
    public void setActivePlayer(int activePlayer){
        if (activePlayer < 0 || activePlayer >= game.getPlayerList().size()) {
			this.activePlayer = -1;
		} else {
			this.activePlayer = activePlayer;
		}
    }

    /**
	 * Repaints the user interface.
	 */
    public void repaint(){
        for(int i = 0; i <= game.getNumOfPlayers();i++){
            bigTwoPanels.get(i).repaintCards();
        }
    }

    /**
	 * Prints the specified string to the message area of the card game user
	 * interface.
	 * 
	 * @param msg the string to be printed to the message area of the card game user
	 *            interface
	 */
    public void printMsg(String msg){
        msgArea.append(msg+"\n"); //ON HOLD
        msgAreaContainer.getVerticalScrollBar().setValue(msgAreaContainer.getVerticalScrollBar().getMaximum()); //Auto scroll to bottom
    }

    /**
	 * Clears the message area of the card game user interface.
	 */
    public void clearMsgArea(){
        msgArea.setText("");
    }

    /**
	 * Resets the card game user interface.
	 */
    public void reset(){
        resetSelected();
        clearMsgArea();
        enable();
    }

    /**
	 * Enables user interactions.
	 */
    public void enable(){
        chatInput.setEnabled(true);
        if(client.getPlayerID() == game.getCurrentPlayerIdx()){
            bigTwoPanels.get(client.getPlayerID()).activateListeners();
            playButton.setEnabled(true);
            passButton.setEnabled(true);
        }
    }

    /**
	 * Disables user interactions.
	 */
    public void disable(){
        playButton.setEnabled(false);
        passButton.setEnabled(false);
        chatInput.setEnabled(false);
        for(int i = 0; i <= game.getNumOfPlayers();i++){
            bigTwoPanels.get(i).deactivateListeners();
        }
    }

    /**
	 * Prompts active player to select cards and make his/her move.
	 */
    public void promptActivePlayer(){
        String message = game.getPlayerList().get(activePlayer).getName() + "'s turn:";
        printMsg(message);
    }

    /**
     * Reset the selected cards
     */
    public void resetSelected(){
        for(int i = 0; i < selected.length; i++){
            selected[i] = false;
        }
    }

    /**
     * Creates a BigTwoUI.
     * 
     * @param game Big Two card game that associates with this GUI.
     */
    public BigTwoGUI(BigTwo game){
        this.game = game;

        //Window
        frame = new JFrame("Big Two");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(44, 47, 51));
        frame.setSize(1024,768);
        frame.setLayout(new GridBagLayout());

        //Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        menuBar.add(menu);
        menuBar.setBackground(TITLE_COLOR);
        menu.setForeground(TEXT_COLOR_WHITE);

        connect = new JMenuItem("Connect");
        connect.setBackground(TITLE_COLOR);
        connect.setForeground(TEXT_COLOR_WHITE);
        connect.addActionListener(new ConnectMenuItemListener());

        quit = new JMenuItem("Quit");
        quit.setBackground(TITLE_COLOR);
        quit.setForeground(TEXT_COLOR_WHITE);
        quit.addActionListener(new QuitMenuItemListener());

        menu.add(connect);
        menu.add(quit);
        frame.setJMenuBar(menuBar);

        //BigTwo Panels
        for(int i = 0; i < game.getNumOfPlayers(); i++){ //Initialize the panels of each player
            bigTwoPanels.add(new BigTwoPanel(i));
        }
        bigTwoPanels.add(new BigTwoPanel(-1)); //Table. playerIdx -1 represents table(non player)

        JPanel panelContainers = new JPanel(); //Setting up the containers
        panelContainers.setLayout(new GridBagLayout());
        for(int i = 0; i <= game.getNumOfPlayers(); i++){ //Add the panels of each player to the container
            panelContainers.add(bigTwoPanels.get(i), new GridBagConstraints(0,i,1,1,1.0,0.2,GridBagConstraints.FIRST_LINE_START,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));
        }
        panelContainers.setBorder(BorderFactory.createLineBorder(TEXT_COLOR_WHITE));

        frame.add(panelContainers, new GridBagConstraints(0,0,1,2,0.6,0.95,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));

        //MsgArea
        msgArea = new JTextArea();
        msgArea.setBackground(BG_COLOR);
        msgArea.setLineWrap(true);
        msgArea.setEditable(false);
        msgArea.setForeground(TEXT_COLOR_WHITE);
        msgArea.setFont(msgArea.getFont().deriveFont(12.0f));

        msgAreaContainer = new JScrollPane(msgArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //Scroll pane that contains the msgArea. Provides scroll bar and prevent its size from going crazy
        msgAreaContainer.getVerticalScrollBar().setBackground(TITLE_COLOR);
        msgAreaContainer.setPreferredSize(new Dimension(350,315));
        msgAreaContainer.setBorder(BorderFactory.createLineBorder(TEXT_COLOR_WHITE));

        frame.add(msgAreaContainer, new GridBagConstraints(1,0,1,1,0.4,0.42,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));

        //ChatArea
        JTextArea chatTitle = new JTextArea("Chat Room"); //Title bar of chatArea
        chatTitle.setEditable(false);
        chatTitle.setForeground(TEXT_COLOR_WHITE);
        chatTitle.setBackground(TITLE_COLOR);
        chatTitle.setMargin(new Insets(1,15,1,0));
        chatTitle.setPreferredSize(new Dimension(350,20));
        chatTitle.setFont(chatTitle.getFont().deriveFont(16.0f).deriveFont(Font.BOLD));

        JPanel titleAndChatContainer = new JPanel(); //container that holds chatTitle and the chatArea
        titleAndChatContainer.setLayout(new BoxLayout(titleAndChatContainer, BoxLayout.Y_AXIS));

        chatArea = new JTextArea();
        chatArea.setBackground(BG_COLOR);
        chatArea.setLineWrap(true);
        chatArea.setEditable(false);
        chatArea.setForeground(new Color(246,246,246));
        chatArea.setFont(chatArea.getFont().deriveFont(12.0f));

        chatAreaContainer = new JScrollPane(chatArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //Scroll pane. Same case as msgAreaContainer above.
        chatAreaContainer.getVerticalScrollBar().setBackground(TITLE_COLOR);
        chatAreaContainer.setPreferredSize(new Dimension(350,295));

        titleAndChatContainer.add(chatTitle);
        titleAndChatContainer.add(chatAreaContainer);
        titleAndChatContainer.setBorder(BorderFactory.createLineBorder(TEXT_COLOR_WHITE));

        frame.add(titleAndChatContainer, new GridBagConstraints(1,1,1,1,0.4,0.47,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));


        //ButtonsArea
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);

        playButton = new JButton("Play");
        playButton.setBackground(new Color(98,0,238));
        playButton.setForeground(TEXT_COLOR_WHITE);
        playButton.addActionListener(new PlayButtonListener());

        passButton = new JButton("Pass");
        passButton.setBackground(new Color(98,0,238));
        passButton.setForeground(TEXT_COLOR_WHITE);
        passButton.addActionListener(new PassButtonListener());

        buttonsPanel.add(playButton);
        buttonsPanel.add(passButton);

        frame.add(buttonsPanel, new GridBagConstraints(0,2,1,1,0.6,0.05,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));

        //ChatInputArea
        chatInput = new JTextField();
        chatInput.setForeground(TEXT_COLOR_WHITE);
        chatInput.setBackground(BG_COLOR);
        chatInput.setPreferredSize(new Dimension(350,20));
        chatInput.addActionListener(new ChatInputListener());

        frame.add(chatInput, new GridBagConstraints(1,2,1,1,0.4,0.05,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));

        //Remaining UI Settings
        frame.setSize(1024,768);
        frame.setMinimumSize(new Dimension(1024, 768)); //To prevent the UI from going wild
        frame.setVisible(true);

        //Disable all the game buttons first
        disable();
    }

    /**
     * Draws the card game panel of one player or a table. Handles mouse click events to the cards.
     */
    class BigTwoPanel extends JPanel implements MouseListener{
        private int playerIdx;
        private JLayeredPane cardsContainer;
        private JTextArea playerNameContainer;

        /**
         * Creates a BigTwoPanel instance.
         * 
         * @param playerIdx The index of the player that belongs to this panel. If the argument of this is -1 this instance represents a table.
         */
        public BigTwoPanel(int playerIdx){
            this.playerIdx = playerIdx;
            this.setup();
        }

        /**
         * Activates the ActionListeners of the shown cards. Only activates if the panel belongs to active player.
         */
        public void activateListeners(){
            if(cardsContainer.getComponentCount() == 0) return; //Invalid.
            if(activePlayer != playerIdx || playerIdx == -1) return; //Not active player. Invalid.
            for(int i = 0; i < cardsContainer.getComponents().length; i++){
                if(cardsContainer.getComponents()[i].getMouseListeners().length != 1) //It is not activated
                    cardsContainer.getComponents()[i].addMouseListener(this);
            }
        }

        /**
         * Deactivates the ActionListeners of the cards.
         */
        public void deactivateListeners(){
            if(cardsContainer.getComponentCount() == 0) return; //Invalid.
            for(int i = 0; i < cardsContainer.getComponents().length; i++){
                cardsContainer.getComponents()[i].removeMouseListener(this);
            }
        }

        /**
         * Creates the GUI. Should only be called by the constructor.
         */
        private void setup(){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBackground(BG_COLOR);

            //Player name
            playerNameContainer = new JTextArea();
            playerNameContainer.setEditable(false);
            playerNameContainer.setForeground(Color.BLACK);
            playerNameContainer.setBackground(TITLE_COLOR);
            playerNameContainer.setMargin(new Insets(1,15,1,0));
            if(playerIdx == -1) //Table
                if(!game.getHandsOnTable().isEmpty()) //Table not empty
                    playerNameContainer.append("Played by "+game.getHandsOnTable().get(game.getHandsOnTable().size() - 1).getPlayer().getName());
                else
                    playerNameContainer.append("Empty table");
            else //Player
                playerNameContainer.append(game.getPlayerList().get(playerIdx).getName());
            
            playerNameContainer.setFont(playerNameContainer.getFont().deriveFont(16.0f).deriveFont(Font.BOLD));
            playerNameContainer.setPreferredSize(new Dimension(100,20));
            this.add(playerNameContainer);

            //Cards container
            cardsContainer = new JLayeredPane();
            cardsContainer.setPreferredSize(new Dimension(500,110));

            //Avatar and cards container
            JPanel avatarAndCardsContainer = new JPanel();
            avatarAndCardsContainer.setBackground(BG_COLOR);
            avatarAndCardsContainer.setPreferredSize(new Dimension(600,110));

            if(playerIdx != -1){ //If it is not a table, add avatar
                JLabel avatar = new JLabel(new ImageIcon("images/avatar/player"+playerIdx+".jpg"));
                avatar.setPreferredSize(new Dimension(75,75));
                avatarAndCardsContainer.add(avatar);
            }
            
            avatarAndCardsContainer.add(cardsContainer);
            this.add(avatarAndCardsContainer);
            
            repaintCards();
        }

        /**
         * Repaint the cards.
         */
        public void repaintCards(){
            cardsContainer.removeAll(); //Clears the cards
            
            if(this.playerIdx == activePlayer){ //Indicate active player
                playerNameContainer.setForeground(Color.WHITE);
            }
            else{
                playerNameContainer.setForeground(Color.BLACK);
            }

            if(playerIdx == -1){ //Table
                //Repaint the table name
                if(!game.getHandsOnTable().isEmpty()){
                    playerNameContainer.setText("Played by "+game.getHandsOnTable().get(game.getHandsOnTable().size() - 1).getPlayer().getName());
                } //Table not empty
                else{
                    playerNameContainer.setText("Empty Table");
                }

                int horizontalOffset = 0;
                if(!game.getHandsOnTable().isEmpty()){
                    Hand lastHandOnTable = game.getHandsOnTable().get(game.getHandsOnTable().size() - 1);
                    for(int i = 0; i < lastHandOnTable.size(); i++){
                        Card currentCard = lastHandOnTable.getCard(i);
                        JLabel theCard = new JLabel(CardImages.cardImages[currentCard.getSuit()][currentCard.getRank()]);
                        theCard.setBounds(horizontalOffset, 13, 73, 97);
                        cardsContainer.add(theCard, new Integer(i));
                        horizontalOffset += 20; //Horizontal distance added to the next card's position
                    }
                }
            }
            else {
                //Repaint the player name
                playerNameContainer.setText(game.getPlayerList().get(playerIdx).getName());

                if(activePlayer == -1 || client.getPlayerID() == this.playerIdx){ //Show all cards when: active player = -1 (the game ended), or this player is active player
                    int horizontalOffset = 0;
                    for(int i = 0; i < game.getPlayerList().get(playerIdx).getNumOfCards(); i++){
                        Card currentCard = game.getPlayerList().get(playerIdx).getCardsInHand().getCard(i);
                        JLabel theCard = new JLabel(CardImages.cardImages[currentCard.getSuit()][currentCard.getRank()]);
                        theCard.setBounds(horizontalOffset, 13, 73, 97);
                        cardsContainer.add(theCard, new Integer(i));
                        horizontalOffset += 20; //Horizontal distance added to the next card's position
                    }
                    if(client != null)//Game started
                        if(activePlayer == client.getPlayerID()){ //If its your round
                            activateListeners();
                            playButton.setEnabled(true);
                            passButton.setEnabled(true);
                        }
                        else{
                            playButton.setEnabled(false);
                            passButton.setEnabled(false);
                        }
                }
                else{ //Just show number of cards owned by players in a hidden manner.
                    int horizontalOffset = 0;
                    for(int i = 0; i < game.getPlayerList().get(playerIdx).getNumOfCards(); i++){
                        JLabel hiddenCard = new JLabel(CardImages.hiddenCard);
                        hiddenCard.setBounds(horizontalOffset, 13, 73, 97);
                        cardsContainer.add(hiddenCard, new Integer(i));
                        horizontalOffset += 20; //Horizontal distance added to the next card's position
                    }
                }
            }

            cardsContainer.repaint();
        }

        /**
         * Handles mouse click events. Allows selection and deselection of the cards.
         */
        public void mouseReleased(MouseEvent e){
            int cardSelected = cardsContainer.getLayer(e.getComponent()); //The layer can be interpret as the index of card
            if(selected[cardSelected]){ //Card already selected. Deselect the card
                selected[cardSelected] = false;
                e.getComponent().setBounds(e.getComponent().getX(),e.getComponent().getY() + 10,73,97);
            }
            else{ //Card not yet selected. Select the card
                selected[cardSelected] = true;
                e.getComponent().setBounds(e.getComponent().getX(),e.getComponent().getY() - 10,73,97);
            }
        }

        //Unused event listeners
        /**
         * Not used in this application
         */
        public void mousePressed(MouseEvent e){}
        /**
         * Not used in this application
         */
        public void mouseEntered(MouseEvent e){}
        /**
         * Not used in this application
         */
        public void mouseClicked(MouseEvent e){}
        /**
         * Not used in this application
         */
        public void mouseExited(MouseEvent e){}
    }

    /**
     * Listener for handling button-click events for "Play" move.
     */
    class PlayButtonListener implements ActionListener{
        /**
        * Handles button-click events for "Play" move.
        */
        public void actionPerformed(ActionEvent e){
            //Construct the card index array
            int[] cardIdx = null;
            int count = 0;
            for(int i = 0; i < selected.length; i++)
                if(selected[i] == true)
                    count++;
            
            cardIdx = new int[count];
            int top = 0;
            for(int i = 0; i < selected.length; i++)
                if(selected[i] == true){
                    cardIdx[top] = i;
                    top++;
                }

            client.sendMessage(new CardGameMessage(CardGameMessage.MOVE, activePlayer, cardIdx));
        }
    }

    /**
     * Listener for handling button-click events for "Pass" move.
     */
    class PassButtonListener implements ActionListener{
        /**
        * Handles button-click events for "Play" move.
        */
        public void actionPerformed(ActionEvent e){
            client.sendMessage(new CardGameMessage(CardGameMessage.MOVE, activePlayer, null));
        }
    }

    /**
     * Listener for handling button-click events for connect button.
     */
    class ConnectMenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String promptPlayerName = JOptionPane.showInputDialog("Please type your player name: ");
            String ipAddress = JOptionPane.showInputDialog("Please type the server IP address");
            int port = Integer.parseInt(JOptionPane.showInputDialog("Please type the TCP port of the server"));

            client = game.getClient();

            client.setPlayerName(promptPlayerName);
            client.setServerIP(ipAddress);
            client.setServerPort(port);

            client.connect();
        }
    }

    /**
     * Listener for handling button-click events for quit button.
     */
    class QuitMenuItemListener implements ActionListener{
        /**
         * Handles button-click events for quit. Terminates the application.
         */
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    /**
     * Listener for handling chat-input events
     */
    class ChatInputListener implements ActionListener{
        /**
         * Handles chat-input events. Appends the player name and chatInput content to chatArea.
         */
        public void actionPerformed(ActionEvent e){
            String messageToBeSent = chatInput.getText();
            client.sendMessage(new CardGameMessage(CardGameMessage.MSG, -1, messageToBeSent));
            chatInput.setText("");
            
        }
    }

}
