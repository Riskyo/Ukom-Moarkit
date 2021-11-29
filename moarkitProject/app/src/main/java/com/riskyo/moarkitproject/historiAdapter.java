package com.riskyo.moarkitproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class historiAdapter extends RecyclerView.Adapter<historiAdapter.Viewholder_hg> {

    unitdata[] unitdata;
    Context context;

    public historiAdapter(unitdata[] unitdata,activity_histori activity) {
        this.unitdata = unitdata;
        this.context = activity;
    }

    @NonNull
    @Override
    public historiAdapter.Viewholder_hg onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_item_list,parent,false);
        Viewholder_hg viewHolder = new Viewholder_hg(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull historiAdapter.Viewholder_hg holder, int position) {
        final unitdata unitdatalist = unitdata[position];
        holder.textViewName.setText(unitdatalist.getUnitName());
        holder.textViewDate.setText(unitdatalist.getPublishDate());
        holder.unitImage.setImageResource(unitdatalist.getUnitImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, unitdatalist.getUnitName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,activity_menuproduk.class);
                intent.putExtra("judul",unitdatalist.getUnitName());
                intent.putExtra("gambar",unitdatalist.getUnitImage());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return unitdata.length;
    }

    public class Viewholder_hg extends RecyclerView.ViewHolder{
        ImageView unitImage;
        TextView textViewName;
        TextView textViewDate;

        public Viewholder_hg(@NonNull View itemView) {
            super(itemView);
            unitImage = itemView.findViewById(R.id.ivgambarunit);
            textViewName = itemView.findViewById(R.id.tvnamaunit);
            textViewDate = itemView.findViewById(R.id.tvtanggalpublish);
        }
    }

}
