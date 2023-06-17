package com.example.qabas18f17994;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class upload_touristic_places extends AppCompatActivity {

    EditText place_id,place_name,place_location,place_information,place_instructions;
    ImageView upolode_img;
    Button add_place;
    String URLimg;
    Uri quri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_touristic_places);

        upolode_img=findViewById(R.id.upolode_img);
        place_id=findViewById(R.id.place_id);
        place_name=findViewById(R.id.place_name);
        place_location=findViewById(R.id.place_location);
        place_information=findViewById(R.id.place_information);
        place_instructions=findViewById(R.id.place_instructions);

        add_place=findViewById(R.id.add_place);

        ActivityResultLauncher<Intent>a=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult r) {
                        if (r.getResultCode()== Activity.RESULT_OK)
                        {
                            Intent qimg=r.getData();
                            quri=qimg.getData();
                            upolode_img.setImageURI(quri);
                        }
                        else
                        {
                            Toast.makeText(upload_touristic_places.this, "No image has selected ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


        upolode_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chose_picture=new Intent(Intent.ACTION_PICK);//to pic image from the galary of camera
                chose_picture.setType("image/*");
                a.launch(chose_picture);
            }
        });

        add_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference storageReference= FirebaseStorage.getInstance().getReference()
                        .child("Tourist places images").child(quri.getLastPathSegment());
                AlertDialog.Builder builder=new AlertDialog.Builder(upload_touristic_places.this);
                builder.setCancelable(false);
                builder.setView(R.layout.progress_layout);//this layout that i bild th show when it saved
                AlertDialog dialog=builder.create();
                dialog.show();


                storageReference.putFile(quri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot ts) {


                        Task<Uri>uriTask=ts.getStorage().getDownloadUrl();
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
         String place__id=place_id.getText().toString();
         String place__name=place_name.getText().toString();
         String place__location=place_location.getText().toString();
         String place__information=place_information.getText().toString();
         String place__instructions=place_instructions.getText().toString();


         data_of_Tourist_places data_toris=new data_of_Tourist_places(URLimg,place__id,place__name,place__location,place__information,place__instructions);

         FirebaseDatabase.getInstance().getReference("Tourist places").child(place__id)
                 .setValue(data_toris).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> t) {
                         if(t.isSuccessful())
                         {
                             Toast.makeText(upload_touristic_places.this, "Tourist places saved", Toast.LENGTH_SHORT).show();
                             finish();
                         }
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(upload_touristic_places.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                     }
                 });


     }
}