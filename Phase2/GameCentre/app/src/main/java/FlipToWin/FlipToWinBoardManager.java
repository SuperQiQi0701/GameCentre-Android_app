package FlipToWin;


import android.os.Handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Basic.SuperBoard;
import Basic.SuperManager;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class FlipToWinBoardManager extends SuperManager implements Serializable {

    /**
     * The board being managed.
     */
    private FlipToWinBoard board;

    /**
     * flippingTiles is true iff the tiles is flipping
     */
    private boolean flippingTiles = false;

    /**
     * the position of the first tap tile if there is a first tap, otherwise set to -1.
     */
    private int positionTileOneFaceUp = -1;

    /**
     * Manage a new shuffled board.
     */
    public FlipToWinBoardManager(int complexity) {
        super(complexity);
        List<FlipToWinTile> fTiles = new ArrayList<>();
        final int numTiles = complexity * (complexity + 1);
        for (int tileNum = 0; tileNum != numTiles / 2; tileNum++) {
            fTiles.add(new FlipToWinTile(tileNum));
            fTiles.add(new FlipToWinTile(tileNum));
        }

        Collections.shuffle(fTiles);
        this.board = new FlipToWinBoard(fTiles, complexity);
    }

    @Override
    public SuperBoard getBoard() {
        return this.board;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    @Override
    public boolean puzzleSolved() {
        Iterator<FlipToWinTile> iter = this.board.iterator();
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
     * return true iff the game is flipping the tiles.
     *
     * @return true iff the game is flipping the tiles.
     */
    boolean isFlippingTiles() {
        return flippingTiles;
    }

    /**
     * Return whether True iff it is isPaired and facedUp and the tile is not flipping.
     *
     * @param position the tile to check
     * @return whether True iff it is isPaired and facedUp
     */
    boolean isValidTap(int position) {
        int row = position / board.getColNum();
        int col = position % board.getColNum();

        return (!(board.getGrid(row, col).isPaired())
                & (!board.getGrid(row, col).facedUpStatus())
                & (!flippingTiles));
    }

    /**
     * Process a touch at position in the board.
     *
     * @param position the position
     */
    @Override
    public void makeChange(int position) {
        //position of the tile that has been tapped.
        int row = position / board.getColNum();
        int col = position % board.getColNum();

        if (isValidTap(position)) {
            addScoreBy(1);
            // if it is the first one is and only is faced up.
            if (positionTileOneFaceUp == -1) {
                positionTileOneFaceUp = position;
                board.makeMove(row, col);

            } else {
                int rowTileOne = positionTileOneFaceUp / board.getColNum();
                int colTileOne = positionTileOneFaceUp % board.getColNum();
                FlipToWinTile tileOwoFaceUp = board.getGrid(rowTileOne, colTileOne);
                FlipToWinTile tileCurrChosen = board.getGrid(row, col);

                //if the current chosen tile and the first tile which is faced up
                // shared the same id, then they are in paired.
                if (tileOwoFaceUp.getId() == tileCurrChosen.getId()) {
                    positionTileOneFaceUp = -1;
                    tileOwoFaceUp.setPaired();
                    tileCurrChosen.setPaired();
                    board.makeMove(row, col);

                }
                // if the two tiles is not in paired, the flip them over.
                else {
                    positionTileOneFaceUp = -1;
                    board.makeMove(row, col);
                    this.flippingTiles = true;

                    // set a delay (0.8 second) to flip two the tiles.
                    new Handler().postDelayed(() -> {

                        board.makeMove(rowTileOne, colTileOne);
                        board.makeMove(row, col);
                        this.flippingTiles = false;

                    }, 800);
                }
            }
        }

    }

}
