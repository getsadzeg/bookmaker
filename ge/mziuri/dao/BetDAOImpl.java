
package ge.mziuri.dao;

import ge.mziuri.enums.RESULT;
import ge.mziuri.metainfo.DatabaseMeta;
import ge.mziuri.model.Bet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BetDAOImpl implements BetDAO {
    
    private Connection con;
    private PreparedStatement pstmt;

    public BetDAOImpl() {
        try {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(DatabaseMeta.databaseURL, DatabaseMeta.username, DatabaseMeta.passsword);
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void addBet(Bet bet) {
        try {
            pstmt = con.prepareStatement("INSERT INTO bet (id, game_id, book_id, result, chosen_team) VALUES ("
                + "?, ?, ?, ?, ?)");
            pstmt.setInt(1, bet.getId());
            pstmt.setInt(2, bet.getGame().getId());
            pstmt.setInt(3, bet.getBook_id());
            pstmt.setString(4, bet.getResult().toString());
            pstmt.setString(5, bet.getChosenteam());
            pstmt.executeUpdate();
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteBet(int betID) {
        try {
            pstmt = con.prepareStatement("DELETE FROM bet WHERE id = ?");
            pstmt.setInt(1, betID);
            pstmt.executeUpdate();
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public RESULT getResult(int betID) {
        RESULT res = null;
        try {
            pstmt = con.prepareStatement("SELECT result FROM bet WHERE id = ?");
            pstmt.setInt(1, betID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String stringres = rs.getString("result");
                res = RESULT.valueOf(stringres);
                
            }
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }
    
}


