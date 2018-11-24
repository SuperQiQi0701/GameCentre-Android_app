package ColorMatching;

public class ColorTile{
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
