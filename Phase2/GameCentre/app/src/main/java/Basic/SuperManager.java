package Basic;

public abstract class SuperManager {

    private int complexity;
    private int score = 0;

    public SuperManager(int complexity) {
        this.complexity = complexity;
    }

    public int getComplexity() {
        return complexity;
    }

    public int getScore() {
        return this.score;
    }

    protected abstract void makeChange(int newColor);

    protected abstract boolean puzzleSolved();

}
