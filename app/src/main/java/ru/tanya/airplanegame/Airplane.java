package ru.tanya.airplanegame;

import android.graphics.Bitmap;

public class Airplane {

    private int x;
    private int y;
    private Bitmap bitmap;

    public Airplane(int x, int y, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
