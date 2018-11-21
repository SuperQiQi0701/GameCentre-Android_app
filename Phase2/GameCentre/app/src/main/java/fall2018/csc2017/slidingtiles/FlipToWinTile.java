package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class FlipToWinTile implements Serializable {

    private boolean flippedUp = false;

    private boolean paired = false;

    /**
     * The background id to find the tile image.
     */
    private static final int BACK_SIDE =  0; //R.drawable.hearteyes;

    /**
     * The unique id.
     */
    private int id;

//    /**
//     * Return the background id.
//     *
//     * @return the background id
//     */
//    public int getBackground() {
//        return background;
//    }

    FlipToWinTile(int id) {
        this.id = id + 1;
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

        this.flippedUp = !(this.flippedUp);
    }

    int flipStatus() {
        if (this.flippedUp){
            return this.id;
        }
        return BACK_SIDE;
    }

    void setPaired() {

        this.paired = true;
    }

    boolean isPaired() {

        return (this.paired);
    }



//    /**
//     * A tile with a background id; look up and set the id.
//     *
//     * @param backgroundId the background id
//     */
//    FlipToWinTile(int backgroundId) {
//        id = backgroundId + 1;
//        switch (backgroundId + 1) {
//            case 1:
//                background = R.drawable.tile_1;
//                break;
//            case 2:
//                background = R.drawable.tile_1;
//                break;
//            case 3:
//                background = R.drawable.tile_2;
//                break;
//            case 4:
//                background = R.drawable.tile_2;
//                break;
//            case 5:
//                background = R.drawable.tile_3;
//                break;
//            case 6:
//                background = R.drawable.tile_3;
//                break;
//            case 7:
//                background = R.drawable.tile_4;
//                break;
//            case 8:
//                background = R.drawable.tile_4;
//                break;
//            case 9:
//                background = R.drawable.tile_5;
//                break;
//            case 10:
//                background = R.drawable.tile_5;
//                break;
//            case 11:
//                background = R.drawable.tile_6;
//                break;
//            case 12:
//                background = R.drawable.tile_6;
//                break;
//            case 13:
//                background = R.drawable.tile_7;
//                break;
//            case 14:
//                background = R.drawable.tile_7;
//                break;
//            case 15:
//                background = R.drawable.tile_8;
//                break;
//            case 16:
//                background = R.drawable.tile_8;
//                break;
//            case 17:
//                background = R.drawable.tile_9;
//                break;
//            case 18:
//                background = R.drawable.tile_9;
//                break;
//            case 19:
//                background = R.drawable.tile_10;
//                break;
//            case 20:
//                background = R.drawable.tile_10;
//                break;
//            case 21:
//                background = R.drawable.tile_11;
//                break;
//            case 22:
//                background = R.drawable.tile_11;
//                break;
//            case 23:
//                background = R.drawable.tile_12;
//                break;
//            case 24:
//                background = R.drawable.tile_12;
//                break;
//            case 25:
//                background = R.drawable.tile_13;
//                break;
//            case 26:
//                background = R.drawable.tile_13;
//                break;
//            case 27:
//                background = R.drawable.tile_14;
//                break;
//            case 28:
//                background = R.drawable.tile_14;
//                break;
//            case 29:
//                background = R.drawable.tile_15;
//                break;
//            case 30:
//                background = R.drawable.tile_15;
//                break;
//            case 31:
//                background = R.drawable.tile_16;
//                break;
//            case 32:
//                background = R.drawable.tile_16;
//                break;
//            case 33:
//                background = R.drawable.tile_17;
//                break;
//            case 34:
//                background = R.drawable.tile_17;
//                break;
//            case 35:
//                background = R.drawable.tile_18;
//                break;
//            case 36:
//                background = R.drawable.tile_18;
//                break;
//            default:
//                background = R.drawable.tile_18;
//        }
//    }
}
