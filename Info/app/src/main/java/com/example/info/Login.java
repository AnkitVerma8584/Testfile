package com.example.info;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
        un=name.substring(0,name.indexOf('-'));
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
        Toast.makeText(this,"Login again",Toast.LENGTH_SHORT).show();
        super.onBackPressed();
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
                b=un+"-"+b;
                newpass(b);
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
    public void newpass(String np)
    {
        FileInputStream fis=null;
        FileOutputStream fos = null;
        String u="";
        try {
            fis=openFileInput(name);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br=new BufferedReader(isr);
            StringBuilder sb=new StringBuilder();
            String text;
            while((text=br.readLine())!=null) {
                u=u+"\n"+text;
            }
            fis.close();
        }
        catch(Exception e)
        {e.printStackTrace();}

        try {
            fos = openFileOutput(np,MODE_APPEND);
            fos.write(u.getBytes());

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

    @Override
    public void onBackPressed() {
        androidx.appcompat.app.AlertDialog.Builder alt=new androidx.appcompat.app.AlertDialog.Builder(this);
        alt.setTitle("Attention!")
                .setCancelable(false)
                .setMessage("Do you want to Logout?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Login.super.onBackPressed();
                    }
                });
        AlertDialog a=alt.create();
        a.show();
    }
}
