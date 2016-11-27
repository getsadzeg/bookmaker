
package ge.mziuri.dao;

import ge.mziuri.enums.RESULT;
import ge.mziuri.metainfo.DatabaseMeta;
import ge.mziuri.model.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class BookDAOImpl implements BookDAO {
    private Connection con;
    private PreparedStatement pstmt;

    public BookDAOImpl() {
        try {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(DatabaseMeta.databaseURL, DatabaseMeta.username, DatabaseMeta.passsword);
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void createBook(Book book) {
        try {
            pstmt = con.prepareStatement("INSERT into book (id, money) VALUES("
                    + "?, ?)");
            pstmt.setInt(1, book.getId());
            pstmt.setDouble(2, book.getMoney());
            pstmt.executeUpdate();
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteBook(int bookID) {
        try {
            pstmt = con.prepareStatement("DELETE from book WHERE id = ?");
            pstmt.setInt(1, bookID);
            pstmt.executeUpdate();
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean isWinner(int bookID) {
        ArrayList results = new ArrayList<RESULT>();
        try {
            pstmt = con.prepareStatement("SELECT result FROM bet WHERE book_id = ?");
            pstmt.setInt(1, bookID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String stringres = rs.getString("result");
                RESULT result = RESULT.valueOf(stringres);
                if(result.equals(RESULT.WON))
                results.add(result);
            }
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return !results.isEmpty();
    }
    
    @Override
    public ArrayList myBooks(int user_id) {
        ArrayList<Book> list = new ArrayList();
        try {
            pstmt = con.prepareStatement("SELECT id, money FROM book WHERE user_id = ?");
            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                int money = rs.getInt("money");
                Book book = new Book();
                book.setId(id);
                book.setMoney(money);
                list.add(book);
            }
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    
    
    
    @Override
    public String getBookInfo(Book book) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            pstmt = con.prepareStatement("SELECT bet.chosenteam, bet.result, game.team_1, game.team_2, game.gamedate"
                    + " FROM bet INNER JOIN game ON bet.game_id = game.id WHERE bet.book_id = ?");
            pstmt.setInt(1, book.getId());
            ResultSet rs = pstmt.executeQuery();
            int num = 1;
            StringBuilder bookinfo = new StringBuilder("book_id: " + book.getId() + "money: " + book.getMoney());
            while(rs.next()) {
                String team_1 = rs.getString("game.team_1");
                String team_2 = rs.getString("game.team_2");
                Date date = rs.getDate("game.gamedate");
               
               // int id = rs.getInt("id");
                String chosenteam = rs.getString("bet.chosen_team");
                String stringres = rs.getString("bet.result");
                RESULT result = RESULT.valueOf(stringres);
                bookinfo.append(num + "team_1: " + team_1 + " team_2: " + team_2 +  "game date: " 
                        + date.toString() + "chosen team" + chosenteam + "result: " + result);
            }
            //if book is winner or not 
                }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //and cast StringBuilder to String
    }
    
}
