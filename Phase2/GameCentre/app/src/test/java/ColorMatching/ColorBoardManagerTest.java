package ColorMatching;

import android.graphics.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ColorBoardManagerTest {

    private ColorBoardManager colorBoardManager;
    private int complexity1 = 3;

    private void setUpCorrect(){
//        List<ColorTile> tiles = makeRandomColorTiles();
//        ColorBoard colorBoard = new ColorBoard(tiles,complexity1);
        colorBoardManager = new ColorBoardManager(complexity1);
    }

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

    private void setUpSameColor(){
        List<ColorTile> tiles = makeSameColorTiles();
        ColorBoard colorBoard = new ColorBoard(tiles, complexity1);
        colorBoardManager = new ColorBoardManager(complexity1);
        colorBoardManager.setColorBoard(colorBoard);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

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

    @Test
    public void testUndoAvailable() {
        setUpCorrect();
        assertFalse(colorBoardManager.undoAvailable());
        colorBoardManager.makeChange(-65536);
        colorBoardManager.makeChange(-16711936);
        assertTrue(colorBoardManager.undoAvailable());
    }

    @Test
    public void testUndo() {
        setUpCorrect();
        colorBoardManager.makeChange(-65536);
        colorBoardManager.makeChange(-16711936);
        colorBoardManager.undo();
        assertEquals(-65536, colorBoardManager.getBoard().getGrid(0, 0).getColor());
    }

    @Test
    public void testGetGame() {
        setUpCorrect();
        assertEquals(3, colorBoardManager.getBoard().getComplexity());
    }

    @Test
    public void testPuzzleSolved() {
        setUpCorrect();
        assertFalse(colorBoardManager.puzzleSolved());
        setUpSameColor();
        assertTrue(colorBoardManager.puzzleSolved());
    }
}