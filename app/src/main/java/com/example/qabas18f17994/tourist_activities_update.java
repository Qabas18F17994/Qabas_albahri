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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.type.Color;

import java.util.Calendar;

public class tourist_activities_update extends AppCompatActivity {


    ImageView upd_activity_img;
    Button upldd_activity;
    EditText upd_activity_name,upd_activity_id,upd_activity_location,upd_activity_information,upd_activity_instructions,upd_activity_price,upd_activity_day;
    String qname,qid,qloc,qimfo,qins,qprice,qday;
    DatePickerDialog.OnDateSetListener ds_From;
    Button TGG_dayFrom;
    String URLIMG;
    String keyq,IMG_OLD;
    Uri quri;
    DatabaseReference DR;
    TextView booking_type;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tourist_activities_update);


        upd_activity_name=findViewById(R.id.upd_activity_name);
        upd_activity_img=findViewById(R.id.upd_activity_img);
        upd_activity_id=findViewById(R.id.upd_activity_id);
        upd_activity_location=findViewById(R.id.upd_activity_location);
        upd_activity_information=findViewById(R.id.upd_activity_information);
        upd_activity_instructions=findViewById(R.id.upd_activity_instructions);
        upd_activity_price=findViewById(R.id.upd_activity_price);
        TGG_dayFrom = findViewById(R.id.TGG_dayFrom);



        upldd_activity=findViewById(R.id.upldd_activity);

        theDays();

        ActivityResultLauncher<Intent> a=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult r) {


                        if(r.getResultCode()== Activity.RESULT_OK)
                        {
                            Intent I =r.getData();
                            quri=I.getData();
                            upd_activity_img.setImageURI(quri);
                        }
                        else
                        {
                            Toast.makeText(tourist_activities_update.this, "No image has selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        Bundle n=getIntent().getExtras();
        if(n!=null)
        {
            Glide.with(tourist_activities_update.this).load(n.getString("image")).into(upd_activity_img);
            upd_activity_name .setText(n.getString("name"));
            upd_activity_id.setText(n.getString("id"));
            upd_activity_location.setText(n.getString("location"));
            upd_activity_information.setText(n.getString("information"));
            upd_activity_instructions.setText(n.getString("instructions"));
            upd_activity_price.setText(n.getString("price"));
            TGG_dayFrom.setText(n.getString("day"));
            IMG_OLD=n.getString("image");
        }

        String id=upd_activity_id.getText().toString();
        DR= FirebaseDatabase.getInstance().getReference("Tourist Activities").child(id);

        upd_activity_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IMG=new Intent(Intent.ACTION_PICK);//gt photo from galary
                IMG.setType("image/*");
                a.launch(IMG);
            }
        });

        upldd_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theSAVINGofdata();
                Intent n=new Intent(tourist_activities_update.this,tourist_activities_admin.class);
                startActivity(n);

            }
        });

    }


    public void theSAVINGofdata()
    {
        StorageReference storageReference= FirebaseStorage.getInstance().getReference()
                .child("Tourist Activities images").child(quri.getLastPathSegment());
        AlertDialog.Builder b=new AlertDialog.Builder(tourist_activities_update.this);
        b.setCancelable(false);
        b.setView(R.layout.progress_layout);
        AlertDialog d=b.create();
        d.show();

        storageReference.putFile(quri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot ts) {
                Task<Uri> TU=ts.getStorage().getDownloadUrl();
                while (!TU.isComplete());
                Uri UI=TU.getResult();
                URLIMG=UI.toString();
                the_update();
                d.dismiss();//pending
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                d.dismiss();
            }
        });
    }



    public void the_update()
    {

        qname=upd_activity_name.getText().toString();
        qid=upd_activity_id.getText().toString();
        qloc=upd_activity_location.getText().toString();
        qimfo=upd_activity_information.getText().toString();
        qins=upd_activity_instructions.getText().toString();
        qprice=upd_activity_price.getText().toString();
        qday=TGG_dayFrom.getText().toString();

        data_ta  dat=new data_ta(URLIMG,qid,qname,qloc,qimfo,qins,qprice,qday);

        DR.setValue(dat).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> t) {


                if (t.isSuccessful())
                {
                    StorageReference sr=FirebaseStorage.getInstance().getReferenceFromUrl(IMG_OLD);
                    sr.delete();
                    Toast.makeText(tourist_activities_update.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(tourist_activities_update.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(tourist_activities_update.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, ds_From, year, month, day
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
