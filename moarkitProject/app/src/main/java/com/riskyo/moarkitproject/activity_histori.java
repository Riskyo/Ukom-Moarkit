package com.riskyo.moarkitproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class activity_histori extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori);

        ImageView ivback = findViewById(R.id.ivback);

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_histori.this, MainActivity.class));
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        unitdata[] unitdata  = new unitdata[]{
                new unitdata("Crossbone Gundam MX - 3", "23-02-2019", R.drawable.crossbonegundamx3verka,"4000000","asjkhtdjkahstdjhkafsdhjkg"),
                new unitdata("Gundam Aile Strike", "24-02-2019", R.drawable.gundamailestrike,"4000000","asjkhtdjkahstdjhkafsdhjkg"),
                new unitdata("Gundam Destiny Xtreme Blast", "25-02-2019", R.drawable.gundamdestinyextremeblast,"4000000","asjkhtdjkahstdjhkafsdhjkg"),
                new unitdata("RS 78 Gundam", "26-02-2019", R.drawable.rs78gundam,"4000000","asjkhtdjkahstdjhkafsdhjkg")
        };

        historiAdapter unitAdapter = new historiAdapter(unitdata, activity_histori.this);
        recyclerView.setAdapter(unitAdapter);

        BottomNavigationView navView = findViewById(R.id.navigation);
        navView.setItemIconTintList(null);

        BottomNavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setSelectedItemId(R.id.fr_histori);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.fr_home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.fr_sell:
                        startActivity(new Intent(getApplicationContext()
                                ,activity_jualunit.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.fr_user:
                        startActivity(new Intent(getApplicationContext()
                                ,activity_user.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}