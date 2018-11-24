package ColorMatching;

import java.io.Serializable;

public class ColorTile implements Serializable {
    int x;
    int y;
    private int color;

    ColorTile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setColor(int color){
        this.color = color;
    }

    public int getColor(){
        return color;
    }
}
