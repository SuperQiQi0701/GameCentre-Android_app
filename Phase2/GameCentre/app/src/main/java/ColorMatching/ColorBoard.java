package ColorMatching;

import java.io.Serializable;

import Basic.Playable;

public class ColorBoard implements Serializable, Playable<ColorTile> {

    /**
     * The name of this game.
     */
    public static final String GAME_NAME = "Color Matching";

    private int complexity;

    /**
     * The color tiles on the board in row-major order.
     */
    public ColorTile[][] tiles;

    /**
     * A new color board of tiles in row major order, and contains 10 rows and 8 columns.
     */
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

    /**
     * Return the color tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public ColorTile getGrid(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Set a color tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     */
    void setGrid(int row, int col) {
        tiles[row][col] = new ColorTile(row, col);
    }


}
