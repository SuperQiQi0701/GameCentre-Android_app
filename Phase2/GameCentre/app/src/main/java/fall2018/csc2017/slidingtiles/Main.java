package fall2018.csc2017.slidingtiles;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * The overall, largest class of this game which control everything
 */
public enum Main {


    INSTANCE;


    /**
     * the FlipToWinBoardManager of this game
     */
    private BoardManager boardManager;


    /**
     * The UserManager of this game
     */
    private UserManager userManager;


    /**
     * The ScoreBoard of this game
     */
    private ScoreBoard scoreBoard;

    /**
     * The FlipToWinBoardManager of this game
     */
    private FlipToWinBoardManager flipToWinBoardManager;


    /**
     * Return the FlipToWinBoardManager
     */
    FlipToWinBoardManager getFlipToWinBoardManager() {
        return this.flipToWinBoardManager;
    }


    /**
     * Set the FlipToWinBoardManager for loading function.
     *
     * @param fbm the FlipToWinBoardManager instance will be loaded
     */
    void setFlipToWinBoardManager(FlipToWinBoardManager fbm) {
        this.flipToWinBoardManager = fbm;
    }


    /**
     * This will create a new instance of FlipToWinBoardManager for new game function
     */
    void startNewFlipToWinGame(int complexity) {
        this.flipToWinBoardManager = new FlipToWinBoardManager(complexity);
    }


    /**
     * Return the boardManager
     */
    BoardManager getBoardManager() {
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
    void startNewGame(int complexity) {
        this.boardManager = new BoardManager(complexity);
    }


    /**
     * Return the UserManager of the game
     *
     * @return the UserManager of the game
     */
    UserManager getUserManager() {
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


    /**
     * This will create a new instance of ScoreBoard for new game function
     */
    void setScoreBoard(ScoreBoard sb) {
        this.scoreBoard = sb;
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
    void newScoreBoard() {
        this.scoreBoard = new ScoreBoard();
    }


    /**
     * Load the FlipToWin BoardManager from fileName.
     *
     * @param fileContext this.getApplicationContext()
     * @param fileName    the name of the file
     */
    void loadFlipToWinBoardManagerFromFile(@NonNull Context fileContext, String fileName) {

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
    void saveFlipToWinBoardManagerToFile(@NonNull Context fileContext, String fileName) {
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
    void loadBoardManagerFromFile(@NonNull Context fileContext, String fileName) {

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
    void saveBoardManagerToFile(@NonNull Context fileContext, String fileName) {
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
    void loadScoreBoardFromFile(Context fileContext) {
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
    void saveScoreBoardToFile(Context fileContext) {
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
    void saveUserManagerToFile(Context fileContext) {
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

}
