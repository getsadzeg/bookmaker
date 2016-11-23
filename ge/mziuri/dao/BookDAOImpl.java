
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
    
}
