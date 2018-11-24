package FlipToWin;


import java.io.Serializable;
import java.util.ArrayList;
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
    private FlipToWinBoard board;

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
        this.board = new FlipToWinBoard(fTiles, complexity);
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
        return board;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
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
     * Return whether True iff it is isPaired and facedUp.
     *
     * @param position the tile to check
     * @return whether True iff it is isPaired and facedUp
     */
    boolean isValidTap(int position) {
        int row = position / board.getColNum();
        int col = position % board.getColNum();

        return (!(board.getGrid(row, col).isPaired())
                & (!board.getGrid(row, col).facedUpStatus()));
    }


    /**
     * Process a touch at position in the board.
     *
     * @param position the position
     */
    public void touchMove(int position) {
        //position:点击的图片
        int row = position / board.getColNum();
        int col = position % board.getColNum();
        ++this.score;

        if (isValidTap(position)) {
            if (positionOneAndOnlyOneTileFaceUp == -1) {
                positionOneAndOnlyOneTileFaceUp = position;
                board.makeMove(row, col);
//                System.out.println("first tap");
            }
            else {
                int matchPosition = positionOneAndOnlyOneTileFaceUp;
                int matchRow = matchPosition / board.getColNum();
                int matchCol = matchPosition % board.getColNum();
                if (matchPosition != position) {
                    if (board.getGrid(row, col).getId() == board.getGrid(matchRow, matchCol).getId()) {
                        board.getGrid(row, col).setPaired();
                        board.getGrid(matchRow, matchCol).setPaired();
                    }

                    board.makeMove(row, col);
//                    System.out.println("second tap");
                    positionOneAndOnlyOneTileFaceUp = -1;
                }
                for (FlipToWinTile ft : board) {
                    if (!(ft.isPaired()) & ft.facedUpStatus()) {

                        ft.setFlipped();
                    }
                }
            }
        }
//                int acc = 0;
//                for (FlipToWinTile ft: board) {
//                    if (!(ft.isPaired()) & ft.flipStatus() != 0) {
//                        int flipRow = acc / board.getComplexity();
//                        int flipCol = acc % board.getComplexity();
//                        board.makeMove(flipRow, flipCol);
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
//                for (FlipToWinTile ft : board) {
//                    if (!(ft.isPaired()) & ft.flipStatus() != 0) {
//
//                        ft.setFlipped();
//                        System.out.println("fliped over");

//                        try {
//                            Thread.currentThread().sleep(1000);//毫秒
//                        } catch (Exception e) {
//                        }
//                        System.out.println("delayed");

//                for (FlipToWinTile ft: board) {
//                    if (!(ft.isPaired()) & ft.flipStatus() != 0) {
//                        int flipRow = acc / board.getComplexity();
//                        int flipCol = acc % board.getComplexity();
//                        board.makeMove(flipRow, flipCol);
//                        }
//                    acc++ ;


//
//        if (!(board.getGrid(row, col).isPaired())) {
//            int matchPosition = positionOneAndOnlyOneTileFaceUp;
//            int matchRow = matchPosition / board.getComplexity();
//            int matchCol = matchPosition % board.getComplexity();
//            if (matchPosition != position) {
//                if (board.getGrid(row, col).getId() == board.getGrid(matchRow, matchCol).getId()) {
//                    board.getGrid(row, col).setPaired();
//                    board.getGrid(matchRow, matchCol).setPaired();
//                }
//                board.makeMove(row, col);
//                positionOneAndOnlyOneTileFaceUp = 0;
//            }
//            for (FlipToWinTile ft: board) {
//                if (ft.flipStatus() != 0) {
//                    ft.setFlipped();
//                }
//            }
//            board.makeMove(row, col);
//            positionOneAndOnlyOneTileFaceUp = board.getGrid(row, col).getId();
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
