
package ge.mziuri.network;

import ge.mziuri.dao.BetDAOImpl;
import ge.mziuri.dao.BookDAOImpl;
import ge.mziuri.dao.GameDAOImpl;
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
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
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
                sendCommand(obj);
                
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
