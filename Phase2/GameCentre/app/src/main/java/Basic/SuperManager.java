package Basic;

public abstract class SuperManager {

    private int complexity;

    public SuperManager(int complexity) {
        this.complexity = complexity;
    }

    public int getComplexity() {
        return complexity;
    }

    protected abstract void makeChange(int newColor);

    protected abstract boolean puzzleSolved();

}
