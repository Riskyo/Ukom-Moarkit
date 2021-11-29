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

import com.bumptech.glide.Glide;

import java.util.List;

public class unitAdapter2 extends RecyclerView.Adapter<unitAdapter2.Viewholder_mg> {

    List<Menu> menuList;
    Context context;

    public unitAdapter2(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public Viewholder_mg onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_item_list,parent,false);
        Viewholder_mg viewHolder = new Viewholder_mg(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder_mg holder, final int position) {
        holder.textViewName.setText(menuList.get(position).getMenu());
        holder.textViewDate.setText(menuList.get(position).getMenu());
//      holder.unitImage.setImageResource(menuList.get(position).getGambar());

        Glide.with(holder.itemView.getContext())
                .load(""+menuList.get(position).getGambar())
                .into(holder.unitImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, menuList.get(position).getMenu(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), activity_menuproduk.class);
                intent.putExtra("judul",menuList.get(position).getMenu());
                intent.putExtra("gambar",menuList.get(position).getGambar());

                v.getContext().startActivity(intent);
            }
        });

//        holder.btnProduk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), BajuDetail.class);
//                intent.putExtra("imgBaju", kategoriList.get(position).getGambar());
////                intent.putExtra("imgBaju", dataBajuList.getKategori());
//                intent.putExtra("namaBaju", kategoriList.get(position).getMenu());
//                intent.putExtra("hargaSewa", kategoriList.get(position).getHarga());
//
//                v.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class Viewholder_mg extends RecyclerView.ViewHolder{
        ImageView unitImage;
        TextView textViewName;
        TextView textViewDate;

        public Viewholder_mg(@NonNull View itemView) {
            super(itemView);
            unitImage = itemView.findViewById(R.id.ivgambarunit);
            textViewName = itemView.findViewById(R.id.tvnamaunit);
            textViewDate = itemView.findViewById(R.id.tvtanggalpublish);
        }
    }

}

