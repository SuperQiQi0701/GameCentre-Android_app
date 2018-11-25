package FlipToWin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import Basic.Main;
import fall2018.csc2017.slidingtiles.R;
import Basic.SelectGameActivity;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class FlipToWinStartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_to_win_starting);
        addStartButtonListener();
        addLoadButtonListener();
        addResumeButtonListener();
        viewScoreBoardButtonListener();
    }


    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.fliptowin_StartButton);
        startButton.setBackgroundColor(Color.DKGRAY);
        startButton.setTextColor(Color.WHITE);
        startButton.setOnClickListener(v -> switchToComplexity());
    }

    private void viewScoreBoardButtonListener() {
        Button startButton = findViewById(R.id.fliptowin_scoreBoard);
        startButton.setBackgroundColor(Color.DKGRAY);
        startButton.setTextColor(Color.WHITE);
        startButton.setOnClickListener(v -> {
            Intent temp = new Intent(this, FlipToTileViewScoreBoardActivity.class);
            startActivity(temp);
            finish();
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.fliptowin_LoadButton);
        loadButton.setBackgroundColor(Color.DKGRAY);
        loadButton.setTextColor(Color.WHITE);
        loadButton.setOnClickListener(v -> {
            String fileName = Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
            if (new File(FlipToWinStartingActivity.this.getFilesDir() + "/" + fileName).exists()) {
                Main.INSTANCE.loadFlipToWinBoardManagerFromFile(FlipToWinStartingActivity.this.getApplicationContext(), fileName);
                Main.INSTANCE.saveFlipToWinBoardManagerToFile(FlipToWinStartingActivity.this.getApplicationContext(),
                        "Auto_" + fileName);
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
        Button resume = findViewById(R.id.fliptowin_resumeButton);
        resume.setBackgroundColor(Color.DKGRAY);
        resume.setTextColor(Color.WHITE);
        resume.setOnClickListener((v) -> {
            String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
            if (new File(FlipToWinStartingActivity.this.getFilesDir() + "/" + fileName).exists()) {
                Main.INSTANCE.loadFlipToWinBoardManagerFromFile(this.getApplicationContext(), fileName);
                makeToastResumeText();
                Intent temp = new Intent(this, FlipToWinGameActivity.class);
                startActivity(temp);
            } else {
                makeToastResumeFailText();
            }

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
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, FlipToWinGameActivity.class);
        String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
        Main.INSTANCE.saveFlipToWinBoardManagerToFile(this.getApplicationContext(), fileName);
        startActivity(tmp);
    }

    /**
     * Switch to the ComplexityActivity view to choose a game level.
     */
    private void switchToComplexity() {
        Intent temp = new Intent(this, FlipToWinComplexityActivity.class);
        startActivity(temp);
        finish();
    }

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
