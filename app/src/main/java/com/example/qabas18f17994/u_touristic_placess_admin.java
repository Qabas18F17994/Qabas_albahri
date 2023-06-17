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

public class u_touristic_placess_admin extends AppCompatActivity {

    RecyclerView touristplacess_recycleview;
    List<data_of_Tourist_places> dataLList;
    DatabaseReference DR;
    ValueEventListener qeventListener;

    adapter_TP_user myadapter;
    SearchView Search;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_touristic_placess_admin);


        touristplacess_recycleview=findViewById(R.id.touristplacess_recycleview);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(u_touristic_placess_admin.this,1);
        touristplacess_recycleview.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder b=new AlertDialog.Builder(u_touristic_placess_admin.this);
        b.setCancelable(false);
        b.setView(R.layout.progress_layout);
        AlertDialog d=b.create();
        d.show();



        dataLList=new ArrayList<>();

         myadapter=new adapter_TP_user(u_touristic_placess_admin.this,dataLList);
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


        ArrayList<data_of_Tourist_places>searchList=new ArrayList<>();

        for (data_of_Tourist_places dataUserBooking:dataLList)
        {
            if (dataUserBooking.getPlace_Name().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }else if(dataUserBooking.getPlace_location().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }

        }
        //serchdata is same in adabter
        myadapter.serchdata(searchList);
    }
}