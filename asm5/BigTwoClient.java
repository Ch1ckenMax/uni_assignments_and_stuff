import java.io.*;
import java.net.*;

/**
 * Models a Big Two game client that is responsible for establishing a connection and communicating with the Big Two game server.
 * 
 * @author Li Hoi Kit
 */
public class BigTwoClient implements NetworkGame{
    //Private variables
    private BigTwo game;
    private BigTwoGUI gui;
    private Socket sock;
    private ObjectOutputStream oos;
    private int playerID;
    private String playerName;
    private String serverIP;
    private int serverPort;

    //Constructor

    /**
     * Creates a Big Two client.
     * 
     * @param game BigTwo object that associates with this client
     * @param gui GUI object that associates with this client
     */
    public BigTwoClient(BigTwo game, BigTwoGUI gui){
        this.game = game;
        this.gui = gui;
    }

    //Methods

    /**
     * Getter method that returns the playerID of this client.
     * @return playerID
     */
    public int getPlayerID(){
        return playerID;
    }

    /**
     * Getter method that returns the player name of this client.
     * @return playerName
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * Getter method that returns the IP address of the server to be connected to this client.
     * @return server IP
     */
    public String getServerIP(){
        return serverIP;
    }

    /**
     * Getter method that returns the TCP port of the server connected to this client.
     * @return the TCP port
     */
    public int getServerPort(){
        return serverPort;
    }

    /**
     * Setter method for setting the playerID
     * @param playerID the playerID(index)
     */
    public void setPlayerID(int playerID){
        this.playerID = playerID;
    }

    /**
     * Setter method for setting the player name
     * @param playerName the player name
     */
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    /**
     * Setter method for setting the IP address of the server to be connected to this client
     * @param serverIP the server IP
     */
    public void setServerIP(String serverIP){
        this.serverIP = serverIP;
    }

    /**
     * Setter method for setting the TCP port of the server to be connected to this client.
     * @param serverPort the tcp port
     */
    public void setServerPort(int serverPort){
        this.serverPort = serverPort;
    }

    /**
     * Makes a socket connection with the game server
     */
    public void connect(){
        try{
            sock = new Socket(serverIP,serverPort);
            oos = new ObjectOutputStream(sock.getOutputStream());
            Thread serverHandlerThread = new Thread(new ServerHandler());
            serverHandlerThread.start();
        }
        catch(Exception ex){
            ex.printStackTrace();
            gui.printMsg("Error occured when trying to connect to the server.");
        }
    }
    /**
     * Parsing the messages received from the game server.
     * 
     * @param message the message
     */
    public synchronized void parseMessage(GameMessage message){
        int type = message.getType();
        switch(type){
            case CardGameMessage.PLAYER_LIST: 
                playerID = message.getPlayerID();
                String[] playerNames = (String[]) message.getData();
                for(int i = 0; i < playerNames.length; i++)
                    game.getPlayerList().get(i).setName(playerNames[i]);
                sendMessage(new CardGameMessage(CardGameMessage.JOIN, -1, getPlayerName()));
                gui.repaint();
            break;
            
            case CardGameMessage.JOIN:
                game.getPlayerList().get(message.getPlayerID()).setName((String) message.getData());
                if(this.playerID == message.getPlayerID())
                    sendMessage(new CardGameMessage(CardGameMessage.READY, -1, null));
                gui.repaint();
            break;

            case CardGameMessage.FULL:
                gui.printMsg("The server is full! Cannot join the game.");
            break;

            case CardGameMessage.QUIT:
                gui.printMsg(game.getPlayerList().get(message.getPlayerID()).getName() + " left the game.");
                if(!game.endOfGame()){
                    game.getPlayerList().get(message.getPlayerID()).setName(null);
                    gui.setActivePlayer(-1);
                    gui.repaint();
                    gui.disable();
                    sendMessage(new CardGameMessage(CardGameMessage.READY, -1, null));
                }
            break;

            case CardGameMessage.READY:
                gui.printMsg(game.getPlayerList().get(message.getPlayerID()).getName() + " is ready.");
            break;

            case CardGameMessage.START:
                gui.reset();
                game.start((Deck) message.getData());
            break;

            case CardGameMessage.MOVE:
                game.makeMove(message.getPlayerID(), (int[]) message.getData());
                gui.actionsAfterMove();
            break;

            case CardGameMessage.MSG:
                gui.printMsgToChat((String) message.getData());
            break;
                
        }
    }

    /**
     * Sending the specified message to the game server.
     * 
     * @param message the message
     */
    public synchronized void sendMessage(GameMessage message){
        try{
            oos.writeObject(message);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    class ServerHandler implements Runnable{
        private ObjectInputStream os;

        /**
         * For running the thread.
         */
        public void run(){
            try{
                os = new ObjectInputStream(sock.getInputStream());
                while(true){
                    GameMessage msg = (GameMessage) os.readObject();
                    parseMessage(msg);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}