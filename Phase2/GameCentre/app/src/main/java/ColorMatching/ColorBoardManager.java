package ColorMatching;

import java.util.ArrayList;
import java.util.List;

import Basic.GameManageable;

public class ColorBoardManager implements GameManageable {
    ColorBoard colorBoard;
    private int score;
    boolean[][] board;
    private ArrayList<ArrayList> allMove;
    private ArrayList<ColorTile> current;
    private ArrayList<Integer> colors;


    public ColorBoardManager(int complexity) {
        this.colorBoard = new ColorBoard();
        board = new boolean[8][10];
        allMove = new ArrayList<>();
        current = new ArrayList<>();
        colors = new ArrayList<>();
    }

    void changeColor(int newColor) {
        if(! puzzleSolved()){
            changeOriColor(newColor);
        }
    }

    private void changeOriColor(int newColor) {
        current = new ArrayList<>();
        int initColor = colorBoard.getGrid(0, 0).getColor();
        int x = 0;
        int y = 0;
        //当前坐标x,y
        while(y < board[x].length && x < board.length && colorBoard.getGrid(x, y).getColor() == initColor){
            colorBoard.getGrid(x, y).setColor(newColor);
            current.add(colorBoard.getGrid(x, y));
            //当前下方
            int temp = y+1;
            while(temp < board[x].length && colorBoard.getGrid(x, temp).getColor() == initColor){
                colorBoard.getGrid(x, temp).setColor(newColor);
                current.add(colorBoard.getGrid(x, temp));
                temp++;
            }
            while(x+1 < board.length && colorBoard.getGrid(x+1, y).getColor() == initColor) {
                colorBoard.getGrid(x+1, y).setColor(newColor);
                current.add(colorBoard.getGrid(x+1, y));
                int temp2 = y+1;
                while(temp2 < board[x].length && colorBoard.getGrid(x+1, temp2).getColor() == initColor){
                    colorBoard.getGrid(x+1, temp2).setColor(newColor);
                    current.add(colorBoard.getGrid(x+1, temp2));
                    temp2++;
                }
                x++;
            }
            y++;
            x = 0;
        }
        score++;
        allMove.add(current);
        colors.add(initColor);
    }

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
