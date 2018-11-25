package FlipToWin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import Basic.Main;
import FlipToWin.FlipToWinGameActivity;
import FlipToWin.FlipToWinStartingActivity;
import fall2018.csc2017.slidingtiles.R;

public class FlipToWinComplexityActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_to_win_complexity);

        setupComplexity1ButtonListener();
        setupComplexity2ButtonListener();
        setupComplexity3ButtonListener();
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        TextView tv = findViewById(R.id.fliptowin_complexityTitle);
        tv.setText("Choose A Level!");
        tv.setTextColor(Color.BLACK);
    }


    /**
     * Activate the Level 1 button.
     */
    private void setupComplexity1ButtonListener() {
        Button complexity1Button = findViewById(R.id.fliptowin_complexity1Button);
        complexity1Button.setBackgroundColor(Color.GREEN);
        complexity1Button.setTextColor(Color.BLACK);
        complexity1Button.setOnClickListener((v) -> setUpGame(3));
    }


    /**
     * Activate the Level 2 button.
     */
    private void setupComplexity2ButtonListener() {
        Button complexity2Button = findViewById(R.id.fliptowin_complexity2Button);
        complexity2Button.setBackgroundColor(Color.YELLOW);
        complexity2Button.setTextColor(Color.BLACK);
        complexity2Button.setOnClickListener((v) -> setUpGame(4));
    }

    /**
     * Activate the Level 3 button.
     */
    private void setupComplexity3ButtonListener() {
        Button complexity2Button = findViewById(R.id.fliptowin_complexity3Button);
        complexity2Button.setBackgroundColor(Color.GRAY);
        complexity2Button.setTextColor(Color.BLACK);
        complexity2Button.setOnClickListener((v) -> setUpGame(5));
    }


    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, FlipToWinStartingActivity.class);
        startActivity(temp);
    }

    private void setUpGame(int complexity) {
        Main.INSTANCE.startNewFlipToWinGame(complexity);
        String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
        Main.INSTANCE.saveFlipToWinBoardManagerToFile(this.getApplicationContext(), fileName);
        Intent temp = new Intent(this, FlipToWinGameActivity.class);
        startActivity(temp);
        finish();
    }
}
