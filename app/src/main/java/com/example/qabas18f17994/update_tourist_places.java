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

public class update_tourist_places extends AppCompatActivity {

    ImageView upd_placed_img;
    Button upldd_placed;
    EditText upd_placed_name,upd_placed_id,upd_placed_location,upd_placed_information,upd_placed_instructions;
    String qname,qid,qloc,qimfo,qins;
    String URLIMG;
    String keyq,IMG_OLD;
    Uri quri;
    DatabaseReference DR;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_tourist_places);

        upd_placed_name=findViewById(R.id.upd_placed_name);
        upd_placed_img=findViewById(R.id.upd_placed_img);
        upd_placed_id=findViewById(R.id.upd_placed_id);
        upd_placed_location=findViewById(R.id.upd_placed_location);
        upd_placed_information=findViewById(R.id.upd_placed_information);
        upd_placed_instructions=findViewById(R.id.upd_placed_instructions);


        upldd_placed=findViewById(R.id.upldd_placed);


        ActivityResultLauncher<Intent>a=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult r) {


                        if(r.getResultCode()== Activity.RESULT_OK)
                        {
                            Intent I =r.getData();
                            quri=I.getData();
                            upd_placed_img.setImageURI(quri);
                        }
                        else
                        {
                            Toast.makeText(update_tourist_places.this, "No img selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        Bundle n=getIntent().getExtras();
        if(n!=null)
        {
            Glide.with(update_tourist_places.this).load(n.getString("image")).into(upd_placed_img);
            upd_placed_name.setText(n.getString("name"));
            upd_placed_id.setText(n.getString("id"));
            upd_placed_location.setText(n.getString("location"));
            upd_placed_information.setText(n.getString("information"));
            upd_placed_instructions.setText(n.getString("instructions"));
            IMG_OLD=n.getString("image");
        }

        String id=upd_placed_id.getText().toString();
        DR= FirebaseDatabase.getInstance().getReference("Tourist places").child(id);

        upd_placed_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IMG=new Intent(Intent.ACTION_PICK);//gt photo from galary
                IMG.setType("image/*");
                a.launch(IMG);
            }
        });

        upldd_placed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theSAVINGofdata();
                Intent n=new Intent(update_tourist_places.this,touristic_places_admin.class);
                startActivity(n);
            }
        });

    }


    public void theSAVINGofdata()
    {
        StorageReference storageReference= FirebaseStorage.getInstance().getReference()
                 .child("Tourist places images").child(quri.getLastPathSegment());
        AlertDialog.Builder b=new AlertDialog.Builder(update_tourist_places.this);
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
         qname=upd_placed_name.getText().toString();
         qid=upd_placed_id.getText().toString();
         qloc=upd_placed_location.getText().toString();
         qimfo=upd_placed_information.getText().toString();
         qins=upd_placed_instructions.getText().toString();

        data_of_Tourist_places  dat=new data_of_Tourist_places(URLIMG,qid,qname,qloc,qimfo,qins);

        DR.setValue(dat).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> t) {


                if (t.isSuccessful())
                {
                    StorageReference sr=FirebaseStorage.getInstance().getReferenceFromUrl(IMG_OLD);
                    sr.delete();
                    Toast.makeText(update_tourist_places.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(update_tourist_places.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}