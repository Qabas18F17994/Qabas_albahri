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

public class rent_car_update extends AppCompatActivity {

    ImageView upd_RC_img;
    Button upldd_RC;
    EditText upd_RC_name,upd_RC_id,upd_RC_company,upd_RC_information,upd_RC_instructions,upd_RC_price;
    String qname,qid,qcom,qimfo,qins,qprice;
    String URLIMG;
    String keyq,IMG_OLD;
    Uri quri;
    DatabaseReference DR;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_car_update);


        upd_RC_name=findViewById(R.id.upd_RC_name);
        upd_RC_img=findViewById(R.id.upd_RC_img);
        upd_RC_id=findViewById(R.id.upd_RC_id);
        upd_RC_company=findViewById(R.id.upd_RC_company);
        upd_RC_information=findViewById(R.id.upd_RC_information);
        upd_RC_instructions=findViewById(R.id.upd_RC_instructions);
        upd_RC_price=findViewById(R.id.upd_RC_price);




        upldd_RC=findViewById(R.id.upldd_RC);


        ActivityResultLauncher<Intent> a=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult r) {


                        if(r.getResultCode()== Activity.RESULT_OK)
                        {
                            Intent I =r.getData();
                            quri=I.getData();
                            upd_RC_img.setImageURI(quri);
                        }
                        else
                        {
                            Toast.makeText(rent_car_update.this, "No image has selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        Bundle n=getIntent().getExtras();
        if(n!=null)
        {
            Glide.with(rent_car_update.this).load(n.getString("image")).into(upd_RC_img);
            upd_RC_name.setText(n.getString("name"));
            upd_RC_id.setText(n.getString("id"));
            upd_RC_company.setText(n.getString("company"));
            upd_RC_information.setText(n.getString("information"));
            upd_RC_instructions.setText(n.getString("instructions"));
            upd_RC_price.setText(n.getString("price"));

            IMG_OLD=n.getString("image");
        }

        String id=upd_RC_id.getText().toString();
        DR= FirebaseDatabase.getInstance().getReference("Rent Cars").child(id);

        upd_RC_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IMG=new Intent(Intent.ACTION_PICK);//gt photo from galary
                IMG.setType("image/*"); //* means the png jpg the type of picture
                a.launch(IMG);
            }
        });

        upldd_RC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theSAVINGofdata();
                Intent n=new Intent(rent_car_update.this,rent_car_admin.class);
                startActivity(n);

            }
        });

    }


    public void theSAVINGofdata()
    {
        StorageReference storageReference= FirebaseStorage.getInstance().getReference()
                .child("Rent Cars images").child(quri.getLastPathSegment());
        AlertDialog.Builder b=new AlertDialog.Builder(rent_car_update.this);
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
        qname=upd_RC_name.getText().toString();
        qid=upd_RC_id.getText().toString();
        qcom=upd_RC_company.getText().toString();
        qimfo=upd_RC_information.getText().toString();
        qins=upd_RC_instructions.getText().toString();
        qprice=upd_RC_price.getText().toString();


        data_rc  dat=new data_rc(URLIMG,qid,qname,qcom,qimfo,qins,qprice);

        DR.setValue(dat).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> t) {


                if (t.isSuccessful())
                {
                    StorageReference sr=FirebaseStorage.getInstance().getReferenceFromUrl(IMG_OLD);
                    sr.delete();
                    Toast.makeText(rent_car_update.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(rent_car_update.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    }
