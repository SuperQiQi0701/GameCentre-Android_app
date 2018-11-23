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
        complexity1Button.setOnClickListener((v) -> {
            Main.INSTANCE.startNewFlipToWinGame(4);
            String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
            Main.INSTANCE.saveFlipToWinBoardManagerToFile(this.getApplicationContext(), fileName);
            Intent temp = new Intent(this, FlipToWinGameActivity.class);
            startActivity(temp);
            finish();
        });
    }


    /**
     * Activate the Level 2 button.
     */
    private void setupComplexity2ButtonListener() {
        Button complexity2Button = findViewById(R.id.fliptowin_complexity2Button);
        complexity2Button.setBackgroundColor(Color.YELLOW);
        complexity2Button.setTextColor(Color.BLACK);
        complexity2Button.setOnClickListener((v) -> {
            Main.INSTANCE.startNewFlipToWinGame(6);
            String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
            Main.INSTANCE.saveFlipToWinBoardManagerToFile(this.getApplicationContext(), fileName);
            Intent temp = new Intent(this, FlipToWinGameActivity.class);
            startActivity(temp);
            finish();
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, FlipToWinStartingActivity.class);
        startActivity(temp);
    }


}
