package ru.tanya.airplanegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SimpleImageObject {
    protected int x;
    protected int y;
    private Bitmap bitmap;
    private int bitmapHeight;
    private int bitmapWidth;
    private boolean touched;
    private int speed = 1;

    public SimpleImageObject (int x, int y, Bitmap bitmap) {
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();
        this.x = x - bitmapWidth/2;
        this.y = y - bitmapHeight/2;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBitmapHeight() {
        return bitmapHeight;
    }

    public int getBitmapWidth() {
        return bitmapWidth;
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
    }


}
