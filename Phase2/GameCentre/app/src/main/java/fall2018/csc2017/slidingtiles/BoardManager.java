package fall2018.csc2017.slidingtiles;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager implements Serializable, GameManageable {

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * An ArrayList that store all the previous moves for undo function.
     */
    private ArrayList<int[]> previousMoves;

    /**
     * An integer that keep track the score of the current game
     */
    private int score = 0;

    /**
     * Manage a new shuffled board.
     */
    BoardManager(int complexity) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = complexity * complexity;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum, complexity));
        }

        Collections.shuffle(tiles);
        this.board = new Board(tiles, complexity);
        this.previousMoves = new ArrayList<>();
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
    public Board getGame() {
        return board;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    public boolean puzzleSolved() {
        Iterator<Tile> iter = this.board.iterator();
        Tile temp = iter.next();
        while (iter.hasNext()) {
            Tile next = iter.next();
            if (temp.compareTo(next) < 0) {
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
        int row = position / board.getComplexity();
        int col = position % board.getComplexity();
        int blankId = board.numGrids();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getGrid(row - 1, col);
        Tile below = row == board.getComplexity() - 1 ? null : board.getGrid(row + 1, col);
        Tile left = col == 0 ? null : board.getGrid(row, col - 1);
        Tile right = col == board.getComplexity() - 1 ? null : board.getGrid(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    public void touchMove(int position) {

        int row = position / board.getComplexity();
        int col = position % board.getComplexity();
        int blankId = board.numGrids();
        boolean done = false;

        if (isValidTap(position)) {
            for (int i = 0; i != board.getComplexity(); i++) {
                for (int j = 0; j != board.getComplexity(); j++) {
                    if (this.board.getGrid(i, j).getId() == blankId) {
                        int save[] = {row, col, i, j};
                        ++this.score;
                        this.board.makeMove(row, col, i, j);
                        previousMoves.add(save);
                        done = true;
                        break;
                    }
                }
                if (done) {
                    break;
                }
            }
        }
    }

    /**
     * Return true if the undo function is available, false otherwise.
     *
     * @return if the undo function is available
     */
    boolean undoAvailable() {
        return this.previousMoves.size() >= 1;
    }


    /**
     * Undo the previous move
     */
    void undo() {
        if (undoAvailable()) {
            int saved[] = this.previousMoves.get(this.previousMoves.size() - 1);
            this.previousMoves.remove(this.previousMoves.size() - 1);
            this.board.makeMove(saved[0], saved[1], saved[2], saved[3]);
            ++this.score;  //penalty for using undo
            ++this.score;
        }
    }
}