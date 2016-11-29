
package ge.mziuri.runner;

import ge.mziuri.dao.BetDAO;
import ge.mziuri.dao.BetDAOImpl;
import ge.mziuri.dao.BookDAO;
import ge.mziuri.dao.BookDAOImpl;
import ge.mziuri.dao.GameDAO;
import ge.mziuri.dao.GameDAOImpl;
import ge.mziuri.enums.RESULT;
import ge.mziuri.exception.IncorrectGameException;
import ge.mziuri.exception.NoSuchTeamException;
import ge.mziuri.model.Bet;
import ge.mziuri.model.Book;
import ge.mziuri.model.Game;
import java.util.ArrayList;
import java.util.Date;


public class GuriRunner {
    public static void main(String[] args) {
        Game game = new Game(1, new Date(), "Barcelona", "Manchester UTD", 1.7, 2.8);
        Game game1 = new Game(2, new Date(), "Real Madrid", "Arsenal", 1.5, 3);
        
        Bet bet = new Bet(1, 1, "Barcelona", game, RESULT.WON);
        Bet bet1 = new Bet(2, 1, "Arsenal", game1, RESULT.LOST);
        ArrayList bets = new ArrayList<>();
        Book book = new Book(1, 1, bets, 50);
        bets.add(bet);
        bets.add(bet1);
        
        BetDAO betdao = new BetDAOImpl();
        GameDAO gamedao = new GameDAOImpl();
        BookDAO bookdao = new BookDAOImpl();
        
        System.out.println(bookdao.myBooks(1));
        System.out.println(bookdao.getBookInfo(book)); 
        //gamedao.addGame(game);
        //gamedao.addGame(game1);
        
        //bookdao.createBook(book);
        //betdao.addBet(bet);
        //betdao.addBet(bet1);
       // betdao.getResult(1);
       // betdao.deleteBet(1);
        
        
        
        /* try {
            System.out.println(gamedao.getCoefficient("Barcelona", game));
        }
        catch(IncorrectGameException | NoSuchTeamException ex) {
            System.out.println(ex.getMessage());
        } */
        /* System.out.println(gamedao.getDate(1).toString());
        System.out.println(gamedao.aboutGames()); */
    }
}
