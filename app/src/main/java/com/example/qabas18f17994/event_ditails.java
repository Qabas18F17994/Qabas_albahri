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

public class event_ditails extends AppCompatActivity {
    String keyq="";
    String URLIMG="";
    ImageView E_img;
    FloatingActionButton B_deleatE,B_updateE;
    TextView E_id,E_name,E_location,E_information;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_ditails);

        E_img=findViewById(R.id.E_img);
        E_id=findViewById(R.id.E_id);
        E_name=findViewById(R.id.E_name);
        E_location=findViewById(R.id.E_location);
        E_information=findViewById(R.id.E_information);


        B_deleatE=findViewById(R.id.B_deleatE);//this is the deleat button
        B_updateE=findViewById(R.id.B_updateE);//this is the update button


        Bundle b=getIntent().getExtras();
        if(b !=null)
        {

            E_name.setText(b.getString("name"));
            E_id.setText(b.getString("id"));
            E_location.setText(b.getString("location"));
            E_information.setText(b.getString("information"));
            keyq=b.getString("keyq");
            URLIMG=b.getString("image");
            Glide.with(this).load(b.getString("image")).into(E_img);
        }
        B_deleatE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference dr= FirebaseDatabase.getInstance().getReference("Events");
                FirebaseStorage fs=FirebaseStorage.getInstance();
                StorageReference sr=fs.getReferenceFromUrl(URLIMG);
                sr.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dr.child(keyq).removeValue();
                        Toast.makeText(event_ditails.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),event_admin.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(event_ditails.this, "didn't Deleted", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



        B_updateE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o=new Intent(event_ditails.this,event_update.class)
                        .putExtra("name",E_name.getText().toString())
                        .putExtra("id",E_id.getText().toString())
                        .putExtra("location",E_location.getText().toString())
                        .putExtra("information",E_information.getText().toString())
                        .putExtra("keyq",keyq)
                        .putExtra("image",URLIMG)
                        ;
                startActivity(o);




            }
        });
    }
}