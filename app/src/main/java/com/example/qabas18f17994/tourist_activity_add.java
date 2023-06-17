package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.type.Color;

import java.util.Calendar;

public class tourist_activity_add extends AppCompatActivity {



    EditText A_id,A_name,A_location,A_information,A_instructions,A_price,A_day;

    DatePickerDialog.OnDateSetListener ds_From;
    Button TGG_dayFrom;
    ImageView upolode_A_img;
    Button add_A;
    String URLimg;
    Uri quri;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tourist_activity_add);


        upolode_A_img=findViewById(R.id.upolode_A_img);
        A_id=findViewById(R.id.A_id);
        A_name=findViewById(R.id.A_name);
        A_location=findViewById(R.id.A_location);
        A_information=findViewById(R.id.A_information);
        A_instructions=findViewById(R.id.A_instructions);
        A_price=findViewById(R.id.A_price);
        TGG_dayFrom = findViewById(R.id.TGG_dayFrom);

        add_A=findViewById(R.id.add_A);
        theDays();

        ActivityResultLauncher<Intent> a=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult r) {
                        if (r.getResultCode()== Activity.RESULT_OK)
                        {
                            Intent qimg=r.getData();
                            quri=qimg.getData();
                            upolode_A_img.setImageURI(quri);
                        }
                        else
                        {
                            Toast.makeText(tourist_activity_add.this, "No image has selected ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


        upolode_A_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chose_picture=new Intent(Intent.ACTION_PICK);//to pic image from the galary of camera
                chose_picture.setType("image/*");
                a.launch(chose_picture);
            }
        });

        add_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference storageReference= FirebaseStorage.getInstance().getReference()
                        .child("Tourist Activities images").child(quri.getLastPathSegment());
                AlertDialog.Builder builder=new AlertDialog.Builder(tourist_activity_add.this);
                builder.setCancelable(false);
                builder.setView(R.layout.progress_layout);//this layout that i bild th show when it saved
                AlertDialog dialog=builder.create();
                dialog.show();


                storageReference.putFile(quri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot ts) {


                        Task<Uri> uriTask=ts.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri urlimg=uriTask.getResult();
                        URLimg=urlimg.toString();
                        uplodeing_data();
                        //pending
                        dialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                    }
                });

            }
        });
    }




    public void uplodeing_data()
    {

        String A__id=A_id.getText().toString();
        String A__name=A_name.getText().toString();
        String A__location=A_location.getText().toString();
        String A__information=A_information.getText().toString();
        String A__instructions=A_instructions.getText().toString();
        String A__price=A_price.getText().toString();
        String A__day=TGG_dayFrom.getText().toString();


        data_ta data_toris=new data_ta(URLimg,A__id,A__name,A__location,A__information,A__instructions,A__price,A__day);

        FirebaseDatabase.getInstance().getReference("Tourist Activities").child(A__id)
                .setValue(data_toris).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> t) {
                        if(t.isSuccessful())
                        {
                            Toast.makeText(tourist_activity_add.this, "Tourist Activities saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(tourist_activity_add.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });


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
                DatePickerDialog datePickerDialog = new DatePickerDialog(tourist_activity_add.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, ds_From, year, month, day
                );
                // to set background for date picker
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
