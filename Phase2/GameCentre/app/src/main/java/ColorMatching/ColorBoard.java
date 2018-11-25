package ColorMatching;

import java.io.Serializable;
import Basic.SuperBoard;

public class ColorBoard extends SuperBoard implements Serializable{

    /**
     * The name of this game.
     */
    private static final String GAME_NAME = "Color Matching";

    private int complexity;

    /**
     * The color tiles on the board in row-major order.
     */
    private ColorTile[][] tiles;

    /**
     * A new color board of tiles in row major order, and contains 10 rows and 8 columns.
     */
    ColorBoard( int complexity) {
        super(complexity);
        this.tiles = new ColorTile[(complexity - 2) * 4][(complexity - 2)  * 5];
    }

    public static String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getComplexity() {
        return super.getComplexity();
    }

    @Override
    protected int numGrids() {
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
    @Override
    public void setGrid(int row, int col) {
        tiles[row][col] = new ColorTile(row, col);
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }
}
