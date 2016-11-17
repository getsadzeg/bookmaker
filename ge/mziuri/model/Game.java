
package ge.mziuri.model;

import java.util.Date;

public class Game {
    private Date date;
    private String team1;
    private String team2;
    private double coefficient1;
    private double coefficient2;
    public Game() {
        
    }
    public Game(Date date, String team1, String team2, double coefficient1, double coefficient2) {
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.coefficient1 = coefficient1;
        this.coefficient2 = coefficient2;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the team1
     */
    public String getTeam1() {
        return team1;
    }

    /**
     * @param team1 the team1 to set
     */
    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    /**
     * @return the team2
     */
    public String getTeam2() {
        return team2;
    }

    /**
     * @param team2 the team2 to set
     */
    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    /**
     * @return the coefficient1
     */
    public double getCoefficient1() {
        return coefficient1;
    }

    /**
     * @param coefficient1 the coefficient1 to set
     */
    public void setCoefficient1(double coefficient1) {
        this.coefficient1 = coefficient1;
    }

    /**
     * @return the coefficient2
     */
    public double getCoefficient2() {
        return coefficient2;
    }

    /**
     * @param coefficient2 the coefficient2 to set
     */
    public void setCoefficient2(double coefficient2) {
        this.coefficient2 = coefficient2;
    }

    @Override
    public String toString() {
        return "Game{" + "date=" + date + ", team1=" + team1 + ", team2=" + team2 + ", coefficient1=" + coefficient1 + ", coefficient2=" + coefficient2 + '}';
    }
    
}
