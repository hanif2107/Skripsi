package com.example.dashboard.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.dashboard.MainActivity;
import com.example.dashboard.R;

public class Tentang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
        WebView webView=findViewById(R.id.webview);

        String text = "Aplikasi ini dibuat untuk mempermudah para petugas kebersihan untuk mendapat informasi\n" +
                "mengenai kotak sampah yang sudah penuh dan sebagai tugas akhir untuk memperoleh gelar\n" +
                "sarjana pada Universitas Bandar Lampung";
        webView.loadData("<p style=\"text-align: justify\">"+text +"</p>", "text/html","UTF-8");
    }
    public void onBackPressed() {
        Intent a = new Intent(Tentang.this, MainActivity.class);
        startActivity(a);
        finish();
    }
}