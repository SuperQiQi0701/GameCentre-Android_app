package ColorMatching;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.io.Serializable;

import Basic.Main;
import Basic.SuperRecord;
import fall2018.csc2017.slidingtiles.Board;

public class ColorMatchingRecord extends SuperRecord implements Comparable<ColorMatchingRecord>, Serializable {


    ColorMatchingRecord(int complexity, int finalScore, String userName){
        super(complexity, finalScore, userName);
        super.setComplexity(Main.INSTANCE.getColorBoardManager().getGame().getComplexity());
        super.setFinalScore(Main.INSTANCE.getColorBoardManager().getScore());
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
        return super.recordToString(ColorBoard.getGameName());
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

    public int compareTo(@NonNull ColorMatchingRecord anotherRecord) {
        return (super.getFinalScore() - anotherRecord.getFinalScore());
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
        return super.checkLowerScore(r);
    }
}
