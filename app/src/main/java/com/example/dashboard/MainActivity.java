package com.example.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.dashboard.Menu.DataPetugas;
import com.example.dashboard.Menu.Data_Sampah;
import com.example.dashboard.Menu.Maps_Sampah;
import com.example.dashboard.Menu.Petugas;
import com.example.dashboard.Storage.SharedPrefManager;

public class MainActivity extends AppCompatActivity {
    ImageView logout,sampah,maps,petugas,tentang;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tentang=(ImageView) findViewById(R.id.tentang);
        petugas=(ImageView) findViewById(R.id.petugas);
        maps=(ImageView) findViewById(R.id.maps);
        sampah=(ImageView) findViewById(R.id.sampah);
        logout=(ImageView)findViewById(R.id.logut);
        sharedPrefManager=new SharedPrefManager(this);
        tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datatentang();
            }
        });
        petugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datapetugas();
            }
        });
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datamaps();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
        sampah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindahdata();

            }
        });

    }

    private void Logout() {
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
        startActivity(new Intent(MainActivity.this, login.class));
//        Intent intent =new Intent(MainActivity.this, Maps_Sampah.class);
//        startActivity(intent);
        finish();
    }
    private void pindahdata(){
        Intent intent =new Intent(MainActivity.this, Data_Sampah.class);
        startActivity(intent);
        finish();

    }
    private void datamaps() {
        Intent intent = new Intent(MainActivity.this, Maps_Sampah.class);
        startActivity(intent);
        finish();
    }

    private void datapetugas() {
        Intent intent = new Intent(MainActivity.this, DataPetugas.class);
        startActivity(intent);
        finish();
    }
    private void datatentang() {
        Intent intent = new Intent(MainActivity.this, Petugas.class);
        startActivity(intent);
        finish();
    }
}
