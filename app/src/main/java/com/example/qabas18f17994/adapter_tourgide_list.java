package com.example.qabas18f17994;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class adapter_tourgide_list extends RecyclerView.Adapter<viewwholdeer>{
    private Context context;
    private List<data_user_booking> dataLList;

    public adapter_tourgide_list(Context context, List<data_user_booking> dataLList) {
        this.context = context;
        this.dataLList = dataLList;

    }

    @NonNull
    @Override
    public viewwholdeer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from((parent.getContext())).inflate(R.layout.booking_recicleview, parent, false);
        return new viewwholdeer(vv);

    }

    @Override

    public void onBindViewHolder(@NonNull viewwholdeer holder, int position) {
       holder.RView_typeB.setText(dataLList.get(position).getBooking_Type());
        holder.RView_booking_name.setText(dataLList.get(position).getBooking_name());
        holder.RView_idB.setText(dataLList.get(position).getBooking_Type_id());
        holder.RView_nameB.setText(dataLList.get(position).getFULLName());
        holder.RView_phoneB.setText(dataLList.get(position).getPHONE());
        holder.RView_priseB.setText(dataLList.get(position).getPrice());
        holder.RView_dayyB.setText(dataLList.get(position).getDay_of_Booking());
        holder.RView_quantity.setText(dataLList.get(position).getQuantity());
        holder.RView_totallB.setText(dataLList.get(position).getTotal_Price());

    }

    @Override
    public int getItemCount() {
        return dataLList.size();
    }
    //for the reserch button
    public void serchdata(ArrayList<data_user_booking>searchList)
    {
        dataLList=searchList;
        notifyDataSetChanged();
    }


}
class viewwholdeer extends RecyclerView.ViewHolder {

    TextView RView_typeB,RView_booking_name, RView_idB, RView_nameB, RView_phoneB,RView_priseB,RView_dayyB,RView_totallB,RView_quantity;
    CardView recyclerview_bookingcard;

    public viewwholdeer(@NonNull View item_View) {
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
