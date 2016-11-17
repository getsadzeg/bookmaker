
package ge.mziuri.model;

import java.util.ArrayList;


public class Book {
    private ArrayList bets = new ArrayList<Bet>();
    private int money;

    /**
     * @return the bets
     */
    public ArrayList getBets() {
        return bets;
    }

    public Book() {
    }

    public Book(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    

}
