package fall2018.csc2017.slidingtiles;

/**
 * Implementing this interface allows you to manage a playable game, including winning the game,
 * getting the score and making move.
 */
public interface GameManageable {

    /**
     * Return the game that is managed.
     *
     * @return the game.
     */
    Playable getGame();


    /**
     * Return whether you win the game.
     *
     * @return whether you win the game.
     */
    boolean puzzleSolved();


    /**
     * Return the score you get in the game.
     *
     * @return the score.
     */
    int getScore();

    /**
     * Process a touch at position in the game.
     *
     * @param position the position.
     */
    void touchMove(int position);

}
