package com.riskyo.moarkitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_login extends AppCompatActivity {

    Button btnmasuk;
    EditText etemail, etsandi;
    TextView tvdaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnmasuk = findViewById(R.id.btnmasuk);
        etemail = findViewById(R.id.etemail);
        etsandi = findViewById(R.id.etsandi);
        tvdaftar = findViewById(R.id.tvdaftar);

        TextView tvDaftar = findViewById(R.id.tvdaftar);
        TextView tvLupa = findViewById(R.id.tvlupapass);
        Button btnmasuk = findViewById(R.id.btnmasuk);

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_login.this, RegisterActivity.class));
            }
        });

        tvLupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_login.this, activity_lupapass.class));
            }
        });

        btnmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etemail.getText().toString()) || TextUtils.isEmpty(etsandi.getText().toString()))

                {
                    String pesan = "All";
                    Toast.makeText(activity_login.this, pesan, Toast.LENGTH_SHORT).show();
                }else{
                    Login login = new Login();
                    login.setEmail(etemail.getText().toString());
                    login.setPassword(etsandi.getText().toString());
//                    login.setAlamat(paassword.getText().toString());


                    LoginUser(login);


                }
//                HomeUtama();
            }
        });
            }

    public void LoginUser(Login login){
        Call<GetLogin> loginCall = apiClientLogin.loginRegInterfaace().loginReg(login);
        loginCall.enqueue(new Callback<GetLogin>() {
            @Override
            public void onResponse(Call<GetLogin> call, Response<GetLogin> response) {
                if (response.isSuccessful()){
                    Login getLogin = response.body().getData();
                    startActivity(new Intent(activity_login.this,MainActivity.class).putExtra("data",getLogin));
                    finish();


                }else {
                    String  pesan1 = "gagal";
                    Toast.makeText(activity_login.this, pesan1, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetLogin> call, Throwable t) {
                String pesan ="Login Gagal";
                Toast.makeText(activity_login.this, pesan, Toast.LENGTH_SHORT).show();

            }
        });
    }





}