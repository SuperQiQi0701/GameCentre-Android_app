package fall2018.csc2017.slidingtiles;

public class ColorBoard implements Playable<ColorTile>{
    public ColorTile[][] tiles;

    ColorBoard() {
        this.tiles = new ColorTile[8][10];
    }

    @Override
    public int getComplexity() {
        return 0;
    }

    @Override
    public int numGrids() {
        return 0;
    }

    public ColorTile getGrid(int row, int col) {
        return tiles[row][col];
    }

    public void setGrid(int row, int col){
        tiles[row][col] = new ColorTile(row, col);
    }
}
