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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button login,reg;

    EditText user,pass;
    String st="",up="",un="";
    public static String need;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.login);
        reg=findViewById(R.id.reg);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                un=user.getText().toString().trim();
                up=pass.getText().toString().trim();


                    if(check(un)) {
                        if (getcheck(un, up)) {
                            st = un + "/" + up;
                            Intent it = new Intent(MainActivity.this, Login.class);
                            it.putExtra("Username", st);
                            startActivity(it);
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_LONG).show();


                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Username does not exists",Toast.LENGTH_LONG).show();
                        user.setText("");
                        pass.setText("");
                    }


            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Register.class);
                startActivity(i);

            }
        });
    }
    public boolean check(String un)
    {
        boolean checkit=new File(getFilesDir(),un).exists();
        return checkit;
    }
    public boolean getcheck(String n,String p)
    { boolean t=false;
        FileInputStream fis=null;
        try{
            fis=openFileInput(n);
            InputStreamReader isr=new InputStreamReader(fis);
            BufferedReader br=new BufferedReader(isr);
            StringBuilder sb=new StringBuilder();
            String text,ch="";
            while((text=br.readLine())!=null){
                sb.append(text).append("");
            }
            ch=sb.toString();

            if(p.equals(ch))
                t= true;
            else
                t= false;
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
        }
        return t;
    }

}
