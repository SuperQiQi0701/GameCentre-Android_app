package FlipToWin;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Basic.GameManageable;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class FlipToWinBoardManager implements Serializable, GameManageable {

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

    private int positionOneAndOnlyOneTileFaceUp = -1;

    /**
     * Manage a new shuffled board.
     */
    public FlipToWinBoardManager(int complexity) {
        List<FlipToWinTile> fTiles = new ArrayList<>();
        final int numTiles = complexity * (complexity + 1);
        for (int tileNum = 0; tileNum != numTiles / 2; tileNum++) {
            fTiles.add(new FlipToWinTile(tileNum));
            fTiles.add(new FlipToWinTile(tileNum));
        }

//        Collections.shuffle(fTiles);
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
        int row = position / fBoard.getColNum();
        int col = position % fBoard.getColNum();

        return (!(fBoard.getGrid(row, col).isPaired())
                & (!fBoard.getGrid(row, col).facedUpStatus()));
    }


    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    public void touchMove(int position) {
        //position:点击的图片
        int row = position / fBoard.getColNum();
        int col = position % fBoard.getColNum();
        ++this.score;

        if (isValidTap(position)) {
            if (positionOneAndOnlyOneTileFaceUp == -1) {
                positionOneAndOnlyOneTileFaceUp = position;
                fBoard.makeMove(row, col);
//                System.out.println("first tap");
            }
            else {
                int matchPosition = positionOneAndOnlyOneTileFaceUp;
                int matchRow = matchPosition / fBoard.getColNum();
                int matchCol = matchPosition % fBoard.getColNum();
                if (matchPosition != position) {
                    if (fBoard.getGrid(row, col).getId() == fBoard.getGrid(matchRow, matchCol).getId()) {
                        fBoard.getGrid(row, col).setPaired();
                        fBoard.getGrid(matchRow, matchCol).setPaired();
                    }

                    fBoard.makeMove(row, col);
//                    System.out.println("second tap");
                    positionOneAndOnlyOneTileFaceUp = -1;
                }
                for (FlipToWinTile ft : fBoard) {
                    if (!(ft.isPaired()) & ft.facedUpStatus()) {

                        ft.setFlipped();
                    }
                }
            }
        }
//                int acc = 0;
//                for (FlipToWinTile ft: fBoard) {
//                    if (!(ft.isPaired()) & ft.flipStatus() != 0) {
//                        int flipRow = acc / fBoard.getComplexity();
//                        int flipCol = acc % fBoard.getComplexity();
//                        fBoard.makeMove(flipRow, flipCol);
//                        try {
//                            Thread.currentThread().sleep(1000);//毫秒
//                        } catch (Exception e) {
//                        }
//                        System.out.println("delay finish");
//                        }
//                    acc++ ;
                }
            }
//
//                int acc = 0;
//                for (FlipToWinTile ft : fBoard) {
//                    if (!(ft.isPaired()) & ft.flipStatus() != 0) {
//
//                        ft.setFlipped();
//                        System.out.println("fliped over");

//                        try {
//                            Thread.currentThread().sleep(1000);//毫秒
//                        } catch (Exception e) {
//                        }
//                        System.out.println("delayed");

//                for (FlipToWinTile ft: fBoard) {
//                    if (!(ft.isPaired()) & ft.flipStatus() != 0) {
//                        int flipRow = acc / fBoard.getComplexity();
//                        int flipCol = acc % fBoard.getComplexity();
//                        fBoard.makeMove(flipRow, flipCol);
//                        }
//                    acc++ ;


//
//        if (!(fBoard.getGrid(row, col).isPaired())) {
//            int matchPosition = positionOneAndOnlyOneTileFaceUp;
//            int matchRow = matchPosition / fBoard.getComplexity();
//            int matchCol = matchPosition % fBoard.getComplexity();
//            if (matchPosition != position) {
//                if (fBoard.getGrid(row, col).getId() == fBoard.getGrid(matchRow, matchCol).getId()) {
//                    fBoard.getGrid(row, col).setPaired();
//                    fBoard.getGrid(matchRow, matchCol).setPaired();
//                }
//                fBoard.makeMove(row, col);
//                positionOneAndOnlyOneTileFaceUp = 0;
//            }
//            for (FlipToWinTile ft: fBoard) {
//                if (ft.flipStatus() != 0) {
//                    ft.setFlipped();
//                }
//            }
//            fBoard.makeMove(row, col);
//            positionOneAndOnlyOneTileFaceUp = fBoard.getGrid(row, col).getId();
//        }



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
//    }}
