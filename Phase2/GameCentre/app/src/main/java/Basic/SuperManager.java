package Basic;

public abstract class SuperManager {

    private int complexity;

    private int score;

    private SuperManager board;

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

    public SuperManager getBoard() {
        return board;
    }

    public void setBoard(SuperManager board) {
        this.board = board;
    }
}
