package Basic;

import android.annotation.SuppressLint;

import fall2018.csc2017.slidingtiles.Board;

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
    protected String recordToString(){
        String temp = "User: %s, took %d steps in game: %s, in level: %d";
        String gameName = Board.GAME_NAME;
        return String.format(temp, userName, finalScore, gameName, complexity - 2);
    }

    public String getUserName() {
        return userName;
    }

    public int compareTo(SuperRecord anotherRecord) {
        return (this.finalScore - anotherRecord.finalScore);
    }



}
