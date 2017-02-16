package ru.tanya.airplanegame;

import android.graphics.Bitmap;

<<<<<<< HEAD
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class Meteorite extends SimpleImageObject {

    public static double CURRENT_SPEED =  3;
    private int meteoriteScore;
    private boolean changedScore;
    private List<Integer> possibleXList;

    public Meteorite(int x, int y, Bitmap bitmap) {
        super(x, y, bitmap);
        initPossibleXList();
=======
public class Meteorite extends SimpleImageObject {

    public static final int CURRENT_SPEED =  3;
    private int meteoriteScore;
    private boolean changedScore;

    public Meteorite(int x, int y, Bitmap bitmap) {
        super(x, y, bitmap);
>>>>>>> 35506752259261a91817083084fe9ebbb4d124e3
    }

    private void checkDisplayBounds() {
        if ((y - getBitmapHeight()/2) > GameManager.getHeight()) {
            y = -getBitmapHeight()/2;
            ++meteoriteScore;
            changedScore = true;
<<<<<<< HEAD
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

=======
            if (x > GameManager.getWidth()/2) {
                x = getBitmapWidth();
            } else {
                x = GameManager.getWidth() - getBitmapWidth();
            }
        }
    }

>>>>>>> 35506752259261a91817083084fe9ebbb4d124e3
    public int getMeteoriteScore() {
        return meteoriteScore;
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
