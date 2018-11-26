package Basic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.slidingtiles.R;


public class SelectGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);
        addSlidingButtonListener();
        addColorMatchingListener();
        addFlipToWinListener();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        TextView tv = findViewById(R.id.gameCentre);
        tv.setText("  Game Centre");
        tv.setTextColor(Color.BLACK);
    }

    /**
     * Activate the SlidingTile button.
     */
    private void addSlidingButtonListener() {
        Button mSliding = findViewById(R.id.enter_sliding_button);
        mSliding.setBackgroundColor(Color.MAGENTA);
        mSliding.setTextColor(Color.YELLOW);
        mSliding.setOnClickListener(v -> {
            DataManager.INSTANCE.setCurrentGameName("ST");
            Intent slide = new Intent(this, StartingActivity.class);
            startActivity(slide);
        });
    }

    private void addColorMatchingListener() {
        Button mSliding = findViewById(R.id.enter_colormatching_button);
        mSliding.setBackgroundColor(Color.YELLOW);
        mSliding.setTextColor(Color.BLACK);
        mSliding.setOnClickListener(v -> {
            DataManager.INSTANCE.setCurrentGameName("CM");
            Intent slide = new Intent(this, StartingActivity.class);
            startActivity(slide);
        });
    }


    private void addFlipToWinListener() {
        Button mSliding = findViewById(R.id.enter_flipToTile_button);
        mSliding.setBackgroundColor(Color.CYAN);
        mSliding.setTextColor(Color.RED);
        mSliding.setOnClickListener(v -> {
            DataManager.INSTANCE.setCurrentGameName("FTW");
            Intent slide = new Intent(this, StartingActivity.class);
            startActivity(slide);
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, LoginActivity.class);
        startActivity(temp);
    }
}
