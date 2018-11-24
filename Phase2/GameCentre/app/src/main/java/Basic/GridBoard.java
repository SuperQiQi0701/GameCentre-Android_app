package Basic;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class GridBoard implements Iterable, Serializable {

    private int complexity;

    abstract public SuperTile getGrid(int row, int col);

    abstract public int getColNum();

    abstract public int getRowNum();

    public int getComplexity() {
        return this.complexity;
    }

    abstract void makeMove();

    @Override
    abstract public String toString();

    /**
     * Return a new BoardIterator.
     *
     * @return a new BoardIterator
     */
    @NonNull
    @Override
    public Iterator iterator() {
        return new SuperBoardIterator();
    }

    /**
     * Iterate over tiles in a range of total number of tiles.
     */
    private class SuperBoardIterator implements Iterator {

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
            return row < GridBoard.this.getComplexity() && col < GridBoard.this.getComplexity();
        }

        @Override
        public SuperTile next() {
            if (hasNext()) {
                if (GridBoard.this.getComplexity() - 1 == col) {
                    SuperTile temp = getGrid(row, col);
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
