package ru.tanya.airplanegame;

import android.graphics.Bitmap;

public class Meteorite extends SimpleImageObject {

    private int dy = 30;

    public Meteorite(int x, int y, Bitmap bitmap) {
        super(x, y, bitmap);
    }

    public void moveOneStep () {
        y += dy;
        checkBounds();
    }

    private void checkBounds() {
        if ((y + getBitmapHeight()/2) > GameManager.getHeight()) {
            y = -getBitmapHeight()/2;
        }
    }
}
