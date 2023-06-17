package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends AppCompatActivity {

    EditText eemail,password;

    Button login,regester;
    boolean valid=true;
    FirebaseAuth fAuth;//main connection
    FirebaseFirestore fStor;//to know if the user admin or not




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        fAuth=FirebaseAuth.getInstance();
        fStor=FirebaseFirestore.getInstance();

        eemail=findViewById(R.id.eemail);
        password=findViewById(R.id.password);

        login=findViewById(R.id.btnLogin);
        regester=findViewById(R.id.buttonRegister);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cff(eemail);
               cff(password);

                Log.d("TAG", "onClick: "+eemail.getText().toString());

                if(valid)
                {
                     //log in based on email and pasword
                    fAuth.signInWithEmailAndPassword(eemail.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            //if user admin will sent to admin activity if the was user will go user page
                           chekUseraccess(authResult.getUser().getUid());

                            Toast.makeText(login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        }


                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


        regester.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent inn=new Intent(getApplicationContext(),SignUp.class);
                startActivity(inn);

            }
        });






}
    private void chekUseraccess(String uid)//uid is user details as name phone and others
    {
        //documents Reference for extract data or store data in firestore
        DocumentReference df=fStor.collection("USERS").document(uid);
        //extract the data from the documents
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess: "+documentSnapshot.getData());
                //check what kind of user
                //is admin and is user is the key to know

                if(documentSnapshot.getString("is ADMIN")!=null)
                {
                    //user is admin
                    startActivity(new Intent(getApplicationContext(),admin_mainPage.class));
                   finish();

                }

                if(documentSnapshot.getString("is USER")!=null)
                {
                    //user is user
                    startActivity(new Intent(getApplicationContext(),user_mainpage.class));
                    finish();

                }

                if(documentSnapshot.getString("is Tour guide")!=null)
                {

                    startActivity(new Intent(getApplicationContext(),tour_guide_mainpage.class));
                    finish();

                }
            }
        });
    }
public boolean cff(EditText tetfild)
{
    if (tetfild.getText().toString().isEmpty())
    {
        tetfild.setError("Error");
        valid=false;
    }
    else
    {valid=true;}
   return valid;
}

//check user is already log in or not
    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() !=null)
        {
            //set the data
            DocumentReference df=FirebaseFirestore.getInstance().collection("USERS").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            //get data of that user and extract data by use get method
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                   //if data retrieved successfully what kind of user is log in
                    if(documentSnapshot.getString("is ADMIN")!=null)
                    {
                        startActivity(new Intent(getApplicationContext(),admin_mainPage.class));
                        finish();
                    }


                    if(documentSnapshot.getString("is USER")!=null)
                    {
                        startActivity(new Intent(getApplicationContext(),user_mainpage.class));
                        finish();
                    }

                    if(documentSnapshot.getString("is Tour guide")!=null)
                    {
                        startActivity(new Intent(getApplicationContext(),tour_guide_mainpage.class));
                        finish();
                    }



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(),login.class));
                    finish();

                }
            });
        }
    }
}