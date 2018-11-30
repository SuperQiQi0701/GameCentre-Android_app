package fall2018.csc2017.slidingtiles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Basic.DataManager;

import static org.junit.Assert.*;

/**
 * Test BoardManager class.
 */
public class BoardManagerTest {

    /**
     * The board manager for testing.
     */
    private BoardManager boardManager;


    @Before
    public void setUp() {
        DataManager.INSTANCE.setCurrentGameName("ST");
        DataManager.INSTANCE.startNewGame(5);
        this.boardManager = (BoardManager) DataManager.INSTANCE.getBoardManager();
        List<Tile> tiles = makeTiles(4);
        Board board = new Board(tiles, 4);
        boardManager = new BoardManager(4);
        boardManager.setBoard(board);
    }

    @After
    public void tearDown() {
        this.boardManager = null;
    }

    /**
     * Make a set of tiles that are in order.
     *
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles(int complexity) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = complexity * complexity;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum, tileNum));
        }

        return tiles;
    }


    /**
     * Make an array of tiles to a list of tiles.
     *
     * @return a list of tiles.
     */
    private List<Tile> makeList(Tile[][] tiles) {
        List<Tile> newTiles = new ArrayList<>();
        for (Tile[] row : tiles) {
            Collections.addAll(newTiles, row);
        }

        return newTiles;
    }

    /**
     * Shuffle a few tiles.
     */
    private void swapFirstTwoTiles() {
        boardManager.getBoard().makeMove(0, 0, 0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved() {
        assertTrue(boardManager.puzzleSolved());
        swapFirstTwoTiles();
        assertFalse(boardManager.puzzleSolved());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        assertEquals(1, boardManager.getBoard().getGrid(0, 0).getId());
        assertEquals(2, boardManager.getBoard().getGrid(0, 1).getId());
        boardManager.getBoard().makeMove(0, 0, 0, 1);
        assertEquals(2, boardManager.getBoard().getGrid(0, 0).getId());
        assertEquals(1, boardManager.getBoard().getGrid(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        assertEquals(15, boardManager.getBoard().getGrid(3, 2).getId());
        assertEquals(16, boardManager.getBoard().getGrid(3, 3).getId());
        boardManager.getBoard().makeMove(3, 3, 3, 2);
        assertEquals(16, boardManager.getBoard().getGrid(3, 2).getId());
        assertEquals(15, boardManager.getBoard().getGrid(3, 3).getId());
    }

    /**
     * Test whether isValidTap works.
     */
    @Test
    public void testIsValidTap() {
        assertTrue(boardManager.isValidTap(11));
        assertTrue(boardManager.isValidTap(14));
        assertFalse(boardManager.isValidTap(10));
    }


    /**
     * Test whether checkSolvable works.
     */
    @Test
    public void testMakeChange() {
        boardManager.makeChange(14);
        assertEquals(16, boardManager.getBoard().getGrid(3, 2).getId());
        assertEquals(15, boardManager.getBoard().getGrid(3, 3).getId());
    }


    /**
     * Test whether Undo works.
     */
    @Test
    public void testUndo() {
        boardManager.makeChange(14);
        boardManager.undo();
        assertEquals(15, boardManager.getBoard().getGrid(3, 2).getId());
        assertEquals(16, boardManager.getBoard().getGrid(3, 3).getId());
    }


    /**
     * Test whether checkSolvable works.
     */
    @Test
    public void testCheckSolvable() {
        assertTrue(boardManager.checkSolvable(makeList(boardManager.getBoard().getTiles()), 4));
        boardManager.getBoard().makeMove(3, 2, 3, 1);
        assertFalse(boardManager.checkSolvable(makeList(boardManager.getBoard().getTiles()), 4));
        swapFirstTwoTiles();
        assertTrue(boardManager.checkSolvable(makeList(boardManager.getBoard().getTiles()), 4));
    }


    @Test
    public void testGetterAndSetterOfTile() {
        int Background = boardManager.getBoard().getGrid(0, 0).getBackground();
        assertEquals(R.drawable.tile_1, Background);
    }
}
