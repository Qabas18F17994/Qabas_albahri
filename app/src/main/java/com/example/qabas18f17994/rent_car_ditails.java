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

public class rent_car_ditails extends AppCompatActivity {

    String keyq="";
    String URLIMG="";
    ImageView rc_img;
    FloatingActionButton B_deleatrc,B_updaterc;
    TextView rc_id,rc_name,rc_company,rc_information,rc_instructions,rc_price;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_car_ditails);


        rc_img=findViewById(R.id.rc_img);
        rc_id=findViewById(R.id.rc_id);
        rc_name=findViewById(R.id.rc_name);
        rc_company=findViewById(R.id.rc_company);
        rc_information=findViewById(R.id.rc_information);
        rc_instructions=findViewById(R.id.rc_instructions);
        rc_price=findViewById(R.id.rc_price);


        B_deleatrc=findViewById(R.id.B_deleatrc);//this is the deleat button
        B_updaterc=findViewById(R.id.B_updaterc);//this is the update button


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
        B_deleatrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference dr= FirebaseDatabase.getInstance().getReference("Rent Cars");
                FirebaseStorage fs=FirebaseStorage.getInstance();
                StorageReference sr=fs.getReferenceFromUrl(URLIMG);
                sr.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dr.child(keyq).removeValue();
                        Toast.makeText(rent_car_ditails.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),rent_car_admin.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(rent_car_ditails.this, "didn't Deleted", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



        B_updaterc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o=new Intent(rent_car_ditails.this,rent_car_update.class)
                        .putExtra("name",rc_name.getText().toString())
                        .putExtra("id",rc_id.getText().toString())
                        .putExtra("company",rc_company.getText().toString())
                        .putExtra("information",rc_information.getText().toString())
                        .putExtra("instructions",rc_instructions.getText().toString())
                        .putExtra("price",rc_price.getText().toString())
                        .putExtra("keyq",keyq)
                        .putExtra("image",URLIMG)
                        ;
                startActivity(o);




            }
        });
    }
}