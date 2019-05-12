package com.cugkuan.linemark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cugkuan.progress.LineProgressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineProgressView progressView = findViewById(R.id.progress);

        progressView.setProgress(8);
    }
}
