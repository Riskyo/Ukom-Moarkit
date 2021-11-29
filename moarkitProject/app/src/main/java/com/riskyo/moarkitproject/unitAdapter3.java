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

import java.util.List;

public class unitAdapter3 extends RecyclerView.Adapter<unitAdapter3.Viewholder_rg> {

    List<Menu> menuList;
    Context context;

    public unitAdapter3(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public Viewholder_rg onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_item_list,parent,false);
        unitAdapter3.Viewholder_rg viewHolder = new unitAdapter3.Viewholder_rg(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder_rg holder, final int position) {
        holder.textViewName.setText(menuList.get(position).getMenu());
        holder.textViewDate.setText(menuList.get(position).getMenu());
//      holder.unitImage.setImageResource(menuList.get(position).getMenu());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), activity_menuproduk.class);
                intent.putExtra("judul",menuList.get(position).getMenu());
                intent.putExtra("gambar",menuList.get(position).getGambar());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

    }
//
//    @Override
//    public void onBindViewHolder(@NonNull unitAdapter2.Viewholder_mg holder, final int position) {
//        holder.textViewName.setText(menuList.get(position).getMenu());
//        holder.textViewDate.setText(menuList.get(position).getMenu());
////      holder.unitImage.setImageResource(menuList.get(position).getMenu());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), activity_menuproduk.class);
//                intent.putExtra("judul",menuList.get(position).getMenu());
//                intent.putExtra("gambar",menuList.get(position).getGambar());
//
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                v.getContext().startActivity(intent);
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class Viewholder_rg extends RecyclerView.ViewHolder{
        ImageView unitImage;
        TextView textViewName;
        TextView textViewDate;

        public Viewholder_rg(@NonNull View itemView) {
            super(itemView);
            unitImage = itemView.findViewById(R.id.ivgambarunit);
            textViewName = itemView.findViewById(R.id.tvnamaunit);
            textViewDate = itemView.findViewById(R.id.tvtanggalpublish);
        }
    }

}
