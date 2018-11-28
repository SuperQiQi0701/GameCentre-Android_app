package FlipToWin;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import Basic.DataManager;
import Basic.ScoreBoardActivity;


class FlipToWinMovementController {

    FlipToWinMovementController() {
    }

    /**
     * Process a tapMovement, change to ScoreBoardActivity view when the game is won
     */
    void processTapMovement(Context context, int position, boolean display) {

        FlipToWinBoardManager boardManager = (FlipToWinBoardManager) DataManager.INSTANCE.getBoardManager();
        int row = position /  ((FlipToWinBoard)boardManager.getGame()).getColNum();
        int col = position %  ((FlipToWinBoard)boardManager.getGame()).getColNum();

        if (boardManager.isValidTap(position)) {
            boardManager.makeChange(position);

            if (boardManager.isChosenTilesMatched()) {
                Toast.makeText(context, "Correct Decision !", Toast.LENGTH_SHORT).show();
            }

            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                Intent temp = new Intent(context, ScoreBoardActivity.class);
                temp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(temp);
            }
        }
        else if (((FlipToWinBoard) boardManager.getGame()).getGrid(row, col).isPaired()){
            Toast.makeText(context, "Already Solved", Toast.LENGTH_SHORT).show();
        }
        else if (boardManager.isFlippingTiles()) {
            Toast.makeText(context, "Flipping cards Right Now", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Already FaceUp", Toast.LENGTH_SHORT).show();
        }
    }

}