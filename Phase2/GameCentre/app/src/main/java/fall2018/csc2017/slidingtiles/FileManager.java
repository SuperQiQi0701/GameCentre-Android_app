package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {


    /**
     * The save the data to .ser file
     *
     * @param fileContext this.getApplicationContext()
     * @param operation this indicates what will be saved
     */
    void saveToFile(@NonNull Context fileContext, String operation) {
        try {
            String fileName = DataManager.INSTANCE.getCurrentGame();
            Object object = null;
            switch (operation){
                case "UM":
                    fileName = fileName + "_UserManager.ser";
                    object = DataManager.INSTANCE.getUserManager();
                    break;
                case "SB":
                    fileName = fileName + "_ScoreBoard.ser";
                    object = DataManager.INSTANCE.getScoreBoard();
                    break;
                case "SAVE":
                    fileName = fileName + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
                    object = DataManager.INSTANCE.getBoardManager();
                    break;
                case "Auto":
                    fileName = fileName + "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
                    object = DataManager.INSTANCE.getBoardManager();
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
     * Load data from .ser file
     *
     * @param fileContext this.getApplicationContext()
     * @param operation this indicates what will be loaded
     */
    void loadFromFile(Context fileContext, String operation) {
        try {
            String fileName = DataManager.INSTANCE.getCurrentGame();
            switch (operation){
                case "UM":
                    fileName = fileName + "_UserManager.ser";
                    break;
                case "SB":
                    fileName = fileName + "_ScoreBoard.ser";
                    break;
                case "SAVE":
                    fileName = fileName + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
                    break;
                case "Auto":
                    fileName = fileName + "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
            }
            InputStream inputStream = fileContext.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                if (operation == "UM") {
                    DataManager.INSTANCE.setUserManager((UserManager) input.readObject());
                } else if (operation == "SB") {
                    DataManager.INSTANCE.setScoreBoard((ScoreBoard) input.readObject());
                } else {
                    DataManager.INSTANCE.setBoardManager(input.readObject());
                }
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            switch (operation){
                case "UM":
                    DataManager.INSTANCE.newUserManager();
                    break;
                case "SB":
                    DataManager.INSTANCE.newScoreBoard();
                    break;
                default:
                    Log.e("login activity", "File not found: " + e.toString());
            }
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }
}
