package fall2018.csc2017.slidingtiles;

public class ColorBoardManager implements GameManageable {
    private ColorBoard colorBoard;
    private int score;
    private int complexity;
    boolean[][] board;

    ColorBoardManager(int complexity) {
        this.complexity = complexity;
        this.colorBoard = new ColorBoard();
        board = new boolean[8][10];
    }

    public void changeColor(int newColor){
        if(! puzzleSolved()){
            changeOriColor(newColor);
        }
    }

    public void changeOriColor(int newColor){
        int initColor = colorBoard.getGrid(0, 0).getColor();
        int x = 0;
        int y = 0;
        while(y < board[x].length && x < board.length && colorBoard.getGrid(x, y).getColor() == initColor){
            colorBoard.getGrid(x, y).setColor(newColor);
            while(x+1 < board.length && colorBoard.getGrid(x+1, y).getColor() == initColor) {
                colorBoard.getGrid(x+1, y).setColor(newColor);
                x++;
            }
            y++;
            x = 0;
        }
        score++;
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
