package ColorMatching;

import android.graphics.Color;

import java.io.Serializable;
import java.util.Random;

public class ColorTile implements Serializable {
    int x;
    int y;
    private int currColor;

    ColorTile(int x, int y){
        this.x = x;
        this.y = y;
        this.currColor = this.randomColor();
    }

    public void setColor(int color){

        this.currColor = color;
    }

    public int getColor(){
        return this.currColor;
    }

    private int randomColor(){
        Random random = new Random();
        int color = random.nextInt(5);
        switch(color){
            case 0:
                color =  Color.RED;
                break;
            case 1:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.YELLOW;
                break;
            case 3:
                color = Color.BLUE;
                break;
            case 4:
                color = Color.GRAY;
                break;
        }
        return color;
    }
}
