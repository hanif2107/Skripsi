package com.example.dashboard.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.dashboard.MainActivity;
import com.example.dashboard.R;
import com.example.dashboard.Storage.SharedPrefManager;

public class DataPetugas extends AppCompatActivity {
    TextView Nm,Almt,Notelp;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_petugas);
        sharedPrefManager =new SharedPrefManager(this);

        Nm = (TextView) findViewById(R.id.namal);
        Almt = (TextView) findViewById(R.id.alamt);
        Notelp = (TextView) findViewById(R.id.telp);
        Nm.setText(sharedPrefManager.getSPNama());
        Almt.setText(sharedPrefManager.getSPAlamat());
        Notelp.setText(sharedPrefManager.getSPTelpon());



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


}