package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class user_booking_tourgide extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;

    ImageView home_button;

    RecyclerView user_booking_recycleview;
    List<data_user_booking> dataLList;

    SearchView Search;
    adabter_user_bookig_tourguid myadapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_booking_tourgide);
        home_button=findViewById(R.id.home_button);
        user_booking_recycleview=findViewById(R.id.user_booking_recycleview);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        GridLayoutManager gridLayoutManager=new GridLayoutManager(user_booking_tourgide.this,1);
        user_booking_recycleview.setLayoutManager(gridLayoutManager);




        dataLList=new ArrayList<>();

         myadapter=new adabter_user_bookig_tourguid(user_booking_tourgide.this,dataLList);
        user_booking_recycleview.setAdapter(myadapter);




        fstore.collection("Booking").
                document(fAuth.getCurrentUser().getUid())
                .collection("TOUR GUID BOOKING LIST").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())


                        {
                            for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                            {
                                data_user_booking ub=documentSnapshot.toObject(data_user_booking.class);
                                String decomentfID=documentSnapshot.getId();
                                ub.setDecomentID(decomentfID);
                                dataLList.add(ub);
                                myadapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inn=new Intent(getApplicationContext(),user_mainpage.class);
                startActivity(inn);
                finish();
            }
        });


        Search= findViewById(R.id.Search);
        Search.clearFocus();
        Search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                serchList(newText);
                return true;
            }
        });

    }

    public void serchList(String iddd)
    {


        ArrayList<data_user_booking>searchList=new ArrayList<>();

        for (data_user_booking dataUserBooking:dataLList)
        {
            if (dataUserBooking.getBooking_Type_id().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }else if(dataUserBooking.getBooking_Type().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }

            else if(dataUserBooking.getBooking_name().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }

        }
        //serchdata is same in adabter
        myadapter.serchdata(searchList);
    }
}