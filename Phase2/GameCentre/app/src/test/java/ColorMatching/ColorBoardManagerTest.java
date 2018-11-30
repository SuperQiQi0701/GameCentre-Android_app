package ColorMatching;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test ColorBoardManager class.
 */
public class ColorBoardManagerTest {

    /** The color board manager for testing. */
    private ColorBoardManager colorBoardManager;

    /** The complexity of board at level 1 */
    private int complexity1 = 3;

    /**
     * Make a random colorBoard.
     */
    @Before
    private void setUpCorrect(){
        colorBoardManager = new ColorBoardManager(complexity1);
    }

    /**
     * Make a set of colorTiles that are in order.
     * @return a set of colorTiles that are in order
     */
    private List<ColorTile> makeSameColorTiles(){
        List<ColorTile> tiles = new ArrayList<>();
        int rowNum = (complexity1 - 2) * 4;
        int colNum = (complexity1 - 2) * 5;
        for (int row = 0; row != rowNum; row++) {
            for (int col = 0; col != colNum; col++) {ColorTile colorTiles = new ColorTile(row, col);
                ColorTile colorTile = new ColorTile(row, col);
                colorTiles.setColor(-65536);
                tiles.add(colorTile);
            }
        }
        return tiles;
    }

    /**
     * Make a solved colorBoard.
     */
    @Before
    private void setUpSameColor(){
        List<ColorTile> tiles = makeSameColorTiles();
        ColorBoard colorBoard = new ColorBoard(tiles, complexity1);
        colorBoardManager = new ColorBoardManager(complexity1);
        colorBoardManager.setColorBoard(colorBoard);
    }


    /**
     * Set the colorBoardManager to null
     */
    @After
    public void tearDown() {
        colorBoardManager = null;
    }

    /**
     * Test whether makeChange does change the color of the top left grid
     */
    @Test
    public void testMakeChange() {
        setUpCorrect();
        colorBoardManager.makeChange(-65536);
        assertEquals(-65536, colorBoardManager.getBoard().getGrid(0, 0).getColor());
        colorBoardManager.makeChange(-16711936);
        assertEquals(-16711936, colorBoardManager.getBoard().getGrid(0, 0).getColor());
        colorBoardManager.makeChange(-16711936);
        assertEquals(-16711936, colorBoardManager.getBoard().getGrid(0, 0).getColor());
    }

    /**
     * Check whether undoAvailable works after make some changes
     */
    @Test
    public void testUndoAvailable() {
        setUpCorrect();
        assertFalse(colorBoardManager.undoAvailable());
        colorBoardManager.makeChange(-65536);
        colorBoardManager.makeChange(-16711936);
        assertTrue(colorBoardManager.undoAvailable());
    }

    /**
     * Check whether undoAvailable works after make some changes
     */
    @Test
    public void testUndo() {
        setUpCorrect();
        colorBoardManager.makeChange(-65536);
        colorBoardManager.makeChange(-16711936);
        colorBoardManager.undo();
        assertEquals(-65536, colorBoardManager.getBoard().getGrid(0, 0).getColor());
    }

    /**
     * Check whether getBoard works.
     */
    @Test
    public void testGetBoard() {
        setUpCorrect();
        assertEquals(3, colorBoardManager.getBoard().getComplexity());
    }

    /**
     * Check whether the game is not solved after randomly create a colorBoard.
     */
    @Test
    public void testPuzzleUnsolved() {
        setUpCorrect();
        assertFalse(colorBoardManager.puzzleSolved());
    }

    /**
     * Check whether the game is solved after setting all the colorTiles the same color.
     */
    @Test
    public void testPuzzleSolved() {
        setUpSameColor();
        assertTrue(colorBoardManager.puzzleSolved());
    }
}