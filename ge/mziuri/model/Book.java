
package ge.mziuri.model;

import java.util.ArrayList;


public class Book {
    
    private int id;
    
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBets(ArrayList bets) {
        this.bets = bets;
    }
    

}
