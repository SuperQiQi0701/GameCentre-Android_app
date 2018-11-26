package Basic;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;


import fall2018.csc2017.slidingtiles.R;

public class ScoreBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        ScoreBoard scoreBoard = FileManager.loadScoreBoard(this.getApplicationContext());

        //modify the TextView of the myScore
        TextView myTextView = findViewById(R.id.myScore);


//        String myScore = Integer.toString(INSTANCE.getFlipToWinBoardManager().getScore());
//        int myRank = Main.INSTANCE.getFlipToWinScoreBoard().getMyBestRank(myRecord);
//        String myScoreToString = "You totally take " + myScore + " steps and your best rank is "
//                + myRank + ".";

//        String noScore = "You don't have a current score because you have not won the current game " +
//                "yet.";

        if (DataManager.INSTANCE.getBoardManager().puzzleSolved()) {

            Record myRecord = new Record();
            scoreBoard.addNewRecords(myRecord);
            FileManager.saveToFile(this.getApplicationContext(), scoreBoard, "SB");
            String myScore = Integer.toString(DataManager.INSTANCE.getBoardManager().getScore());
            int myRank = scoreBoard.getMyBestRank(myRecord);
            String myScoreToString = "You totally take " + myScore + " steps and your best rank is "
                    + myRank + ".";
            myTextView.setText(myScoreToString);
            System.out.println("run if");

        } else {

            System.out.println("run else");
            Bundle extras = getIntent().getExtras();
            int complexity;

            complexity = extras.getInt("complexity");
            // and get whatever type user account id is

            String noScore = "You don't have a current score because you have not won the current game " +
                    "yet.";
            myTextView.setText(noScore);
            scoreBoard.setComplexity(complexity);
        }
        myTextView.setTextColor(Color.RED);

        myTextView.setTextColor(Color.RED);

        //set the TextView for the first five record.
        ArrayList topFive = scoreBoard.TopFiveToString();
//        String noScore = "You don't have a current score because you have not won the current game " +
//                "yet.";
        TextView no1TextView = findViewById(R.id.no1Record);
        no1TextView.setText((String) topFive.get(0));

        TextView no2TextView = findViewById(R.id.no2Record);
        no2TextView.setText((String) topFive.get(1));

        TextView no3TextView = findViewById(R.id.no3Record);
        no3TextView.setText((String) topFive.get(2));

        TextView no4TextView = findViewById(R.id.no4Record);
        no4TextView.setText((String) topFive.get(3));

        TextView no5TextView = findViewById(R.id.no5Record);
        no5TextView.setText((String) topFive.get(4));

        //modify the TextView of each text box
        TextView titleTextView = findViewById(R.id.ScoreBoardTitle);
        String title = "Scoreboard";
        titleTextView.setText(title);
        titleTextView.setTextColor(Color.BLACK);

        TextView TextView01 = findViewById(R.id.no1);
        String the1 = "No.1";
        TextView01.setText(the1);
        TextView01.setTextColor(Color.RED);

        TextView TextView02 = findViewById(R.id.no2);
        String the2 = "No.2";
        TextView02.setText(the2);
        TextView02.setTextColor(Color.GREEN);

        TextView TextView03 = findViewById(R.id.no3);
        String the3 = "No.3";
        TextView03.setText(the3);
        TextView03.setTextColor(Color.CYAN);

        TextView TextView04 = findViewById(R.id.no4);
        String the4 = "No.4";
        TextView04.setText(the4);
        TextView04.setTextColor(Color.BLUE);

        TextView TextView05 = findViewById(R.id.no5);
        String the5 = "No.5";
        TextView05.setText(the5);
        TextView05.setTextColor(Color.MAGENTA);
    }

    @Override
    public void onBackPressed() {
        if (DataManager.INSTANCE.getBoardManager().puzzleSolved()) {
            System.out.println("solved");
            finish();
            Intent temp = new Intent(this, SelectGameActivity.class);
            startActivity(temp);
        } else {
            System.out.println("not solved");
            finish();
            Intent temp = new Intent(this, StartingActivity.class);
            startActivity(temp);
        }
    }
}
