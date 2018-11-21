package fall2018.csc2017.slidingtiles;

/**
 * Implementing this interface allows it to be playable game with grid, including getting the
 * complexity of the game .
 */
public interface Playable<T> {
    /**
     * Return the complexity of playable game.
     *
     * @return the Complexity.
     */
    int getComplexity();


    /**
     * Return the number of grids in the game.
     *
     * @return the number of grids.
     */
    int numGrids();


    /**
     * Return the object at (row, col)
     *
     * @param row the grid row
     * @param col the grid column
     * @return the object at (row, col)
     */
    T getGrid(int row, int col);


//    /**
//     * Process a move for the object at (row1, col1) and (row2, col2)
//     *
//     * @param row1 the first object at row1
//     * @param col1 the first object at col1
//     * @param row2 the second object  row2
//     * @param col2 the second object  col2
//     */
//    void makeMove(int row1, int col1, int row2, int col2);
//
}
