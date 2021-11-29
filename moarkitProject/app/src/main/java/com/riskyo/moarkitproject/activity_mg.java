package com.riskyo.moarkitproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_mg extends AppCompatActivity {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter unitAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static activity_mg ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mg);

        ImageView ivback = findViewById(R.id.ivback);

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_mg.this, MainActivity.class));
            }
        });

        // Recycler View
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma=this;

//        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        unitdata[] unitdata  = new unitdata[]{
//                new unitdata("Crossbone Gundam MX - 3", "23-02-2019", R.drawable.crossbonegundamx3verka,"4000000","asjkhtdjkahstdjhkafsdhjkg"),
//                new unitdata("Gundam Aile Strike", "24-02-2019", R.drawable.gundamailestrike,"4000000","asjkhtdjkahstdjhkafsdhjkg"),
//                new unitdata("Gundam Destiny Xtreme Blast", "25-02-2019", R.drawable.gundamdestinyextremeblast,"4000000","asjkhtdjkahstdjhkafsdhjkg"),
//                new unitdata("RS 78 Gundam", "26-02-2019", R.drawable.rs78gundam,"4000000","asjkhtdjkahstdjhkafsdhjkg")
//        };
//
//        unitAdapter2 unitAdapter = new unitAdapter2(unitdata, activity_mg.this);
//        recyclerView.setAdapter(unitAdapter);

        BottomNavigationView navView = findViewById(R.id.navigation);
        navView.setItemIconTintList(null);

        BottomNavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setSelectedItemId(R.id.fr_user);

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

        panggilRetrofit();
    }

    public void panggilRetrofit() {
        Call<GetMenu> menuCall = apiInterface.getMenu();
        menuCall.enqueue(new Callback<GetMenu>() {
            @Override
            public void onResponse(Call<GetMenu> call, Response<GetMenu>
                    response) {
                List<Menu> MenuList = response.body().getData();
                Log.d("Retrofit Get", "Jumlah data Kontak: " +
                        String.valueOf(MenuList.size()));
                unitAdapter = new unitAdapter(MenuList);
                recyclerView.setAdapter(unitAdapter);
            }

            @Override
            public void onFailure(Call<GetMenu> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}