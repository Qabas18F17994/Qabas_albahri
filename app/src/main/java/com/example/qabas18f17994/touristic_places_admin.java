package com.example.qabas18f17994;

import android.annotation.SuppressLint;
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

public class touristic_places_admin extends AppCompatActivity {

   FloatingActionButton Butoon_uplode_place;
   RecyclerView touristplacess_recycleview;
   List<data_of_Tourist_places>dataLList;
   DatabaseReference DR;
   ValueEventListener qeventListener;
    SearchView Search;


    adaptre_tp myadapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touristic_places_admin);

        Butoon_uplode_place=findViewById(R.id.Butoon_uplode_place);
        touristplacess_recycleview=findViewById(R.id.touristplacess_recycleview);
        Search= findViewById(R.id.Search);
        Search.clearFocus();

        GridLayoutManager gridLayoutManager=new GridLayoutManager(touristic_places_admin.this,1);
        touristplacess_recycleview.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder b=new AlertDialog.Builder(touristic_places_admin.this);
        b.setCancelable(false);
        b.setView(R.layout.progress_layout);
        AlertDialog d=b.create();
        d.show();



        dataLList=new ArrayList<>();

         myadapter=new adaptre_tp(touristic_places_admin.this,dataLList);
        touristplacess_recycleview.setAdapter(myadapter);


        DR= FirebaseDatabase.getInstance().getReference("Tourist places");
        d.show();


        qeventListener=DR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot s) {
                      dataLList.clear();
                      for (DataSnapshot iTem:s.getChildren())
                      {
                          data_of_Tourist_places dataClass=iTem.getValue(data_of_Tourist_places.class);
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

        Butoon_uplode_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(touristic_places_admin.this,upload_touristic_places.class);
                startActivity(i);
            }
        });


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


        ArrayList<data_of_Tourist_places>searchList=new ArrayList<>();

        for (data_of_Tourist_places dataUserBooking:dataLList)
        {
            if (dataUserBooking.getPlace_ID().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }else if(dataUserBooking.getPlace_Name().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }

        }
        //serchdata is same in adabter
        myadapter.serchdata(searchList);
    }

}