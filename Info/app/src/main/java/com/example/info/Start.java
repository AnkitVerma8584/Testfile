package com.example.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                reset();
            }
        },2000);

    }
    void reset()
    {
        Intent it =new Intent(Start.this,MainActivity.class);
        startActivity(it);
        finish();
    }
}
