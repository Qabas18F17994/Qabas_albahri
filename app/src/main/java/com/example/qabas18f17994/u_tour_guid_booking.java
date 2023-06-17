package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class u_tour_guid_booking extends AppCompatActivity {
    TextView booking_type_tg2,B_name,B_phone,B_typebokking,B_ID,B_price ,B_day,B_quantity,B_totalprice;
    Button B_booked;



    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;//becuse we reteve the data by user id

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_tour_guid_booking);

        booking_type_tg2=findViewById(R.id.booking_type_tg2);

        B_name=findViewById(R.id.B_name);
        B_phone=findViewById(R.id.B_phone);
        B_typebokking=findViewById(R.id.B_typebokking);
        B_ID=findViewById(R.id.B_ID);
        B_price=findViewById(R.id.B_price);
        B_day=findViewById(R.id.B_day);
        B_quantity=findViewById(R.id.B_quantity);
        B_totalprice=findViewById(R.id.B_totalprice);
        B_booked=findViewById(R.id.B_booked);




        fAuth= FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();


        Bundle nw=getIntent().getExtras();
        if(nw!=null)
        {
            booking_type_tg2.setText(nw.getString("type_tg"));
            B_typebokking.setText(nw.getString("FULLName_tg"));//name of tour guid
            B_ID.setText(nw.getString("ID_tg"));//of tour guid
            B_price.setText(nw.getString("PRICE_tg"));//price of tour guid
            B_day.setText(nw.getString("Days_tg"));//days booking of tour guid
            B_quantity.setText(nw.getString("Quantity_tg"));// booking of tour guid
            B_totalprice.setText(nw.getString("TotalPrice_tg"));//the total after the calculation of tour guid

        }




        userID=fAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fstore.collection("USERS").document(userID);
        //snaplesnor to lesen  from the database
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                B_name .setText(documentSnapshot.getString("FULLName")); //name of user

                B_phone.setText(documentSnapshot.getString("PHONE"));//phone

            }
        });


        B_booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addTObookingtourgide();
            }
        });

    }



    private void addTObookingtourgide() {


        final HashMap<String,Object>userbooking=new HashMap<>();
        //give the key to be used on the database on firbase
        userbooking.put("FULLName",B_name.getText().toString());
        userbooking.put("PHONE",B_phone.getText().toString());
        userbooking.put("Booking_Type",booking_type_tg2.getText().toString());
        userbooking.put("Booking_name",B_typebokking.getText().toString());
        userbooking.put("Booking_Type_id",B_ID.getText().toString());
        userbooking.put("price",B_price.getText().toString());
        userbooking.put("Day_of_Booking",B_day.getText().toString());
        userbooking.put("Quantity",B_quantity.getText().toString());
        userbooking.put("Total_Price",B_totalprice.getText().toString());

        fstore.collection("Booking").document(fAuth.getCurrentUser().getUid()).collection("TOUR GUID BOOKING LIST").add(userbooking)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(u_tour_guid_booking.this, "Add to Booking List", Toast.LENGTH_SHORT).show();
                        Intent inn=new Intent(getApplicationContext(),user_booking_tourgide.class);
                        startActivity(inn);
                    }

                });


    }
    }
