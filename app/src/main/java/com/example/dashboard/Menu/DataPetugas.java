package com.example.dashboard.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.dashboard.MainActivity;
import com.example.dashboard.R;
import com.example.dashboard.Storage.SharedPrefManager;
import com.example.dashboard.login;

public class DataPetugas extends AppCompatActivity {
    TextView Nm,Almt,Notelp;
    Button Kl,Edt;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_petugas);
        sharedPrefManager =new SharedPrefManager(this);
        Edt=(Button) findViewById(R.id.edit);
        Kl=(Button) findViewById(R.id.keluar);
        Nm = (TextView) findViewById(R.id.namal);
        Almt = (TextView) findViewById(R.id.alamt);
        Notelp = (TextView) findViewById(R.id.telp);
        Nm.setText(sharedPrefManager.getSPNama());
        Almt.setText(sharedPrefManager.getSPAlamat());
        Notelp.setText(sharedPrefManager.getSPTelpon());
        Kl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Keluar();
            }
        });
        Edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfil();
            }
        });



//        Nm.setText();
//        Almt.setText(almt);
//        Nm.setText(sharedPrefManager.getSPNm());
//        Almt.setText(sharedPrefManager.getSPAlmt());
//        Notelp.setText(sharedPrefManager.getSPAlamt());
//        Log.d("alamat:",sharedPrefManager.getSPAlamt());
    }
    public void onBackPressed() {
        Intent a = new Intent(DataPetugas.this, MainActivity.class);
        startActivity(a);
        finish();
    }

    private void Keluar() {
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
        startActivity(new Intent(DataPetugas.this, login.class));
//        Intent intent =new Intent(MainActivity.this, Maps_Sampah.class);
//        startActivity(intent);
        finish();
    }

    private void EditProfil() {
        Intent intent = new Intent(DataPetugas.this, EditProfil.class);
        startActivity(intent);
        finish();
    }

}