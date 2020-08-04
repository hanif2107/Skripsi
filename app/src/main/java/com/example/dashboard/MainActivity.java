package com.example.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.dashboard.Menu.DataPetugas;
import com.example.dashboard.Menu.Data_Sampah;
import com.example.dashboard.Menu.History;
import com.example.dashboard.Menu.Maps_Sampah;
import com.example.dashboard.Menu.Petugas;
import com.example.dashboard.Menu.Registrasi_Kotak;
import com.example.dashboard.Menu.Tentang;
import com.example.dashboard.Storage.SharedPrefManager;

public class MainActivity extends AppCompatActivity {
    ImageView sampah,maps,petugas,profil,regkot,tentang,history;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tentang=(ImageView) findViewById(R.id.tentang);
        history=(ImageView) findViewById(R.id.history);
        regkot=(ImageView) findViewById(R.id.regkot);
       profil=(ImageView) findViewById(R.id.profil);
        petugas=(ImageView) findViewById(R.id.petugas);
        maps=(ImageView) findViewById(R.id.maps);
        sampah=(ImageView) findViewById(R.id.sampah);
//        logout=(ImageView)findViewById(R.id.logut);
        sharedPrefManager=new SharedPrefManager(this);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datahistory();
            }
        });
        tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datatentang();
            }
        });
        regkot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datakotak();
            }
        });
        petugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datapetugass();
            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataprofils();
            }
        });
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datamaps();
            }
        });
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Logout();
//            }
//        });
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

    private void dataprofils() {
        Intent intent = new Intent(MainActivity.this, DataPetugas.class);
        startActivity(intent);
        finish();
    }
    private void datapetugass() {
        Intent intent = new Intent(MainActivity.this, Petugas.class);
        startActivity(intent);
        finish();
    }
    private void datakotak() {
        Intent intent = new Intent(MainActivity.this, Registrasi_Kotak.class);
        startActivity(intent);
        finish();
    }
    private void datatentang() {
        Intent intent = new Intent(MainActivity.this, Tentang.class);
        startActivity(intent);
        finish();
    }
    private void datahistory() {
        Intent intent = new Intent(MainActivity.this, History.class);
        startActivity(intent);
        finish();
    }
}
