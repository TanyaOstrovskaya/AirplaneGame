package ru.tanya.airplanegame;

import android.graphics.Bitmap;

import java.util.Vector;

public class Cloud extends SimpleImageObject {

    public static final double CURRENT_SPEED  = 1;

    private Vector<double> possibleX =

    public Cloud(int x, int y, Bitmap bitmap) {
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

    public void updateCoordinates() {
        y += CURRENT_SPEED;
        checkDisplayBounds();
    }
}
