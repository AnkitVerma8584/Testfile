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
    EditText nm, dpt, roll, info;
    static String pss;
    String name, dept, rno, in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinfo);
        nm.findViewById(R.id.name);
        dpt = findViewById(R.id.dept);
        roll = findViewById(R.id.rollno);
        info = findViewById(R.id.info);
        sv = findViewById(R.id.save);
        Intent intent = getIntent();
        pss = (intent.getStringExtra(Login.name));
        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getinformation();
            }
        });
    }
    void getinformation() {
        name = nm.getText().toString().trim();
        dept = dpt.getText().toString().trim();
        rno = roll.getText().toString().trim();
        in = info.getText().toString().trim();
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
        info.setText("");
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
