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

public class u_event_admin extends AppCompatActivity {
    RecyclerView event_recycleview;
    List<data_events> dataLList;
    DatabaseReference DR;
    ValueEventListener qeventListener;

    SearchView Search;
    adabter_Evnts myadapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_event_admin);
        event_recycleview=findViewById(R.id.event_recycleview);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(u_event_admin.this,1);
        event_recycleview.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder b=new AlertDialog.Builder(u_event_admin.this);
        b.setCancelable(false);
        b.setView(R.layout.progress_layout);
        AlertDialog d=b.create();
        d.show();



        dataLList=new ArrayList<>();

          myadapter=new adabter_Evnts(u_event_admin.this,dataLList);
        event_recycleview.setAdapter(myadapter);


        DR= FirebaseDatabase.getInstance().getReference("Events");
        d.show();


        qeventListener=DR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot s) {
                dataLList.clear();
                for (DataSnapshot iTem:s.getChildren())
                {
                    data_events dataClass=iTem.getValue(data_events.class);
                    dataClass.setKeyq(iTem.getKey());
                    dataLList.add(dataClass);
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


        ArrayList<data_events>searchList=new ArrayList<>();

        for (data_events dataUserBooking:dataLList)
        {
            if (dataUserBooking.getEvent_ID().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }else if(dataUserBooking.getEvent_Name().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }

        }
        //serchdata is same in adabter
        myadapter.serchdata(searchList);
    }
}