package com.example.qabas18f17994;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class adabter_Evnts extends RecyclerView.Adapter<VV> {
    private Context context;
    private List<data_events> dataLList;

    public adabter_Evnts(Context context, List<data_events> dataLList) {
        this.context = context;
        this.dataLList = dataLList;
    }



    @NonNull
    @Override
    public VV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from((parent.getContext())).inflate(R.layout.event_recicleview, parent, false);
        return new VV(vv);
    }

    @Override
    public void onBindViewHolder(@NonNull VV holder, int position) {

        Glide.with(context).load(dataLList.get(position).getEvent_Image()).into(holder.RView_imgE);
        holder.RView_IDE.setText(dataLList.get(position).getEvent_ID());
        holder.RView_NameE.setText(dataLList.get(position).getEvent_Name());
        holder.RView_LocationE.setText(dataLList.get(position).getEvent_location());
        holder.RView_informationE.setText(dataLList.get(position).getEvent_Informations());



        holder.recyclerview_Ecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, u_event.class);
                i.putExtra("image", dataLList.get(holder.getAdapterPosition()).getEvent_Image());
                i.putExtra("id", dataLList.get(holder.getAdapterPosition()).getEvent_ID());
                i.putExtra("name", dataLList.get(holder.getAdapterPosition()).getEvent_Name());
                i.putExtra("location", dataLList.get(holder.getAdapterPosition()).getEvent_location());
                i.putExtra("information", dataLList.get(holder.getAdapterPosition()).getEvent_Informations());

                i.putExtra("keyq", dataLList.get(holder.getAdapterPosition()).getKeyq());

                context.startActivity(i);
            }
        });

    }
    public void serchdata(ArrayList<data_events> searchList)
    {
        dataLList=searchList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return dataLList.size();
    }
}


class VV extends RecyclerView.ViewHolder {
    ImageView RView_imgE;
    TextView RView_IDE, RView_NameE, RView_LocationE, RView_informationE;
    CardView recyclerview_Ecard;

    public VV(@NonNull View item_View) {
        super(item_View);

        recyclerview_Ecard = item_View.findViewById(R.id.recyclerview_Ecard);
        RView_imgE = item_View.findViewById(R.id.RView_imgE);
        RView_IDE = item_View.findViewById(R.id.RView_IDE);
        RView_NameE = item_View.findViewById(R.id.RView_NameE);
        RView_LocationE = item_View.findViewById(R.id.RView_LocationE);
        RView_informationE = item_View.findViewById(R.id.RView_informationE);


    }
}
