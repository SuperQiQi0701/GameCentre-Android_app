package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import Basic.SuperBoard;

/**
 * The sliding tiles board.
 */

public class Board extends SuperBoard implements Iterable<Tile>, Serializable {

    /**
     * The game name for current board
     */
    private static final String GAME_NAME = "Sliding_Tiles";
    /**
     * The number of row and col
     */
    private int complexity = getComplexity();
    ;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles, int complexity) {
        super(complexity);
        Iterator<Tile> iter = tiles.iterator();
        this.tiles = new Tile[this.complexity][this.complexity];

        for (int row = 0; row != this.complexity; row++) {
            for (int col = 0; col != this.complexity; col++) {
                this.tiles[row][col] = iter.next();
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
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public Tile getGrid(int row, int col) {
        return tiles[row][col];
    }

    @Override
    public void setGrid(int i, int i1) {

    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void makeMove(int row1, int col1, int row2, int col2) {
        Tile temp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = temp;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
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
    public Iterator<Tile> iterator() {
        return new BoardIterator();
    }

    /**
     * Iterate over tiles in a range of total number of tiles.
     */
    private class BoardIterator implements Iterator<Tile> {

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
            return row < Board.this.getComplexity() && col < Board.this.getComplexity();
        }

        @Override
        public Tile next() {
            if (hasNext()) {
                if (Board.this.getComplexity() - 1 == col) {
                    Tile temp = getGrid(row, col);
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
