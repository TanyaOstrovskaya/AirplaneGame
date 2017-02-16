package ru.tanya.airplanegame;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.view.OrientationEventListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity  extends Activity{

    private int currentOrientationAngle = 0;
    public static boolean isRunning;
    OrientationEventListener orientationEventListener;

    MainGamePanel gamepanel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gamepanel = new MainGamePanel((this));
        setContentView(gamepanel);

        initOrientationEventListener();
        enableOrientationEventListener();
    }

    private void initOrientationEventListener() {
        orientationEventListener
                = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int arg0) {
                // TODO Auto-generated method stub
                currentOrientationAngle = arg0;
                gamepanel.getGameManager().setCurrentAirplaneAngle(currentOrientationAngle);
            }};
    }

    private void enableOrientationEventListener() {
        if (orientationEventListener.canDetectOrientation()){
            orientationEventListener.enable();
        } else {
            Toast.makeText(this, "Can't Detect Orientation", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}