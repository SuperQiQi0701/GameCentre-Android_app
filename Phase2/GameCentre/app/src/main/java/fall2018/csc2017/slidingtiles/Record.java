package fall2018.csc2017.slidingtiles;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.io.Serializable;

import Basic.Main;
import Basic.SuperRecord;

public class Record extends SuperRecord implements Comparable<Record>, Serializable {

//    /**
//     * The complexity of the game.
//     */
//    private int complexity = Main.INSTANCE.getBoardManager().getGame().getComplexity();
//
//
//    /**
//     * The current score of the game.
//     */
//    private int finalScore = Main.INSTANCE.getBoardManager().getScore();
//
//
//    /**
//     * The current username of the game.
//     */
//    private String userName = Main.INSTANCE.getUserManager().getCurrentUser();

    Record(int complexity, int finalScore, String userName){
        super(complexity, finalScore, userName);
        super.setComplexity(Main.INSTANCE.getBoardManager().getGame().getComplexity());
        super.setFinalScore(Main.INSTANCE.getBoardManager().getScore());
        super.setUserName(Main.INSTANCE.getUserManager().getCurrentUser());
    }
    /**
     * Return a string type of record.
     *
     * @return a string type of record.
     */

    @SuppressLint("DefaultLocale")
    @Override
    protected String recordToString(String game) {
//        String temp = "User: %s, took %d steps in game: %s, in level: %d";
//        String gameName = Board.GAME_NAME;
//        return String.format(temp, userName, finalScore, gameName, complexity - 2);
//        String gameName = Board.GAME_NAME;
        return super.recordToString(Board.getGameName());
    }

    /**
     * Return username
     *
     * @return username
     */
    @Override
    public String getUserName() {
        return super.getUserName();
    }

    public int compareTo(@NonNull Record anotherRecord) {
//        return (this.finalScore - anotherRecord.finalScore);
        return(super.getFinalScore() - anotherRecord.getFinalScore());
    }


    /**
     * Return complexity
     *
     * @return complexity
     */
    @Override
    public int getComplexity() {
//        return this.complexity;
        return super.getComplexity();
    }


    /**
     * Return true if r has lower final score
     *
     * @param r is a record of the game.
     * @return true if r has lower final score
     */
    @Override
    protected boolean checkLowerScore(SuperRecord r) {
//        return this.finalScore >= r.finalScore;
        return super.checkLowerScore(r);
    }

}