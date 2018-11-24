package ColorMatching;

import java.util.ArrayList;

import Basic.GameManageable;

public class ColorBoardManager implements GameManageable {
    ColorBoard colorBoard;
    private int score;
    boolean[][] board;
    private ArrayList<ArrayList<ColorTile>> allMove;
    private ArrayList<ColorTile> current;
    private ArrayList<Integer> colors;
    ColorTile curTile;
    private ArrayList<ColorTile> allState;
    ArrayList arr;

    public ColorBoardManager(int complexity) {
        this.colorBoard = new ColorBoard(complexity);
        board = new boolean[(complexity - 2) * 4][ (complexity - 2) * 5];
        allMove = new ArrayList<>();
        current = new ArrayList<>();
        colors = new ArrayList<>();
        curTile = colorBoard.getGrid(0, 0);
        allState = new ArrayList<ColorTile>();
        arr = new ArrayList();
    }

    public ColorTile getLeft(ColorTile tile){
        if((tile.x)-1 >= 0){
            return colorBoard.getGrid((tile.x)-1, tile.y);
        }
        return null;
    }

    public ColorTile getRight(ColorTile tile){
        if((tile.x)+1 < board.length){
            return colorBoard.getGrid((tile.x)+1, tile.y);
        }
        return null;
    }

    public ColorTile getTop(ColorTile tile){
        if((tile.y)-1 >= 0){
            return colorBoard.getGrid(tile.x, (tile.y)-1);
        }
        return null;
    }

    public ColorTile getBottom(ColorTile tile){
        if((tile.y)+1 < board.length*5/4){
            return colorBoard.getGrid(tile.x, (tile.y)+1);
        }
        return null;
    }

//    void changeColor(int newColor) {
////        if(! puzzleSolved()){
////            changeOriColor(newColor);
////        }
////    }

    public void neighbour(ColorTile tile, int initColor){
        if(getLeft(tile)!= null){
            if(getLeft(tile).getColor() == initColor){
                if(! allState.contains(getLeft(tile)) && ! arr.contains(getLeft(tile))){
                    allState.add(getLeft(tile));
                }
            }
        }
        if(getRight(tile)!= null){
            if(getRight(tile).getColor() == initColor){
                if(! allState.contains(getRight(tile)) && ! arr.contains(getRight(tile))){
                    allState.add(getRight(tile));
                }
            }
        }
        if(getTop(tile)!= null){
            if(getTop(tile).getColor() == initColor){
                if(! allState.contains(getTop(tile)) && ! arr.contains(getTop(tile))){
                    allState.add(getTop(tile));
                }
            }
        }
        if(getBottom(tile)!= null){
            if(getBottom(tile).getColor() == initColor){
                if(! allState.contains(getBottom(tile)) && ! arr.contains(getBottom(tile))){
                    allState.add(getBottom(tile));
                }
            }
        }
    }


    void changeColor(int newColor) {
        allState = new ArrayList<ColorTile>();
        ArrayList<ColorTile> arr =  new ArrayList<ColorTile>();
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
    }


//    private void changeOriColor(int newColor) {
//        current = new ArrayList<>();
//        int initColor = colorBoard.getGrid(0, 0).getColor();
//        int x = 0;
//        int y = 0;
//        //当前坐标x,y
//        while(y < board[x].length && x < board.length && colorBoard.getGrid(x, y).getColor() == initColor){
//            colorBoard.getGrid(x, y).setColor(newColor);
//            current.add(colorBoard.getGrid(x, y));
//            //当前下方
//            int temp = y+1;
//            while(temp < board[x].length && colorBoard.getGrid(x, temp).getColor() == initColor){
//                colorBoard.getGrid(x, temp).setColor(newColor);
//                current.add(colorBoard.getGrid(x, temp));
//                temp++;
//            }
//            while(x+1 < board.length && colorBoard.getGrid(x+1, y).getColor() == initColor) {
//                colorBoard.getGrid(x+1, y).setColor(newColor);
//                current.add(colorBoard.getGrid(x+1, y));
//                int temp2 = y+1;
//                while(temp2 < board[x].length && colorBoard.getGrid(x+1, temp2).getColor() == initColor){
//                    colorBoard.getGrid(x+1, temp2).setColor(newColor);
//                    current.add(colorBoard.getGrid(x+1, temp2));
//                    temp2++;
//                }
//                x++;
//            }
//            y++;
//            x = 0;
//        }
//        score++;
//        allMove.add(current);
//        colors.add(initColor);
//    }

    public void undo(){
        if(allMove.size() >= 1){
            ArrayList<ColorTile> whatever = allMove.remove(allMove.size()-1);
            int color = colors.remove(colors.size()-1);
            for(ColorTile cur: whatever){
                cur.setColor(color);
            }
        }
    }

    public ColorBoard getGame(){
        return colorBoard;
    }

    @Override
    public boolean puzzleSolved() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (colorBoard.getGrid(x, y).getColor() != colorBoard.getGrid(0, 0).getColor()){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int getScore() {
        return this.score;
    }
}
