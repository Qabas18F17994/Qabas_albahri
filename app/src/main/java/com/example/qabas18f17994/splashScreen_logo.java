package com.example.qabas18f17994;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashScreen_logo extends AppCompatActivity {
    Handler s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_logo);

        s=new Handler();
        s.postDelayed(new Runnable()
        { @Override public void run()
        { Intent u=new Intent(splashScreen_logo.this,login.class);
            startActivity(u);
            finish();
        }
        },3000);
    }
    }
