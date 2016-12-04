
package ge.mziuri.network;

import ge.mziuri.dao.BetDAOImpl;
import ge.mziuri.dao.BookDAOImpl;
import ge.mziuri.dao.GameDAOImpl;
import ge.mziuri.exception.IncorrectGameException;
import ge.mziuri.exception.NoSuchTeamException;
import ge.mziuri.model.Game;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerThread extends Thread {
    GameDAOImpl gamedao;
    BetDAOImpl betdao;
    BookDAOImpl bookdao;
    
    private int clientId;

    private Socket socket;

    private ObjectInputStream in;

    private ObjectOutputStream out;

    public ServerThread(Socket socket) {
        this.socket = socket;
        gamedao = new GameDAOImpl();
        betdao = new BetDAOImpl();
        bookdao = new BookDAOImpl();
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ServerThread(Socket socket, int clientId) {
        this(socket);
        this.clientId = clientId;
    }
    
    public int getClientId() {
        return clientId;
    }

    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                Object[] obj = (Object[]) in.readObject();
                Object[] ans = new Object[3];
                        while (true) {
                            switch((String)obj[0]) {
                                case "game 1": 
                                    gamedao.addGame((Game)obj[1]);
                                    ans[0] = "game 1";
                                    ans[1] = "game added successfully";
                                    sendCommand(ans);
                                    break;
                                case "game 2":
                                    gamedao.deleteGame((Integer)obj[1]);
                                    ans[0] = "game 2";
                                    ans[1] = "game deleted successfully";
                                    sendCommand(ans);
                                    break;
                                case "game 3":
                                    gamedao.editGame(Integer.parseInt((String)obj[1]), (Game)obj[2]);
                                    ans[0] = "game 3";
                                    ans[1] = "game edited successfully";
                                    sendCommand(ans);
                                case "game 4":
                                    ans[0] = "game 4";
                                    ans[1] = "entered 'about games'";
                                    ans[2] = gamedao.aboutGames();
                                    sendCommand(ans);
                                    break;
                                case "game 5":
                                    ans[0] = "game 5";
                                    ans[1] = "entered 'get date' ";
                                    ans[2] = gamedao.getDate((Integer)obj[1]);
                                    sendCommand(ans);
                                    break;
                                case "game 6":
                                    try {
                                        ans[0] = "game 6";
                                        ans[1] = "entered 'get coefficient' ";
                                        ans[2] = gamedao.getCoefficient((String)obj[1], (Game)obj[2]);
                                        sendCommand(ans);
                                    }
                                    catch(IncorrectGameException | NoSuchTeamException ex) {
                                        System.out.println("incorrect game or no such team");
                                    }
                                break;
                                    
                            }
                        }
                
            } catch(IOException | ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void sendCommand(Object[] obj) {
        try {
            out.writeObject(obj);
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void closeConnection() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
