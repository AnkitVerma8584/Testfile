package com.example.info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
                } else if(!check(un))
                {
                    save1(un, up);
                    Toast.makeText(getApplicationContext(),"Saved to "+getFilesDir()+"/"+un,Toast.LENGTH_LONG).show();
                    String s=un+"-"+up;
                    save1(s,"");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    void save1(String un, String up) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(un,MODE_APPEND);
            fos.write(up.getBytes());
            regu.setText("");
            regp.setText("");

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
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
    { return (new File(getFilesDir(),un).exists()); }
}

