package ColorMatching;

import android.graphics.Canvas;
import android.graphics.Color;

import java.io.Serializable;
import java.util.Random;

import Basic.DataManager;
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

    void createNewBoard(Canvas canvas){
        for (int x = 0; x < ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getBoard().length; x++) {
            for (int y = 0; y < ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getBoard()[x].length; y++) {
                int color = randomColor();
                ColorMatchingGameActivity.getColorView().drawBox(canvas, color, x, y);
                //存color
                DataManager.INSTANCE.getBoardManager().getGame().setGrid(x, y);
                ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getGame().getGrid(x, y).setColor(color);
            }
        }
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
