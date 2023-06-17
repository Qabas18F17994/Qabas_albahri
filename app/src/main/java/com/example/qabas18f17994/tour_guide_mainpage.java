package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class tour_guide_mainpage extends AppCompatActivity {

    CardView logout,tourgide_profile,tourgide_admin,booking_List,Touristic_placess_tg,aboutus;
    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_guide_mainpage);

        logout=findViewById(R.id.logout);
        tourgide_profile=findViewById(R.id.tourgide_profile);
        tourgide_admin=findViewById(R.id.tourgide_admin);
        booking_List=findViewById(R.id.booking_List);
        Touristic_placess_tg=findViewById(R.id.Touristic_placess_tg);
        aboutus=findViewById(R.id.aboutus) ;

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inn=new Intent(getApplicationContext(),about_us.class);
                startActivity(inn);

            }
        });

        Touristic_placess_tg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),u_touristic_placess_admin.class));


            }
        });

        booking_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),tg_booking_list.class));


            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();

            }
        });

        tourgide_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),profile.class));

            }
        });

        tourgide_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),tour_gide_information.class));


            }
        });

    }
}