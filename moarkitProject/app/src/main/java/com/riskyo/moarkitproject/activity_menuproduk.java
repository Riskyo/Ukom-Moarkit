package com.riskyo.moarkitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class activity_menuproduk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuproduk);

        TextView t1, t2, t3, t4;
        ImageView i1;

        t1 = (TextView) findViewById(R.id.tvjuduldetail);
        t2 = (TextView) findViewById(R.id.tvhargadetail);
        t3 = (TextView) findViewById(R.id.tvdeskripsi);
        i1 = (ImageView) findViewById(R.id.gambardt);

        Glide.with(activity_menuproduk.this)
                .load(""+getIntent().getStringExtra("gambar"))
                .apply(new RequestOptions().override(350, 550))
                .into(i1);

//        i1.setImageResource(getIntent().getIntExtra("gambar",0));
        t1.setText(getIntent().getStringExtra("judul"));
        t2.setText(Integer.toString(getIntent().getIntExtra("hargaq", 0)));
        t3.setText(getIntent().getStringExtra("deskrip`si"));
    }
}