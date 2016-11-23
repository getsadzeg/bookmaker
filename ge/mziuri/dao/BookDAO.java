
package ge.mziuri.dao;

import ge.mziuri.model.Bet;
import ge.mziuri.model.Book;


public interface BookDAO {
    void createBook(Book book);
    void deleteBook(int bookID);
    boolean isWinner(int bookID);
}
   