package FlipToWin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import Basic.DataManager;
import Basic.StartingActivity;
import fall2018.csc2017.slidingtiles.R;
import Basic.ScoreBoardActivity;

public class FlipToTileViewScoreBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_to_tile_view_score_board);

        viewLevel1ScoreBoardButtonListener();
        viewLevel2ScoreBoardButtonListener();
        viewLevel3ScoreBoardButtonListener();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        TextView tv = findViewById(R.id.view_scoreboard);
        tv.setText("To View A Scoreboard!");
        tv.setTextColor(Color.BLACK);
    }

    /**
     * Activate the Level 1 button.
     */
    private void viewLevel1ScoreBoardButtonListener() {
        Button complexity1Button = findViewById(R.id.level_1_scoreboard_button);
        complexity1Button.setBackgroundColor(Color.GREEN);
        complexity1Button.setTextColor(Color.BLACK);
        complexity1Button.setOnClickListener((v) -> setUpScoreBoard(3));
    }

    /**
     * Activate the Level 2 button.
     */
    private void viewLevel2ScoreBoardButtonListener() {
        Button complexity1Button = findViewById(R.id.level_2_scoreboard_button);
        complexity1Button.setBackgroundColor(Color.GREEN);
        complexity1Button.setTextColor(Color.BLACK);
        complexity1Button.setOnClickListener((v) -> setUpScoreBoard(4));
    }

    /**
     * Activate the Level 3 button.
     */
    private void viewLevel3ScoreBoardButtonListener() {
        Button complexity1Button = findViewById(R.id.level_3_scoreboard_button);
        complexity1Button.setBackgroundColor(Color.GREEN);
        complexity1Button.setTextColor(Color.BLACK);
        complexity1Button.setOnClickListener((v) -> setUpScoreBoard(5));
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, StartingActivity.class);
        startActivity(temp);
    }

    private void setUpScoreBoard(int complexity) {
        DataManager.INSTANCE.startNewGame(complexity);
        Intent temp = new Intent(this, ScoreBoardActivity.class);
        temp.putExtra("complexity", complexity);
        startActivity(temp);
        finish();
    }
}
