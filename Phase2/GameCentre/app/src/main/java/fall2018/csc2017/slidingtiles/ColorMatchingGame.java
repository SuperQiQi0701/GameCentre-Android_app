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

public class ColorMatchingGame extends AppCompatActivity {

    View view;
    int width, height;
    Paint linePaint;
    Paint boxPaint;

    boolean[][] board;
    Point[] box;
    ColorBoard colorBoard;
    int boxSize;
    int firstColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matching_game);
        initData();
        initView();
//        addRedButtonListener();
    }

    public void initData(){
        int width = getScreenWidth(this);
        this.width = width;
        height = width*5/4;
        board = new boolean[8][10];
        box = new Point[]{new Point(0,0)};
        boxSize = this.width / board.length;
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
                drawNewBoard(canvas);
                //draw line
                for (int x = 0; x < board.length; x++) {
                    canvas.drawLine(x * boxSize, 0, x * boxSize, view.getHeight(), linePaint);
                }
                for (int y = 0; y < board[0].length; y++) {
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
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                int color = randomColor();
                drawBox(canvas, color, x, y);
                //存color
//                colorBoard.getGrid(x, y).setColor(color);
            }
        }
    }

//    private void addRedButtonListener() {
//        Button redButton = findViewById(R.id.red);
//        redButton.setOnClickListener(v -> changeColor(Color.RED));
//        view.invalidate();
//    }

    //new
    //change color
//    public void changeColor(int newColor){
//        int initColor = colorBoard.getGrid(0, 0).getColor();
//        int y = 1;
//        while(colorBoard.getGrid(0, y).getColor() == initColor && y < board[0].length){
//            y++;
//        }
//        while(colorBoard.getGrid(0, y).getColor() == newColor && y < board[0].length) {
//            y++;
//        }
//        for(int i = 0; i < y; i++) {
//            colorBoard.getGrid(0, i).setColor(newColor);
//        }
//    }



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
