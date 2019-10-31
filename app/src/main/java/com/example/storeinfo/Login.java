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
    Button view,add,reset;
    TextView t1;
    EditText o,n;
    String name,op,np,un;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        view=findViewById(R.id.view);
        add=findViewById(R.id.add);
        reset=findViewById(R.id.reset);
        t1=findViewById(R.id.welcome);
        o.findViewById(R.id.op);
        n=findViewById(R.id.np);
        name=getIntent().getExtras().getString("Username");
        un=name.substring(0,name.indexOf('/'));
        t1.setText("Welcome "+un);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Login.this,Viewinfo.class);
                it.putExtra("password",name);
                startActivity(it);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Login.this,Add.class);
                it.putExtra("password",name);
                startActivity(it);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op=o.getText().toString();
                np=n.getText().toString();
                Reset(op,np,un);
            }
        });
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
                PrintWriter pw = new PrintWriter(un);
                pw.print("");
                pw.close();
                fos = openFileOutput(un,MODE_APPEND);
                fos.write(b.getBytes());
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
