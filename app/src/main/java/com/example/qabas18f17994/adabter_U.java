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

public class adabter_U extends  RecyclerView.Adapter<Vie_wHolder> {
    private Context context;
    private List<data_U> dataLList;

    public adabter_U(Context context, List<data_U> dataLList) {
        this.context  = context;
        this.dataLList = dataLList;
    }

    @NonNull
    @Override
    public Vie_wHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from((parent.getContext())).inflate(R.layout.users_recicleview, parent, false);
        return new Vie_wHolder(vv);
    }

    @Override
    public void onBindViewHolder(@NonNull Vie_wHolder holder, int position) {

        holder.RView_ID.setText(dataLList.get(position).getID());
        holder.RView_Name.setText(dataLList.get(position).getFULLName());
        holder.RView_email.setText(dataLList.get(position).getEMAIL());
        holder.RView_phone.setText(dataLList.get(position).getPHONE());
        holder.RView_password.setText(dataLList.get(position).getPASSWORD());
        holder.RView_gender.setText(dataLList.get(position).getGENDER());
        holder.RView_type.setText(dataLList.get(position).getTYPE());



        holder.recyclerview_user_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, users_ditail.class);
                i.putExtra("ID", dataLList.get(holder.getAdapterPosition()).getID());
                i.putExtra("FULLName", dataLList.get(holder.getAdapterPosition()).getFULLName());
                i.putExtra("PHONE", dataLList.get(holder.getAdapterPosition()).getPHONE());
                i.putExtra("EMAIL", dataLList.get(holder.getAdapterPosition()).getEMAIL());
                i.putExtra("PASSWORD", dataLList.get(holder.getAdapterPosition()).getPASSWORD());
                i.putExtra("GENDER", dataLList.get(holder.getAdapterPosition()).getGENDER());
                i.putExtra("TYPE", dataLList.get(holder.getAdapterPosition()).getTYPE());

                //i.putExtra("keyq", dataLList.get(holder.getAdapterPosition()).getKeyq());

                context.startActivity(i);
            }
        });

    }

    public void serchdata(ArrayList<data_U> searchList)
    {
        dataLList=searchList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataLList.size();
    }
}

class Vie_wHolder extends RecyclerView.ViewHolder
{
    TextView RView_ID,RView_Name,RView_email,RView_phone,RView_password,RView_gender,RView_type;
    CardView recyclerview_user_card;

    public Vie_wHolder(@NonNull View item_View) {
        super(item_View);

        recyclerview_user_card=item_View.findViewById(R.id.recyclerview_user_card);

        RView_ID=item_View.findViewById(R.id.rRView_ID);
        RView_Name=item_View.findViewById(R.id.rRView_Name);
        RView_email=item_View.findViewById(R.id.rRView_email);
        RView_phone=item_View.findViewById(R.id.rRView_phone);
        RView_password=item_View.findViewById(R.id.rRView_password);
        RView_gender=item_View.findViewById(R.id.rRView_gender);
        RView_type=item_View.findViewById(R.id.rRView_type);



    }
}


