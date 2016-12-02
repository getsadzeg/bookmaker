
package ge.mziuri.network;

import ge.mziuri.dao.GameDAO;
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
                            GameDAOImpl gamedao = new GameDAOImpl();
                            obj = (Object[]) in.readObject();
                            switch((String)obj[0]) {
                                case "game 1": 
                                    gamedao.addGame((Game)obj[1]);
                                    break;
                                case "game 2":
                                    gamedao.deleteGame((Integer)obj[1]);
                                    break;
                                case "game 3":
                                    gamedao.editGame(Integer.parseInt((String)obj[1]), (Game)obj[2]);
                                case "game 4":
                                    System.out.println(gamedao.aboutGames());
                                    break;
                                case "game 5":
                                    System.out.println(gamedao.getDate((Integer)obj[1]));
                                    break;
                                case "game 6":
                                    try {
                                        System.out.println(gamedao.getCoefficient((String)obj[1], (Game)obj[2]));
                                    }
                                    catch(IncorrectGameException | NoSuchTeamException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                break;
                                    
                                    
                            }
                        }
                    } catch(IOException | ClassNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            };
            thread.start();
            System.out.println("1 - add game"
                                    + System.lineSeparator() + "2 - delete game"
                                    + System.lineSeparator() + "3 - edit game"
                                    + System.lineSeparator() + "4 - about games"
                                    + System.lineSeparator() + "5 - get date"
                                    + System.lineSeparator() + "6 - get coefficient");
            while (scanner.hasNextLine()) {
                
                
                
                String text = scanner.nextLine();
                if(text.contains("game 1")) {
                    out.writeObject("game 1");
                    out.writeObject(processGame(text));
                }
                else if(text.contains("game 2")) {
                    out.writeObject("game 2");
                    out.writeObject(getID(text));
                    out.writeObject(processGame(text));
                }
                else if(text.contains("game 3")) {
                    out.writeObject("game 3");
                    out.writeObject(getAdditionalParam(text)); // oldGameID
                    out.writeObject(processGame(text)); //game obj
                }
                else if(text.contains("game 4")) {
                    out.writeObject("game 4");
                }
                
                else if(text.contains("game 5")) {
                    out.writeObject("game 5");
                    out.writeObject(getID(text));
                }
                else if(text.contains("game 6")) {
                    out.writeObject("game 6");
                    out.writeObject(getAdditionalParam(text)); //team
                    out.writeObject(processGame(text)); //game obj
                }
                
                
                else if (text.equals("exit")) { 
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
    
    public static Game processGame(String args) {
                    String[] splitted = args.split("\\s+");
                    Integer id = Integer.parseInt(splitted[2]);
                    DateFormat formatter; 
                    Date date = null;
                    formatter = new SimpleDateFormat("dd-MMM-yy");
                     try {
                         date = formatter.parse(splitted[3]);
                     } 
                     catch(ParseException ex) {
                         System.out.println(ex.getMessage());
                     }
                     String team1 = splitted[4];
                     String team2 = splitted[5];
                     Integer coefficient1 = Integer.parseInt(splitted[6]);
                     Integer coefficient2 = Integer.parseInt(splitted[7]);
                     
                     return new Game(id.intValue(), date, team1, team2, coefficient1.doubleValue(), coefficient2.doubleValue());
    }
    
    public static int getID(String args) {
        String[] splitted = args.split("\\s+");
        return Integer.parseInt(splitted[2]);
    }
    
    public static String getAdditionalParam(String args) { //to oldgameID's and another additional params. passed to the end of input
        String[] splitted = args.split("\\s+");
        return splitted[8];
    }
}
