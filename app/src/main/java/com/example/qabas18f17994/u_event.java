package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class u_event extends AppCompatActivity {

    String keyq="";
    String URLIMG="";
    ImageView E_img;
    TextView E_id,E_name,E_location,E_information;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_event);

        E_img=findViewById(R.id.E_img);
        E_id=findViewById(R.id.E_id);
        E_name=findViewById(R.id.E_name);
        E_location=findViewById(R.id.E_location);
        E_information=findViewById(R.id.E_information);




        Bundle b=getIntent().getExtras();
        if(b !=null)
        {

            E_name.setText(b.getString("name"));
            E_id.setText(b.getString("id"));
            E_location.setText(b.getString("location"));
            E_information.setText(b.getString("information"));
            keyq=b.getString("keyq");
            URLIMG=b.getString("image");
            Glide.with(this).load(b.getString("image")).into(E_img);
        }

    }
}