package ColorMatching;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Basic.SuperManager;

public class ColorBoardManager extends SuperManager implements Serializable{

    /**
     * The ColorBoard being managed.
     */
    private ColorBoard colorBoard;

    /**
     * The arrayList of arrayList of all color tiles being changed.
     */
    private ArrayList<ArrayList<ColorTile>> allMove;

    /**
     * The arrayList of all colors being set before.
     */
    private ArrayList<Integer> colors;

    /**
     * A temporary arrayList used for record which color tiles has been set colors for each time doing color change.
     */
    private ArrayList<ColorTile> allState;

    /**
     * A temporary arrayList used for record the color tiles which are the neighbour of a specific
     * color tile and is not repeatedly added.
     */
    private ArrayList arr;

    /**
     * A new ColorBoardManager with complexity.
     *
     * @param complexity the complexity for the ColorMatching Game.
     */
    public ColorBoardManager(int complexity) {
        super(complexity);
        List<ColorTile> tiles = new ArrayList<>();
        int rowNum = (getComplexity() - 2) * 4;
        int colNum = (getComplexity() - 2) * 5;

        for (int row = 0; row != rowNum; row++) {
            for (int col = 0; col != colNum; col++) {
                ColorTile colorTiles = new ColorTile(row, col);
                colorTiles.setColor(colorTiles.randomColor());
                tiles.add(colorTiles);
            }
        }
        this.colorBoard = new ColorBoard(tiles, complexity);
        allMove = new ArrayList<>();
        colors = new ArrayList<>();
        allState = new ArrayList<>();
        arr = new ArrayList();
    }

    /**
     * Add color tiles which are the neighbour at specific direction of the specific ColorTile
     * tile and have color initColor.
     *
     * @param tile      the ColorTile whose neighbours are being checked.
     * @param initColor the Color which are the same as the color of ColorTile(0, 0)
     * @param direction the direction of neighbour being checked.
     */
    private void helpFindNeighbour(ColorTile tile, int initColor, String direction ){
        if(colorBoard.getNeighbour(tile, direction)!= null
                && Objects.requireNonNull(colorBoard.getNeighbour(tile, direction)).getColor() ==
                initColor && ! allState.contains(colorBoard.getNeighbour(tile, direction))
                && ! arr.contains(colorBoard.getNeighbour(tile, direction)) ){

            allState.add(colorBoard.getNeighbour(tile, direction));
        }
    }

    /**
     * Add color tiles which are the neighbour at all directions of the specific ColorTile
     * tile and have color initColor.
     *
     * @param tile the ColorTile whose neighbours are being checked.
     * @param initColor the Color which are the same as the color of ColorTile(0, 0)
     */
    private void neighbour(ColorTile tile, int initColor){
        helpFindNeighbour(tile, initColor, "top");
        helpFindNeighbour(tile, initColor, "bottom");
        helpFindNeighbour(tile, initColor, "right");
        helpFindNeighbour(tile, initColor, "left");
    }


    /**
     * Change the ColorTiles lined with the ColorTile at (0, 0) with the same color to the new color
     * newColor.
     *
     * @param newColor the newColor to be set to eligible ColorTiles.
     */
    @Override
    public void makeChange(int newColor) {
        allState = new ArrayList<>();
        ArrayList<ColorTile> arr =  new ArrayList<>();
        ColorTile tile = colorBoard.getGrid(0, 0);
        int initColor = tile.getColor();

        tile.setColor(newColor);
        arr.add(tile);
        // find all neighbour tiles that with initColor
        neighbour(tile, initColor);

        while(allState.size() != 0){
            tile = allState.get(0);
            // find all tiles that with initColor
            neighbour(tile, initColor);
            tile.setColor(newColor);
            arr.add(tile);
            allState.remove(0);
        }
        allMove.add(arr);
        colors.add(initColor);
        addScoreBy(1);
    }

    /**
     * Return true if the undo function is available, false otherwise.
     *
     * @return true if the undo function is available
     */
    boolean undoAvailable(){
        return (this.allMove.size() >= 1);
    }

    /**
     * Undo the previous move
     */
    void undo(){
        if(undoAvailable()){
            ArrayList<ColorTile> whatever = allMove.remove(allMove.size()-1);
            int color = colors.remove(colors.size()-1);
            for(ColorTile cur: whatever){
                cur.setColor(color);
            }
        }
        addScoreBy(2);
    }

    /**
     * Return the current ColorBoard colorBoard.
     *
     * @return the current ColorBoard.
     */
    public ColorBoard getBoard(){
        return colorBoard;
    }

    public void setColorBoard(ColorBoard colorBoard){
        this.colorBoard = colorBoard;
    }

    /**
     * Return true if all the color tiles in this ColorBoard have the same color, else return False.
     *
     * @return if all the color tiles in this ColorBoard have the same color.
     */
    @Override
    public boolean puzzleSolved() {
        boolean result = true;
        for (int x = 0; x < colorBoard.getTiles().length; x++) {
            for (int y = 0; y < colorBoard.getTiles()[x].length; y++) {
                if (colorBoard.getGrid(x, y).getColor() != colorBoard.getGrid(0, 0).getColor()){
                    result = false;
                }
            }
        }
        return result;
    }
}
