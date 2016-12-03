package ge.mziuri.network;

import ge.mziuri.model.Game;
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
                while (true) {
                    try {
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
                        //  System.out.println(answers[0]);
                        //  if(answers[1] != null) System.out.println(answers[1]);
                    } catch (IOException | ClassNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
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
                    out.writeObject("game 1");
                    out.writeObject(processGame(text));
                } else if (text.contains("game 2")) {
                    out.writeObject("game 2");
                    out.writeObject(getID(text));
                    out.writeObject(processGame(text));
                } else if (text.contains("game 3")) {
                    out.writeObject("game 3");
                    out.writeObject(getAdditionalParam(text)); // oldGameID
                    out.writeObject(processGame(text)); //game obj
                } else if (text.contains("game 4")) {
                    out.writeObject("game 4");
                } else if (text.contains("game 5")) {
                    out.writeObject("game 5");
                    out.writeObject(getID(text));
                } else if (text.contains("game 6")) {
                    out.writeObject("game 6");
                    out.writeObject(getAdditionalParam(text)); //team
                    out.writeObject(processGame(text)); //game obj
                } else if (text.equals("exit")) {
                    break;
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } finally {
                in.close();
                out.close();
                socket.close();
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
        String[] splitted = args.split("\\s+");
        return Integer.parseInt(splitted[2]);
    }

    public static String getAdditionalParam(String args) { //to oldgameID's and another additional params. passed to the end of input
        String[] splitted = args.split("\\s+");
        return splitted[8];
    }
}
