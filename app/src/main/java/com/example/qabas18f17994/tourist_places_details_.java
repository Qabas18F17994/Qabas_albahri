package com.example.qabas18f17994;

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

public class tourist_places_details_ extends AppCompatActivity {

   String keyq="";
   String URLIMG="";
    ImageView placed_img;
    FloatingActionButton B_deleatPlaces,B_updatePlaces;
    TextView placed_id,placed_name,placed_location,placed_information,placed_instructions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tourist_places_details_);

      placed_img=findViewById(R.id.placed_img);
     placed_id=findViewById(R.id.placed_id);
     placed_name=findViewById(R.id.placed_name);
     placed_location=findViewById(R.id.placed_location);
     placed_information=findViewById(R.id.placed_information);
     placed_instructions=findViewById(R.id.placed_instructions);

     B_deleatPlaces=findViewById(R.id.B_deleatPlaces);//this is the delete button
     B_updatePlaces=findViewById(R.id.B_updatePlaces);//this is the update button


     Bundle b=getIntent().getExtras();
     if(b !=null)
     {

         placed_name.setText(b.getString("name"));
         placed_id.setText(b.getString("id"));
         placed_location.setText(b.getString("location"));
         placed_information.setText(b.getString("information"));
         placed_instructions.setText(b.getString("instructions"));
         keyq=b.getString("keyq");
         URLIMG=b.getString("image");
         Glide.with(this).load(b.getString("image")).into(placed_img);
     }
        B_deleatPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference dr= FirebaseDatabase.getInstance().getReference("Tourist places");
                FirebaseStorage fs=FirebaseStorage.getInstance();
                StorageReference sr=fs.getReferenceFromUrl(URLIMG);
                sr.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dr.child(keyq).removeValue();
                        Toast.makeText(tourist_places_details_.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),touristic_places_admin.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(tourist_places_details_.this, "didn't Deleted", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



     B_updatePlaces.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent o=new Intent(tourist_places_details_.this,update_tourist_places.class)
                     .putExtra("name",placed_name.getText().toString())

                     .putExtra("id",placed_id.getText().toString())
                     .putExtra("location",placed_location.getText().toString())
                     .putExtra("information",placed_information.getText().toString())
                     .putExtra("instructions",placed_instructions.getText().toString())
                     .putExtra("keyq",keyq)
                     .putExtra("image",URLIMG)
                     ;
             startActivity(o);




         }
     });
    }
}