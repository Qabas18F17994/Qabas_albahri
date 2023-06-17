package com.example.qabas18f17994;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class event_admin extends AppCompatActivity {
    FloatingActionButton Butoon_uplode_event;
    RecyclerView event_recycleview;
    List<data_events> dataLList;
    DatabaseReference DR;
    ValueEventListener qeventListener;

    SearchView Search;
    adabter_Event myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_admin);

        Butoon_uplode_event=findViewById(R.id.Butoon_uplode_event);
        event_recycleview=findViewById(R.id.event_recycleview);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(event_admin.this,1);
        event_recycleview.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder b=new AlertDialog.Builder(event_admin.this);
        b.setCancelable(false);
        b.setView(R.layout.progress_layout);
        AlertDialog d=b.create();
        d.show();



        dataLList=new ArrayList<>();

         myadapter=new adabter_Event(event_admin.this,dataLList);
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

        Butoon_uplode_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(event_admin.this,event_add.class);
                startActivity(i);
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