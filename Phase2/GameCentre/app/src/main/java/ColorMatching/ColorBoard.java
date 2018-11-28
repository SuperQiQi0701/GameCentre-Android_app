package ColorMatching;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.List;


import Basic.DataManager;
import Basic.SuperBoard;

public class ColorBoard extends SuperBoard implements Iterable<ColorTile>, Serializable{

    /**
     * The name of this game.
     */
//    private static final String GAME_NAME = "Color Matching";

//    private int complexity = getComplexity();

    /**
     * The color tiles on the board in row-major order.
     */
    private ColorTile[][] tiles;

    public ColorTile[][] getTiles(){
        return tiles;
    }

    private int rowNum;

    private int colNum;

    /**
     * A new color board of tiles in row major order, and contains 10 rows and 8 columns.
     */
    ColorBoard(List<ColorTile> tiles, int complexity) {
        super(complexity);
        Iterator<ColorTile> iter = tiles.iterator();
        this.rowNum = (getComplexity() - 2) * 4;
        this.colNum = (getComplexity() - 2) * 5;

        this.tiles = new ColorTile[this.rowNum][this.colNum];
        for (int row = 0; row != this.rowNum; row++) {
            for (int col = 0; col != this.colNum; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    @Override
    public int numGrids() {

        return this.rowNum * this.colNum;
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

//    public void setComplexity(int complexity) {
//        this.complexity = complexity;
//    }



    // 这里有code smell！！！！！！！！！！！！！
    ColorTile getNeighbour(ColorTile tile, String direction){
        switch (direction){
            case "top":
                if((tile.y)-1 >= 0){
                    return getGrid(tile.x, (tile.y)-1);
                }
                return null;
            case "bottom":
                if((tile.y)+1 < tiles.length*5/4){
                    return getGrid(tile.x, (tile.y)+1);
                }
                return null;
            case "right":
                if((tile.x)+1 < tiles.length){
                    return getGrid((tile.x)+1, tile.y);
                }
                return null;
            case "left":
                if((tile.x)-1 >= 0){
                    return getGrid((tile.x)-1, tile.y);
                }
                return null;

        }
        return null;
    }

/*    ColorTile getLeft(ColorTile tile){
        if((tile.x)-1 >= 0){
            return getGrid((tile.x)-1, tile.y);
        }
        return null;
    }

    ColorTile getRight(ColorTile tile){
        if((tile.x)+1 < tiles.length){
            return getGrid((tile.x)+1, tile.y);
        }
        return null;
    }

    ColorTile getTop(ColorTile tile){
        if((tile.y)-1 >= 0){
            return getGrid(tile.x, (tile.y)-1);
        }
        return null;
    }

    ColorTile getBottom(ColorTile tile){
        if((tile.y)+1 < tiles.length*5/4){
            return getGrid(tile.x, (tile.y)+1);
        }
        return null;
    }*/

//    void createNewBoard(Canvas canvas){
//        for (int x = 0; x < tiles.length; x++) {
//            for (int y = 0; y < tiles[x].length; y++) {
//                int color = randomColor();
//                ColorMatchingGameActivity.getColorView().drawBox(canvas, color, x, y);
//                //存color
//                DataManager.INSTANCE.getBoardManager().getGame().setGrid(x, y);
//                ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getGame().getGrid(x, y).setColor(color);
//            }
//        }
//    }

//    private int randomColor(){
//        Random random = new Random();
//        int color = random.nextInt(5);
//        switch(color){
//            case 0:
//                color =  Color.RED;
//                break;
//            case 1:
//                color = Color.GREEN;
//                break;
//            case 2:
//                color = Color.YELLOW;
//                break;
//            case 3:
//                color = Color.BLUE;
//                break;
//            case 4:
//                color = Color.GRAY;
//                break;
//        }
//        return color;
//    }

    @Override
    public String toString() {
        return "FlipToWinBoard{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }


    /**
     * Return a new BoardIterator.
     *
     * @return a new BoardIterator
     */
    @NonNull
    @Override
    public Iterator<ColorTile> iterator() {

        return new ColorBoardIterator();
    }

    /**
     * Iterate over tiles in a range of total number of tiles.
     */
    private class ColorBoardIterator implements Iterator<ColorTile> {

        /**
         * The row number of the tile.
         */
        private int row;
        /**
         * The column number of the tile.
         */
        private int col;

        @Override
        public boolean hasNext() {
            return row < ColorBoard.this.rowNum &&
                    col < ColorBoard.this.colNum;
        }

        @Override
        public ColorTile next() {
            if (hasNext()) {
                if (ColorBoard.this.colNum - 1 == col) {
                    ColorTile temp = getGrid(row, col);
                    row++;
                    col = 0;
                    return temp;
                }
                return getGrid(row, col++);
            }
            throw new NoSuchElementException();
        }
    }

    int getColNum() {
        return this.colNum;
    }

    int getRowNum() {
        return this.rowNum;
    }
}
