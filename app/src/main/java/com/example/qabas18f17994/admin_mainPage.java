package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class admin_mainPage extends AppCompatActivity {


    CardView proflie,logputAdmin,Touristic_placess_admin,Tourist_Activities_admin,rentcarAdmin,eventAdmin,add_userAdmin,bookingListAdmin,aboutus;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_mainpage);

        proflie = findViewById(R.id.proflie);
        Touristic_placess_admin=findViewById(R.id.Touristic_placess_admin);
        Tourist_Activities_admin=findViewById(R.id.Tourist_Activities_admin);
        rentcarAdmin=findViewById(R.id.rentcarAdmin);
        eventAdmin=findViewById(R.id.eventAdmin);
        add_userAdmin=findViewById(R.id.add_userAdmin);
        bookingListAdmin=findViewById(R.id.bookingListAdmin);
        logputAdmin=findViewById(R.id.logputAdmin);
        aboutus=findViewById(R.id.aboutus) ;

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inn=new Intent(getApplicationContext(),about_us.class);
                startActivity(inn);

            }
        });

        bookingListAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inn=new Intent(getApplicationContext(),booking_list_admin.class);
                startActivity(inn);

            }
        });

        //profile button
        proflie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inn=new Intent(getApplicationContext(),profile.class);
                startActivity(inn);

            }
        });

        logputAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),login.class));
           finish();

            }
       });

        Touristic_placess_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),touristic_places_admin.class));


            }
        });

        Tourist_Activities_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),tourist_activities_admin.class));


            }
        });

        rentcarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),rent_car_admin.class));


            }
        });

        eventAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),event_admin.class));


            }
        });

        add_userAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), users_admin.class));


            }
        });

    }
}