package ColorMatching;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.io.Serializable;

public class ColorView implements Serializable {

    View view;
    private int boxSize;
    private ColorBoardManager colorBoardManager;

    ColorView(){
        colorBoardManager = new ColorBoardManager(5);
    }


    void drawBox(Canvas canvas, int color, int x, int y){
        Paint boxPaint = new Paint();
        boxPaint.setAntiAlias(true);

        //draw box
        boxPaint.setColor(color);
        canvas.drawRect(x * boxSize, y * boxSize, x * boxSize + boxSize, y * boxSize + boxSize, boxPaint);
    }

    void drawLine(Canvas canvas){
        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setAntiAlias(true);

        for (int x = 0; x < colorBoardManager.board.length; x++) {
            canvas.drawLine(x * boxSize, 0, x * boxSize, view.getHeight(), linePaint);
        }
        for (int y = 0; y < colorBoardManager.board[0].length; y++) {
            canvas.drawLine(0, y * boxSize, view.getWidth(), y * boxSize, linePaint);
        }
    }

    void setBoxSize(int size){
        boxSize = size;
    }
}
