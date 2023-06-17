package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class profile extends AppCompatActivity {
    TextView p_id,p_name,p_email,p_phone,p_password,p_gender,p_type;
    Button changepass,updateemail,updatephone;
    FirebaseAuth fQAuth;
    FirebaseFirestore fQstore;
    String userQID;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        changepass=findViewById(R.id.changepass);
        updateemail=findViewById(R.id.updateemail);
        updatephone=findViewById(R.id.updatephone);
        p_id=findViewById(R.id.q_id);
        p_name =findViewById(R.id.q_nqme);
        p_email=findViewById(R.id.q_email);
        p_phone=findViewById(R.id.q_phone);
        p_password=findViewById(R.id.q_password);
        p_gender=findViewById(R.id.q_gender);
        p_type=findViewById(R.id.q_type);
        fQAuth=FirebaseAuth.getInstance();
        fQstore=FirebaseFirestore.getInstance();
        userQID=fQAuth.getCurrentUser().getUid();
        DocumentReference ddrr=fQstore.collection("USERS").document(userQID);
        ddrr.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot ddss, @Nullable FirebaseFirestoreException error) {
                p_id.setText(ddss.getString("ID"));
                p_name .setText(ddss.getString("FULLName"));
                p_email.setText(ddss.getString("EMAIL"));
                p_phone.setText(ddss.getString("PHONE"));
                p_password.setText(ddss.getString("PASSWORD"));
                p_gender.setText(ddss.getString("GENDER"));
                p_type.setText(ddss.getString("TYPE"));
            }
        });

        changepass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent inn=new Intent(getApplicationContext(),change_password.class);
                startActivity(inn);

            }
        });

        updateemail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent inn=new Intent(getApplicationContext(), update_email.class);
                startActivity(inn);

            }
        });
        updatephone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent inn=new Intent(getApplicationContext(),change_phone.class);
                startActivity(inn);

            }
        });
    }
    }
