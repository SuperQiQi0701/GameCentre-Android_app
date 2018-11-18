package fall2018.csc2017.slidingtiles;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.io.Serializable;

public class Record implements Comparable<Record>, Serializable {


    /**
     * The complexity of the game.
     */
    private int complexity = Main.INSTANCE.getBoardManager().getGame().getComplexity();


    /**
     * The current score of the game.
     */
    private int finalScore = Main.INSTANCE.getBoardManager().getScore();


    /**
     * The current username of the game.
     */
    private String userName = Main.INSTANCE.getUserManager().getCurrentUser();


    /**
     * Return a string type of record.
     *
     * @return a string type of record.
     */
    @SuppressLint("DefaultLocale")
    String recordToString() {
        String temp = "User: %s, took %d steps in game: %s, in level: %d";
        String gameName = Board.GAME_NAME;
        return String.format(temp, userName, finalScore, gameName, complexity - 2);
    }


    /**
     * Return username
     *
     * @return username
     */
    String getUserName() {
        return userName;
    }


    @Override
    public int compareTo(@NonNull Record anotherRecord) {
        return (this.finalScore - anotherRecord.finalScore);
    }


    /**
     * Return complexity
     *
     * @return complexity
     */
    int getComplexity() {
        return this.complexity;
    }


    /**
     * Return true if r has lower final score
     *
     * @param r is a record of the game.
     * @return true if r has lower final score
     */
    boolean checkLowerScore(Record r) {
        return this.finalScore >= r.finalScore;
    }


}