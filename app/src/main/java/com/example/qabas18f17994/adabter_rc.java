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
import androidx.recyclerview.widget.RecyclerView;;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class adabter_rc extends RecyclerView.Adapter<rcViewHolder>{

    private Context context_rc;
    private List<data_rc> dataLList_rc;

    public adabter_rc(Context context_rc, List<data_rc> dataLList_rc) {
        this.context_rc = context_rc;
        this.dataLList_rc = dataLList_rc;
    }


    @NonNull
    @Override
    public rcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from((parent.getContext())).inflate(R.layout.rc_recicleview, parent, false);
        return new rcViewHolder(vv);
    }

    @Override
    public void onBindViewHolder(@NonNull rcViewHolder holder, int position) {


        Glide.with(context_rc).load(dataLList_rc.get(position).getCar_Image()).into(holder.RView_imgRC);
        holder.RView_IDRC.setText(dataLList_rc.get(position).getCar_ID());
        holder.RView_NameRC.setText(dataLList_rc.get(position).getCar_Name());
        holder.RView_companyRC.setText(dataLList_rc.get(position).getCar_Company());
        holder.RView_informationRC.setText(dataLList_rc.get(position).getCar_Informations());
        holder.RView_instructionRC.setText(dataLList_rc.get(position).getCar_Instructions());
        holder.RView_priceRC.setText(dataLList_rc.get(position).getCar_Rent_price());



        holder.recyclerview_rcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context_rc, rent_car_ditails.class);
                i.putExtra("image", dataLList_rc.get(holder.getAdapterPosition()).getCar_Image());
                i.putExtra("id", dataLList_rc.get(holder.getAdapterPosition()).getCar_ID());
                i.putExtra("name", dataLList_rc.get(holder.getAdapterPosition()).getCar_Name());
                i.putExtra("company", dataLList_rc.get(holder.getAdapterPosition()).getCar_Company());
                i.putExtra("information", dataLList_rc.get(holder.getAdapterPosition()).getCar_Informations());
                i.putExtra("instructions", dataLList_rc.get(holder.getAdapterPosition()).getCar_Instructions());
                i.putExtra("price", dataLList_rc.get(holder.getAdapterPosition()).getCar_Rent_price());

                i.putExtra("keyq", dataLList_rc.get(holder.getAdapterPosition()).getKeyq());

                context_rc.startActivity(i);
            }
        });

    }

    public void serchdata(ArrayList<data_rc> searchList)
    {
        dataLList_rc=searchList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataLList_rc.size();
    }
}


class rcViewHolder extends RecyclerView.ViewHolder
{
    ImageView RView_imgRC;
    TextView RView_IDRC,RView_NameRC,RView_companyRC,RView_informationRC,RView_instructionRC,RView_priceRC;
    CardView recyclerview_rcard;

    public rcViewHolder(@NonNull View item_View) {
        super(item_View);

        recyclerview_rcard=item_View.findViewById(R.id.recyclerview_rcard);
        RView_imgRC=item_View.findViewById(R.id.RView_imgRC);
        RView_IDRC=item_View.findViewById(R.id.RView_IDRC);
        RView_NameRC=item_View.findViewById(R.id.RView_NameRC);
        RView_companyRC=item_View.findViewById(R.id.RView_companyRC);
        RView_informationRC=item_View.findViewById(R.id.RView_informationRC);
        RView_instructionRC=item_View.findViewById(R.id.RView_instructionRC);
        RView_priceRC=item_View.findViewById(R.id.RView_priceRC);



    }
}


