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

public class rent_car_add extends AppCompatActivity {


    EditText RC_id,RC_name,RC_company,RC_information,RC_instructions,RC_price;
    ImageView upolode_RC_img;
    Button add_RC;
    String URLimg;
    Uri quri;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_car_add);

        upolode_RC_img=findViewById(R.id.upolode_RC_img);
        RC_id=findViewById(R.id.RC_id);
        RC_name=findViewById(R.id.RC_name);
        RC_company=findViewById(R.id.RC_company);
        RC_information=findViewById(R.id.RC_information);
        RC_instructions=findViewById(R.id.RC_instructions);
        RC_price=findViewById(R.id.RC_price);


        add_RC=findViewById(R.id.add_RC);

        ActivityResultLauncher<Intent> a=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult r) {
                        if (r.getResultCode()== Activity.RESULT_OK)
                        {
                            Intent qimg=r.getData();
                            quri=qimg.getData();
                            upolode_RC_img.setImageURI(quri);
                        }
                        else
                        {
                            Toast.makeText(rent_car_add.this, "No image has selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


        upolode_RC_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chose_picture=new Intent(Intent.ACTION_PICK);//to pic image from the galary of camera
                chose_picture.setType("image/*");
                a.launch(chose_picture);
            }
        });

        add_RC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference storageReference= FirebaseStorage.getInstance().getReference()
                        .child("Rent Cars images").child(quri.getLastPathSegment());
                AlertDialog.Builder builder=new AlertDialog.Builder(rent_car_add.this);
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
        String RC__id=RC_id.getText().toString();
        String RC__name=RC_name.getText().toString();
        String RC__company=RC_company.getText().toString();
        String RC__information=RC_information.getText().toString();
        String RC__instructions=RC_instructions.getText().toString();
        String RC__price=RC_price.getText().toString();



        data_rc data_toris=new data_rc(URLimg,RC__id,RC__name,RC__company,RC__information,RC__instructions,RC__price);

        FirebaseDatabase.getInstance().getReference("Rent Cars").child(RC__id)
                .setValue(data_toris).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> t) {
                        if(t.isSuccessful())
                        {
                            Toast.makeText(rent_car_add.this, "Rent Car is saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(rent_car_add.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });


    }
}

