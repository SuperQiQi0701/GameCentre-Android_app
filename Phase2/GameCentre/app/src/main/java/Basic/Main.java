package Basic;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ColorMatching.ColorBoard;
import ColorMatching.ColorMatchingScoreBoard;
import fall2018.csc2017.slidingtiles.Board;
import fall2018.csc2017.slidingtiles.BoardManager;
import ColorMatching.ColorBoardManager;
import FlipToWin.FlipToWinBoard;
import FlipToWin.FlipToWinBoardManager;
import FlipToWin.FlipToWinScoreBoard;
import fall2018.csc2017.slidingtiles.ScoreBoard;


/**
 * The overall, largest class of this game which control everything
 */
public enum Main {


    INSTANCE;


    /**
     * the FlipToWinBoardManager of this game
     */
    public BoardManager boardManager;


    /**
     * The UserManager of this game
     */
    public UserManager userManager;

    /**
     * The ColorBoardManager of this game
     */
    public ColorBoardManager colorBoardManager;


    /**
     * The ScoreBoard of this game
     */
    public ScoreBoard scoreBoard;

    public FlipToWinScoreBoard flipScoreBoard;

    public ColorMatchingScoreBoard colorScoreBoard;

    /**
     * The FlipToWinBoardManager of this game
     */
    public FlipToWinBoardManager flipToWinBoardManager;


    /**
     * Return the FlipToWinBoardManager
     */
    public FlipToWinBoardManager getFlipToWinBoardManager() {
        return this.flipToWinBoardManager;
    }


    /**
     * Return the ColorBoardManager
     */
    public ColorBoardManager getColorBoardManager(){ return this.colorBoardManager;}

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
    public void startNewFlipToWinGame(int complexity) {
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
        return this.boardManager;
    }


    /**
     * Set the boardManager for loading function.
     *
     * @param bm the FlipToWinBoardManager instance will be loaded
     */
    void setBoardManager(BoardManager bm) {
        this.boardManager = bm;
    }


