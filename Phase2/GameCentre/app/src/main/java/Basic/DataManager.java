package Basic;

import ColorMatching.ColorBoardManager;
import FlipToWin.FlipToWinBoardManager;
import fall2018.csc2017.slidingtiles.BoardManager;
import fall2018.csc2017.slidingtiles.ScoreBoard;

public enum DataManager {
    INSTANCE;

    /**
     * The String that represent the current game name.
     * <p>
     * Precondition: currentGameName should be one of the "ST", "CM" and "FTW"
     */
    private String currentGameName;

    /**
     * The String that represent the current user name.
     */
    private String currentUserName;

    /**
     * The boardManager of the game
     */
    private GridBoardManager boardManager;


    /**
     * Return the current game name
     *
     * @return the current game name
     */
    public String getCurrentGameName() {
        return currentGameName;
    }

    /**
     * Set the this.currentGameName to the given game name
     *
     * @param currentGameName the given current game name
     */
    public void setCurrentGameName(String currentGameName) {
        this.currentGameName = currentGameName;
    }

    /**
     * Return the name of the current user
     *
     * @return current user name
     */
    public String getCurrentUserName() {
        return currentUserName;
    }

    /**
     * Set this.currentUserName to the given game name
     *
     * @param currentUserName the given game name
     */
    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    /**
     * Return the boardManager of the game
     *
     * @return the boardManager of the game
     */
    public GridBoardManager getBoardManager() {
        return boardManager;
    }

    /**
     * Set this.boardManager to the given boardManager
     *
     * @param boardManager the given boardManager
     */
    public void setBoardManager(GridBoardManager boardManager) {
        this.boardManager = boardManager;
    }

//    /**
//     * This will create a new instance of FlipToWinBoardManager for new game function
//     */
//    void startNewGame(int complexity) {
//        if (currentGameName.equals("ST")) {
//            this.boardManager = new BoardManager(complexity);
//        } else if (currentGameName.equals("CM")) {
//            this.boardManager = new ColorBoardManager(complexity);
//        } else {
//            this.boardManager = new FlipToWinBoardManager(complexity);
//        }
//    }


//    /**
//     * Return the boardManager
//     */
//    public BoardManager getBoardManager() {
//        return slideTileBoardManager;
//        switch (currentGameName){
//            case "ST":
//                return slideTileBoardManager;
//            case "Color":
//                return colorBoardManager;
//            case "FTW":
//                return flipToWinBoardManager;
//        }
//    }
//
//
//    /**
//     * Set the boardManager for loading function.
//     *
//     * @param object the FlipToWinBoardManager instance will be loaded
//     */
//    public void setBoardManager(Object object) {
//        switch (currentGameName){
//            case "ST":
//                this.slideTileBoardManager = (BoardManager) object;
//                break;
//            case "Color":
//                this.colorBoardManager = (ColorBoardManager) object;
//                break;
//            case "FTW":
//                this.flipToWinBoardManager = (FlipToWinBoardManager) object;
//                break;
//        }
//    }


//    /**
//     * This will create a new instance of FlipToWinBoardManager for new game function
//     */
//    void startNewGame(int complexity) {
//        this.slideTileBoardManager = new BoardManager(complexity);
//    }


}
