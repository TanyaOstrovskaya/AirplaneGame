package ru.tanya.airplanegame;

import android.graphics.Bitmap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.*;

public class Cloud extends SimpleImageObject {

    public static final double CURRENT_SPEED  = 4;

    private List<Integer> possibleXList;

    public Cloud(int x, int y, Bitmap bitmap) {
        super(x, y, bitmap);
        initPossibleXList();
    }

    private void initPossibleXList() {
        int width = GameManager.getWidth();
        possibleXList = new LinkedList<Integer>(asList(width/3, width/3*2, width/4, width/4*3));
    }

    private void checkDisplayBounds() {
        if ((y - getBitmapHeight()/2) > GameManager.getHeight()) {
            y = -getBitmapHeight();
            x = getRandomPossibleX();
        }
    }

    public void updateCoordinates() {
        y += CURRENT_SPEED;
        checkDisplayBounds();
    }

    public int getRandomPossibleX() {
        Random random = new Random(System.currentTimeMillis());
        return possibleXList.get(random.nextInt(3));
    }
}
