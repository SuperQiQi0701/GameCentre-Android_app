package Basic;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fall2018.csc2017.slidingtiles.ScoreBoard;

public class FileManager {

    /**
     * Save the given object to file.
     * <p>
     * Precondition: the type is either "UM" or "SB"
     *
     * @param fileContext this.getApplicationContext()
     * @param object      the object that will be saved
     * @param type        the type of the object.
     */
    void saveToFile(Context fileContext, Object object, String type) {
        try {
            String fileName = DataManager.INSTANCE.getCurrentGameName();
            if ("UM".equals(type)) {
                fileName = fileName + "_UserManager.ser";
            } else {
                fileName = fileName + "_ScoreBoard.ser";
            }
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    fileContext.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStream.writeObject(object);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load the userManager from file and return it
     *
     * @param fileContext this.getApplicationContext()
     * @return the userManager that loaded from file
     */
    UserManager loadUserManager(Context fileContext) {
        try {
            String fileName = DataManager.INSTANCE.getCurrentGameName() + "_UserManager.ser";
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                UserManager userManager = (UserManager) input.readObject();
                inputStream.close();
                return userManager;
            }
            return new UserManager();
        } catch (Exception e) {
            return new UserManager();
        }
    }

    /**
     * Load the scoreBoard from file and return it
     *
     * @param fileContext this.getApplicationContext()
     * @return the scoreBoard that loaded from file
     */
    ScoreBoard loadScoreBoard(Context fileContext) {
        try {
            String fileName = DataManager.INSTANCE.getCurrentGameName() + "_ScoreBoard.ser";
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                ScoreBoard scoreBoard = (ScoreBoard) input.readObject();
                inputStream.close();
                return scoreBoard;
            }
            return new ScoreBoard();
        } catch (Exception e) {
            return new ScoreBoard();
        }
    }


//    /**
//     * The save the data to .ser file
//     *
//     * @param fileContext this.getApplicationContext()
//     * @param operation this indicates what will be saved
//     */
//    void saveToFile(@NonNull Context fileContext, String operation) {
//        try {
//            String fileName = DataManager.INSTANCE.getCurrentGameName();
//            Object object = null;
//            switch (operation){
//                case "UM":
//                    fileName = fileName + "_UserManager.ser";
//                    object = DataManager.INSTANCE.getUserManager();
//                    break;
//                case "SB":
//                    fileName = fileName + "_ScoreBoard.ser";
//                    object = DataManager.INSTANCE.getScoreBoard();
//                    break;
//                case "SAVE":
//                    fileName = fileName + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
//                    object = DataManager.INSTANCE.getBoardManager();
//                    break;
//                case "Auto":
//                    fileName = fileName + "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
//                    object = DataManager.INSTANCE.getBoardManager();
//            }
//            ObjectOutputStream outputStream = new ObjectOutputStream(
//                    fileContext.openFileOutput(fileName, Context.MODE_PRIVATE));
//            outputStream.writeObject(object);
//            outputStream.close();
//        } catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//    }
//
//
//
//
//    /**
//     * Load data from .ser file
//     *
//     * @param fileContext this.getApplicationContext()
//     * @param operation this indicates what will be loaded
//     */
//    void loadFromFile(Context fileContext, String operation) {
//        try {
//            String fileName = DataManager.INSTANCE.getCurrentGameName();
//            switch (operation){
//                case "UM":
//                    fileName = fileName + "_UserManager.ser";
//                    break;
//                case "SB":
//                    fileName = fileName + "_ScoreBoard.ser";
//                    break;
//                case "SAVE":
//                    fileName = fileName + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
//                    break;
//                case "Auto":
//                    fileName = fileName + "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
//            }
//            InputStream inputStream = fileContext.openFileInput(fileName);
//            if (inputStream != null) {
//                ObjectInputStream input = new ObjectInputStream(inputStream);
//                if (operation == "UM") {
//                    DataManager.INSTANCE.setUserManager((UserManager) input.readObject());
//                } else if (operation == "SB") {
//                    DataManager.INSTANCE.setScoreBoard((ScoreBoard) input.readObject());
//                } else {
//                    DataManager.INSTANCE.setBoardManager(input.readObject());
//                }
//                inputStream.close();
//            }
//        } catch (FileNotFoundException e) {
//            switch (operation){
//                case "UM":
//                    DataManager.INSTANCE.newUserManager();
//                    break;
//                case "SB":
//                    DataManager.INSTANCE.newScoreBoard();
//                    break;
//                default:
//                    Log.e("login activity", "File not found: " + e.toString());
//            }
//        } catch (IOException e) {
//            Log.e("login activity", "Can not read file: " + e.toString());
//        } catch (ClassNotFoundException e) {
//            Log.e("login activity", "File contained unexpected data type: " + e.toString());
//        }
//    }
}
