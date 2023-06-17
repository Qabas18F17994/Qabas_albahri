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

//this adapter for Tourist Places
public class adaptre_tp  extends RecyclerView.Adapter<theViewHolder> {
    private Context context;
    private List<data_of_Tourist_places> dataLList;

    public adaptre_tp(Context context, List<data_of_Tourist_places> dataLList) {
        this.context = context;
        this.dataLList = dataLList;
    }

    @NonNull
    @Override
    public theViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from((parent.getContext())).inflate(R.layout.touristplaces_recyclerview, parent, false);
        return new theViewHolder(vv);
    }

    @Override
    public void onBindViewHolder(@NonNull theViewHolder holder, int position) {
        Glide.with(context).load(dataLList.get(position).getPlace_Image()).into(holder.RView_img);
        holder.RView_ID.setText(dataLList.get(position).getPlace_ID());
        holder.RView_Name.setText(dataLList.get(position).getPlace_Name());
        holder.RView_Location.setText(dataLList.get(position).getPlace_location());
        holder.RView_information.setText(dataLList.get(position).getPlace_Informations());
        holder.RView_instruction.setText(dataLList.get(position).getPlace_Instructions());


        holder.recyclerview_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, tourist_places_details_.class);
                i.putExtra("image", dataLList.get(holder.getAdapterPosition()).getPlace_Image());
                i.putExtra("id", dataLList.get(holder.getAdapterPosition()).getPlace_ID());
                i.putExtra("name", dataLList.get(holder.getAdapterPosition()).getPlace_Name());
                i.putExtra("location", dataLList.get(holder.getAdapterPosition()).getPlace_location());
                i.putExtra("information", dataLList.get(holder.getAdapterPosition()).getPlace_Informations());
                i.putExtra("instructions", dataLList.get(holder.getAdapterPosition()).getPlace_Instructions());
                i.putExtra("keyq", dataLList.get(holder.getAdapterPosition()).getKeyq());

                context.startActivity(i);
            }
        });

    }
    public void serchdata(ArrayList<data_of_Tourist_places> searchList)
    {
        dataLList=searchList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataLList.size();
    }

}
class theViewHolder extends RecyclerView.ViewHolder
    {
        ImageView RView_img;
        TextView  RView_ID,RView_Name,RView_Location,RView_information,RView_instruction;
        CardView recyclerview_card;

        public theViewHolder(@NonNull View item_View) {
            super(item_View);

            recyclerview_card=item_View.findViewById(R.id.recyclerview_card);
            RView_img=item_View.findViewById(R.id.RView_img);
            RView_ID=item_View.findViewById(R.id.RView_ID);
            RView_Name=item_View.findViewById(R.id.RView_Name);
            RView_Location=item_View.findViewById(R.id.RView_Location);
            RView_information=item_View.findViewById(R.id.RView_information);
            RView_instruction=item_View.findViewById(R.id.RView_instruction);



        }
    }


