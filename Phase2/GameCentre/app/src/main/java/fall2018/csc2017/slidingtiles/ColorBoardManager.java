package fall2018.csc2017.slidingtiles;

public class ColorBoardManager implements GameManageable {
    private ColorBoard board;
    private int score;
    public ColorBoard getGame(){
        return board;
    }

    @Override
    public boolean puzzleSolved() {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }

    ;

}
