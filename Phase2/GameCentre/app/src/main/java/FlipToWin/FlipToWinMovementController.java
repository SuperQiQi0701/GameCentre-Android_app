package FlipToWin;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import Basic.Main;
import FlipToWin.FlipToWinScoreBoardActivity;


class FlipToWinMovementController {

    FlipToWinMovementController() {
    }

    /**
     * Process a tapMovement, change to ScoreBoardActivity view when the game is won
     */
    void processTapMovement(Context context, int position, boolean display) {

        int row = position /  Main.INSTANCE.getFlipToWinBoardManager().getGame().getColNum();
        int col = position %  Main.INSTANCE.getFlipToWinBoardManager().getGame().getColNum();

        if (Main.INSTANCE.getFlipToWinBoardManager().isValidTap(position)) {
//            Toast.makeText(context, "is valid", Toast.LENGTH_SHORT).show();
            Main.INSTANCE.getFlipToWinBoardManager().makeChange(position);
//            Toast.makeText(context, "does move", Toast.LENGTH_SHORT).show();
            if (Main.INSTANCE.getFlipToWinBoardManager().decisionMaking == -1) {
                Toast.makeText(context, "Wrong Decision !", Toast.LENGTH_SHORT).show();
            }
            else if (Main.INSTANCE.getFlipToWinBoardManager().decisionMaking == 1){
                Toast.makeText(context, "Correct Decision !", Toast.LENGTH_SHORT).show();
            }
            if (Main.INSTANCE.getFlipToWinBoardManager().puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                Intent temp = new Intent(context, FlipToWinScoreBoardActivity.class);
                temp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(temp);
            }
        }
        else if (Main.INSTANCE.getFlipToWinBoardManager().getGame().getGrid(row, col).isPaired()){
            Toast.makeText(context, "Already Solved", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Already FaceUp", Toast.LENGTH_SHORT).show();
        }
    }

}