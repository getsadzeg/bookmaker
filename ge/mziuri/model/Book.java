
package ge.mziuri.model;

import java.util.ArrayList;


public class Book {
    private ArrayList bets = new ArrayList<Bet>();
   
    
    private double money;
    
    public Book() {
        
    }
    
    public Book(ArrayList bets, double money) {
        this.bets = bets;
        this.money = money;
    }

    /**
     * @return the bets
     */
    public ArrayList getBets() {
        return bets;
    }


    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    

}
