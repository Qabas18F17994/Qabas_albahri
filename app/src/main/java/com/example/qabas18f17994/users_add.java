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

public class users_add extends AppCompatActivity {

    EditText IDTG,fullnameTG,emilTG,phoneTG,password2TG;
    Button siginTG;
    Spinner type2TG,gender2TG;
    boolean valid=true;//to cheak all the feaild has been reten on
    FirebaseAuth fAuth; //conncrtion to firbase
    FirebaseFirestore fstore;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_add);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        IDTG=findViewById(R.id.IDTG);
        fullnameTG=findViewById(R.id.fullnameTG);
        emilTG=findViewById(R.id.emilTG);
        phoneTG=findViewById(R.id.phoneTG);
        password2TG=findViewById(R.id.password2TG);
        gender2TG=findViewById(R.id.gender2TG);
        ArrayAdapter<CharSequence> d=ArrayAdapter.createFromResource(this,R.array.Usergender, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        gender2TG.setAdapter(d);
        type2TG=findViewById(R.id.type2TG);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.UserTYPE, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        type2TG.setAdapter(adapter);



        siginTG=findViewById(R.id.siginTG);


        siginTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //to cheack if the data valid o
                vb(IDTG);
                vb(fullnameTG);
                vb(emilTG);
                vb(phoneTG);
                vb(password2TG);



                if(valid)
                {
                    //registered the user
                    fAuth.createUserWithEmailAndPassword(emilTG.getText().toString(),password2TG.getText().toString())//////////
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {/////////
                                @Override
                                public void onSuccess(AuthResult authResult) {//getUid be on this method only/////////////

                                    //object for firbase user
                                    FirebaseUser user=fAuth.getCurrentUser();//permission to crate//////////////
                                    Toast.makeText(users_add.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                    //full name and number to the database
                                    DocumentReference df=fstore.collection("USERS").document(user.getUid());//this the databse

                                    // stor data by map
                                    Map<String,Object> userInfo=new HashMap<>();
                                    //give the key to be used on the database on firbase
                                    userInfo.put("ID",IDTG.getText().toString());
                                    userInfo.put("FULLName",fullnameTG.getText().toString());
                                    userInfo.put("EMAIL",emilTG.getText().toString());
                                    userInfo.put("PHONE",phoneTG.getText().toString());
                                    userInfo.put("PASSWORD",password2TG.getText().toString());
                                    userInfo.put("GENDER",gender2TG.getSelectedItem().toString());
                                    userInfo.put("TYPE",type2TG.getSelectedItem().toString());

                                    //specified the user
                                    userInfo.put("is Tour guide","1");

                                    //to store data and base the object
                                    df.set(userInfo);

                                    //after the data saved in the firstore now will handle it to other page to add more information
                                    startActivity(new Intent(getApplicationContext(), users_admin.class));



                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(users_add.this, " Error in Created  Account , try again!!", Toast.LENGTH_SHORT).show();
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
