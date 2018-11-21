package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


class FlipToWinMovementController {

    FlipToWinMovementController() {
    }

    /**
     * Process a tapMovement, change to ScoreBoardActivity view when the game is won
     */
    void processTapMovement(Context context, int position, boolean display) {
        if (Main.INSTANCE.getFlipToWinBoardManager().isValidTap(position)) {
//            Toast.makeText(context, "is valid", Toast.LENGTH_SHORT).show();
            Main.INSTANCE.getFlipToWinBoardManager().touchMove(position);
//            Toast.makeText(context, "does move", Toast.LENGTH_SHORT).show();
            if (Main.INSTANCE.getFlipToWinBoardManager().puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                Intent temp = new Intent(context, ScoreBoardActivity.class);
                temp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(temp);
            }
        } else {
            Toast.makeText(context, "Already Solved", Toast.LENGTH_SHORT).show();
        }
    }

}