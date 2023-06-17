package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class tour_gide_information extends AppCompatActivity {

    TextView TG_id ,TG_name, TG_email, TG_phone ,TG_gender ;
    EditText TG_information, TG_price;

    Button B_add_TG,B_update_TG;

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;//becuse we reteve the data by user id

    boolean valid=true;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_gide_information);
        TG_id = findViewById(R.id.TG_id);
        TG_name = findViewById(R.id.TG_name);
        TG_email = findViewById(R.id.TG_email);
        TG_phone = findViewById(R.id.TG_phone);
        TG_gender = findViewById(R.id.TG_gender);
        TG_information = findViewById(R.id.TG_information);
        TG_price = findViewById(R.id.TG_price);
        B_update_TG= findViewById(R.id.B_update_TG);
        B_add_TG = findViewById(R.id.B_add_TG);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("USERS").document(userID);
        //snaplesnor to lesen  from the database
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                TG_id.setText(documentSnapshot.getString("ID"));
                TG_name.setText(documentSnapshot.getString("FULLName"));
                TG_email.setText(documentSnapshot.getString("EMAIL"));
                TG_phone.setText(documentSnapshot.getString("PHONE"));
                TG_gender.setText(documentSnapshot.getString("GENDER"));


            }
        });


        B_update_TG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to cheack if the data valid o
                vb(TG_information);
                vb(TG_price);


                if(valid)
                {
                    if(TG_information.equals("")||TG_price.equals("")){
                        Toast.makeText(tour_gide_information.this, "Can not Enter", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        final HashMap<String,Object> userbooking=new HashMap<>();
                        //give the key to be used on the database on firbase
                        userbooking.put("ID",TG_id.getText().toString());
                        userbooking.put("FULLName",TG_name.getText().toString());
                        userbooking.put("EMAIL",TG_email.getText().toString());
                        userbooking.put("PHONE",TG_phone.getText().toString());
                        userbooking.put("GENDER",TG_gender.getText().toString());
                        userbooking.put("INFORMATION",TG_information.getText().toString());
                        userbooking.put("PRICE",TG_price.getText().toString());




                        fstore.collection("Tour Guide").document(fAuth.getCurrentUser().getUid()).update(userbooking).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(tour_gide_information.this, " Updated  ", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }


                }






            }
        });
        B_add_TG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to cheack if the data valid o
                vb(TG_information);
                vb(TG_price);


                if(valid)
                {
                    if(TG_information.equals("")||TG_price.equals("")){
                        Toast.makeText(tour_gide_information.this, "Can not Enter", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        final HashMap<String,Object> userbooking=new HashMap<>();
                        //give the key to be used on the database on firbase
                        userbooking.put("ID",TG_id.getText().toString());
                        userbooking.put("FULLName",TG_name.getText().toString());
                        userbooking.put("EMAIL",TG_email.getText().toString());
                        userbooking.put("PHONE",TG_phone.getText().toString());
                        userbooking.put("GENDER",TG_gender.getText().toString());
                        userbooking.put("INFORMATION",TG_information.getText().toString());
                        userbooking.put("PRICE",TG_price.getText().toString());




                        fstore.collection("Tour Guide").document(fAuth.getCurrentUser().getUid()).set(userbooking).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(tour_gide_information.this, " Enter  ", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }


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
