package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class u_Touristic_placess extends AppCompatActivity {
    String keyq="";
    String URLIMG="";
    ImageView placed_img;
    TextView placed_id,placed_name,placed_location,placed_information,placed_instructions;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_touristic_placess);



        placed_img=findViewById(R.id.placed_img);
        placed_id=findViewById(R.id.placed_id);
        placed_name=findViewById(R.id.placed_name);
        placed_location=findViewById(R.id.placed_location);
        placed_information=findViewById(R.id.placed_information);
        placed_instructions=findViewById(R.id.placed_instructions);


//to show the data in next page
        Bundle b=getIntent().getExtras();
        if(b !=null)
        {

            placed_name.setText(b.getString("name"));
            placed_id.setText(b.getString("id"));
            placed_location.setText(b.getString("location"));
            placed_information.setText(b.getString("information"));
            placed_instructions.setText(b.getString("instructions"));
            keyq=b.getString("keyq");
            URLIMG=b.getString("image");
            Glide.with(this).load(b.getString("image")).into(placed_img);
        }

    }
}