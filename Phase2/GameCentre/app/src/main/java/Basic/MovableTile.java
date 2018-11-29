package Basic;

import java.io.Serializable;

public abstract class MovableTile implements Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    private int id;


//    protected MovableTile(){
//    }
//
    public MovableTile(int num) {
        this.id = num;
    }

    public int getId() {
        return this.id;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background){
        this.background = background;
    }
}
