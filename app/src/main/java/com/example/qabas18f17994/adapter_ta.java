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

public class adapter_ta  extends RecyclerView.Adapter<ta_ViewHolder> {

    private Context context_ta;
    private List<data_ta> dataLList_ta;

    public adapter_ta(Context context_ta, List<data_ta> dataLList_ta) {
        this.context_ta = context_ta;
        this.dataLList_ta = dataLList_ta;
    }

    @NonNull
    @Override
    public ta_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from((parent.getContext())).inflate(R.layout.ta_recicleview, parent, false);
        return new ta_ViewHolder(vv);
    }

    @Override
    public void onBindViewHolder(@NonNull ta_ViewHolder holder, int position) {


        Glide.with(context_ta).load(dataLList_ta.get(position).getActivity_Image()).into(holder.RView_imgA);

        holder.RView_IDA.setText(dataLList_ta.get(position).getActivity_ID());
        holder.RView_NameA.setText(dataLList_ta.get(position).getActivity_Name());
        holder.RView_LocationA.setText(dataLList_ta.get(position).getActivity_location());
        holder.RView_informationA.setText(dataLList_ta.get(position).getActivity_Informations());
        holder.RView_instructionA.setText(dataLList_ta.get(position).getActivity_Instructions());
        holder.RView_priceA.setText(dataLList_ta.get(position).getActivity_Price());
        holder.RView_dayA.setText(dataLList_ta.get(position).getActivity_day());


        holder.recyclerview_acard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context_ta, tourist_activities_ditails.class);
                i.putExtra("image", dataLList_ta.get(holder.getAdapterPosition()).getActivity_Image());

                i.putExtra("id", dataLList_ta.get(holder.getAdapterPosition()).getActivity_ID());
                i.putExtra("name", dataLList_ta.get(holder.getAdapterPosition()).getActivity_Name());
                i.putExtra("location", dataLList_ta.get(holder.getAdapterPosition()).getActivity_location());
                i.putExtra("information", dataLList_ta.get(holder.getAdapterPosition()).getActivity_Informations());
                i.putExtra("instructions", dataLList_ta.get(holder.getAdapterPosition()).getActivity_Instructions());
                i.putExtra("price", dataLList_ta.get(holder.getAdapterPosition()).getActivity_Price());
                i.putExtra("day", dataLList_ta.get(holder.getAdapterPosition()).getActivity_day());
                i.putExtra("keyq", dataLList_ta.get(holder.getAdapterPosition()).getKeyq());

                context_ta.startActivity(i);
            }
        });

    }

    public void serchdata(ArrayList<data_ta> searchList)
    {
        dataLList_ta=searchList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataLList_ta.size();
    }
}





class ta_ViewHolder extends RecyclerView.ViewHolder

{
    ImageView RView_imgA;
    TextView RView_IDA,RView_NameA,RView_LocationA,RView_informationA,RView_instructionA,RView_priceA,RView_dayA;
    CardView recyclerview_acard;

    public ta_ViewHolder(@NonNull View item_View) {
        super(item_View);

        recyclerview_acard=item_View.findViewById(R.id.recyclerview_acard);
        RView_imgA=item_View.findViewById(R.id.RView_imgA);

        RView_IDA=item_View.findViewById(R.id.RView_IDA);
        RView_NameA=item_View.findViewById(R.id.RView_NameA);
        RView_LocationA=item_View.findViewById(R.id.RView_LocationA);
        RView_informationA=item_View.findViewById(R.id.RView_informationA);
        RView_instructionA=item_View.findViewById(R.id.RView_instructionA);
        RView_priceA=item_View.findViewById(R.id.RView_priceA);
        RView_dayA=item_View.findViewById(R.id.RView_dayA);



    }

}


