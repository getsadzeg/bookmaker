
package ge.mziuri.dao;

import ge.mziuri.model.Bet;
import ge.mziuri.model.Book;


public interface BookDAO {
    void CreateBook(Book book);
    void DeleteBook(int bookID);
    void addGameBet(Bet bet);
    void DeleteGameBet(Bet bet);
    void Winner();

}
   