package fall2018.csc2017.slidingtiles;

import android.graphics.Point;

public class ColorTile {
    int x;
    int y;
    int color;

    public ColorTile(int x, int y){
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
