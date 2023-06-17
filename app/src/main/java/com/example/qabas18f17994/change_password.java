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

public class change_password extends AppCompatActivity {
    TextView qpassword;
    EditText newpass;
    Button updatqe;
  boolean valid=true;

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;//because I retrieve the data by user id

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        qpassword=findViewById(R.id.qpassword);
        newpass =findViewById(R.id.newpass);
        updatqe=findViewById(R.id.updatqe);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        userID=fAuth.getCurrentUser().getUid();



        //get data from firstore
        DocumentReference documentReference=fstore.collection("USERS").document(userID);
        //SnapshotListener to Listen   from the database
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                qpassword.setText(documentSnapshot.getString("PASSWORD"));

            }
        });



        vb(newpass);

        updatqe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid)
                {
                    String e=qpassword.getText().toString();
                    String ce=newpass.getText().toString();
                    qpassword.setText("");
                    newpass.setText("");
                    updating(e,ce);

                }


            }
        });

    }
    private void updating(String e,String ce)
    {
        Map<String,Object> usrQ=new HashMap<>();//usrQ is user details
        usrQ.put("PASSWORD",ce);

        fstore.collection("USERS").whereEqualTo("PASSWORD",e)
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
                                            Toast.makeText(change_password.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(change_password.this, "Doesn't Updated", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                        }else
                        {
                            Toast.makeText(change_password.this, "Error", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }





    private boolean vb(EditText td) {

        if(td.getText().toString().isEmpty())
        {
            td.setError("The password should be 8 character");
            valid=false;
        }
        else
        {
            valid=true;
        }
        return valid;
    }
}