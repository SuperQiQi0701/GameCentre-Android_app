package Basic;

import java.io.Serializable;

public abstract class SuperTile implements Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    private int id;

    public int getId() {
        return id;
    }

    public int getBackground() {
        return background;
    }



}
