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
import Basic.DataManager;
import Basic.FileManager;
import Basic.ScoreBoardActivity;
import Basic.StartingActivity;
import fall2018.csc2017.slidingtiles.R;

public class ColorMatchingGameActivity extends AppCompatActivity {

    int width, height;
    static ColorView colorView;
    int complexity;
    int[] color_button = {R.id.red, R.id.yellow, R.id.blue, R.id.green, R.id.grey};
    int[] colors = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY};

    public static ColorView getColorView(){
        return colorView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matching_game);
        initData();
        initView();
        int position = 0;
        while (position < 5){
            addColorButtonListener(position);
            position++;
        }
        addUndoButtonListener();
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
//                if ((DataManager.INSTANCE.getBoardManager().getGame()).getGrid(1, 1) == null){
//                    colorBoard.createNewBoard(canvas);
//                }else{uploadBoard(canvas);}

                createTiles(canvas);

                //draw line
                colorView.drawLine(canvas);
            }
        };
        colorView.view.setLayoutParams(new FrameLayout.LayoutParams(width,height));
        colorView.view.setBackgroundColor(0x10000000);
        getScore();
    }

    void createTiles(Canvas canvas) {
        ColorBoard board = (ColorBoard) DataManager.INSTANCE.getBoardManager().getGame();
        for (int row = 0; row < board.getRowNum(); row++) {
            for (int col = 0; col < board.getColNum(); col++) {
                int color = board.getGrid(row, col).getColor();
                ColorMatchingGameActivity.getColorView().drawBox(canvas, color, row, col);
                //存color
//                board.getGrid(row, col).setColor(color);
            }
        }
    }

    private void initData(){
        complexity = DataManager.INSTANCE.getBoardManager().getComplexity();
        int width = getScreenWidth(this);
        this.width = width;
        height = width * 5 / 4;
//        DataManager.INSTANCE.startNewGame(complexity);
        colorView = new ColorView();
//        ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).setBoard(complexity);
        colorView.setBoxSize(this.width / ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getGame().getTiles().length);
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

//    private void uploadBoard(Canvas canvas){
//        for (int x = 0; x < ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getGame().getTiles().length; x++) {
//            for (int y = 0; y < ((ColorBoardManager) DataManager.INSTANCE.getBoardManager()).getGame().getTiles()[x].length; y++) {
//                int color = ((ColorTile) DataManager.INSTANCE.getBoardManager().getGame().getGrid(x, y)).getColor();
//                colorView.drawBox(canvas, color, x, y);
//            }
//        }
//        getScore();
//    }

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
        String score = "          " + "Score:   "+ Integer.toString(DataManager.INSTANCE.getBoardManager().getScore());
        currScoreTextView.setText(score);
    }


    private void addColorButtonListener(int color){
        Button colorButton = findViewById(color_button[color]);
        colorButton.setOnClickListener((v) -> {
            DataManager.INSTANCE.getBoardManager().makeChange(colors[color]);
            colorView.view.invalidate();
            getScore();
            checkWin();
        });
    }

    @Override
    public void onBackPressed() {
        Intent temp = new Intent(this, StartingActivity.class);
        startActivity(temp);
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

}
