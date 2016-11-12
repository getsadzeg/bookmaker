
package ge.mziuri.model;


public class Bet {
    private double coefficient;

    private String chosenteam;
    
    public Bet(String chosenteam) {
        this.chosenteam = chosenteam;
    }
    /**
     * @return the coefficient1
     */
    public double getCoefficient() {
        return coefficient;
    }

    /**
     * @param coefficient1 the coefficient1 to set
     */
    public void setCoefficient1(double coefficient) {
        this.coefficient = coefficient;
    }

    
    
}
