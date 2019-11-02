package com.example.storeinfo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
    Button view1, add1, reset1, srh;
    TextView t1, v;
    EditText o, n, det;
    String op, np, un;
    static String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        view1 = findViewById(R.id.view);
        add1 = findViewById(R.id.edit);
        reset1 = findViewById(R.id.reset);
        srh = findViewById(R.id.search);
        t1 = findViewById(R.id.welcome);
        v = findViewById(R.id.searchinfo);
        o = findViewById(R.id.op);
        n = findViewById(R.id.np);
        det = findViewById(R.id.detail);
        o.setVisibility(View.INVISIBLE);
        n.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        name = (intent.getStringExtra(MainActivity.st));
        name.trim();
        un = name.substring(0, name.indexOf('-'));
        t1.setText("Welcome " + un);
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
                String s = reset1.getText().toString();
                if (s.equals("Reset Password")) {
                    o.setVisibility(View.VISIBLE);
                    n.setVisibility(View.VISIBLE);
                    reset1.setText("Reset");
                } else
                    m3();
            }
        });
        srh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m0();
            }
        });
    }
    public void m0()
    {
        String str = det.getText().toString().trim();
        v.setText(str);
        if (str.equals("")) {
            Toast.makeText(getApplicationContext(), "Field cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            char ch = str.charAt(0);
            if (ch >= '0' && ch <= '9')
                searchroll(str);
            else
                searchname(str);
        }
    }

    public void m1() {
        Intent it = new Intent(Login.this, Viewinfo.class);
        it.putExtra(name, name);
        startActivity(it);
    }

    public void m2() {
        Intent it = new Intent(Login.this, Addinfo.class);
        it.putExtra(name, name);
        startActivity(it);
    }

    public void m3() {
        op = o.getText().toString();
        np = n.getText().toString();
        if (op.equals("") || np.equals("")) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Reset(op, np, un);
        }
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
                reset1.setVisibility(View.INVISIBLE);
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

    @Override
    public void onBackPressed() {
        androidx.appcompat.app.AlertDialog.Builder alt = new androidx.appcompat.app.AlertDialog.Builder(this);
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
        AlertDialog a = alt.create();
        a.show();
    }

    void searchname(String n) {
        FileInputStream fis = null;
        n = ("Name : " + n);
        String u = "No match found";
        try {
            fis = openFileInput(name);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            while ((text = br.readLine()) != null) {
                if (text.equals(n)) {
                    u = "\n" + text;
                    text = br.readLine();
                    u = u + "\n" + text;
                    text = br.readLine();
                    u = u + "\n" + text;
                    text = br.readLine();
                    u = u + "\n" + text;
                    text = br.readLine();
                    u = u + "\n" + text + "\n";
                }
            }

            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        v.setText(u);
        det.setText("");
        v.setMovementMethod(new ScrollingMovementMethod());

    }
    void searchroll(String r)
    {
        r = ("Roll no. : "+r);
        FileInputStream fis = null;
        String text="",u="No match found",text1="";
        try {
            fis=openFileInput(name);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br=new BufferedReader(isr);

            while((text=br.readLine())!=null)  {
                text1=br.readLine();
                if(text1.equals(r)) {
                    u ="\n" + text;
                    u = u + "\n" + text1;
                    text = br.readLine();
                    u = u + "\n" + text;
                    text = br.readLine();
                    u = u + "\n" + text;
                    text = br.readLine();
                    u = u + "\n" + text + "\n";
                }
            }

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        v.setText(u);
        v.setMovementMethod(new ScrollingMovementMethod());
        det.setText("");
    }
}
