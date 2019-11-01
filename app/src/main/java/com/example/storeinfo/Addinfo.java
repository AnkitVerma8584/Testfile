package com.example.storeinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Addinfo extends AppCompatActivity {
    Button sv;
    EditText nm, dpt, roll, inf;
    public static String pss;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinfo);
        sv = findViewById(R.id.save);
        nm = findViewById(R.id.name);
        roll = findViewById(R.id.rollno);
        dpt = findViewById(R.id.dept);
        inf = findViewById(R.id.info);
        Intent intent = getIntent();
        pss = (intent.getStringExtra(MainActivity.st));
        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getinformation();
            }
        });

    }

    void getinformation() {
        final String name = nm.getText().toString().trim();
        final String dept = dpt.getText().toString().trim();
        final String rno = roll.getText().toString().trim();
        final String in = inf.getText().toString().trim();
        if (name.equals(null) || dept.equals(null) || rno.equals(null) || in.equals(null)) {
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        save(name, dept, rno, in);
    }

    void clr() {
        nm.setText("");
        roll.setText("");
        dpt.setText("");
        inf.setText("");
    }

    void save(String name, String dept, String roll, String info) {
        name = "Name : " + name + "\n";
        roll = "Roll no. : " + roll + "\n";
        dept = "Department : " + dept + "\n";
        info = "About : " + info + "\n\n\n";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(pss, MODE_APPEND);
            fos.write(name.getBytes());
            fos.write(roll.getBytes());
            fos.write(dept.getBytes());
            fos.write(info.getBytes());
            clr();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + pss, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
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
