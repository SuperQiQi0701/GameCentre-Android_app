package FlipToWin;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import Basic.SuperBoard;


/**
 * The sliding tiles board.
 */

public class FlipToWinBoard extends SuperBoard implements Iterable<FlipToWinTile>, Serializable{


    public static final String GAME_NAME = "Flip To Win";
    /**
     * The number of row and col
     */
    private int complexity;

    private int rowNum;

    private int colNum;


    /**
     * The tiles on the board in row-major order.
     */
    private FlipToWinTile[][] ftiles;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param ftiles the tiles for the board
     */
    FlipToWinBoard(List<FlipToWinTile> ftiles, int complexity) {
        super(complexity);
        Iterator<FlipToWinTile> iter = ftiles.iterator();
        this.complexity = complexity;
        this.rowNum = complexity;
        this.colNum = complexity + 1;
        this.ftiles = new FlipToWinTile[this.rowNum][this.colNum];

        for (int row = 0; row != this.rowNum; row++) {
            for (int col = 0; col != this.colNum; col++) {
                this.ftiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    public int numGrids() {

        return this.rowNum* this.colNum;
    }

    public int getColNum() {
        return this.colNum;
    }

    public int getRowNum() {
        return this.rowNum;
    }

    /**
     * Return the complexity of current board
     *
     * @return the complexity of current board
     */
    public int getComplexity() {

        return this.complexity;
    }


    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public FlipToWinTile getGrid(int row, int col) {
        return ftiles[row][col];
    }

    @Override
    public void setGrid(int i, int i1) {

    }

    /**
     * FLip the tile
     *
     */
    void makeMove(int row, int col) {
        ftiles[row][col].setFlipped();
        setChanged();
        notifyObservers();
        System.out.println("update");
    }

    @Override
    public String toString() {
        return "FlipToWinBoard{" +
                "tiles=" + Arrays.toString(ftiles) +
                '}';
    }

//    public void flipBack() {
//        for (FlipToWinTile temp : this) {
//            if (!(temp.isPaired()) & temp.flipStatus() != 0) {
//                temp.setFlipped();
//            }
//        }
//        setChanged();
//        notifyObservers();
//    }


    /**
     * Return a new BoardIterator.
     *
     * @return a new BoardIterator
     */
    @NonNull
    @Override
    public Iterator<FlipToWinTile> iterator() {

        return new FlipToWinBoardIterator();
    }

    /**
     * Iterate over tiles in a range of total number of tiles.
     */
    private class FlipToWinBoardIterator implements Iterator<FlipToWinTile> {

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
            return row < FlipToWinBoard.this.rowNum &&
                    col < FlipToWinBoard.this.colNum;
        }

        @Override
        public FlipToWinTile next() {
            if (hasNext()) {
                if (FlipToWinBoard.this.colNum - 1 == col) {
                    FlipToWinTile temp = getGrid(row, col);
                    row++;
                    col = 0;
                    return temp;
                }
                return getGrid(row, col++);
            }
            throw new NoSuchElementException();
        }
    }
}
