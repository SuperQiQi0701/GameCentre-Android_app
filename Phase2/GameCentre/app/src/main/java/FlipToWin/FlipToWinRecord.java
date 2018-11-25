package FlipToWin;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.io.Serializable;

import Basic.Main;
import Basic.SuperRecord;
import fall2018.csc2017.slidingtiles.Board;

public class FlipToWinRecord extends SuperRecord implements Comparable<FlipToWinRecord>, Serializable {


//    /**
//     * The complexity of the game.
//     */
//    private int complexity = Main.INSTANCE.getFlipToWinBoardManager().getGame().getComplexity();
//
//
//    /**
//     * The current score of the game.
//     */
//    private int finalScore = Main.INSTANCE.getFlipToWinBoardManager().getScore();
//
//
//    /**
//     * The current username of the game.
//     */
//    private String userName = Main.INSTANCE.getUserManager().getCurrentUser();

    public FlipToWinRecord(int complexity, int finalScore, String userName){
        super(complexity, finalScore, userName);
        super.setComplexity(Main.INSTANCE.getFlipToWinBoardManager().getGame().getComplexity());
        super.setFinalScore(Main.INSTANCE.getFlipToWinBoardManager().getScore());
        super.setUserName(Main.INSTANCE.getUserManager().getCurrentUser());
    }

    /**
     * Return a string type of record.
     *
     * @return a string type of record.
     */
    @SuppressLint("DefaultLocale")
    @Override
    protected String recordToString(String name) {
//        String temp = "User: %s, took %d steps in game: %s, in level: %d";
//        String gameName = FlipToWinBoard.GAME_NAME;
//        return String.format(temp, userName, finalScore, gameName, complexity - 2);
        return super.recordToString(FlipToWinBoard.GAME_NAME);
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

    public int compareTo(@NonNull FlipToWinRecord anotherRecord) {
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