package ru.tanya.airplanegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinishActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
    }

    public void onRetryButtonClick (View view) {
        Intent intent = new Intent(FinishActivity.this, StartActivity.class);
        startActivity(intent);
    }

    public void onExitButtonClick (View view) {
        finish();
    }
}