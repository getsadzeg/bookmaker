
package ge.mziuri.model;


public class Bet {
    private Game game;

    private String chosenteam;
    
    public Bet(String chosenteam, Game game) {
        this.chosenteam = chosenteam;
        this.game = game;
    }
    
    

    @Override
    public String toString() {
        return "Bet{" + "game=" + game.toString() + ", chosenteam=" + getChosenteam() + '}';
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * @return the chosenteam
     */
    public String getChosenteam() {
        return chosenteam;
    }

    /**
     * @param chosenteam the chosenteam to set
     */
    public void setChosenteam(String chosenteam) {
        this.chosenteam = chosenteam;
    }
    
    
    
}
