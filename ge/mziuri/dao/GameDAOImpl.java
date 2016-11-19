
package ge.mziuri.dao;

import ge.mziuri.metainfo.DatabaseMeta;
import ge.mziuri.model.Game;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;


public class GameDAOImpl implements GameDAO {
    private Connection con;
    private PreparedStatement pstmt;
    
    public GameDAOImpl() {
        try {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(DatabaseMeta.databaseURL, DatabaseMeta.username, DatabaseMeta.passsword);
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void addGame(Game game) {
        try {
            pstmt = con.prepareStatement("INSERT INTO game(id, gamedate, team_1, team_2, coefficient_1, coefficient_2)"
                + "VALUES(?, ?, ?, ?, ?, ?");
            pstmt.setInt(1, game.getId());
            pstmt.setDate(2, new java.sql.Date(game.getDate().getTime())); //needs to fix
            pstmt.setString(3, game.getTeam1());
            pstmt.setString(4, game.getTeam2());
            pstmt.setDouble(5, game.getCoefficient1());
            pstmt.setDouble(6, game.getCoefficient2());
            pstmt.executeUpdate();
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void deleteGame(int gameID) {
        try {
            pstmt = con.prepareStatement("DELETE FROM game WHERE id = ?");
            pstmt.setInt(1, gameID);
            pstmt.executeUpdate();
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void editGame(int oldgameID, Game game) {
        try {
            pstmt = con.prepareStatement("UPDATE TABLE SET id = ?, gamedate = ?, team_1 = ?, team_2 = ?, coefficient_1 = ?, coefficient_2 = ?"
                    + "WHERE id = ?");
            pstmt.setInt(1, game.getId());
            pstmt.setDate(2, new java.sql.Date(game.getDate().getTime())); //needs to fix
            pstmt.setString(3, game.getTeam1());
            pstmt.setString(4, game.getTeam2());
            pstmt.setDouble(5, game.getCoefficient1());
            pstmt.setDouble(6, game.getCoefficient2());
            pstmt.setInt(7, oldgameID);
            pstmt.executeUpdate();
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Game> aboutGames() {
        ArrayList<Game> games = new ArrayList<>();
        try {
            pstmt = con.prepareStatement("SELECT * FROM game");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                Date date = new Date(rs.getDate("gamedate").getTime());
                String team1 = rs.getString("team_1");
                String team2 = rs.getString("team_2");
                double coefficient1 = rs.getDouble("coefficient_1");
                double coefficient2 = rs.getDouble("coefficient_2");
                games.add(new Game(id, date, team1, team2, coefficient1, coefficient2));
            }
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return games;
    }

    @Override
    public Date getDate(int gameID) {
       /* Date date = new Date();
        try {
            pstmt = con.prepareStatement("SELECT gamedate FROM game WHERE id = ?");
            pstmt.setInt(1, gameID);
            
        }*/
        return new Date(); //
       
    }

    @Override
    public ArrayList<Integer> getCoefficient(String team) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
