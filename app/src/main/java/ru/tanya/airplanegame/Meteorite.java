package ru.tanya.airplanegame;

import android.graphics.Bitmap;

public class Meteorite extends SimpleImageObject {

    public static final int CURRENT_SPEED =  1;

    public Meteorite(int x, int y, Bitmap bitmap) {
        super(x, y, bitmap);
    }

    private void checkDisplayBounds() {
        if ((y - getBitmapHeight()/2) > GameManager.getHeight()) {
            y = -getBitmapHeight()/2;
            if (x > GameManager.getWidth()/2) {
                x = getBitmapWidth();
            } else {
                x = GameManager.getWidth() - getBitmapWidth();
            }
        }
    }

    @Override
    public void updateCoordinates() {
        y += CURRENT_SPEED;
        checkDisplayBounds();
    }
}
