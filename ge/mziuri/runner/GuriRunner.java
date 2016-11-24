
package ge.mziuri.runner;

import ge.mziuri.dao.BetDAO;
import ge.mziuri.dao.BetDAOImpl;
import ge.mziuri.dao.BookDAO;
import ge.mziuri.dao.BookDAOImpl;
import ge.mziuri.dao.GameDAO;
import ge.mziuri.dao.GameDAOImpl;
import ge.mziuri.model.Game;
import java.util.Date;


public class GuriRunner {
    public static void main(String[] args) {
        BetDAO betdao = new BetDAOImpl();
        GameDAO gamedao = new GameDAOImpl();
        BookDAO bookdao = new BookDAOImpl();
        gamedao.addGame(new Game(1, new Date(), "Barcelona", "Manchester UTD", 1.7, 2.8));
        
    }
}
