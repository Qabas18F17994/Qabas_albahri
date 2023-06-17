package com.example.qabas18f17994;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class users_admin extends AppCompatActivity {

    FloatingActionButton Butoon_adde_user;
    RecyclerView user_recycleview;
    List<data_U> dataLList;
    FirebaseFirestore fstore=FirebaseFirestore.getInstance();

    adabter_U myadapter;

    SearchView Search;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_admin);


        Butoon_adde_user=findViewById(R.id.Butoon_adde_user);
        user_recycleview=findViewById(R.id.user_recycleview);


        user_recycleview.setLayoutManager(new LinearLayoutManager(this));

        fstore=FirebaseFirestore.getInstance();
        dataLList = new ArrayList<>();
        myadapter = new adabter_U(users_admin.this, dataLList);

        user_recycleview.setAdapter(myadapter);



        Butoon_adde_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(users_admin.this, users_add.class);
                startActivity(i);
            }
        });

        show();


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


        ArrayList<data_U>searchList=new ArrayList<>();

        for (data_U dataUserBooking:dataLList)
        {
            if (dataUserBooking.getID().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }
            else if(dataUserBooking.getEMAIL().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }
            else if(dataUserBooking.getFULLName().contains(iddd.toString()))
            {
                searchList.add(dataUserBooking);
            }

        }
        //serchdata is same in adabter
        myadapter.serchdata(searchList);
    }

    private void show()
    {

        AlertDialog.Builder b = new AlertDialog.Builder(users_admin.this);
        b.setCancelable(false);
        b.setView(R.layout.progress_layout);
        AlertDialog d = b.create();
        d.show();

        fstore.collection("USERS").orderBy("ID", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                              if (error !=null)
                              {
                                  Log.e(" Error in Firebase", error.getMessage());
                                  return;
                              }


                              for (DocumentChange dc:value.getDocumentChanges())
                              {
                                  if(dc.getType()==DocumentChange.Type.ADDED)
                                  {
                                      dataLList.add(dc.getDocument().toObject(data_U.class));
                                  }

                                  myadapter.notifyDataSetChanged();
                                  d.dismiss();
                              }

                    }
                });


    }


}