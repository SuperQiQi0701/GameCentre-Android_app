package ColorMatching;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Basic.SuperManager;

public class ColorBoardManager extends SuperManager implements Serializable{
    /**
     * The board being managed.
     */
    private ColorBoard colorBoard;
    private ArrayList<ArrayList<ColorTile>> allMove;
    private ArrayList<ColorTile> current;
    private ArrayList<Integer> colors;
    private ArrayList<ColorTile> allState;
    private ArrayList arr;


    public ColorBoardManager(int complexity) {
        super(complexity);
        List<ColorTile> tiles = new ArrayList<>();
        int rowNum = (getComplexity() - 2) * 4;
        int colNum = (getComplexity() - 2) * 5;

        for (int row = 0; row != rowNum; row++) {
            for (int col = 0; col != colNum; col++) {
                tiles.add(new ColorTile(row, col));
            }
        }
        this.colorBoard = new ColorBoard(tiles, complexity);
        allMove = new ArrayList<>();
        current = new ArrayList<>();
        colors = new ArrayList<>();
        allState = new ArrayList<>();
        arr = new ArrayList();
    }


    private void helpFindNeighbour(ColorTile tile, int initColor, String direction ){
        if(colorBoard.getNeighbour(tile, direction)!= null
                && Objects.requireNonNull(colorBoard.getNeighbour(tile, direction)).getColor() ==
                initColor && ! allState.contains(colorBoard.getNeighbour(tile, direction))
                && ! arr.contains(colorBoard.getNeighbour(tile, direction)) ){
            allState.add(colorBoard.getNeighbour(tile, direction));
        }
    }

    private void neighbour(ColorTile tile, int initColor){
        helpFindNeighbour(tile, initColor, "top");
        helpFindNeighbour(tile, initColor, "bottom");
        helpFindNeighbour(tile, initColor, "right");
        helpFindNeighbour(tile, initColor, "left");
    }

    //这个可以考虑优化一下
    @Override
    public void makeChange(int newColor) {
        allState = new ArrayList<>();
        ArrayList<ColorTile> arr =  new ArrayList<>();
        ColorTile tile = colorBoard.getGrid(0, 0);
        int initColor = tile.getColor();
        if(newColor != initColor){
            tile.setColor(newColor);
            arr.add(tile);
            neighbour(tile, initColor);
            while(allState.size() != 0){
                tile = allState.get(0);
                neighbour(tile, initColor);
                tile.setColor(newColor);
                arr.add(tile);
                allState.remove(0);
            }
            allMove.add(arr);
            colors.add(initColor);
        }else if (allMove.size() != 0) {
            allMove.add(allMove.get(allMove.size()-1));
            colors.add(initColor);

        }
        addScoreBy(1);
    }

    /**
     * Return true if the undo function is available, false otherwise.
     *
     * @return if the undo function is available
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

    public ColorBoard getGame(){
        return colorBoard;
    }

    @Override
    public boolean puzzleSolved() {
        for (int x = 0; x < colorBoard.getTiles().length; x++) {
            for (int y = 0; y < colorBoard.getTiles()[x].length; y++) {
                if (colorBoard.getGrid(x, y).getColor() != colorBoard.getGrid(0, 0).getColor()){
                    return false;
                }
            }
        }
        return true;
    }


    public ArrayList<ColorTile> getCurrent() {
        return current;
    }

    public void setCurrent(ArrayList<ColorTile> current) {
        this.current = current;
    }
}
