package Basic;

import java.io.Serializable;

public abstract class SuperBoardManager implements Serializable {

    /**
     * An integer that keep track the score of the current game
     */
    private int score = 0;

    /**
     * The board being managed.
     */
    private SuperBoard board;

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
    public SuperBoard getGame() {
        return board;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    abstract public boolean puzzleSolved();

    /**
     * Return whether True iff it is valid tap.
     *
     * @param position the tile to check
     * @return whether True iff it is valid tap
     */
    abstract boolean isValidTap(int position);

    /**
     * Process a touch at position in the board.
     *
     * @param position the position
     */
    abstract public void touchMove(int position);
}
