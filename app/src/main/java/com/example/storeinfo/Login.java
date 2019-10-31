package com.example.storeinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Login extends AppCompatActivity {
    Button view1,add1,reset1;
    TextView t1;
    EditText o,n;
    String op,np,un;
    static String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        view1=findViewById(R.id.view);
        add1=findViewById(R.id.edit);
        reset1=findViewById(R.id.reset);
        t1=findViewById(R.id.welcome);
        o=findViewById(R.id.op);
        n=findViewById(R.id.np);
        Intent intent =getIntent();
        name=(intent.getStringExtra(MainActivity.st));
        name.trim();
        un=name.substring(0,name.indexOf('/'));
        t1.setText("Welcome "+un);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m1();
            }
        });
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m2();
            }
        });
        reset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m3();
            }
        });
    }
    public void m1()
    {
        Intent it=new Intent(Login.this,Viewinfo.class);
        it.putExtra(name,name);
        startActivity(it);
    }
    public void m2()
    {
        Intent it=new Intent(Login.this,Addinfo.class);
        it.putExtra(name,name);
        startActivity(it);
    }
    public void m3()
    {
        op=o.getText().toString();
        np=n.getText().toString();
        Reset(op,np,un);
    }
    public void Reset(String a,String b,String un)
    {
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fos=openFileOutput(un,MODE_APPEND);
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
                fos = openFileOutput(un,0);
                fos.write(b.getBytes());
                Toast.makeText(this,"Password has been reset",Toast.LENGTH_LONG).show();
                o.setVisibility(View.INVISIBLE);
                n.setVisibility(View.INVISIBLE);
                reset1.setVisibility(View.INVISIBLE);
            } else
            {
                Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_LONG).show();
                o.setText("");
                return;
            }

        }catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {e.printStackTrace();}
        finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
