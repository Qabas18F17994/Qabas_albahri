package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class users_ditail extends AppCompatActivity {

    TextView U_id,U_name,U_email,U_phone,U_password,U_gender,U_type;

    FloatingActionButton Butoon_deleat_user;
    String userID;
    FirebaseAuth fAuth=FirebaseAuth.getInstance();
    FirebaseFirestore fstore=FirebaseFirestore.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_ditail);


        U_id=findViewById(R.id.U_id);
        U_name=findViewById(R.id.U_name);
        U_email=findViewById(R.id.U_email);
        U_phone=findViewById(R.id.U_phone);
        U_password=findViewById(R.id.U_password);
        U_gender=findViewById(R.id.U_gender);
        U_type=findViewById(R.id.U_type);
        Butoon_deleat_user=(FloatingActionButton)findViewById(R.id.Butoon_deleat_user);



        Bundle b=getIntent().getExtras();
        if(b !=null)
        {
            U_id.setText(b.getString("ID"));
            U_name.setText(b.getString("FULLName"));
            U_phone.setText(b.getString("PHONE"));
            U_email.setText(b.getString("EMAIL"));
            U_password.setText(b.getString("PASSWORD"));
            U_gender.setText(b.getString("GENDER"));
            U_type.setText(b.getString("TYPE"));

        }



        Butoon_deleat_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletf();

            }
        });
    }


    private  void deletf(){
        userID=U_email.getText().toString();
        fstore.collection("USERS").whereEqualTo("EMAIL",userID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful() && !task.getResult().isEmpty())
                {
                    DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(0);
                    String decomentID =documentSnapshot.getId();
                    fstore.collection("USERS").document(decomentID).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(users_ditail.this, "deleate", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), users_admin.class));
                        }
                    });
                }

            }
        });
   }


}



