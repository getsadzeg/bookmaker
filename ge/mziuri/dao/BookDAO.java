
package ge.mziuri.dao;

import ge.mziuri.model.Bet;
import ge.mziuri.model.Book;
import java.util.ArrayList;


public interface BookDAO {
    void createBook(Book book);
    void deleteBook(int bookID);
    boolean isWinner(int bookID);
    String myBooks(int user_id);
    String getBookInfo(Book book);
}
   