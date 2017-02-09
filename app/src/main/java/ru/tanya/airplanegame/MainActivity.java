package ru.tanya.airplanegame;

import android.app.Activity;
import android.os.Bundle;

import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity  extends Activity {    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // çàïðîñ íà îòêëþ÷åíèå ñòðîêè çàãîëîâêà
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // ïåðåâîä ïðèëîæåíèÿ â ïîëíîýêðàííûé ðåæèì
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // óñòàíàâëèâàåì MainGamePanel êàê View
        setContentView(new MainGamePanel(this));
        Log.d(TAG, "View added");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Destroying...");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Stopping...");
        super.onStop();
    }
}