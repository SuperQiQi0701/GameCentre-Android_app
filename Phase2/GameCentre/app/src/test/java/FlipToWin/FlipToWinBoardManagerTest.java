package FlipToWin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import fall2018.csc2017.slidingtiles.R;

import static org.junit.Assert.*;

public class FlipToWinBoardManagerTest {


    private FlipToWinBoardManager boardManager;

    private FlipToWinBoard board;

    private List<FlipToWinTile> setUpInOrderFlipToWinTiles() {
        List<FlipToWinTile> tiles = new ArrayList<>();
        int numTiles = 5 * (5 + 1);
        for (int tileNum = 0; tileNum != numTiles / 2; tileNum++) {
            tiles.add(new FlipToWinTile(tileNum));
            tiles.add(new FlipToWinTile(tileNum));
        }
        return tiles;
    }


    private void setUpFlipToWinBoardManager() {
        List<FlipToWinTile> tiles = setUpInOrderFlipToWinTiles();
        FlipToWinBoard board = new FlipToWinBoard(tiles, 5);
        boardManager = new FlipToWinBoardManager(board, 5);
    }

    private void setUpPaired() {
        FlipToWinBoard board = (FlipToWinBoard) boardManager.getBoard();
        for (FlipToWinTile tile: board) {
            tile.setPaired();
        }
    }


    @Before
    public void setUp() {
        setUpFlipToWinBoardManager();
        List<FlipToWinTile> tiles = setUpInOrderFlipToWinTiles();
        board = new FlipToWinBoard(tiles, 5);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        FlipToWinBoardManager newBoardManager = new FlipToWinBoardManager(5);
        assertFalse(newBoardManager.puzzleSolved());
        assertEquals(newBoardManager.getBoard().getComplexity(), 5);
        assertEquals(newBoardManager.getBoard().numGrids(), 30);
    }

    @Test
    public void testGetBoard() {

        // test two board has same toString() output.
        String str = boardManager.getBoard().toString();
        String strExpected = board.toString();
        assertEquals(strExpected, str);

        //test the board has a correct number of grids.
        assertEquals(board.numGrids(), board.getColNum() * board.getRowNum());

    }

    @Test
    public void testBoardTilesBackground() {

        for (FlipToWinTile tile: board) {
            assertEquals(tile.getBackground(), R.drawable.back_of_tile4);
        }
    }

    @Test
    public void testBoardTilesFrontPage() {

        assertEquals(board.getGrid(0, 0).getFrontPage(), board.getGrid(0,1).getFrontPage());
    }

    @Test
    public void testBoardIterable() {
        Iterator<FlipToWinTile> iterator = board.iterator();
        int i = 0;
        while (i <= board.numGrids()) {
            try {
                iterator.next();
            } catch (NoSuchElementException e){
                System.out.println("No more FlipToWinTiles.");
            }
            i ++;
        }
    }

    @Test
    public void testPuzzleSolved() {

        assertFalse(boardManager.puzzleSolved());

        //set all tiles paired
        setUpPaired();
        assertTrue(boardManager.puzzleSolved());

    }

    @Test
    public void testIsFlippingTiles() {

        boardManager.setFlipping(true);
        assertTrue(boardManager.isFlippingTiles());

        boardManager.setFlipping(false);
        assertFalse(boardManager.isFlippingTiles());

    }

    @Test
    public void testIsValidTap() {

        // not paired and not faced up and not flipping
        assertTrue(boardManager.isValidTap(1));
        // paired
        setUpPaired();
        assertFalse(boardManager.isValidTap(1));
        //faced up
        ((FlipToWinTile) boardManager.getBoard().getGrid(0,0)).setFlipped();
        assertFalse(boardManager.isValidTap(1));
        //flipping
        boardManager.setFlipping(true);
        assertFalse(boardManager.isValidTap(1));



    }

    @Test
    public void testMakeChange() {

        //test on two tiles that has same id.
        boardManager.makeChange(0);
        assertTrue(((FlipToWinTile) boardManager.getBoard().getGrid(0,0)).isFacedUp());
        boardManager.makeChange(1);
        assertTrue(((FlipToWinTile) boardManager.getBoard().getGrid(0,1)).isFacedUp());
        assertTrue(((FlipToWinTile) boardManager.getBoard().getGrid(0,0)).isPaired());
        assertTrue(((FlipToWinTile) boardManager.getBoard().getGrid(0,1)).isPaired());

    }

    @Test
    public void testFlipTwoTiles(){

        assertFalse(((FlipToWinTile) boardManager.getBoard().getGrid(0,0)).isFacedUp());
        assertFalse(((FlipToWinTile) boardManager.getBoard().getGrid(1,1)).isFacedUp());

        boardManager.flipTwoTiles(0,0,1,1);

        assertTrue(((FlipToWinTile) boardManager.getBoard().getGrid(0,0)).isFacedUp());
        assertTrue(((FlipToWinTile) boardManager.getBoard().getGrid(1,1)).isFacedUp());
        assertFalse(boardManager.isFlippingTiles());
    }

    @Test
    public void testSetUpBeforeFlipping() {

        boardManager.setUpBeforeFlipping(0,0);
        assertEquals(boardManager.getPositionTileOneFaceUp(), -1);
    }


}