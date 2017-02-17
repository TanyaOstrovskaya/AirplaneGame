package ru.tanya.airplanegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

public class FinishActivity extends Activity {

    private String finalScore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        finalScore = getIntent().getExtras().getString("score");
        TextView scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        scoreTextView.setText("FINAL SCORE: " + finalScore);
    }

    public void onRetryButtonClick (View view) {
        Intent intent = new Intent(FinishActivity.this, StartActivity.class);
        startActivity(intent);
    }

    public void onExitButtonClick (View view) {
        Intent intent = new Intent(FinishActivity.this, FinishActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finishAffinity();
    }
}