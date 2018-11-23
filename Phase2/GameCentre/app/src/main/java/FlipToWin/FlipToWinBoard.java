package FlipToWin;

import android.support.annotation.NonNull;

import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import Basic.Playable;

/**
 * The sliding tiles board.
 */

public class FlipToWinBoard extends Observable implements Iterable<FlipToWinTile>, Serializable,
        Playable<FlipToWinTile> {


    public static final String GAME_NAME = "Flip To Win";
    /**
     * The number of row and col
     */
    private int complexity;

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
        Iterator<FlipToWinTile> iter = ftiles.iterator();
        this.complexity = complexity;
        this.ftiles = new FlipToWinTile[this.complexity][this.complexity];

        for (int row = 0; row != this.complexity; row++) {
            for (int col = 0; col != this.complexity; col++) {
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

        return this.complexity * this.complexity;
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

    /**
     * FLip the tile
     *
     */
    public void makeMove(int row, int col) {
        ftiles[row][col].setFlipped();
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(ftiles) +
                '}';
    }


    /**
     * Return a new BoardIterator.
     *
     * @return a new BoardIterator
     */
    @NonNull
    @Override
    public Iterator<FlipToWinTile> iterator() {

        return new FilpToWinBoardIterator();
    }

    /**
     * Iterate over tiles in a range of total number of tiles.
     */
    private class FilpToWinBoardIterator implements Iterator<FlipToWinTile> {

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
            return row < FlipToWinBoard.this.getComplexity() &&
                    col < FlipToWinBoard.this.getComplexity();
        }

        @Override
        public FlipToWinTile next() {
            if (hasNext()) {
                if (FlipToWinBoard.this.getComplexity() - 1 == col) {
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
