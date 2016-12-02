
package ge.mziuri.network;

import ge.mziuri.dao.GameDAO;
import ge.mziuri.dao.GameDAOImpl;
import ge.mziuri.model.Game;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Client {
    static ObjectInputStream in = null;
    static ObjectOutputStream out = null;
    static Socket socket = null;
    static Object[] obj = null;
    
    public static void main(String[] args) {
        try {
            
            socket = new Socket("localhost", 8659);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            Thread thread = new Thread(){
                
                @Override
                public void run() {
                    try {
                        while (true) {
                            obj = (Object[]) in.readObject();
                            for(int i=0; i<obj.length; i++) {
                                if(obj[0] == "game 1") new GameDAOImpl().addGame((Game)obj[1]);
                            }
                            
                        }
                    } catch(IOException | ClassNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            };
            thread.start();
            while (scanner.hasNextLine()) {
                
                System.out.println("1 - add game"
                                    + System.lineSeparator() + "2 - delete game"
                                    + System.lineSeparator() + "3 - edit game"
                                    + System.lineSeparator() + "4 - about games"
                                    + System.lineSeparator() + "5 - get coefficient");
                String text = scanner.nextLine();
                if(text.contains("game 1")) {
                    out.writeObject("game 1");
                    out.writeObject(makeGameObject(text));
                }
                
                
                
                //out.writeUTF(text);
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
    
    public static Game makeGameObject(String args) {
                    String[] splitted = args.split("\\s+");
                    Integer id = Integer.parseInt(splitted[3]);
                    DateFormat formatter; 
                    Date date = null;
                    formatter = new SimpleDateFormat("dd-MMM-yy");
                     try {
                         date = formatter.parse(splitted[4]);
                     } 
                     catch(ParseException ex) {
                         System.out.println(ex.getMessage());
                     }
                     String team1 = splitted[5];
                     String team2 = splitted[6];
                     Integer coefficient1 = Integer.parseInt(splitted[7]);
                     Integer coefficient2 = Integer.parseInt(splitted[8]);
                     return new Game(id.intValue(), date, team1, team2, coefficient1.doubleValue(), coefficient2.doubleValue());
    }
}
