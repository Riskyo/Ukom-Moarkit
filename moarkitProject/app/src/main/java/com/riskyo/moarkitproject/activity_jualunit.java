package com.riskyo.moarkitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_jualunit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jualunit);

        ImageView ivback = findViewById(R.id.ivback);
        Button btnlanjut = findViewById(R.id.btnlanjut);

        btnlanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_jualunit.this, activity_jualunit2.class));
            }
        });

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_jualunit.this, MainActivity.class));
            }
        });
    }
}