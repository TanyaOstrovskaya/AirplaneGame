package ru.tanya.airplanegame;

import android.graphics.Bitmap;

public class Airplane extends SimpleImageObject{

    public static final double SPEED_COEFFICIENT = 0.25; //speed on OX
    private int angle;

    public Airplane(int x, int y, Bitmap bitmap) {
        super(x, y, bitmap);
    }

    private boolean checkBounds() {
        // check if airplane if on display
        if ((x + getBitmapWidth()/2 >= GameManager.getWidth()) ||
        (x - getBitmapWidth()/2) <= 0)
            return false;
        else
            return true;
    }

    public void updateCoordinates() {
        if (checkBounds()) {
            x += SPEED_COEFFICIENT * angle;
        } else {
            // if plane is near RIGHT display border
            if (x > GameManager.getWidth()/2) {
                // if user tryes to move plain to the LEFT side
                // then update coordinates, else do nothing
                if (angle <= 0) {
                    x += SPEED_COEFFICIENT * angle;
                }
            } else {
                // if plane is near LEFT display border

                // if user tryes to move plain to the RIGHT side
                // then update coordinates, else do nothing
                if (angle > 0) {
                    x += SPEED_COEFFICIENT * angle;
                }
            }
        }
    }

    public void setAngle(int angle) {
        // modifies angle from (0 .. 360) to (-90 .. 90)
        if (angle < 90) {
            this.angle = angle;
        } else {
            this.angle = angle - 360;
        }

    }
}
