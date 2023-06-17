package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    EditText ID,fulllname,email,phone,Password2;
    Button sigein;
    Spinner type2,gender;
    boolean valid=true;//to cheak all the feaild has been reten on
    FirebaseAuth  fAuth; //conncrtion to firbase
    FirebaseFirestore fstore;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

       fAuth=FirebaseAuth.getInstance();
      fstore=FirebaseFirestore.getInstance();

        ID=findViewById(R.id.IDd);
        fulllname=findViewById(R.id.fullname);
        email=findViewById(R.id.emil);
        phone=findViewById(R.id.phone);
        Password2=findViewById(R.id.password2);
        gender=findViewById(R.id.gender2);
        ArrayAdapter<CharSequence> d=ArrayAdapter.createFromResource(this,R.array.Usergender, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        gender.setAdapter(d);
        type2=findViewById(R.id.type2);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.User, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        type2.setAdapter(adapter);



        sigein=findViewById(R.id.sigin);



        sigein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //to cheack if the data valid o
                vb(ID);
                vb(fulllname);
                vb(email);
                vb(phone);
                vb(Password2);


                if(valid)
                {
                    //registered the user
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(),Password2.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {//getUid be on this method only/////////////

                            //object for firbase user
                            FirebaseUser user=fAuth.getCurrentUser();//permission to crate//////////////
                            Toast.makeText(SignUp.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                            //full name and number to the database
                            DocumentReference df=fstore.collection("USERS").document(user.getUid());//this the database

                            // store data by map
                            Map<String,Object>userInfo=new HashMap<>();
                            //give the key to be used on the database on firbase
                            userInfo.put("ID",ID.getText().toString());
                            userInfo.put("FULLName",fulllname.getText().toString());
                            userInfo.put("EMAIL",email.getText().toString());
                            userInfo.put("PHONE",phone.getText().toString());
                            userInfo.put("PASSWORD",Password2.getText().toString());
                            userInfo.put("GENDER",gender.getSelectedItem().toString());
                            userInfo.put("TYPE",type2.getSelectedItem().toString());


                            //specified the user
                            userInfo.put("is USER","1");

                            //to store data and base the object
                            df.set(userInfo);

                            startActivity(new Intent(getApplicationContext(),login.class));


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUp.this, "Error in Created  Account , try again!!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });




    }

    private boolean vb(EditText td) {

        if(td.getText().toString().isEmpty())
        {
            td.setError("Error ");
            valid=false;
        }
        else
        {
            valid=true;
        }
        return valid;
    }


}