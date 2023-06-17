package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class  u_ta extends AppCompatActivity {
    TextView quintity;
    TextView booking_type;
    int totalQuintity=1;
    ImageView quintityADD,quintityNIG;


    //the detail of tourist activity  for the user
    String keyq="";
    String URLIMG="";
    ImageView AA_img;
    Button AA_booked;
    TextView AA_id,AA_name,AA_location,AA_information,AA_instructions,AA_price,AA_day,AA_totalprice;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_ta);



        AA_img=findViewById(R.id.AA_img);
        booking_type=findViewById(R.id.booking_type);
        AA_id=findViewById(R.id.AA_id);
        AA_name=findViewById(R.id.AA_name);
        AA_location=findViewById(R.id.AA_location);
        AA_information=findViewById(R.id.AA_information);
        AA_instructions=findViewById(R.id.AA_instructions);
        AA_price=findViewById(R.id.AA_price);
        AA_day=findViewById(R.id.AA_day);

        AA_booked=findViewById(R.id.AA_booked);


        AA_totalprice=findViewById(R.id.AA_totalprice);
//quntity
        quintity= findViewById(R.id.quintity);
        quintityADD=findViewById(R.id.quintityADD);
        quintityNIG=findViewById(R.id.quintityNIG);

        quintityADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuintity<10)
                {
                    //add numbers
                    totalQuintity++;
                    quintity.setText(String.valueOf(totalQuintity));
                }

            }
        });

        quintityNIG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuintity<10)
                {
                    //removenumbers or less numbers
                    totalQuintity--;
                    quintity.setText(String.valueOf(totalQuintity));
                }

            }
        });




        Bundle b=getIntent().getExtras();
        if(b !=null)
        {

            AA_name.setText(b.getString("name"));

            AA_id.setText(b.getString("id"));
            AA_location.setText(b.getString("location"));
            AA_information.setText(b.getString("information"));
            AA_instructions.setText(b.getString("instructions"));
            AA_price.setText(b.getString("price"));
            AA_day.setText(b.getString("day"));
            AA_totalprice.setText(b.getString("total_price"));
            keyq=b.getString("keyq");
            URLIMG=b.getString("image");
            Glide.with(this).load(b.getString("image")).into(AA_img);
        }


        AA_booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 calculate();

                Intent o=new Intent(u_ta.this,u_ta_booking.class)
                        .putExtra("name",AA_name.getText().toString())
                        .putExtra("type",booking_type.getText().toString())
                        .putExtra("id",AA_id.getText().toString())
                        .putExtra("location",AA_location.getText().toString())
                        .putExtra("information",AA_information.getText().toString())
                        .putExtra("instructions",AA_instructions.getText().toString())
                        .putExtra("price",AA_price.getText().toString())
                        .putExtra("day",AA_day.getText().toString())
                        .putExtra("Quantity",quintity.getText().toString())
                        .putExtra("total_price",AA_totalprice.getText().toString())
                        .putExtra("keyq",keyq)
                        .putExtra("image",URLIMG)
                        ;
                startActivity(o);

            }
        });
    }

    private void calculate() {

        double p=Double.parseDouble(AA_price.getText().toString());
        double d=Double.parseDouble(String.valueOf(totalQuintity));
        double total=p*d;
        AA_totalprice.setText(String.valueOf(total));




    }
}