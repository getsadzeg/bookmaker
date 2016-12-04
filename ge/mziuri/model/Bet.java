
package ge.mziuri.model;

import ge.mziuri.enums.RESULT;
import static ge.mziuri.enums.RESULT.NO_DATA;
import java.io.Serializable;


public class Bet implements Serializable {
    private int id;
    
    private int book_id;
    
    private Game game;

    private String chosenteam;
    
    private RESULT result;
    
    public Bet(int id, int book_id, String chosenteam, Game game) {
        this.id = id;
        this.book_id = book_id;
        this.chosenteam = chosenteam;
        this.game = game;
        result = NO_DATA;
    }
    
    public Bet(int id, int book_id, String chosenteam, Game game, RESULT result) {
        this.id = id;
        this.book_id = book_id;
        this.chosenteam = chosenteam;
        this.game = game;
        this.result = result;
    }
    
    

    @Override
    public String toString() {
        return "Bet{" + "id=" + getId() + ", book_id=" + getBook_id() + ", game=" + game.toString() + ", chosenteam=" + getChosenteam()
                + ", result=" + getResult() +  '}';
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RESULT getResult() {
        return result;
    }

    public void setResult(RESULT result) {
        this.result = result;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
    
    
    
}
