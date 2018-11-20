package fall2018.csc2017.slidingtiles;

public class ColorBoard {
    public ColorTile[][] tiles;

    public ColorTile getGrid(int row, int col) {
        return tiles[row][col];
    }

}
