package Basic;

import java.util.Observable;

import ColorMatching.ColorTile;

public abstract class SuperBoard<T> extends Observable {
    private int complexity;

    public SuperBoard(int complexity){
        this.complexity = complexity;
    }

    public int getComplexity() {
        return complexity;
    }

    public abstract int numGrids();

    public abstract T getGrid(int i, int i1);

    public abstract void setGrid(int i, int i1);
}
