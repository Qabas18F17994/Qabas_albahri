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

;

public class adabter_rent_car  extends RecyclerView.Adapter<rc_ViewHolder>{

private Context context;
private List<data_rc> dataLList;

public adabter_rent_car(Context context, List<data_rc> dataLList) {
        this.context = context;
        this.dataLList = dataLList;
        }


@NonNull
@Override
public rc_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from((parent.getContext())).inflate(R.layout.rc_recicleview, parent, false);
        return new rc_ViewHolder(vv);
        }


    @Override
public void onBindViewHolder(@NonNull rc_ViewHolder holder, int position) {

        Glide.with(context).load(dataLList.get(position).getCar_Image()).into(holder.RView_imgRC);
        holder.RView_type.setText(dataLList.get(position).getType());
        holder.RView_IDRC.setText(dataLList.get(position).getCar_ID());
        holder.RView_NameRC.setText(dataLList.get(position).getCar_Name());
        holder.RView_companyRC.setText(dataLList.get(position).getCar_Company());
        holder.RView_informationRC.setText(dataLList.get(position).getCar_Informations());
        holder.RView_instructionRC.setText(dataLList.get(position).getCar_Instructions());
        holder.RView_priceRC.setText(dataLList.get(position).getCar_Rent_price());

        holder.recyclerview_rcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,u_rent_car.class);
                i.putExtra("image", dataLList.get(holder.getAdapterPosition()).getCar_Image());
                i.putExtra("id", dataLList.get(holder.getAdapterPosition()).getCar_ID());
                i.putExtra("name", dataLList.get(holder.getAdapterPosition()).getCar_Name());
                i.putExtra("company", dataLList.get(holder.getAdapterPosition()).getCar_Company());
                i.putExtra("information", dataLList.get(holder.getAdapterPosition()).getCar_Informations());
                i.putExtra("instructions", dataLList.get(holder.getAdapterPosition()).getCar_Instructions());
                i.putExtra("price", dataLList.get(holder.getAdapterPosition()).getCar_Rent_price());

                i.putExtra("keyq", dataLList.get(holder.getAdapterPosition()).getKeyq());

                context.startActivity(i);
            }
        });




    }

    public void serchdata(ArrayList<data_rc> searchList)
    {
        dataLList=searchList;
        notifyDataSetChanged();
    }

@Override
public int getItemCount() {
        return dataLList.size();
        }
        }


class rc_ViewHolder extends RecyclerView.ViewHolder {
        ImageView RView_imgRC;
    TextView RView_IDRC, RView_NameRC, RView_companyRC, RView_informationRC, RView_instructionRC, RView_priceRC,RView_type;
    CardView recyclerview_rcard;

    public rc_ViewHolder(@NonNull View item_View) {
        super(item_View);

        recyclerview_rcard = item_View.findViewById(R.id.recyclerview_rcard);
        RView_imgRC = item_View.findViewById(R.id.RView_imgRC);
        RView_type=item_View.findViewById(R.id.RView_type);

        RView_IDRC = item_View.findViewById(R.id.RView_IDRC);
        RView_NameRC = item_View.findViewById(R.id.RView_NameRC);
        RView_companyRC = item_View.findViewById(R.id.RView_companyRC);
        RView_informationRC = item_View.findViewById(R.id.RView_informationRC);
        RView_instructionRC = item_View.findViewById(R.id.RView_instructionRC);
        RView_priceRC = item_View.findViewById(R.id.RView_priceRC);


    }
}