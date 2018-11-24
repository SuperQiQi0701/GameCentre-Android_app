package ColorMatching;

import Basic.Playable;

public class ColorBoard implements Playable<ColorTile> {

    /**
     * The name of this game.
     */
    public static final String GAME_NAME = "Color Matching";

    /**
     * The color tiles on the board in row-major order.
     */
    public ColorTile[][] tiles;

    /**
     * A new color board of tiles in row major order, and contains 10 rows and 8 columns.
     */
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
