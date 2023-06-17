package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class tourist_activities_ditails extends AppCompatActivity {

    String keyq="";
    String URLIMG="";
    ImageView AA_img;
    FloatingActionButton B_deleatA,B_updateA;
    TextView AA_id,AA_name,AA_location,AA_information,AA_instructions,AA_price,AA_day;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tourist_activities_ditails);

        AA_img=findViewById(R.id.AA_img);
        AA_id=findViewById(R.id.AA_id);
        AA_name=findViewById(R.id.AA_name);
        AA_location=findViewById(R.id.AA_location);
        AA_information=findViewById(R.id.AA_information);
        AA_instructions=findViewById(R.id.AA_instructions);
        AA_price=findViewById(R.id.AA_price);
        AA_day=findViewById(R.id.AA_day);

        B_deleatA=findViewById(R.id.B_deleatA);//this is the deleat button
        B_updateA=findViewById(R.id.B_updateA);//this is the update button


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
            keyq=b.getString("keyq");
            URLIMG=b.getString("image");
            Glide.with(this).load(b.getString("image")).into(AA_img);
        }
        B_deleatA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference dr= FirebaseDatabase.getInstance().getReference("Tourist Activities");
                FirebaseStorage fs=FirebaseStorage.getInstance();
                StorageReference sr=fs.getReferenceFromUrl(URLIMG);
                sr.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dr.child(keyq).removeValue();
                        Toast.makeText(tourist_activities_ditails.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),tourist_activities_admin.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(tourist_activities_ditails.this, "didn't Deleted", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



        B_updateA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o=new Intent(tourist_activities_ditails.this,tourist_activities_update.class)
                        .putExtra("name",AA_name.getText().toString())
                        .putExtra("id",AA_id.getText().toString())
                        .putExtra("location",AA_location.getText().toString())
                        .putExtra("information",AA_information.getText().toString())
                        .putExtra("instructions",AA_instructions.getText().toString())
                        .putExtra("price",AA_price.getText().toString())
                        .putExtra("day",AA_day.getText().toString())
                        .putExtra("keyq",keyq)
                        .putExtra("image",URLIMG)
                        ;
                startActivity(o);




            }
        });
    }
    }
