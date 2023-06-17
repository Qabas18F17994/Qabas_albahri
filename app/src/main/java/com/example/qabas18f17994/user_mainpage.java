package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class user_mainpage extends AppCompatActivity {



    CardView rentcarUSER,Touristic_placess_USER,tourgideUSER,aboutus,eventUSER,Tourist_Activities_USER,logputUSER,proflie_USER,bookingUSER,tourgid_e_USER;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_mainpage);


        proflie_USER = findViewById(R.id.proflie_USER);
        rentcarUSER = findViewById(R.id.rentcarUSER);
        Touristic_placess_USER=findViewById(R.id.Touristic_placess_USER);
        tourgideUSER=findViewById(R.id.tourgideUSER);
        eventUSER=findViewById(R.id.eventUSER);
        Tourist_Activities_USER=findViewById(R.id.Tourist_Activities_USER);
        logputUSER=findViewById(R.id.logputUSER);
        bookingUSER=findViewById(R.id.bookingUSER);
        tourgid_e_USER=findViewById(R.id.tourgid_e_USER);

        aboutus=findViewById(R.id.aboutus) ;

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inn=new Intent(getApplicationContext(),about_us.class);
                startActivity(inn);

            }
        });
        logputUSER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();

            }
        });

        tourgid_e_USER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),user_booking_tourgide.class));


            }
        });

        proflie_USER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),profile.class));


            }
        });


        Touristic_placess_USER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),u_touristic_placess_admin.class));


            }
        });

        eventUSER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),u_event_admin.class));


            }
        });

        Tourist_Activities_USER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),u_ta_admin.class));


            }
        });




        bookingUSER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),user_booking.class));


            }
        });

        tourgideUSER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),u_tour_gide_admin.class));


            }
        });


        rentcarUSER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),u_rent_car_admin.class));


            }
        });


    }
}