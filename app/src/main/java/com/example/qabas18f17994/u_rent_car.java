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

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.type.Color;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class u_rent_car extends AppCompatActivity {
    TextView booking_type;
    String keyq="";
    String URLIMG="";
    ImageView rc_img;
    FloatingActionButton B_deleatrc,B_updaterc;
    TextView rc_id,rc_name,rc_company,rc_information,rc_instructions,rc_price,rc_totaldays,rc_totalprice;
    Button B_booked_rc,RC_dayFrom,RC_dayto;
    DatePickerDialog.OnDateSetListener ds_From,ds_to;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_rent_car);

        rc_img=findViewById(R.id.rc_img);
        booking_type=findViewById(R.id.booking_type);
        rc_id=findViewById(R.id.rc_id);
        rc_name=findViewById(R.id.rc_name);
        rc_company=findViewById(R.id.rc_company);
        rc_information=findViewById(R.id.rc_information);
        rc_instructions=findViewById(R.id.rc_instructions);
        rc_price=findViewById(R.id.rc_price);


        rc_totaldays=findViewById(R.id.rc_totaldays);
        rc_totalprice=findViewById(R.id.rc_totalprice);


        RC_dayFrom=findViewById(R.id.RC_dayFrom);
        RC_dayto=findViewById(R.id.RC_dayto);

        B_booked_rc=findViewById(R.id.B_booked_rc);

        theDays();


        Bundle b=getIntent().getExtras();
        if(b !=null)
        {

            rc_name.setText(b.getString("name"));
            rc_id.setText(b.getString("id"));
            rc_company.setText(b.getString("company"));
            rc_information.setText(b.getString("information"));
            rc_instructions.setText(b.getString("instructions"));
            rc_price.setText(b.getString("price"));
            keyq=b.getString("keyq");
            URLIMG=b.getString("image");
            Glide.with(this).load(b.getString("image")).into(rc_img);
        }



        B_booked_rc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                try {

                    String dayfrom=RC_dayFrom.getText().toString();
                    String dayto=RC_dayto.getText().toString();

                    SimpleDateFormat simpleDateFormat_1=new SimpleDateFormat("dd/MM/yyyy");
                    //for tray to convert frm dilog to date format
                    Date date1;
                    Date date2;
                    date1= simpleDateFormat_1.parse(dayfrom);
                    date2=simpleDateFormat_1.parse(dayto);

                   long statD=date1.getTime();
                   long EndD=date2.getTime();

                  Period period = new Period(statD, EndD,PeriodType.yearMonthDay());
                    int dayss = period.getDays();
                    //the result or the output
                    rc_totaldays.setText(String.valueOf(dayss));
                }
                catch (ParseException i)
                {
                    i.printStackTrace();
                }

                thecalculation();

                Intent of=new Intent(u_rent_car.this,u_rent_car_booking.class)

                        .putExtra("name_Car",rc_name.getText().toString())
                        .putExtra("type",booking_type.getText().toString())
                        .putExtra("id_Car",rc_id.getText().toString())
                        .putExtra("company",rc_company.getText().toString())
                        .putExtra("information",rc_information.getText().toString())
                        .putExtra("instructions",rc_instructions.getText().toString())
                        .putExtra("price_Car",rc_price.getText().toString())
                        .putExtra("day_from",RC_dayFrom.getText().toString())
                        .putExtra("day_to",RC_dayto.getText().toString())
                        .putExtra("TotalPrice_Car",rc_totalprice.getText())
                        ;
                startActivity(of);

            }
        });










    }
    private void thecalculation() {
        double p=Double.parseDouble(rc_price.getText().toString());
        double d=Double.parseDouble(rc_totaldays.getText().toString());
        double total=p*d;
        rc_totalprice.setText(String.valueOf(total));

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
        RC_dayFrom.setText(String.valueOf(date));
        RC_dayto.setText(String.valueOf(date));



        // action to be performed when button 1 is clicked
        RC_dayFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // date picker dialog is used
                // and its style and color are also passed
                DatePickerDialog datePickerDialog = new DatePickerDialog(u_rent_car.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, ds_From, year, month, day
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
                RC_dayFrom.setText(date);
            }
        };


        RC_dayto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // date picker dialog is used
                // and its style and color are also passed
                DatePickerDialog datePickerDialog = new DatePickerDialog(u_rent_car.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, ds_to, year, month, day
                );
                // to set background for datepicker
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLUE_FIELD_NUMBER));
                datePickerDialog.show();

            }
        });

// for set the date which user selects
        ds_to=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                RC_dayto.setText(date);
            }
        };



    }
    }

