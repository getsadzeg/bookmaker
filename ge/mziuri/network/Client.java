package ge.mziuri.network;

import ge.mziuri.model.Game;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Client {

    static ObjectInputStream in = null;
    static ObjectOutputStream out = null;
    static Socket socket = null;
    static Object[] objects = new Object[3];
    static Object[] answers = new Object[3];

    public static void main(String[] args) throws IOException {
        try {
            socket = new Socket("localhost", 8659);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    System.out.println("before initializing answers");
                    
                    System.out.println("after initializing answers");
                    while(true) {
                        answers = (Object[]) in.readObject();
                        switch ((String) answers[0]) {
                            case "game 1":
                            case "game 2":
                            case "game 3":
                                System.out.println(answers[0]);
                                System.out.println(answers[1]);
                                break;
                            case "game 4":
                            case "game 5":
                            case "game 6":
                                System.out.println(answers[0]);
                                System.out.println(answers[1]);
                                System.out.println(answers[2]);
                                break;

                        }
                    }
                }
                catch(IOException | ClassNotFoundException ex) {
                    System.out.println("in catch block of run()");
                    
                    System.out.println("answers : " + Arrays.toString(answers));
                   // System.out.println(in.available());
                    ex.printStackTrace();
                }
            }
        };
        thread.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "1 - add game"
                + System.lineSeparator() + "2 - delete game"
                + System.lineSeparator() + "3 - edit game"
                + System.lineSeparator() + "4 - about games"
                + System.lineSeparator() + "5 - get date"
                + System.lineSeparator() + "6 - get coefficient");
        while (scanner.hasNextLine()) {

            try {
                String text = scanner.nextLine();
                if (text.contains("game 1")) {
                    objects[0] = "game 1";
                    objects[1] = processGame(text);
                    System.out.println("before write object: " + Arrays.toString(objects));
                    out.writeObject(objects);
                    System.out.println("yep objects written " + Arrays.toString(objects));
                } else if (text.contains("game 2")) {
                    objects[0] = "game 2";
                    objects[1] = getID(text);
                    objects[2] = processGame(text);
                    out.writeObject(objects);
                } else if (text.contains("game 3")) {
                    objects[0] = "game 3";
                    objects[1] = getAdditionalParam(text); //oldGameID
                    objects[2] = processGame(text); //game obj
                    out.writeObject(objects);
                } else if (text.contains("game 4")) {
                    objects[0] = "game 4";
                    out.writeObject(objects);
                } else if (text.contains("game 5")) {
                    objects[0] = "game 5";
                    objects[1] = getID(text);
                    out.writeObject(objects);
                } else if (text.contains("game 6")) {
                    objects[0] = "game 6";
                    objects[1] = getAdditionalParam(text); //team
                    objects[2] = processGame(text); //game obj
                    out.writeObject(objects);
                } else if (text.equals("exit")) {
                    return;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }   finally {
                System.out.println("got to finally block");
                /*try {
                    out.close();
                    in.close();
                    socket.close();
                }
                catch(IOException ex) {
                    System.out.println("came into finally's catch");
                    ex.printStackTrace();
                } */
                
            }
        }
    }

    public static Game processGame(String args) {
        Double coefficient1 = null;
        Double coefficient2 = null;
        String[] splitted = args.split(" ");
        Integer id = Integer.parseInt(splitted[2]);
        DateFormat formatter;
        Date date = null;
        formatter = new SimpleDateFormat("dd-mm-yyyy");
        try {
            date = formatter.parse(splitted[3]);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        String team1 = splitted[4];
        String team2 = splitted[5];
        try {
            coefficient1 = Double.parseDouble(splitted[6]);
            coefficient2 = Double.parseDouble(splitted[7]);
        } catch(NumberFormatException ex) {
            
        }
        return new Game(id, date, team1, team2, coefficient1.doubleValue(), coefficient2.doubleValue());
    }

    public static int getID(String args) {
        String[] splitted = args.split(" ");
        return Integer.parseInt(splitted[2]);
    }

    public static String getAdditionalParam(String args) { //to oldgameID's and another additional params. passed to the end of input
        String[] splitted = args.split(" ");
        return splitted[8];
    }
}
