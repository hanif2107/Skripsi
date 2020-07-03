package com.example.dashboard.Menu;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dashboard.R;
import com.example.dashboard.Storage.SharedPrefManager;
import com.example.dashboard.login;
import com.example.dashboard.server.Koneksi_RMQ;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

import butterknife.ButterKnife;

public class Data_Sampah extends AppCompatActivity {
    TextView Brt, Vlm,Alamat,tanggal;
    Koneksi_RMQ rmq = new Koneksi_RMQ();
    private String message;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__sampah);
        sharedPrefManager =new SharedPrefManager(this);

        Brt = (TextView) findViewById(R.id.berat);
        Vlm = (TextView) findViewById(R.id.volume);
        Alamat = (TextView) findViewById(R.id.lokasi);
        tanggal = (TextView) findViewById(R.id.tanggal);

        ButterKnife.bind(this);
        subscribeNotification();
    }
    private void subscribeNotification() {
        rmq.setupConnectionFactory();
        final Handler incomingMessageHandler = new Handler() {
            @SuppressLint("HandlerLeak")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void handleMessage(Message msg) {
                String message = msg.getData().getString("msg");
                Log.d("RMQMessage", message);
                String s = message.toString();
                try {
                    JSONObject jsonRESULTS = new JSONObject(s);
                    String berat = jsonRESULTS.getString("Berat");
                    String volume = jsonRESULTS.getString("Volume");
                    Log.d("Berat",berat);
                    Log.d("Volume",volume);
                    Brt.setText(berat);
                    Vlm.setText(volume);
                    Alamat.setText(sharedPrefManager.getSPAlamat());
                    Log.d("alamat:",sharedPrefManager.getSPAlamat());
                    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

// textView is the TextView view that should display it
                    tanggal.setText(currentDateTimeString);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String[] tokens = s.split("");

//                LoginPassword.setText(message);
//                Toast.makeText(Data_Sampah.this, "ini data dari RMQ"+message, Toast.LENGTH_SHORT).show();
            }
        };

        Thread subscribeThread = new Thread(); //ini gua coba iseng kak
        rmq.subscribe(incomingMessageHandler,subscribeThread);
    }
    public void onBackPressed() {
        Intent a = new Intent(Data_Sampah.this, login.class);
        startActivity(a);
        finish();
    }
    
}