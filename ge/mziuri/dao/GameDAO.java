
package ge.mziuri.dao;

import ge.mziuri.model.Game;
import java.util.ArrayList;
import java.util.Date;

public interface GameDAO {
    void addGame(Game game);
    void deleteGame(int gameID);
    void editGame(int gameID);
    ArrayList<Game> aboutGames();
    Date getDate(int gameID);
    ArrayList<Integer> getCoefficient(String team);
}
