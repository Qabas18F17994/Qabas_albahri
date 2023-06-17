package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class u_ta_admin extends AppCompatActivity {

    RecyclerView tourist_activities_recycleview;
    List<data_ta> dataLList_ta;
    DatabaseReference DR;
    ValueEventListener qeventListener;
    SearchView Search;
    adapter_U_TA myadapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_ta_admin);



        tourist_activities_recycleview = findViewById(R.id.tourist_activities_recycleview);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(u_ta_admin.this, 1);
        tourist_activities_recycleview.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder b = new AlertDialog.Builder(u_ta_admin.this);
        b.setCancelable(false);
        b.setView(R.layout.progress_layout);
        AlertDialog d = b.create();
        d.show();


        dataLList_ta = new ArrayList<>();

          myadapter = new adapter_U_TA(u_ta_admin.this, dataLList_ta);
        tourist_activities_recycleview.setAdapter(myadapter);


        DR = FirebaseDatabase.getInstance().getReference("Tourist Activities");
        d.show();


        qeventListener = DR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot s) {
                dataLList_ta.clear();
                for (DataSnapshot iTem : s.getChildren()) {
                    data_ta dataClass = iTem.getValue(data_ta.class);
                    dataClass.setKeyq(iTem.getKey());
                    dataLList_ta.add(dataClass);
                }
                myadapter.notifyDataSetChanged();
                d.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                d.dismiss();

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


        ArrayList<data_ta>searchList=new ArrayList<>();

        for (data_ta dataUserBooking:dataLList_ta)
        {
            if (dataUserBooking.getActivity_day().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }else if(dataUserBooking.getActivity_Name().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }

            else if(dataUserBooking.getActivity_location().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }

        }
        //serchdata is same in adabter
        myadapter.serchdata(searchList);
    }
}