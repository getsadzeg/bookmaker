
package ge.mziuri.dao;

import ge.mziuri.enums.RESULT;
import ge.mziuri.model.Bet;


public interface BetDAO {
    void addBet(Bet bet);
    void deleteBet(int betID);
    RESULT getResult(int betID);
}
