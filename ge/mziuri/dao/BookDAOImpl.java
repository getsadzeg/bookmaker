
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
            pstmt = con.prepareStatement("INSERT into book (id, money, user_id) VALUES("
                    + "?, ?, ?)");
            pstmt.setInt(1, book.getId());
            pstmt.setDouble(2, book.getMoney());
            pstmt.setInt(3, book.getUser_id());
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
    public String myBooks(int user_id) {
        StringBuilder info = new StringBuilder();
        try {
            pstmt = con.prepareStatement("SELECT id, money FROM book WHERE user_id = ?");
            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int money = rs.getInt("money");
                info.append("id: " + id + " money: " + money + System.lineSeparator());
            }
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return info.toString();
    }
    
    
    
    
    @Override
    public String getBookInfo(Book book) {
        ArrayList<Book> books = new ArrayList<>();
        StringBuilder bookinfo = new StringBuilder();
        try {
            pstmt = con.prepareStatement("SELECT bet.chosen_team, bet.result, game.team_1, game.team_2, game.gamedate"
                    + " FROM bet INNER JOIN game ON bet.game_id = game.id WHERE bet.book_id = ?");
            pstmt.setInt(1, book.getId());
            ResultSet rs = pstmt.executeQuery();
            int num = 1;
            bookinfo = new StringBuilder(num + ".  book_id: " + book.getId() + ", money: " + book.getMoney());
            while(rs.next()) {
                String team_1 = rs.getString("team_1");
                String team_2 = rs.getString("team_2");
                Date date = rs.getDate("gamedate");
                String chosenteam = rs.getString("chosen_team");
                String stringres = rs.getString("result");
                RESULT result = RESULT.valueOf(stringres);
                bookinfo.append(", team_1: " + team_1 + ", team_2: " + team_2 +  ", game date: " 
                        + date.toString() + ", chosen team: " + chosenteam + ", result: " + result);
                bookinfo.append(", winner: " + isWinner(book.getId()) + System.lineSeparator());
                num++;
            }
            //if book is winner or not
            
                }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bookinfo.toString();
    }
    
}
