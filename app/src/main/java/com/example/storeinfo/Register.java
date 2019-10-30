package com.example.storeinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Register extends AppCompatActivity {
    EditText regu, regp;
    Button reg;
    String un = "", up = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regu = findViewById(R.id.regu);
        regp = findViewById(R.id.regp);
        reg = findViewById(R.id.reg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                un = regu.getText().toString().trim();
                up = regp.getText().toString().trim();
                if (un.equals("") || up.equals("")) {
                    Toast.makeText(getApplicationContext(), "Username or Password cannot be empty", Toast.LENGTH_LONG).show();
                } else if(check(un))
                {
                    save(un, up);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    void save(String un, String up) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(un,MODE_APPEND);
            fos.write(up.getBytes());
            regu.setText("");
            regp.setText("");
            Toast.makeText(this,"Saved to "+getFilesDir()+"/"+un,Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(fos!=null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
    }
    public boolean check(String un)
    {
        boolean t=true;
        FileInputStream fis=null;
        try{
            fis=openFileInput(un);
        }
        catch (FileNotFoundException e)
        { return true;
        }
        finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      return t;
    }
}




