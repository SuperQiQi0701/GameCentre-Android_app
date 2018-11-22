package fall2018.csc2017.slidingtiles;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class FlipToWinBoardManager implements Serializable, GameManageable {

    /**
     * The board being managed.
     */
    private FlipToWinBoard fBoard;

//    /**
//     * An ArrayList that store all the previous moves for undo function.
//     */
//    private ArrayList<int[]> previousMoves;

    /**
     * An integer that keep track the score of the current game
     */
    private int score = 0;

    /**
     * Manage a new shuffled board.
     */
    FlipToWinBoardManager(int complexity) {
        List<FlipToWinTile> fTiles = new ArrayList<>();
        final int numTiles = complexity * complexity;
        for (int tileNum = 0; tileNum != numTiles / 2; tileNum++) {
            fTiles.add(new FlipToWinTile(tileNum));
            fTiles.add(new FlipToWinTile(tileNum));
        }

        Collections.shuffle(fTiles);
        this.fBoard = new FlipToWinBoard(fTiles, complexity);
    }

    /**
     * Return the current score of the game
     *
     * @return the current score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Return the current board.
     */
    public FlipToWinBoard getGame() {
        return fBoard;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    public boolean puzzleSolved() {
        Iterator<FlipToWinTile> iter = this.fBoard.iterator();
        FlipToWinTile temp = iter.next();
        while (iter.hasNext()) {
            FlipToWinTile next = iter.next();
            if (!(temp.isPaired())) {
                return false;
            }
            temp = next;
        }
        return true;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {
        int row = position / fBoard.getComplexity();
        int col = position % fBoard.getComplexity();

        return !(fBoard.getGrid(row, col).isPaired());
    }


    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    public void touchMove(int position) {

        int row = position / fBoard.getComplexity();
        int col = position % fBoard.getComplexity();
        ++this.score;
        fBoard.makeMove(row, col);
    }


    //    /**
//     * Return true if the undo function is available, false otherwise.
//     *
//     * @return if the undo function is available
//     */
//    boolean undoAvailable() {
//        return this.previousMoves.size() >= 1;
//    }


//    /**
//     * Undo the previous move
//     */
//    void undo() {
//        if (undoAvailable()) {
//            int saved[] = this.previousMoves.get(this.previousMoves.size() - 1);
//            this.previousMoves.remove(this.previousMoves.size() - 1);
//            this.board.makeMove(saved[0], saved[1], saved[2], saved[3]);
//            ++this.score;  //penalty for using undo
//            ++this.score;
//        }
//    }
}
