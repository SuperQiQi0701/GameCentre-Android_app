//package ColorMatching;
//
//import android.annotation.SuppressLint;
//import android.support.v7.app.AppCompatActivity;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.widget.Button;
//
//import Basic.Main;
//
//import Basic.SelectGameActivity;
//import fall2018.csc2017.slidingtiles.R;
//
//public class ColorMatchingComplexityActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_color_matching_complexity);
//        setupComplexity1ButtonListener();
//        setupComplexity2ButtonListener();
//        setupComplexity3ButtonListener();
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//
//
//    /**
//     * Activate the Level 1 button.
//     */
//    private void setupComplexity1ButtonListener() {
//        Button complexity1Button = findViewById(R.id.color_complexity1_button);
//        complexity1Button.setBackgroundColor(Color.GREEN);
//        complexity1Button.setTextColor(Color.BLACK);
//        complexity1Button.setOnClickListener((v) -> setUpGame(3));
//
//    }
//
//
//    /**
//     * Activate the Level 2 button.
//     */
//    private void setupComplexity2ButtonListener() {
//        Button complexity2Button = findViewById(R.id.color_complexity2_button);
//        complexity2Button.setBackgroundColor(Color.YELLOW);
//        complexity2Button.setTextColor(Color.BLACK);
//        complexity2Button.setOnClickListener((v) -> setUpGame(4));
//    }
//
//
//    /**
//     * Activate the Level 1 button.
//     */
//    private void setupComplexity3ButtonListener() {
//        Button complexity3Button = findViewById(R.id.color_complexity3_button);
//        complexity3Button.setBackgroundColor(Color.RED);
//        complexity3Button.setTextColor(Color.BLACK);
//        complexity3Button.setOnClickListener((v) -> setUpGame(5));
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        finish();
//        Intent temp = new Intent(this, SelectGameActivity.class);
//        startActivity(temp);
//    }
//
//    private void setUpGame(int complexity) {
//        Main.INSTANCE.startNewColorMatchingGame(complexity);
//        String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
//        Main.INSTANCE.saveColorBoardManagerToFile(this.getApplicationContext(), fileName);
//        Intent temp = new Intent(this, ColorMatchingGameActivity.class);
//        startActivity(temp);
//        finish();
//    }
//
//}
//
