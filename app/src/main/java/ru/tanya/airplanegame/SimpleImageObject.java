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

    public void handleActionDown(int eventX, int eventY) {
        if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2))) {
            if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getHeight() / 2))) {
                // droid touched
                setTouched(true);
            } else {
                setTouched(false);
            }
        } else {
            setTouched(false);
        }

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void update() {
        if (!touched) {
            y += speed;
        }
    }


    public int getSpeed() {
        return speed;
    }
}
