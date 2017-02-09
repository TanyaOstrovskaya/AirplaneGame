package ru.tanya.airplanegame;

import android.graphics.Bitmap;

public class Meteorite extends SimpleImageObject {

    private int speed = 5;

    public Meteorite(int x, int y, Bitmap bitmap) {
        super(x, y, bitmap);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private void checkBounds() {
        if ((y + getBitmapHeight()/2) > GameManager.getHeight()) {
            y = -getBitmapHeight()/2;
        }
    }

    public void update() {
       y += speed;
        checkBounds();
    }
}
