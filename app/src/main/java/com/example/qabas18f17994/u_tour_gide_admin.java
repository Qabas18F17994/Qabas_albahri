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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class u_tour_gide_admin extends AppCompatActivity {


    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    RecyclerView tour_gide_recycleview;
    List<data_tour_guid> dataLList;

    ValueEventListener qeventListener;
    SearchView Search;
    adabter_tour_gide myadapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_tour_gide_admin);


        tour_gide_recycleview = findViewById(R.id.tour_gide_recycleview);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(u_tour_gide_admin.this, 1);
        tour_gide_recycleview.setLayoutManager(gridLayoutManager);


        dataLList = new ArrayList<>();

          myadapter = new adabter_tour_gide(u_tour_gide_admin.this, dataLList);
        tour_gide_recycleview.setAdapter(myadapter);


        fstore.collection("Tour Guide").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                data_tour_guid ub = documentSnapshot.toObject(data_tour_guid.class);
                                dataLList.add(ub);
                                myadapter.notifyDataSetChanged();
                            }
                        }
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


        ArrayList<data_tour_guid>searchList=new ArrayList<>();

        for (data_tour_guid dataUserBooking:dataLList)
        {
            if (dataUserBooking.getFULLName().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }else if(dataUserBooking.getGENDER().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }
            else if(dataUserBooking.getPHONE().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }

        }
        //serchdata is same in adabter
        myadapter.serchdata(searchList);
    }
}