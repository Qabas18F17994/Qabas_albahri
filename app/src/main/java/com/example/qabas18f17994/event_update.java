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

public class event_update extends AppCompatActivity {

    ImageView upd_E_img;
    Button upldd_EVENTe;
    EditText upd_E_name,upd_E_id,upd_E_location,upd_E_information;
    String qname,qid,qloc,qimfo;
    String URLIMG;
    String keyq,IMG_OLD;
    Uri quri;
    DatabaseReference DR;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_update);


        upd_E_name=findViewById(R.id.upd_E_name);
        upd_E_img=findViewById(R.id.upd_E_img);
        upd_E_id=findViewById(R.id.upd_E_id);
        upd_E_location=findViewById(R.id.upd_E_location);
        upd_E_information=findViewById(R.id.upd_E_information);


        upldd_EVENTe=findViewById(R.id.upldd_EVENTe);


        ActivityResultLauncher<Intent> a=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult r) {


                        if(r.getResultCode()== Activity.RESULT_OK)
                        {
                            Intent I =r.getData();
                            quri=I.getData();
                            upd_E_img.setImageURI(quri);
                        }
                        else
                        {
                            Toast.makeText(event_update.this, "No image has selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        Bundle n=getIntent().getExtras();
        if(n!=null)
        {
            Glide.with(event_update.this).load(n.getString("image")).into(upd_E_img);
            upd_E_name.setText(n.getString("name"));
            upd_E_id.setText(n.getString("id"));
            upd_E_location.setText(n.getString("location"));
            upd_E_information.setText(n.getString("information"));
            IMG_OLD=n.getString("image");
        }

        String id=upd_E_id.getText().toString();
        DR= FirebaseDatabase.getInstance().getReference("Events").child(id);

        upd_E_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IMG=new Intent(Intent.ACTION_PICK);//gt photo from galary
                IMG.setType("image/*");
                a.launch(IMG);
            }
        });

        upldd_EVENTe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theSAVINGofdata();
                Intent n=new Intent(event_update.this,event_admin.class);
                startActivity(n);

            }
        });

    }


    public void theSAVINGofdata()
    {
        StorageReference storageReference= FirebaseStorage.getInstance().getReference()
                .child("Events images").child(quri.getLastPathSegment());
        AlertDialog.Builder b=new AlertDialog.Builder(event_update.this);
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
        qname=upd_E_name.getText().toString();
        qid=upd_E_id.getText().toString();
        qloc=upd_E_location.getText().toString();
        qimfo=upd_E_information.getText().toString();


        data_events  dat=new data_events(URLIMG,qid,qname,qloc,qimfo);

        DR.setValue(dat).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> t) {


                if (t.isSuccessful())
                {
                    StorageReference sr=FirebaseStorage.getInstance().getReferenceFromUrl(IMG_OLD);
                    sr.delete();
                    Toast.makeText(event_update.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(event_update.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}