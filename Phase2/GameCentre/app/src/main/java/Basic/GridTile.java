package Basic;

import java.io.Serializable;

public abstract class GridTile implements Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    private int complexity;

    public GridTile(){
    }

    public GridTile(int background, int complexity) {
        this.background = background;
        this.complexity = complexity;
    }

    public int getComplexity() {
        return complexity;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background){
        this.background = background;
    }
}
