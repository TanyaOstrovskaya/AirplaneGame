package ru.tanya.airplanegame;

import android.graphics.Bitmap;

public class Meteorite extends SimpleImageObject {

    public static final int CURRENT_SPEED =  3;
    private int meteoriteScore;
    private boolean changedScore;

    public Meteorite(int x, int y, Bitmap bitmap) {
        super(x, y, bitmap);
    }

    private void checkDisplayBounds() {
        if ((y - getBitmapHeight()/2) > GameManager.getHeight()) {
            y = -getBitmapHeight()/2;
            ++meteoriteScore;
            changedScore = true;
            if (x > GameManager.getWidth()/2) {
                x = getBitmapWidth();
            } else {
                x = GameManager.getWidth() - getBitmapWidth();
            }
        }
    }

    public int getMeteoriteScore() {
        return meteoriteScore;
    }

    public boolean isChangedScore() {
        return changedScore;
    }

    public void updateCoordinates() {
        changedScore = false;
        y += CURRENT_SPEED;
        checkDisplayBounds();
    }
}
