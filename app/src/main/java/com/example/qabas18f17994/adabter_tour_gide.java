package com.example.qabas18f17994;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class adabter_tour_gide extends RecyclerView.Adapter<VvH>{

    private Context context;
    private List<data_tour_guid> dataLList;


    public adabter_tour_gide(Context context, List<data_tour_guid> dataLList) {
        this.context = context;
        this.dataLList = dataLList;
    }
    @NonNull
    @Override
    public VvH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from((parent.getContext())).inflate(R.layout.tour_guid_recicleview, parent, false);
        return new VvH(vv);
    }

    @Override
    public void onBindViewHolder(@NonNull VvH holder, int position) {

        holder.rRView_ID.setText(dataLList.get(position).getID());
        holder.RView_type.setText(dataLList.get(position).getType());
        holder.rRView_Name.setText(dataLList.get(position).getFULLName());
        holder.rRView_email.setText(dataLList.get(position).getEMAIL());
        holder.rRView_phone.setText(dataLList.get(position).getPHONE());
        holder.rRView_gender.setText(dataLList.get(position).getGENDER());
        holder.rRView_information.setText(dataLList.get(position).getINFORMATION());
        holder.rRView_price.setText(dataLList.get(position).getPRICE());


        holder.recyclerview_tourGide_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, u_tour_guid.class);
                i.putExtra("ID", dataLList.get(holder.getAdapterPosition()).getID());
                i.putExtra("FULLName", dataLList.get(holder.getAdapterPosition()).getFULLName());
                i.putExtra("EMAIL", dataLList.get(holder.getAdapterPosition()).getEMAIL());
                i.putExtra("PHONE", dataLList.get(holder.getAdapterPosition()).getPHONE());
                i.putExtra("GENDER", dataLList.get(holder.getAdapterPosition()).getGENDER());
                i.putExtra("INFORMATION", dataLList.get(holder.getAdapterPosition()).getINFORMATION());
                i.putExtra("PRICE", dataLList.get(holder.getAdapterPosition()).getPRICE());


                context.startActivity(i);
            }
        });




    }
    public void serchdata(ArrayList<data_tour_guid> searchList)
    {
        dataLList=searchList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return dataLList.size();
    }
}

class VvH extends RecyclerView.ViewHolder {

    TextView rRView_ID, rRView_Name, rRView_email, rRView_phone,rRView_gender,rRView_information,rRView_price,RView_type;
    CardView recyclerview_tourGide_card;

    public VvH(@NonNull View item_View) {
        super(item_View);

        recyclerview_tourGide_card = item_View.findViewById(R.id.recyclerview_tourGide_card);

        rRView_ID = item_View.findViewById(R.id.rRView_ID);
        RView_type=item_View.findViewById(R.id.RView_type);

        rRView_Name = item_View.findViewById(R.id.rRView_Name);
        rRView_email = item_View.findViewById(R.id.rRView_email);
        rRView_phone = item_View.findViewById(R.id.rRView_phone);
        rRView_gender = item_View.findViewById(R.id.rRView_gender);
        rRView_information = item_View.findViewById(R.id.rRView_information);
        rRView_price = item_View.findViewById(R.id.rRView_price);



    }
}
