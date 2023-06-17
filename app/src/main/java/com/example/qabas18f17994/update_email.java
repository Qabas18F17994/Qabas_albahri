package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class update_email extends AppCompatActivity {
    TextView qemail;
    EditText conformemail;
    Button updatqeE;
    boolean valid=true;

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;//because I retrieve the data by user id
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_email);

        fstore=FirebaseFirestore.getInstance();
        qemail=findViewById(R.id.qemail);
        conformemail =findViewById(R.id.conformemail);
        updatqeE=findViewById(R.id.updatqeE);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        userID=fAuth.getCurrentUser().getUid();
        //get data from firstore
        DocumentReference documentReference=fstore.collection("USERS").document(userID);
        //SnapshotListener to Listen   from the database
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                qemail.setText(documentSnapshot.getString("EMAIL"));

            }
        });
        vb(conformemail);
        updatqeE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid){ String e=qemail.getText().toString();
                    String ce=conformemail.getText().toString();
                    qemail.setText("");
                    conformemail.setText("");
                    updating(e,ce);}


            }
        });


    }
    private void updating(String e,String ce)
    {
        Map<String,Object>usrQ=new HashMap<>();//usrQ is user details
        usrQ.put("EMAIL",ce);

        fstore.collection("USERS").whereEqualTo("EMAIL",e)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()&&!task.getResult().isEmpty())
                {
                    DocumentSnapshot dS=task.getResult().getDocuments().get(0);
                    String docomentid=dS.getId();
                    fstore.collection("USERS")
                            .document(docomentid).update(usrQ)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(update_email.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(update_email.this, "Doesn't Updated", Toast.LENGTH_SHORT).show();

                                }
                            });
                }else
                {
                    Toast.makeText(update_email.this, "Error", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
    private boolean vb(EditText td) {

        if(td.getText().toString().isEmpty())
        {
            td.setError("Error ,The email should include @ ,A_a ,1_9 ");
            valid=false;
        }
        else
        {
            valid=true;
        }
        return valid;
    }
}