package Basic;

import java.util.Observable;

public abstract class SuperBoard extends Observable {
    private int complexity;

    public SuperBoard(int complexity){
        this.complexity = complexity;
    }

    public int getComplexity() {
        return complexity;
    }

    public abstract int numGrids();

}
