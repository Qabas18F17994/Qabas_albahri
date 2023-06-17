package com.example.qabas18f17994;

import android.annotation.SuppressLint;
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

public class event_add extends AppCompatActivity {

    EditText place_idE,place_nameE,place_locationE,place_informationE;
    ImageView place_imgE;
    Button add_E;
    String URLimg;
    Uri quri;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_add);

        place_imgE=findViewById(R.id.place_imgE);
        place_idE=findViewById(R.id.place_idE);
        place_nameE=findViewById(R.id.place_nameE);
        place_locationE=findViewById(R.id.place_locationE);
        place_informationE=findViewById(R.id.place_informationE);


        add_E=findViewById(R.id.add_E);

        ActivityResultLauncher<Intent> a=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult r) {
                        if (r.getResultCode()== Activity.RESULT_OK)
                        {
                            Intent qimg=r.getData();
                            quri=qimg.getData();
                            place_imgE.setImageURI(quri);
                        }
                        else
                        {
                            Toast.makeText(event_add.this, "No image has selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


        place_imgE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chose_picture=new Intent(Intent.ACTION_PICK);//to pic image from the image gallery of phone
                chose_picture.setType("image/*");
                a.launch(chose_picture);
            }
        });

        add_E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference storageReference= FirebaseStorage.getInstance().getReference()
                        .child("Events images").child(quri.getLastPathSegment());
                AlertDialog.Builder builder=new AlertDialog.Builder(event_add.this);
                builder.setCancelable(false);
                builder.setView(R.layout.progress_layout);//this layout that i build th show when it saved
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
        String E__id=place_idE.getText().toString();
        String E__name=place_nameE.getText().toString();
        String E__location=place_locationE.getText().toString();
        String E__information=place_informationE.getText().toString();



        data_events data_toris=new data_events(URLimg,E__id,E__name,E__location,E__information);

        FirebaseDatabase.getInstance().getReference("Events").child(E__id)
                .setValue(data_toris).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> t) {
                        if(t.isSuccessful())
                        {
                            Toast.makeText(event_add.this, "Event saved successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(event_add.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });


    }
    }
