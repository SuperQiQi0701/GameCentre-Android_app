package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.Random;

public class ColorMatchingActivity extends AppCompatActivity {

    View view;
    int width, height;
    Paint linePaint;
    Paint boxPaint;

    Point[] box;
    ColorBoardManager colorBoardManager;
    int boxSize;

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
    }

    public void initData(){
        int width = getScreenWidth(this);
        this.width = width;
        height = width*5/4;
        box = new Point[]{new Point(0,0)};
        colorBoardManager = new ColorBoardManager(5);
        colorBoardManager.board = new boolean[8][10];
        boxSize = this.width / colorBoardManager.board.length;
    }

    public void initView(){
        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setAntiAlias(true);

        boxPaint = new Paint();
        boxPaint.setAntiAlias(true);

        FrameLayout layoutGame = (FrameLayout) findViewById(R.id.layoutGame);

        view = new View(this) {
            protected void onDraw(Canvas canvas) {
                if (colorBoardManager.colorBoard.getGrid(1, 1) == null){
                    drawNewBoard(canvas);
                }else{uploadBoard(canvas);}
                //draw line
                for (int x = 0; x < colorBoardManager.board.length; x++) {
                    canvas.drawLine(x * boxSize, 0, x * boxSize, view.getHeight(), linePaint);
                }
                for (int y = 0; y < colorBoardManager.board[0].length; y++) {
                    canvas.drawLine(0, y * boxSize, view.getWidth(), y * boxSize, linePaint);
                }
            }
        };
        view.setLayoutParams(new FrameLayout.LayoutParams(width,height));
        view.setBackgroundColor(0x10000000);
        layoutGame.addView(view);
        }

    public static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public void drawNewBoard(Canvas canvas){
        for (int x = 0; x < colorBoardManager.board.length; x++) {
            for (int y = 0; y < colorBoardManager.board[x].length; y++) {
                int color = randomColor();
                drawBox(canvas, color, x, y);
                //存color
                colorBoardManager.colorBoard.setGrid(x, y);
                colorBoardManager.colorBoard.getGrid(x, y).setColor(color);
            }
        }
    }

    public void uploadBoard(Canvas canvas){
        for (int x = 0; x < colorBoardManager.board.length; x++) {
            for (int y = 0; y < colorBoardManager.board[x].length; y++) {
                int color = colorBoardManager.colorBoard.getGrid(x, y).getColor();
                drawBox(canvas, color, x, y);
            }
        }
    }

    private void addRedButtonListener() {
        Button redButton = findViewById(R.id.red);
        redButton.setOnClickListener((v) -> {
            colorBoardManager.changeColor(Color.RED);
            view.invalidate();
        });
    }

    private void addYellowButtonListener() {
        Button redButton = findViewById(R.id.yellow);
        redButton.setOnClickListener((v) -> {
            colorBoardManager.changeColor(Color.YELLOW);
            view.invalidate();
        });
    }

    private void addBlueButtonListener() {
        Button redButton = findViewById(R.id.blue);
        redButton.setOnClickListener((v) -> {
            colorBoardManager.changeColor(Color.BLUE);
            view.invalidate();
        });
    }

    private void addGREENButtonListener() {
        Button redButton = findViewById(R.id.green);
        redButton.setOnClickListener((v) -> {
            colorBoardManager.changeColor(Color.GREEN);
            view.invalidate();
        });
    }

    private void addGreyButtonListener() {
        Button redButton = findViewById(R.id.grey);
        redButton.setOnClickListener((v) -> {
            colorBoardManager.changeColor(Color.GRAY);
            view.invalidate();
        });
    }

    public void drawBox(Canvas canvas, int color, int x, int y){
        //draw box
        boxPaint.setColor(color);
        canvas.drawRect(x * boxSize, y * boxSize, x * boxSize + boxSize, y * boxSize + boxSize, boxPaint);
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
