package FlipToWin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Basic.DataManager;
import Basic.FileManager;
import Basic.CustomAdapter;
import Basic.StartingActivity;
import fall2018.csc2017.slidingtiles.R;

/**
 * The game activity.
 */
public class FlipToWinGameActivity extends AppCompatActivity implements Observer {

    /**
     * The buttons to display.
     */
    private ArrayList<Button> fTileButtons;

    // Grid View and calculated column height and width based on device size
    private FlipGestureDetectGridView flipGridView;
    private static int columnWidth2, columnHeight2;

    // Display
    public void display() {
        updateTileButtons();
        flipGridView.setAdapter(new CustomAdapter(fTileButtons, columnWidth2, columnHeight2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTileButtons(this);
        setContentView(R.layout.activity_flip_to_win_game);
        // Add View to activity
        flipGridView = findViewById(R.id.fliptowingrid);
        flipGridView.setNumColumns(((FlipToWinBoard) DataManager.INSTANCE.getBoardManager().getGame()).getColNum());
        DataManager.INSTANCE.getBoardManager().getGame().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        flipGridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        flipGridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = flipGridView.getMeasuredWidth();
                        int displayHeight = flipGridView.getMeasuredHeight();
                        FlipToWinBoard board = (FlipToWinBoard) DataManager.INSTANCE.getBoardManager().getGame();
                        columnWidth2 = displayWidth / board.getColNum();
                        columnHeight2 = displayHeight / board.getRowNum();

                        display();
                    }
                });
        addSaveGameButtonListener();
        addScoreTextViewListener();
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        fTileButtons = new ArrayList<>();

        FlipToWinBoard board = (FlipToWinBoard) DataManager.INSTANCE.getBoardManager().getGame();

        for (int i = 0; i != board.numGrids(); i++) {
                Button tmp = new Button(context);
                this.fTileButtons.add(tmp);
            }
        }



    /**
     * Update the background or frontpage on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        int nextPos = 0;
        for (Button b : fTileButtons) {
            FlipToWinBoard board = (FlipToWinBoard) DataManager.INSTANCE.getBoardManager().getGame();
            int row = nextPos /  board.getColNum();
            int col = nextPos %  board.getColNum();
            FlipToWinTile tile = board.getGrid(row, col);

            if (!(tile.facedUpStatus())) {
                b.setBackgroundResource(tile.getBackground());
                b.setText("");
            }
            else {
                b.setText(tile.getFrontPage());
                b.setTextSize(40);
                b.setBackgroundColor(Color.YELLOW);
            }
            nextPos++;
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        FileManager.saveGame(this.getApplicationContext(), "Auto");
    }

    /**
     * Dispatch onStop() to fragments.
     */
    @Override
    protected void onStop() {
        super.onStop();
        FileManager.saveGame(this.getApplicationContext(), "Auto");
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
        addScoreTextViewListener();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, StartingActivity.class);
        startActivity(temp);
    }

    /**display
     * GetScore of the state.
     */
    void addScoreTextViewListener() {
        TextView currScoreTextView = findViewById(R.id.fliptowin_currScoreText);
        String score = Integer.toString(DataManager.INSTANCE.getBoardManager().getScore());
        currScoreTextView.setText(score);
    }

    /**
     * Activate SaveGame button.
     */
    private void addSaveGameButtonListener() {
        Button save = findViewById(R.id.fliptowin_saveGameButton);
        save.setOnClickListener(v -> {
            FileManager.saveGame(this.getApplicationContext(), "Auto");
            FileManager.saveGame(this.getApplicationContext(), "Save");
            makeToastSavedText();
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }


}
