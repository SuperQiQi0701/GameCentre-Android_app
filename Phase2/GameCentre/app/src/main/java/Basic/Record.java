package Basic;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.io.Serializable;

public class Record implements Comparable<Record>, Serializable {


    /**
     * The complexity of the game.
     */
    private int complexity = DataManager.INSTANCE.getBoardManager().getComplexity();


    /**
     * The current score of the game.
     */
    private int finalScore = DataManager.INSTANCE.getBoardManager().getScore();


    /**
     * The current username of the game.
     */
    private String userName = DataManager.INSTANCE.getCurrentUserName();

    /**
     * The current name of the game.
     */
    private String gameName = DataManager.INSTANCE.getCurrentGameName();


    /**
     * Return a string type of record.
     *
     * @return a string type of record.
     */
    @SuppressLint("DefaultLocale")
    String recordToString() {
        String temp = "User: %s, got score %d in game: %s, in level: %d";
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

    /**
     * Return the game name for this record
     *
     * @return the game name for this record
     */
    public String getGameName() {
        return gameName;
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