
package ge.mziuri.dao;

import ge.mziuri.exception.IncorrectGameException;
import ge.mziuri.exception.NoSuchTeamException;
import ge.mziuri.model.Game;
import java.util.ArrayList;
import java.util.Date;

public interface GameDAO {
    void addGame(Game game);
    void deleteGame(int gameID);
    void editGame(int oldgameID, Game game);
    ArrayList<Game> aboutGames();
    Date getDate(int gameID);
    double getCoefficient(String team, Game game) throws IncorrectGameException, NoSuchTeamException;
}
