package FlipToWin;

import java.io.Serializable;

import Basic.MovableTile;
import fall2018.csc2017.slidingtiles.R;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class FlipToWinTile extends MovableTile implements Serializable {

    private boolean facedUp = false;

    private boolean paired = false;

    /**
     * The unique id.
     */
    private int id;

    /**
     * The background id to find the tile image.
     */
    private int backGround;

    private String frontPage;

    FlipToWinTile(int num) {
        this.id = num + 1;
        this.backGround = R.drawable.back_of_tile4;

        String emoji[] = {"🐶", "🐻", "🌝", "🌚", "🍑", "🐱", "❤️", "🍭️",
                "💻", "💊", "🚗", "🗿", "🍗", "🍩", "🍺"};

        this.frontPage = emoji[this.id - 1];
    }

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return backGround;
    }

    public String getFrontPage() {
        return this.frontPage;
    }




    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

    void setFlipped() {

        this.facedUp = !(this.facedUp);
    }

    boolean facedUpStatus() {
        return facedUp;
    }

    void setPaired() {

        this.paired = true;
    }

    boolean isPaired() {

        return (this.paired);
    }
}
