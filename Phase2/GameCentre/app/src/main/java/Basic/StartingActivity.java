package Basic;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import ColorMatching.ColorMatchingGameActivity;
import FlipToWin.FlipToWinGameActivity;
import fall2018.csc2017.slidingtiles.GameActivity;
import fall2018.csc2017.slidingtiles.R;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_);
        addStartButtonListener();
        addLoadButtonListener();
        addResumeButtonListener();
        addScoreBoardButtonListener();
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setBackgroundColor(Color.DKGRAY);
        startButton.setTextColor(Color.WHITE);
        startButton.setOnClickListener(v -> {
            Intent temp = new Intent(this, ComplexityActivity.class);
            startActivity(temp);
            finish();
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setBackgroundColor(Color.DKGRAY);
        loadButton.setTextColor(Color.WHITE);
        loadButton.setOnClickListener(v -> {
            String fileName = DataManager.INSTANCE.getCurrentGameName() + "_" +
                    DataManager.INSTANCE.getCurrentUserName() + "_Save.ser";
            if (new File(StartingActivity.this.getFilesDir() + "/" + fileName).exists()) {
                FileManager.loadGame(StartingActivity.this.getApplicationContext(), fileName);
                FileManager.saveGame(StartingActivity.this.getApplicationContext(), "Auto");
                makeToastLoadedText();
                switchToGame();
            } else {
                makeToastLoadedFailText();
            }

        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that a game was fail to be loaded.
     */
    private void makeToastLoadedFailText() {
        Toast.makeText(this, "No Saved Game found", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate the resume button.
     */
    private void addResumeButtonListener() {
        Button resume = findViewById(R.id.resumeButton);
        resume.setBackgroundColor(Color.DKGRAY);
        resume.setTextColor(Color.WHITE);
        resume.setOnClickListener((v) -> {
            String fileName = DataManager.INSTANCE.getCurrentGameName() + "_" +
                    DataManager.INSTANCE.getCurrentUserName() + "_Auto.ser";
            if (new File(StartingActivity.this.getFilesDir() + "/" + fileName).exists()) {
                FileManager.loadGame(StartingActivity.this.getApplicationContext(), fileName);
                makeToastResumeText();
                switchToGame();
            } else {
                makeToastResumeFailText();
            }

        });
    }

    private void addScoreBoardButtonListener() {
        Button resume = findViewById(R.id.scoreboardButton);
        resume.setBackgroundColor(Color.DKGRAY);
        resume.setTextColor(Color.WHITE);
        resume.setOnClickListener((v) -> {
            Intent temp = new Intent(this, ComplexityActivity.class);

            Intent preIntent = getIntent();
            Bundle bundle = preIntent.getExtras();
            if (bundle != null) {

                temp.putExtra("currGameName", bundle.getString("currGameName"));

                System.out.println("put extra successfully in StartingActivity");

            }

            startActivity(temp);
            finish();

        });
    }

    /**
     * Display that a game was resumed successfully.
     */
    private void makeToastResumeText() {
        Toast.makeText(this, "Game resumed", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that a game was fail to resume.
     */
    private void makeToastResumeFailText() {
        Toast.makeText(this, "Resumed Failed: Cannot find the game", Toast.LENGTH_SHORT).show();
    }


    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Switch to the Activity class of current game to play the game.
     */
    private void switchToGame() {
        FileManager.saveGame(this.getApplicationContext(), "Auto");
        String gameName = DataManager.INSTANCE.getCurrentGameName();
        Intent temp;
        if ("ST".equals(gameName)) {
            temp = new Intent(this, GameActivity.class);
        } else if ("CM".equals(gameName)) {
            temp = new Intent(this, ColorMatchingGameActivity.class);
        } else {
            temp = new Intent(this, FlipToWinGameActivity.class);
        }
        startActivity(temp);
    }

//    /**
//     * Switch to the ComplexityActivity view to choose a game level.
//     */
//    private void switchToComplexity() {
//        Intent temp = new Intent(this, ComplexityActivity.class);
//        startActivity(temp);
//        finish();
//    }

    /**
     * Override onBackPressed method, so that when touch back button it goes back to the
     * SelectGameActivity view to choose the game level.
     */
    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, SelectGameActivity.class);
        startActivity(temp);
    }

}
