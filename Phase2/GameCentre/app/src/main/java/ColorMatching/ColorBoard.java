package ColorMatching;

import Basic.Playable;

public class ColorBoard implements Playable<ColorTile> {

    public static final String GAME_NAME = "Color Matching";

    private int complexity;

    public ColorTile[][] tiles;

    ColorBoard( int complexity) {
        this.tiles = new ColorTile[(complexity - 2) * 4][(complexity - 2)  * 5];
        this.complexity = complexity;
    }

    @Override
    public int getComplexity() {
        return complexity;
    }

    @Override
    public int numGrids() {
        return complexity * complexity * 20;
    }

    public ColorTile getGrid(int row, int col) {
        return tiles[row][col];
    }

    void setGrid(int row, int col) {
        tiles[row][col] = new ColorTile(row, col);
    }


}
