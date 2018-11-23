package Basic;

import Basic.UserManager;
import ColorMatching.ColorBoardManager;
import FlipToWin.FlipToWinBoardManager;
import fall2018.csc2017.slidingtiles.BoardManager;
import fall2018.csc2017.slidingtiles.ScoreBoard;

public enum DataManager {
    INSTANCE;

    /**
     * The String that represent the current game name.
     */
    private String currentGame;

    /**
     * the FlipToWinBoardManager of this game
     */
    private BoardManager slideTileBoardManager;


    /**
     * The UserManager of this game
     */
    private UserManager userManager;

    /**
     * The ColorBoardManager of this game
     */
    private ColorBoardManager colorBoardManager;


    /**
     * The ScoreBoard of this game
     */
    private ScoreBoard scoreBoard;

    /**
     * The FlipToWinBoardManager of this game
     */
    private FlipToWinBoardManager flipToWinBoardManager;

    /**
     * Return the current game name
     *
     * @return the current game name
     */
    public String getCurrentGame() {
        return currentGame;
    }

    /**
     * Set the currentGame to the given game name
     *
     * @param currentGame the given current game name
     */
    public void setCurrentGame(String currentGame) {
        this.currentGame = currentGame;
    }

    /**
     * Return the FlipToWinBoardManager
     */
    FlipToWinBoardManager getFlipToWinBoardManager() {
        return this.flipToWinBoardManager;
    }


    /**
     * Return the ColorBoardManager
     */
    ColorBoardManager getColorBoardManager(){ return this.colorBoardManager;}

    /**
     * Set the FlipToWinBoardManager for loading function.
     *
     * @param fbm the FlipToWinBoardManager instance will be loaded
     */
    void setFlipToWinBoardManager(FlipToWinBoardManager fbm) {
        this.flipToWinBoardManager = fbm;
    }

    /**
     * Set the ColorBoardManager for loading function.
     *
     * @param cbm the ColorBoardManager instance will be loaded
     */
    void setColorBoardManager(ColorBoardManager cbm) {
        this.colorBoardManager = cbm;
    }

    /**
     * This will create a new instance of FlipToWinBoardManager for new game function
     */
    void startNewFlipToWinGame(int complexity) {
        this.flipToWinBoardManager = new FlipToWinBoardManager(complexity);
    }

    /**
     * This will create a new instance of FlipToWinBoardManager for new game function
     */
    void startNewColorMatchingGame(int complexity) {
        this.colorBoardManager = new ColorBoardManager(complexity);
    }


    /**
     * Return the boardManager
     */
    public BoardManager getBoardManager() {
        return slideTileBoardManager;
//        switch (currentGame){
//            case "ST":
//                return slideTileBoardManager;
//            case "Color":
//                return colorBoardManager;
//            case "FTW":
//                return flipToWinBoardManager;
//        }
    }


    /**
     * Set the boardManager for loading function.
     *
     * @param object the FlipToWinBoardManager instance will be loaded
     */
    public void setBoardManager(Object object) {
        switch (currentGame){
            case "ST":
                this.slideTileBoardManager = (BoardManager) object;
                break;
            case "Color":
                this.colorBoardManager = (ColorBoardManager) object;
                break;
            case "FTW":
                this.flipToWinBoardManager = (FlipToWinBoardManager) object;
                break;
        }
    }


    /**
     * This will create a new instance of FlipToWinBoardManager for new game function
     */
    void startNewGame(int complexity) {
        this.slideTileBoardManager = new BoardManager(complexity);
    }



    /**
     * Return the UserManager of the game
     *
     * @return the UserManager of the game
     */
    public UserManager getUserManager() {
        return userManager;
    }


    /**
     * Set the UserManager for loading function
     *
     * @param um the UserManager from the file
     */
    public void setUserManager(UserManager um) {
        this.userManager = um;
    }


    /**
     * Return the ScoreBoard
     *
     * @return the ScoreBoard of the game
     */
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }


    /**
     * This will create a new instance of ScoreBoard for new game function
     */
    public void setScoreBoard(ScoreBoard sb) {
        this.scoreBoard = sb;
    }


    /**
     * Create an new UserManager
     */
    public void newUserManager() {
        this.userManager = new UserManager();
    }


    /**
     * Create an new ScoreBoard
     */
    public void newScoreBoard() {
        this.scoreBoard = new ScoreBoard();
    }
}
