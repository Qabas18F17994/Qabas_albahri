package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.os.Bundle;

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

public class booking_list_admin extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;

    RecyclerView user_booking_recycleview;
    List<data_user_booking> dataLList;

    SearchView Search;
    adapter_tourgide_list myadapter;
    adabter_user_booking myadapter2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_list_admin);

        user_booking_recycleview = findViewById(R.id.user_booking_recycleview);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(booking_list_admin.this, 1);
        user_booking_recycleview.setLayoutManager(gridLayoutManager);


        dataLList = new ArrayList<>();

        myadapter = new adapter_tourgide_list(booking_list_admin.this, dataLList);
        user_booking_recycleview.setAdapter(myadapter);



        myadapter2 = new adabter_user_booking(booking_list_admin.this, dataLList);
        user_booking_recycleview.setAdapter(myadapter2);

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






        fstore.collectionGroup("BOOKING LIST").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        String decomentID = documentSnapshot.getId();
                        data_user_booking ub = documentSnapshot.toObject(data_user_booking.class);
                        ub.setDecomentID(decomentID);

                        dataLList.add(ub);
                        myadapter2.notifyDataSetChanged();
                    }
                }
            }
        });


        fstore.collectionGroup("TOUR GUID BOOKING LIST").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        String decomentID = documentSnapshot.getId();
                        data_user_booking ub = documentSnapshot.toObject(data_user_booking.class);
                        ub.setDecomentID(decomentID);

                        dataLList.add(ub);
                        myadapter.notifyDataSetChanged();
                    }
                }
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

        }
        //serchdata is same in adapter
        myadapter.serchdata(searchList);
    }


}
