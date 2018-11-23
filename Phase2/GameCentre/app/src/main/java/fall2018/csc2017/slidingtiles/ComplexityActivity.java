package fall2018.csc2017.slidingtiles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import Basic.Main;

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
        complexity1Button.setOnClickListener((v) -> {
            Main.INSTANCE.startNewGame(3);
            String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
            Main.INSTANCE.saveBoardManagerToFile(this.getApplicationContext(), fileName);
            Intent temp = new Intent(this, GameActivity.class);
            startActivity(temp);
            finish();
        });
    }


    /**
     * Activate the Level 2 button.
     */
    private void setupComplexity2ButtonListener() {
        Button complexity2Button = findViewById(R.id.complexity2Button);
        complexity2Button.setBackgroundColor(Color.YELLOW);
        complexity2Button.setTextColor(Color.BLACK);
        complexity2Button.setOnClickListener((v) -> {
            Main.INSTANCE.startNewGame(4);
            String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
            Main.INSTANCE.saveBoardManagerToFile(this.getApplicationContext(), fileName);
            Intent temp = new Intent(this, GameActivity.class);
            startActivity(temp);
            finish();
        });
    }


    /**
     * Activate the Level 1 button.
     */
    private void setupComplexity3ButtonListener() {
        Button complexity3Button = findViewById(R.id.complexity3Button);
        complexity3Button.setBackgroundColor(Color.RED);
        complexity3Button.setTextColor(Color.BLACK);
        complexity3Button.setOnClickListener((v) -> {
            Main.INSTANCE.startNewGame(5);
            String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
            Main.INSTANCE.saveBoardManagerToFile(this.getApplicationContext(), fileName);
            Intent temp = new Intent(this, GameActivity.class);
            startActivity(temp);
            finish();
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, StartingActivity.class);
        startActivity(temp);
    }


}
