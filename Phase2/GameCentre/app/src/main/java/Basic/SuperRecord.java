package Basic;

import android.annotation.SuppressLint;

import FlipToWin.FlipToWinRecord;
import fall2018.csc2017.slidingtiles.Board;
import fall2018.csc2017.slidingtiles.Record;

public abstract class SuperRecord {

    private int complexity;
    private int finalScore;
    private String userName;

    public SuperRecord(int complexity, int finalScore, String userName){
        this.complexity = complexity;
        this.finalScore = finalScore;
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public int getComplexity() {
        return complexity;
    }

    @SuppressLint("DefaultLocale")
    protected String recordToString(String gameName){
        String temp = "User: %s, took %d steps in game: %s, in level: %d";
//        String gameName = Board.GAME_NAME;
        return String.format(temp, userName, finalScore, gameName, complexity - 2);
    }

    public String getUserName() {
        return userName;
    }

    protected boolean checkLowerScore(SuperRecord r){
        return getFinalScore() >= r.getFinalScore();
    }
}
