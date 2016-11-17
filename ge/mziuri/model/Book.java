
package ge.mziuri.model;

import java.util.ArrayList;


public class Book {
    private ArrayList bets = new ArrayList<Bet>();

    /**
     * @return the bets
     */
    public ArrayList getBets() {
        return bets;
    }

    /**
     * @param bets the bets to set
     */
    public void setBets(ArrayList bets) {
        this.bets = bets;
    }

    @Override
    public String toString() {
        return "Book{" + "bets=" + bets + '}';
    }
    
}
