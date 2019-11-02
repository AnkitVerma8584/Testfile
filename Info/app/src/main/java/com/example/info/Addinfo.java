package com.example.info;

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
    EditText nm, dpt, roll, inf,ph;
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
        ph=findViewById(R.id.phn);
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
        final String p=ph.getText().toString().trim();
        final String in = inf.getText().toString().trim();
        if (name.equals("") || dept.equals("") || rno.equals("") || in.equals("")||p.equals("")) {
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_LONG).show();
        }
        else
            save(name, dept, rno, in,p);
    }

    void clr() {
        nm.setText("");
        roll.setText("");
        dpt.setText("");
        inf.setText("");
        ph.setText("");
    }

    void save(String name, String dept, String roll, String info,String phone) {
        name = "Name : " + name + "\n";
        roll = "Roll no. : " + roll + "\n";
        dept = "Department : " + dept + "\n";
        phone="Phone : "+phone+"\n";
        info = "Address : " + info + "\n\n\n";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(pss, MODE_APPEND);
            fos.write(name.getBytes());
            fos.write(roll.getBytes());
            fos.write(dept.getBytes());
            fos.write(phone.getBytes());
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
