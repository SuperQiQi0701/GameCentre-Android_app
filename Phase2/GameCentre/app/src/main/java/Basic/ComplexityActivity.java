package Basic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import ColorMatching.ColorMatchingActivity;
import ColorMatching.ColorMatchingStartActivity;
import FlipToWin.FlipToWinGameActivity;
import FlipToWin.FlipToWinStartingActivity;
import fall2018.csc2017.slidingtiles.GameActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.StartingActivity;

public class ComplexityActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complexity);

        setupComplexity1ButtonListener();
        setupComplexity2ButtonListener();
        setupComplexity3ButtonListener();

    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        TextView tv = findViewById(R.id.complexityTitle);
        tv.setText("Choose A Level!");
        tv.setTextColor(Color.BLACK);
    }


    /**
     * Activate the Level 1 button.
     */
    private void setupComplexity1ButtonListener() {
        Button complexity1Button = findViewById(R.id.complexity1Button);
        complexity1Button.setBackgroundColor(Color.GREEN);
        complexity1Button.setTextColor(Color.BLACK);
        complexity1Button.setOnClickListener((v) -> setUpGame(3));

    }


    /**
     * Activate the Level 2 button.
     */
    private void setupComplexity2ButtonListener() {
        Button complexity2Button = findViewById(R.id.complexity2Button);
        complexity2Button.setBackgroundColor(Color.YELLOW);
        complexity2Button.setTextColor(Color.BLACK);
        complexity2Button.setOnClickListener((v) -> setUpGame(4));
    }


    /**
     * Activate the Level 1 button.
     */
    private void setupComplexity3ButtonListener() {
        Button complexity3Button = findViewById(R.id.complexity3Button);
        complexity3Button.setBackgroundColor(Color.RED);
        complexity3Button.setTextColor(Color.BLACK);
        complexity3Button.setOnClickListener((v) -> setUpGame(5));
    }


    @Override
    public void onBackPressed() {
        String gameName = DataManager.INSTANCE.getCurrentGameName();
        Intent temp;
        if ("ST".equals(gameName)) {
            temp = new Intent(this, StartingActivity.class);
        } else if ("CM".equals(gameName)) {
            temp = new Intent(this, ColorMatchingStartActivity.class);
        } else {
            temp = new Intent(this, FlipToWinStartingActivity.class);
        }
        finish();
        startActivity(temp);
    }

    private void setUpGame(int complexity) {
        DataManager.INSTANCE.startNewGame(complexity);
        FileManager.saveGame(this.getApplicationContext(), "Auto");
        String gameName = DataManager.INSTANCE.getCurrentGameName();
        Intent temp;
        if ("ST".equals(gameName)) {
            temp = new Intent(this, GameActivity.class);
        } else if ("CM".equals(gameName)) {
            temp = new Intent(this, ColorMatchingActivity.class);
        } else {
            temp = new Intent(this, FlipToWinGameActivity.class);
        }
        startActivity(temp);
        finish();
    }

}