
package ge.mziuri.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private static int idGenerator = 1;
    
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8659);
            while (true) {
                Socket socket = server.accept();
                ServerThread serverThread = new ServerThread(socket, idGenerator);
                idGenerator++;
                serverThread.start();
            }
            
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
