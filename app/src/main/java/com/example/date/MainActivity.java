
package com.example.date;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Locale;

import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    private TextView dateTextView;
    private Handler handler;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateTextView = findViewById(R.id.dateTextView);
        handler = new Handler();
        updateDateTime();

        // Update the date and time every second
        runnable = new Runnable() {
            @Override
            public void run() {
                updateDateTime();
                handler.postDelayed(this, 1000);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    private void updateDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy 'at' HH:mm:ss", Locale.getDefault());
        String currentDateAndTime = dateFormat.format(new Date());

        dateTextView.setText(currentDateAndTime);
    }
}