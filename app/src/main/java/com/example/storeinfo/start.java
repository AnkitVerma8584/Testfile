package com.example.storeinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class start extends AppCompatActivity {

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
        },3000);

    }
    void reset()
    {
        Intent it =new Intent(start.this,MainActivity.class);
        startActivity(it);
        finish();
    }
}
