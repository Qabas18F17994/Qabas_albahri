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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class  u_ta_booking extends AppCompatActivity {

  TextView booking_type, B_name,B_phone,B_type,B_ID,B_price ,B_day,B_quantity,B_totalprice;
  Button B_booked;
    DatabaseReference DR;

    //int B_totalPrice=0;

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;//becuse we reteve the data by user id



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_ta_booking);
        booking_type=findViewById(R.id.booking_type);
        B_name=findViewById(R.id.B_name);
        B_phone=findViewById(R.id.B_phone);
        B_type=findViewById(R.id.B_type);
        B_ID=findViewById(R.id.B_ID);
        B_price=findViewById(R.id.B_price);
        B_day=findViewById(R.id.B_day);
        B_quantity=findViewById(R.id.B_quantity);

        B_booked=findViewById(R.id.B_booked);

        B_totalprice=findViewById(R.id.B_totalprice);






        fAuth= FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();


        Bundle n=getIntent().getExtras();
        if(n!=null)
        {

            B_type.setText(n.getString("name"));
            booking_type.setText(n.getString("type"));
            B_ID.setText(n.getString("id"));
            B_price.setText(n.getString("price"));
            B_day.setText(n.getString("day"));
            B_quantity.setText(n.getString("Quantity"));
            B_totalprice.setText(n.getString("total_price"));


        }
        String id=B_ID.getText().toString();
        DR= FirebaseDatabase.getInstance().getReference("Tourist Activities").child(id);



        userID=fAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fstore.collection("USERS").document(userID);
        //snaplesnor to lesen  from the database
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                B_name .setText(documentSnapshot.getString("FULLName"));

                B_phone.setText(documentSnapshot.getString("PHONE"));

            }
        });


        B_booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addTObooking();

            }
        });

    }

    private void addTObooking() {
        final HashMap<String,Object>userbooking=new HashMap<>();
        //give the key to be used on the database on firbase
        userbooking.put("FULLName",B_name.getText().toString());
        userbooking.put("PHONE",B_phone.getText().toString());
        userbooking.put("Booking_Type",booking_type.getText().toString());
        userbooking.put("Booking_name",B_type.getText().toString());
        userbooking.put("Booking_Type_id",B_ID.getText().toString());
        userbooking.put("price",B_price.getText().toString());
        userbooking.put("Day_of_Booking",B_day.getText().toString());
        userbooking.put("Quantity",B_quantity.getText().toString());
        userbooking.put("Total_Price",B_totalprice.getText().toString());

  fstore.collection("Booking").document(fAuth.getCurrentUser().getUid()).collection("BOOKING LIST").add(userbooking)
          .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {


              @Override
              public void onComplete(@NonNull Task<DocumentReference> task) {


                  Toast.makeText(u_ta_booking.this, "Add to Booking List", Toast.LENGTH_SHORT).show();
                  Intent inn=new Intent(getApplicationContext(),user_booking.class);
                  startActivity(inn);
              }

          });


    }











}