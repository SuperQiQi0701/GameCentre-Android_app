package fall2018.csc2017.slidingtiles;

import java.util.Iterator;
import java.util.List;

public class ColorBoard {
    public ColorTile[][] tiles;

    ColorBoard() {
        this.tiles = new ColorTile[8][10];
    }

    public ColorTile getGrid(int row, int col) {
        return tiles[row][col];
    }

    public void setGrid(int row, int col){
        tiles[row][col] = new ColorTile(row, col);
    }
}
