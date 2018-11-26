package ColorMatching;

import java.io.Serializable;
import Basic.SuperBoard;

public class ColorBoard extends SuperBoard implements Serializable{

    /**
     * The name of this game.
     */
    private static final String GAME_NAME = "Color Matching";

    private int complexity = getComplexity();

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
    public void setGrid(int row, int col) {
        tiles[row][col] = new ColorTile(row, col);
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public ColorTile getLeft(ColorTile tile){
        if((tile.x)-1 >= 0){
            return getGrid((tile.x)-1, tile.y);
        }
        return null;
    }

    public ColorTile getRight(ColorTile tile){
        if((tile.x)+1 < tiles.length){
            return getGrid((tile.x)+1, tile.y);
        }
        return null;
    }

    public ColorTile getTop(ColorTile tile){
        if((tile.y)-1 >= 0){
            return getGrid(tile.x, (tile.y)-1);
        }
        return null;
    }

    public ColorTile getBottom(ColorTile tile){
        if((tile.y)+1 < tiles.length*5/4){
            return getGrid(tile.x, (tile.y)+1);
        }
        return null;
    }


}
