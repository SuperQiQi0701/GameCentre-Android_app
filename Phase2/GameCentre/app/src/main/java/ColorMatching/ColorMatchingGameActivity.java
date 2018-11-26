package ColorMatching;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import Basic.DataManager;
import Basic.FileManager;
import Basic.ScoreBoardActivity;
import Basic.StartingActivity;
import fall2018.csc2017.slidingtiles.R;

public class ColorMatchingGameActivity extends AppCompatActivity {

    int width, height;
    ColorView colorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matching_game);
        initData();
        initView();
        addRedButtonListener();
        addYellowButtonListener();
        addBlueButtonListener();
        addGREENButtonListener();
        addGreyButtonListener();
        addUndoButtonListener();
        addScoreBoardListener();
        addSaveGameButtonListener();
    }

    private void checkWin(){
        if (DataManager.INSTANCE.getBoardManager().puzzleSolved()){
            Toast.makeText(this, "YOU WIN!", Toast.LENGTH_SHORT).show();
            Intent temp = new Intent(this, ScoreBoardActivity.class);
            startActivity(temp);
        }
    }

    private void draw(){
        colorView.view = new View(this) {
            protected void onDraw(Canvas canvas) {
                if ((DataManager.INSTANCE.getBoardManager().getGame()).getGrid(1, 1) == null){
                    drawNewBoard(canvas);
                }else{uploadBoard(canvas);}
                //draw line
                colorView.drawLine(canvas);
            }
        };
        colorView.view.setLayoutParams(new FrameLayout.LayoutParams(width,height));
        colorView.view.setBackgroundColor(0x10000000);
        getScore();
    }

    private void initData(){
            int complexity = DataManager.INSTANCE.getBoardManager().getComplexity();
        int width = getScreenWidth(this);
        this.width = width;
        height = width * 5 / 4;
        DataManager.INSTANCE.startNewGame(complexity);
        colorView = new ColorView();
        ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).setBoard(new boolean[(complexity - 2) * 4 ][(complexity - 2) * 5 ]);
        colorView.setBoxSize(this.width / ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getBoard().length);
    }

    private void initView(){
        FrameLayout layoutGame = findViewById(R.id.layoutGame);
        draw();
        layoutGame.addView(colorView.view); }

    private static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        assert wm != null;
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    private void drawNewBoard(Canvas canvas){
        for (int x = 0; x < ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getBoard().length; x++) {
            for (int y = 0; y < ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getBoard()[x].length; y++) {
                int color = randomColor();
                colorView.drawBox(canvas, color, x, y);
                //存color
                (DataManager.INSTANCE.getBoardManager().getGame()).setGrid(1, 1);
                (DataManager.INSTANCE.getBoardManager().getGame()).getGrid(x, y).setColor(color);
            }
        }
        getScore();
    }

    private void uploadBoard(Canvas canvas){
        for (int x = 0; x < ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getBoard().length; x++) {
            for (int y = 0; y < ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getBoard()[x].length; y++) {
                int color = DataManager.INSTANCE.getBoardManager().getGame().getGrid(x, y).getColor();
                colorView.drawBox(canvas, color, x, y);
            }
        }
        getScore();
    }

    private void addUndoButtonListener(){
        Button undo = findViewById(R.id.undo);
        undo.setOnClickListener((v) -> {
            ColorBoardManager boardManager = (ColorBoardManager) DataManager.INSTANCE.getBoardManager();
            if (boardManager.undoAvailable()) {
                boardManager.undo();
                colorView.view.invalidate();

                makeToastUndoSuccessText();
            }
            else{
                makeToastUndoFailText();
            }
            FileManager.saveGame(this.getApplicationContext(), "Auto");
            getScore();
        });
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

    /**
     * Activate SaveGame button.
     */
    private void addSaveGameButtonListener() {
        Button save = findViewById(R.id.save);
        save.setOnClickListener(v -> {
            FileManager.saveGame(this.getApplicationContext(), "Save");
            FileManager.saveGame(this.getApplicationContext(), "Auto");
            makeToastSavedText();
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * GetScore of the state.
     */
    void getScore() {
        TextView currScoreTextView = findViewById(R.id.color_match_currScoreText);
        String score = "          " + Integer.toString(DataManager.INSTANCE.getBoardManager().getScore());
        currScoreTextView.setText(score);
    }

    private void addRedButtonListener() {
        Button redButton = findViewById(R.id.red);
        redButton.setOnClickListener((v) -> {
            DataManager.INSTANCE.getBoardManager().makeChange(Color.RED);
            colorView.view.invalidate();
            getScore();
            checkWin();
        });
    }

    private void addYellowButtonListener() {
        Button redButton = findViewById(R.id.yellow);
        redButton.setOnClickListener((v) -> {
            DataManager.INSTANCE.getBoardManager().makeChange(Color.YELLOW);
            colorView.view.invalidate();
            getScore();
            checkWin();
        });
    }

    private void addBlueButtonListener() {
        Button redButton = findViewById(R.id.blue);
        redButton.setOnClickListener((v) -> {
            DataManager.INSTANCE.getBoardManager().makeChange(Color.BLUE);
            colorView.view.invalidate();
            getScore();
            checkWin();
        });
    }

    private void addGREENButtonListener() {
        Button redButton = findViewById(R.id.green);
        redButton.setOnClickListener((v) -> {
            DataManager.INSTANCE.getBoardManager().makeChange(Color.GREEN);
            colorView.view.invalidate();
            getScore();
            checkWin();
        });
    }

    private void addGreyButtonListener() {
        Button redButton = findViewById(R.id.grey);
        redButton.setOnClickListener((v) -> {
            DataManager.INSTANCE.getBoardManager().makeChange(Color.GRAY);
            colorView.view.invalidate();
            getScore();
            checkWin();
        });
    }

    private void addScoreBoardListener(){
        Button scoreboard = findViewById(R.id.scoreboard);
        scoreboard.setOnClickListener((v) -> {
            Intent temp = new Intent(this, ScoreBoardActivity.class);
            startActivity(temp);
        });
    }

    private int randomColor(){
        Random random = new Random();
        int color = random.nextInt(5);
        switch(color){
            case 0:
                color =  Color.RED;
                break;
            case 1:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.YELLOW;
                break;
            case 3:
                color = Color.BLUE;
                break;
            case 4:
                color = Color.GRAY;
                break;
        }
        return color;
    }

    @Override
    public void onBackPressed() {
        Intent temp = new Intent(this, StartingActivity.class);
        startActivity(temp);
    }
}
