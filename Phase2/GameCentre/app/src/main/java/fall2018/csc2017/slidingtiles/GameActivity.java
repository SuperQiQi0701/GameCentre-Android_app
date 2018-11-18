package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * The game activity.
 */
public class GameActivity extends AppCompatActivity implements Observer {

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
        Main.INSTANCE.loadBoardManagerFromFile(this.getApplicationContext(), fileName);
        createTileButtons(this);
        setContentView(R.layout.activity_main);
        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(Main.INSTANCE.getBoardManager().getGame().getComplexity());
        Main.INSTANCE.getBoardManager().getGame().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / Main.INSTANCE.getBoardManager().getGame().getComplexity();
                        columnHeight = displayHeight / Main.INSTANCE.getBoardManager().getGame().getComplexity();

                        display();
                    }
                });
        addSaveGameButtonListener();
        addUndoButtonListener();
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        Board board = Main.INSTANCE.getBoardManager().getGame();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != Main.INSTANCE.getBoardManager().getGame().getComplexity(); row++) {
            for (int col = 0; col != Main.INSTANCE.getBoardManager().getGame().getComplexity(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getGrid(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / Main.INSTANCE.getBoardManager().getGame().getComplexity();
            int col = nextPos % Main.INSTANCE.getBoardManager().getGame().getComplexity();
            b.setBackgroundResource(Main.INSTANCE.getBoardManager().getGame().getGrid(row, col).getBackground());
            nextPos++;
        }
        getScore();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
        Main.INSTANCE.saveBoardManagerToFile(this.getApplicationContext(), fileName);
    }

    /**
     * Dispatch onStop() to fragments.
     */
    @Override
    protected void onStop() {
        super.onStop();
        String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
        Main.INSTANCE.saveBoardManagerToFile(this.getApplicationContext(), fileName);
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, StartingActivity.class);
        startActivity(temp);
    }

    /**
     * GetScore of the state.
     */
    void getScore() {
        TextView currScoreTextView = findViewById(R.id.currScoreText);
        String score = Integer.toString(Main.INSTANCE.getBoardManager().getScore());
        currScoreTextView.setText(score);
    }

    /**
     * Activate SaveGame button.
     */
    private void addSaveGameButtonListener() {
        Button save = findViewById(R.id.saveGameButton);
        save.setOnClickListener(v -> {
            String fileName = Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
            Main.INSTANCE.saveBoardManagerToFile(GameActivity.this.getApplicationContext(), fileName);
            Main.INSTANCE.saveBoardManagerToFile(GameActivity.this.getApplicationContext(), "Auto_" + fileName);
            makeToastSavedText();
        });
    }

    /**
     * Activate Undo button.
     */
    private void addUndoButtonListener() {
        Button undo = findViewById(R.id.undoButton);
        undo.setOnClickListener((v) -> {
            if (Main.INSTANCE.getBoardManager().undoAvailable()) {
                Main.INSTANCE.getBoardManager().undo();
                makeToastUndoSuccessText();
            } else {
                makeToastUndoFailText();
            }
            String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
            Main.INSTANCE.saveBoardManagerToFile(this.getApplicationContext(), fileName);
            getScore();
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that a game was undo successfully.
     */
    private void makeToastUndoSuccessText() {
        Toast.makeText(this, "Undo successfully", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that a game was failed to undo.
     */
    private void makeToastUndoFailText() {
        Toast.makeText(this, "Undo failed: Cannot undo in this state.", Toast.LENGTH_SHORT).show();
    }

}
