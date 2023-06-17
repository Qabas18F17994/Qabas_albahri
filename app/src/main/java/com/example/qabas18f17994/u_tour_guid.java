package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.type.Color;

import java.util.Calendar;

public class u_tour_guid extends AppCompatActivity {

    TextView booking_type_tg,TGG_id ,TGG_name, TGG_email, TGG_phone ,TGG_gender , TGG_information, TGG_price,TGG_totaldays,TGG_totalprice;

    Button B_booked_TGG,TGG_dayFrom;
    DatePickerDialog.OnDateSetListener ds_From;
    //the quantity of people
    TextView quintity;

    int totalQuintity=1;
    ImageView quintityADD,quintityNIG;

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;//becuse we reteve the data by user id

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_tour_guid);

        booking_type_tg=findViewById(R.id.booking_type_tg);

        TGG_id = findViewById(R.id.TGG_id);
        TGG_name = findViewById(R.id.TGG_name);
        TGG_email = findViewById(R.id.TGG_email);
        TGG_phone = findViewById(R.id.TGG_phone);
        TGG_gender = findViewById(R.id.TGG_gender);
        TGG_information = findViewById(R.id.TGG_information);
        TGG_price = findViewById(R.id.TGG_price);
        //quntity
        quintity= findViewById(R.id.quintity);
        quintityADD=findViewById(R.id.quintityADD);
        quintityNIG=findViewById(R.id.quintityNIG);


      //  TGG_totaldays = findViewById(R.id.TGG_totaldays);
        TGG_totalprice = findViewById(R.id.TGG_totalprice);

        TGG_dayFrom = findViewById(R.id.TGG_dayFrom);
        //TGG_dayto = findViewById(R.id.TGG_dayto);

        B_booked_TGG = findViewById(R.id.B_booked_TGG);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        userID = fAuth.getCurrentUser().getUid();




        Bundle b=getIntent().getExtras();
        if(b !=null)
        {
            TGG_id.setText(b.getString("ID"));//id of tour guid
            TGG_name.setText(b.getString("FULLName"));// name of tour guid
            TGG_email.setText(b.getString("EMAIL"));
            TGG_phone.setText(b.getString("PHONE"));
            TGG_gender.setText(b.getString("GENDER"));
            TGG_information.setText(b.getString("INFORMATION"));
            TGG_price.setText(b.getString("PRICE"));
            TGG_dayFrom.setText(b.getString("Day"));
            TGG_totalprice.setText(b.getString("Total_Price"));


        }


        theDays();



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

        B_booked_TGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                thecalculation();
                Intent of=new Intent(u_tour_guid.this,u_tour_guid_booking.class)
                        .putExtra("type_tg",booking_type_tg.getText().toString())
                        .putExtra("ID_tg",TGG_id.getText().toString())
                        .putExtra("FULLName_tg",TGG_name.getText().toString())
                        .putExtra("EMAIL_tg",TGG_email.getText().toString())
                        .putExtra("PHONE_tg",TGG_phone.getText().toString())
                        .putExtra("GENDER_tg",TGG_gender.getText().toString())

                        .putExtra("PRICE_tg",TGG_price.getText().toString())
                        .putExtra("Days_tg",TGG_dayFrom.getText().toString())
                        .putExtra("Quantity_tg",quintity.getText().toString())
                        .putExtra("TotalPrice_tg",TGG_totalprice.getText())

                        ;
                startActivity(of);



            }
        });

    }

    private void thecalculation() {


        double p=Double.parseDouble(TGG_price.getText().toString());
        double d=Double.parseDouble(String.valueOf(totalQuintity));
        double total=p*d;
        TGG_totalprice.setText(String.valueOf(total));
    }


    private void theDays() {

        Calendar calendar=Calendar.getInstance();

        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        // to set the current date as by default
        String date=simpleDateFormat.format(Calendar.getInstance().getTime());

       // TGG_dayFrom.setText(date);
        TGG_dayFrom.setText(String.valueOf(date));



        // action to be performed when button 1 is clicked
        TGG_dayFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // date picker dialog is used
                // and its style and color are also passed
                DatePickerDialog datePickerDialog = new DatePickerDialog(u_tour_guid.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, ds_From, year, month, day
                );
                // to set background for datepicker
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLUE_FIELD_NUMBER));
                datePickerDialog.show();
            }
        });


        ds_From=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                TGG_dayFrom.setText(date);
            }
        };



    }



}