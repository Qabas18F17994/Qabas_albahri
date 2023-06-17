package com.example.qabas18f17994;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class adabter_user_booking extends RecyclerView.Adapter<VH>{

    private Context context;
    private List<data_user_booking> dataLList;
    FirebaseFirestore fstore;
    FirebaseAuth fauth;


    public adabter_user_booking(Context context, List<data_user_booking> dataLList) {
        this.context = context;
        this.dataLList = dataLList;

        fstore=FirebaseFirestore.getInstance();
        fauth=FirebaseAuth.getInstance();
    }
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from((parent.getContext())).inflate(R.layout.booking_recicleview, parent, false);
        return new VH(vv);
    }

    @Override
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(@NonNull VH holder, int position) {

        holder.RView_typeB.setText(dataLList.get(position).getBooking_Type());
      holder.RView_booking_name.setText(dataLList.get(position).getBooking_name());
        holder.RView_idB.setText(dataLList.get(position).getBooking_Type_id());
        holder.RView_nameB.setText(dataLList.get(position).getFULLName());
        holder.RView_phoneB.setText(dataLList.get(position).getPHONE());
        holder.RView_priseB.setText(dataLList.get(position).getPrice());
        holder.RView_dayyB.setText(dataLList.get(position).getDay_of_Booking());
        holder.RView_quantity.setText(dataLList.get(position).getQuantity());
        holder.RView_totallB.setText(dataLList.get(position).getTotal_Price());


        holder.recyclerview_bookingcard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure to Delete ? ")
                        .setIcon(R.drawable.delete_icon)
                        .setPositiveButton(" Yes ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                fstore.collection("Booking").document(fauth.getCurrentUser().getUid())
                                        .collection("BOOKING LIST")
                                        .document(dataLList.get(position).getDecomentID())
                                        .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if(task.isSuccessful())
                                                {
                                                    dataLList.remove(dataLList.get(position));
                                                    notifyDataSetChanged();


                                                }

                                            }
                                        });




                            }

                        }).setNegativeButton("No ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();
                return true;
            }
        });



    }
    public void serchdata(ArrayList<data_user_booking> searchList)
    {
        dataLList=searchList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return dataLList.size();
    }
}

class VH extends RecyclerView.ViewHolder {

    TextView RView_typeB,RView_booking_name, RView_idB, RView_nameB, RView_phoneB,RView_priseB,RView_dayyB,RView_totallB,RView_quantity;
    CardView recyclerview_bookingcard;

    public VH(@NonNull View item_View) {
        super(item_View);

        recyclerview_bookingcard = item_View.findViewById(R.id.recyclerview_bookingcard);

        RView_typeB = item_View.findViewById(R.id.RView_typeB);
     RView_booking_name = item_View.findViewById(R.id.RView_booking_name);
        RView_idB = item_View.findViewById(R.id.RView_idB);
        RView_nameB = item_View.findViewById(R.id.RView_nameB);
        RView_phoneB = item_View.findViewById(R.id.RView_phoneB);
        RView_priseB = item_View.findViewById(R.id.RView_priseB);
        RView_dayyB = item_View.findViewById(R.id.RView_dayyB);
        RView_quantity= item_View.findViewById(R.id.RView_quantity);
        RView_totallB = item_View.findViewById(R.id.RView_totallB);



    }
}
