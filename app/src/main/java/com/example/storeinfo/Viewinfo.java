package com.example.storeinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Viewinfo extends AppCompatActivity {
    TextView t;
    static String nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewinfo);
        Intent it= getIntent();
        nm=(it.getStringExtra(Login.name));

    }
}
