package Basic;

public abstract class SuperBoard {
    private int complexity;

    public SuperBoard(int complexity){
        this.complexity = complexity;
    }

    public int getComplexity() {
        return complexity;
    }

    protected abstract int numGrids();

    public abstract void setGrid(int row, int col);

}
