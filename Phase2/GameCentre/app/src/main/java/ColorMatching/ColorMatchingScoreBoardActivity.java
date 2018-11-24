package ColorMatching;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import Basic.Main;
import Basic.SelectGameActivity;
import fall2018.csc2017.slidingtiles.R;

import static Basic.Main.INSTANCE;

public class ColorMatchingScoreBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matching_score_board);
        Main.INSTANCE.newColorMatchingScoreBoard();
        Main.INSTANCE.loadColorMatchingScoreBoardFromFile(this.getApplicationContext());
        ColorMatchingRecord myRecord = new ColorMatchingRecord();
        Main.INSTANCE.getColorScoreBoard().addNewRecords(myRecord);
        Main.INSTANCE.saveColorMatchingScoreBoardToFile(this.getApplicationContext());

        //modify the TextView of the myScore
        TextView myTextView = findViewById(R.id.cm_myScore);
        String myScore = Integer.toString(INSTANCE.getColorBoardManager().getScore());
        int myRank = Main.INSTANCE.getColorScoreBoard().getMyBestRank(myRecord);

        String myScoreToString = "You totally take " + myScore + " steps and your best rank is "
                + myRank + ".";

        myTextView.setText(myScoreToString);
        myTextView.setTextColor(Color.RED);

        //set the TextView for the first five record.
        ArrayList topFive = INSTANCE.getColorScoreBoard().TopFiveToString();

        TextView no1TextView = findViewById(R.id.cm_no1Record);
        no1TextView.setText((String) topFive.get(0));

        TextView no2TextView = findViewById(R.id.cm_no2Record);
        no2TextView.setText((String) topFive.get(1));

        TextView no3TextView = findViewById(R.id.cm_no3Record);
        no3TextView.setText((String) topFive.get(2));

        TextView no4TextView = findViewById(R.id.cm_no4Record);
        no4TextView.setText((String) topFive.get(3));

        TextView no5TextView = findViewById(R.id.cm_no5Record);
        no5TextView.setText((String) topFive.get(4));

        //modify the TextView of each text box
        TextView titleTextView = findViewById(R.id.cm_ScoreBoardTitle);
        String title = "ColorMatchingScoreBoard";
        titleTextView.setText(title);
        titleTextView.setTextColor(Color.BLACK);

        TextView TextView01 = findViewById(R.id.cm_no1);
        String the1 = "No.1";
        TextView01.setText(the1);
        TextView01.setTextColor(Color.RED);

        TextView TextView02 = findViewById(R.id.cm_no2);
        String the2 = "No.2";
        TextView02.setText(the2);
        TextView02.setTextColor(Color.GREEN);

        TextView TextView03 = findViewById(R.id.cm_no3);
        String the3 = "No.3";
        TextView03.setText(the3);
        TextView03.setTextColor(Color.CYAN);

        TextView TextView04 = findViewById(R.id.cm_no4);
        String the4 = "No.4";
        TextView04.setText(the4);
        TextView04.setTextColor(Color.BLUE);

        TextView TextView05 = findViewById(R.id.cm_no5);
        String the5 = "No.5";
        TextView05.setText(the5);
        TextView05.setTextColor(Color.MAGENTA);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, SelectGameActivity.class);
        startActivity(temp);
    }
}
