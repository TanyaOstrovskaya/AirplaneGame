package ru.tanya.airplanegame;

import android.graphics.Bitmap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class Meteorite extends SimpleImageObject {

    public static double CURRENT_SPEED =  3;
    private boolean changedScore;
    private List<Integer> possibleXList;

    public Meteorite(int x, int y, Bitmap bitmap) {
        super(x, y, bitmap);
        initPossibleXList();
    }

    private void checkDisplayBounds() {
        if ((y - getBitmapHeight()/2) > GameManager.getHeight()) {
            y = -getBitmapHeight()/2;
            changedScore = true;
            x = getRandomPossibleX();
            CURRENT_SPEED += 0.3;
        }
    }

    private void initPossibleXList() {
        int width = GameManager.getWidth();
        possibleXList = new LinkedList<Integer>(asList(width/3, width/3*2, width/4, width/4*3));
    }

    public int getRandomPossibleX() {
        Random random = new Random(System.currentTimeMillis());
        return possibleXList.get(random.nextInt(3));
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
