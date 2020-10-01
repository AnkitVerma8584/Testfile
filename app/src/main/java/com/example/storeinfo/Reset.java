package com.example.storeinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reset extends AppCompatActivity {

    String op, np, un, name;
    EditText o, n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        name = getIntent().getStringExtra("Name");
        o = findViewById(R.id.op);
        n = findViewById(R.id.np);
    }

    public void m3() {
        op = o.getText().toString();
        np = n.getText().toString();
        if (op.equals("") || np.equals("")) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        } else
            Reset(op, np, un);
    }

    public void Reset(String a, String b, String un) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(un, MODE_APPEND);
            fis = openFileInput(un);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text, ch = "";
            while ((text = br.readLine()) != null) {
                sb.append(text).append("");
            }
            ch = sb.toString();

            if (a.equals(ch)) {
                fos = openFileOutput(un, 0);
                fos.write(b.getBytes());
                Toast.makeText(this, "Password has been reset", Toast.LENGTH_LONG).show();
                b = un + "-" + b;
                Toast.makeText(this, "Login again", Toast.LENGTH_SHORT).show();
                super.onBackPressed();
                newpass(b);
                o.setVisibility(View.INVISIBLE);
                n.setVisibility(View.INVISIBLE);
            } else {
                Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_LONG).show();
                o.setText("");
                return;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void newpass(String np) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        String u = "";
        try {
            fis = openFileInput(name);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                u = u + "\n" + text;
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            fos = openFileOutput(np, MODE_APPEND);
            fos.write(u.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
