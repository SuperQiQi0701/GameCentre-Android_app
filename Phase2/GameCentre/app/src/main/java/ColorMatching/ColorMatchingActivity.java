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

import Basic.CustomAdapter;
import Basic.Main;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.ScoreBoardActivity;

public class ColorMatchingActivity extends AppCompatActivity {

    int width, height;
    ColorBoardManager colorBoardManager = Main.INSTANCE.getColorBoardManager();
    ColorView colorView;

//    private ColorMatchingGestureDetectGridView colorGridView;


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

    public void checkWin(){
        if (Main.INSTANCE.getColorBoardManager().puzzleSolved()){
            Toast.makeText(this, "YOU WIN!", Toast.LENGTH_SHORT).show();
            Intent temp = new Intent(this, ColorMatchingScoreBoardActivity.class);
            startActivity(temp);
        }
    }

    public void draw(){
        colorView.view = new View(this) {
            protected void onDraw(Canvas canvas) {
                if (Main.INSTANCE.getColorBoardManager().colorBoard.getGrid(1, 1) == null){
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

    public void initData(){
        int complexity = Main.INSTANCE.getColorBoardManager().getGame().getComplexity();
        int width = getScreenWidth(this);
        this.width = width;
        height = width * 5 / 4;
        colorBoardManager = new ColorBoardManager(complexity);
        Main.INSTANCE.colorBoardManager = new ColorBoardManager(complexity);
        colorView = new ColorView();
        Main.INSTANCE.getColorBoardManager().board = new boolean[(complexity - 2) * 4 ][(complexity - 2) * 5 ];
        colorBoardManager.board = new boolean[(complexity - 2) * 4 ][(complexity - 2) * 5 ];
        colorView.setBoxSize(this.width / Main.INSTANCE.getColorBoardManager().board.length);
    }

    public void initView(){
        FrameLayout layoutGame = findViewById(R.id.layoutGame);
        draw();
        layoutGame.addView(colorView.view); }

    public static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        assert wm != null;
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public void drawNewBoard(Canvas canvas){
        for (int x = 0; x < Main.INSTANCE.getColorBoardManager().board.length; x++) {
            for (int y = 0; y < Main.INSTANCE.getColorBoardManager().board[x].length; y++) {
                int color = randomColor();
                colorView.drawBox(canvas, color, x, y);
                //存color
                Main.INSTANCE.getColorBoardManager().colorBoard.setGrid(x, y);
                Main.INSTANCE.getColorBoardManager().colorBoard.getGrid(x, y).setColor(color);
            }
        }
        getScore();
    }

    public void uploadBoard(Canvas canvas){
        for (int x = 0; x < Main.INSTANCE.getColorBoardManager().board.length; x++) {
            for (int y = 0; y < Main.INSTANCE.getColorBoardManager().board[x].length; y++) {
                int color = Main.INSTANCE.getColorBoardManager().colorBoard.getGrid(x, y).getColor();
                colorView.drawBox(canvas, color, x, y);
            }
        }
        getScore();
    }

    private void addUndoButtonListener(){
        Button undo = findViewById(R.id.undo);
        undo.setOnClickListener((v) -> {
            if (Main.INSTANCE.getColorBoardManager().undoAvailable()) {
                Main.INSTANCE.getColorBoardManager().undo();
                colorView.view.invalidate();

                makeToastUndoSuccessText();
            }
            else{
                makeToastUndoFailText();
            }
            String fileName = "Auto_" + Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
            Main.INSTANCE.saveColorBoardManagerToFile(this.getApplicationContext(), fileName);
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
            String fileName = Main.INSTANCE.getUserManager().getCurrentUser() + ".ser";
            Main.INSTANCE.saveColorBoardManagerToFile(this.getApplicationContext(), fileName);
            Main.INSTANCE.saveColorBoardManagerToFile(this.getApplicationContext(), "Auto_" + fileName);
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
        String score = "          " + Integer.toString(Main.INSTANCE.getColorBoardManager().getScore());
        currScoreTextView.setText(score);
    }

    private void addRedButtonListener() {
        Button redButton = findViewById(R.id.red);
        redButton.setOnClickListener((v) -> {
            Main.INSTANCE.getColorBoardManager().changeColor(Color.RED);
            colorView.view.invalidate();
            getScore();
            checkWin();
        });
    }

    private void addYellowButtonListener() {
        Button redButton = findViewById(R.id.yellow);
        redButton.setOnClickListener((v) -> {
            Main.INSTANCE.getColorBoardManager().changeColor(Color.YELLOW);
            colorView.view.invalidate();
            getScore();
            checkWin();
        });
    }

    private void addBlueButtonListener() {
        Button redButton = findViewById(R.id.blue);
        redButton.setOnClickListener((v) -> {
            Main.INSTANCE.getColorBoardManager().changeColor(Color.BLUE);
            colorView.view.invalidate();
            getScore();
            checkWin();
        });
    }

    private void addGREENButtonListener() {
        Button redButton = findViewById(R.id.green);
        redButton.setOnClickListener((v) -> {
            Main.INSTANCE.getColorBoardManager().changeColor(Color.GREEN);
            colorView.view.invalidate();
            getScore();
            checkWin();
        });
    }

    private void addGreyButtonListener() {
        Button redButton = findViewById(R.id.grey);
        redButton.setOnClickListener((v) -> {
            Main.INSTANCE.getColorBoardManager().changeColor(Color.GRAY);
            colorView.view.invalidate();
            getScore();
            checkWin();
        });
    }

    private void addScoreBoardListener(){
        Button scoreboard = findViewById(R.id.scoreboard);
        scoreboard.setOnClickListener((v) -> {
            Intent temp = new Intent(this, ColorMatchingScoreBoardActivity.class);
            startActivity(temp);
        });
    }

    public int randomColor(){
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
        Intent temp = new Intent(this, ColorMatchingStartActivity.class);
        startActivity(temp);
    }
}
