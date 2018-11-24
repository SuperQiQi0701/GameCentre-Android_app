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

import java.util.Random;

import Basic.CustomAdapter;
import Basic.Main;
import fall2018.csc2017.slidingtiles.R;

public class ColorMatchingActivity extends AppCompatActivity {

    int width, height;
//    ColorBoardManager colorBoardManager = Main.INSTANCE.getColorBoardManager();
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
        int width = getScreenWidth(this);
        this.width = width;
        height = width*5/4;
        Main.INSTANCE.colorBoardManager = new ColorBoardManager(5);
        colorView = new ColorView();
        Main.INSTANCE.getColorBoardManager().board = new boolean[8][10];
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
            Main.INSTANCE.getColorBoardManager().undo();
            colorView.view.invalidate();
            getScore();
        });

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
        });
    }

    private void addYellowButtonListener() {
        Button redButton = findViewById(R.id.yellow);
        redButton.setOnClickListener((v) -> {
            Main.INSTANCE.getColorBoardManager().changeColor(Color.YELLOW);
            colorView.view.invalidate();
            getScore();
        });
    }

    private void addBlueButtonListener() {
        Button redButton = findViewById(R.id.blue);
        redButton.setOnClickListener((v) -> {
            Main.INSTANCE.getColorBoardManager().changeColor(Color.BLUE);
            colorView.view.invalidate();
            getScore();
        });
    }

    private void addGREENButtonListener() {
        Button redButton = findViewById(R.id.green);
        redButton.setOnClickListener((v) -> {
            Main.INSTANCE.getColorBoardManager().changeColor(Color.GREEN);
            colorView.view.invalidate();
            getScore();
        });
    }

    private void addGreyButtonListener() {
        Button redButton = findViewById(R.id.grey);
        redButton.setOnClickListener((v) -> {
            Main.INSTANCE.getColorBoardManager().changeColor(Color.GRAY);
            colorView.view.invalidate();
            getScore();
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
}
