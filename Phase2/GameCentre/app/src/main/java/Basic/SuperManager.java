package Basic;

import java.io.Serializable;

public abstract class SuperManager implements Serializable {

    private int complexity;

    private int score;

    private SuperBoard board;

    public SuperManager(int complexity) {
        this.complexity = complexity;
    }

    public int getComplexity() {
        return complexity;
    }

    public abstract void makeChange(int newColor);

    public abstract boolean puzzleSolved();

    public int getScore() {
        return this.score;
    }

    public void setSocre(int num) {
        this.score += num;
    }

    public abstract SuperBoard getGame();

//    public void setBoard(SuperBoard board) {
//        this.board = board;
//    }
}
