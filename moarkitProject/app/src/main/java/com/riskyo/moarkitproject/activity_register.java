package com.riskyo.moarkitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_register extends AppCompatActivity {

    Button btndaftar;
    EditText daftarnamapengguna, daftarnamalengkap, daftarkatasandi, daftaremail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btndaftar = findViewById(R.id.btnotp);
        daftarnamapengguna = findViewById(R.id.daftarnamapengguna);
        daftarnamalengkap = findViewById(R.id.daftarnamalengkap);
        daftarkatasandi = findViewById(R.id.daftarkatasandi);
        daftaremail = findViewById(R.id.daftarkodeotp);

        Button btndaftar = findViewById(R.id.btnotp);

        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_register.this, activity_login.class));
            }
        });



    }

    public void yelo(View view) {
        Toast.makeText(view.getContext(), "yo", Toast.LENGTH_SHORT).show();
    }
}