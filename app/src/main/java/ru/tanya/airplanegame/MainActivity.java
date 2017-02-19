package ru.tanya.airplanegame;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.view.OrientationEventListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity{

    private int currentOrientationAngle = 0;
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
                currentOrientationAngle = arg0;
                gamepanel.getGameManager().setCurrentAirplaneAngle(currentOrientationAngle);
            }};
    }

    private void enableOrientationEventListener() {
        if (orientationEventListener.canDetectOrientation()){
            orientationEventListener.enable();
        } else {
            Toast.makeText(this, "Can't detect orientation", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}