    /**
     * This will create a new instance of FlipToWinBoardManager for new game function
     */
    public void startNewGame(int complexity) {
        this.boardManager = new BoardManager(complexity);
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
    void setUserManager(UserManager um) {
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


    public FlipToWinScoreBoard getFlipToWinScoreBoard() {
        return flipScoreBoard;
    }

    public ColorMatchingScoreBoard getColorScoreBoard(){
        return colorScoreBoard;
    }


    /**
     * This will create a new instance of ScoreBoard for new game function
     */
    void setScoreBoard(ScoreBoard sb) {
        this.scoreBoard = sb;
    }

    void setFlipToWinScoreBoard(FlipToWinScoreBoard fsb) {
        this.flipScoreBoard = fsb;
    }

    void setColorMatchingScoreboard(ColorMatchingScoreBoard csb){
        this.colorScoreBoard = csb;
    }


    /**
     * Create an new UserManager
     */
    void newUserManager() {
        this.userManager = new UserManager();
    }


    /**
     * Create an new ScoreBoard
     */
    public void newFlipScoreBoard() {
        this.flipScoreBoard = new FlipToWinScoreBoard();
    }

    /**
     * Create an new ScoreBoard
     */
    public void newScoreBoard() {
        this.scoreBoard = new ScoreBoard();
    }


    /**
     * Create an new ScoreBoard
     */
    public void newColorMatchingScoreBoard() {
        this.colorScoreBoard = new ColorMatchingScoreBoard();
    }


    /**
     * Load the FlipToWin BoardManager from fileName.
     *
     * @param fileContext this.getApplicationContext()
     * @param fileName    the name of the file
     */
    public void loadFlipToWinBoardManagerFromFile(@NonNull Context fileContext, String fileName) {

        try {
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                setFlipToWinBoardManager((FlipToWinBoardManager) input.readObject());
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }


    /**
     * Save the FlipToWin BoardManager to fileName.
     *
     * @param fileContext this.getApplicationContext()
     * @param fileName    the name of the file
     */
    void saveColorBoardManagerToFile(@NonNull Context fileContext, String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    fileContext.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStream.writeObject(colorBoardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    /**
     * Load the ColorMatching BoardManager from fileName.
     *
     * @param fileContext this.getApplicationContext()
     * @param fileName    the name of the file
     */
    void loadColorBoardManagerFromFile(@NonNull Context fileContext, String fileName) {

        try {
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                setColorBoardManager((ColorBoardManager) input.readObject());
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }


    /**
     * Save the ColorMatching BoardManager to fileName.
     *
     * @param fileContext this.getApplicationContext()
     * @param fileName    the name of the file
     */
    public void saveFlipToWinBoardManagerToFile(@NonNull Context fileContext, String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    fileContext.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStream.writeObject(flipToWinBoardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load the board manager from fileName.
     *
     * @param fileContext this.getApplicationContext()
     * @param fileName    the name of the file
     */
    public void loadBoardManagerFromFile(@NonNull Context fileContext, String fileName) {

        try {
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                setBoardManager((BoardManager) input.readObject());
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }


    /**
     * Save the board manager to fileName.
     *
     * @param fileContext this.getApplicationContext()
     * @param fileName    the name of the file
     */
    public void saveBoardManagerToFile(@NonNull Context fileContext, String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    fileContext.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStream.writeObject(boardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    /**
     * Load scoreBoard from file
     *
     * @param fileContext this.getApplicationContext()
     */
    public void loadScoreBoardFromFile(Context fileContext) {
        try {
            String fileName = Board.GAME_NAME + "_ScoreBoard.ser";
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                setScoreBoard((ScoreBoard) input.readObject());
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            newScoreBoard();
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }


    /**
     * Save the scoreBoard to file.
     *
     * @param fileContext this.getApplicationContext()
     */
    public void saveScoreBoardToFile(Context fileContext) {
        try {
            String fileName = Board.GAME_NAME + "_ScoreBoard.ser";
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    fileContext.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStream.writeObject(scoreBoard);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    /**
     * Load userManager from file
     *
     * @param fileContext this.getApplicationContext()
     */
    void loadUserManagerFromFile(Context fileContext) {
        try {
            String fileName = Board.GAME_NAME + "_UserManager.ser";
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                setUserManager((UserManager) input.readObject());
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            newUserManager();
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }


    /**
     * Save the userManager to file.
     *
     * @param fileContext this.getApplicationContext()
     */
    public void saveUserManagerToFile(Context fileContext) {
        try {
            String fileName = Board.GAME_NAME + "_UserManager.ser";
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    fileContext.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStream.writeObject(userManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    /**
     * Load scoreBoard from file
     *
     * @param fileContext this.getApplicationContext()
     */
    public void loadFlipToWinScoreBoardFromFile(Context fileContext) {
        try {
            String fileName = FlipToWinBoard.GAME_NAME + "_ScoreBoard.ser";
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                setFlipToWinScoreBoard((FlipToWinScoreBoard) input.readObject());
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            newFlipScoreBoard();
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }


    /**
     * Save the scoreBoard to file.
     *
     * @param fileContext this.getApplicationContext()
     */
    public void saveFlipToWinScoreBoardToFile(Context fileContext) {
        try {
            String fileName = FlipToWinBoard.GAME_NAME + "_ScoreBoard.ser";
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    fileContext.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStream.writeObject(flipScoreBoard);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load scoreBoard from file
     *
     * @param fileContext this.getApplicationContext()
     */
    public void loadColorMatchingScoreBoardFromFile(Context fileContext) {
        try {
            String fileName = ColorBoard.GAME_NAME + "_ScoreBoard.ser";
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                setColorMatchingScoreboard((ColorMatchingScoreBoard) input.readObject());
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            newFlipScoreBoard();
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the scoreBoard to file.
     *
     * @param fileContext this.getApplicationContext()
     */
    public void saveColorMatchingScoreBoardToFile(Context fileContext) {
        try {
            String fileName = ColorBoard.GAME_NAME + "_ScoreBoard.ser";
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    fileContext.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStream.writeObject(colorScoreBoard);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }



}
