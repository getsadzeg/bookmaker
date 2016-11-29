
package ge.mziuri.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    static DataInputStream in = null;
    static DataOutputStream out = null;
    static Socket socket = null;
    
    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 8659);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            Thread thread = new Thread(){
                
                @Override
                public void run() {
                    try {
                        while (true) {
                            String msg = in.readUTF();
                           // do some stuff
                        }
                    } catch(IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            };
            thread.start();
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                out.writeUTF(text);
                if (text.equals("exit")) { 
                    break;
                }
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }
}
