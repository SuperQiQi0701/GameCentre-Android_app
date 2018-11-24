package ColorMatching;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.io.Serializable;

import Basic.Main;
import fall2018.csc2017.slidingtiles.Board;

public class ColorMatchingRecord implements Comparable<ColorMatchingRecord>, Serializable {


    /**
     * The complexity of the game.
     */
    private int complexity = Main.INSTANCE.getColorBoardManager().getGame().getComplexity();

    /**
     * The current score of the game.
     */
    private int finalScore = Main.INSTANCE.getColorBoardManager().getScore();

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
        String gameName = ColorBoard.GAME_NAME;
        return String.format(temp, userName, finalScore, gameName, complexity);
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
    public int compareTo(@NonNull ColorMatchingRecord anotherRecord) {
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
    boolean checkLowerScore(ColorMatchingRecord r) {
        return this.finalScore >= r.finalScore;
    }

}
