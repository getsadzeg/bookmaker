
package ge.mziuri.network;

import ge.mziuri.dao.BetDAOImpl;
import ge.mziuri.dao.BookDAOImpl;
import ge.mziuri.dao.GameDAOImpl;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ServerThread extends Thread {
    GameDAOImpl gamedao;
    BetDAOImpl betdao;
    BookDAOImpl bookdao;
    
    private int clientId;

    private Socket socket;

    private DataInputStream in;

    private DataOutputStream out;

    public ServerThread(Socket socket) {
        this.socket = socket;
        gamedao = new GameDAOImpl();
        betdao = new BetDAOImpl();
        bookdao = new BookDAOImpl();
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
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
                String text = in.readUTF();
                if (text.equals("exit")) {
                    closeConnection();
                    break;
                }
                sendCommand(text);
            } catch(IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void sendCommand(String cmd) {
        try {
            out.writeUTF(cmd);
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
