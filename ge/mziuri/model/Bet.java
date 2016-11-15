
package ge.mziuri.model;


public class Bet {
    private double coefficient;

    private final String chosenteam;
    
    public Bet(String chosenteam) {
        this.chosenteam = chosenteam;
    }
    /**
     * @return the coefficient1
     */
    public double getCoefficient() {
        return coefficient;
    }

    
    public void setCoefficient1(double coefficient) {
        this.coefficient = coefficient;
    }

    
    
}
