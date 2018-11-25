package ColorMatching;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import Basic.SuperManager;

public class ColorBoardManager extends SuperManager implements Serializable{
    /**
     * The board being managed.
     */
    ColorBoard colorBoard;

    /**
     * An integer that keep track the score of the current game
     */
    private int score = 0;

    boolean[][] board;
    private ArrayList<ArrayList<ColorTile>> allMove;
    private ArrayList<ColorTile> current;
    private ArrayList<Integer> colors;
    private ArrayList<ColorTile> allState;
    private ArrayList arr;

    public ColorBoardManager(int complexity) {
        super(complexity);
        this.colorBoard = new ColorBoard(complexity);
        board = new boolean[(complexity - 2) * 4][ (complexity - 2) * 5];
        allMove = new ArrayList<>();
        current = new ArrayList<>();
        colors = new ArrayList<>();
        allState = new ArrayList<>();
        arr = new ArrayList();
    }

    private ColorTile getLeft(ColorTile tile){
        if((tile.x)-1 >= 0){
            return colorBoard.getGrid((tile.x)-1, tile.y);
        }
        return null;
    }

    /**
     * Return the current score of the game
     *
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    private ColorTile getRight(ColorTile tile){
        if((tile.x)+1 < board.length){
            return colorBoard.getGrid((tile.x)+1, tile.y);
        }
        return null;
    }

    private ColorTile getTop(ColorTile tile){
        if((tile.y)-1 >= 0){
            return colorBoard.getGrid(tile.x, (tile.y)-1);
        }
        return null;
    }

    private ColorTile getBottom(ColorTile tile){
        if((tile.y)+1 < board.length*5/4){
            return colorBoard.getGrid(tile.x, (tile.y)+1);
        }
        return null;
    }

    private void neighbour(ColorTile tile, int initColor){
        if(getLeft(tile)!= null){
            if(Objects.requireNonNull(getLeft(tile)).getColor() == initColor){
                if(! allState.contains(getLeft(tile)) && ! arr.contains(getLeft(tile))){
                    allState.add(getLeft(tile));
                }
            }
        }
        if(getRight(tile)!= null){
            if(Objects.requireNonNull(getRight(tile)).getColor() == initColor){
                if(! allState.contains(getRight(tile)) && ! arr.contains(getRight(tile))){
                    allState.add(getRight(tile));
                }
            }
        }
        if(getTop(tile)!= null){
            if(Objects.requireNonNull(getTop(tile)).getColor() == initColor){
                if(! allState.contains(getTop(tile)) && ! arr.contains(getTop(tile))){
                    allState.add(getTop(tile));
                }
            }
        }
        if(getBottom(tile)!= null){
            if(Objects.requireNonNull(getBottom(tile)).getColor() == initColor){
                if(! allState.contains(getBottom(tile)) && ! arr.contains(getBottom(tile))){
                    allState.add(getBottom(tile));
                }
            }
        }
    }


    @Override
    protected void makeChange(int newColor) {
        allState = new ArrayList<>();
        ArrayList<ColorTile> arr =  new ArrayList<>();
        ColorTile tile = colorBoard.getGrid(0, 0);
        int initColor = tile.getColor();
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
        ++this.score;
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
        ++this.score;
        ++this.score;
    }

    public ColorBoard getGame(){
        return colorBoard;
    }

    @Override
    protected boolean puzzleSolved() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
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
