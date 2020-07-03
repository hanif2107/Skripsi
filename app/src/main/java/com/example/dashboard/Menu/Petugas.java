package com.example.dashboard.Menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.dashboard.R;
import com.example.dashboard.server.ApiServices;
import com.example.dashboard.server.Network;
import com.example.dashboard.server.adapter.adapter_petugas;
import com.example.dashboard.server.item.petugas_item;
import com.example.dashboard.server.response.response_petugas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Petugas extends AppCompatActivity {
private RecyclerView recyclerView;
GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas);

        recyclerView = (RecyclerView)findViewById(R.id.listpetugas);
        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//
//        //GRID 2 kolom
        GridLayoutManager gridLayoutManager=new GridLayoutManager(Petugas.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        tampilpetugas();
//

    }

    private void tampilpetugas() {
        ApiServices api = Network.getInstance().getApi();
        Call<response_petugas> menuCall = api.getuser();
        menuCall.enqueue(new Callback<response_petugas>() {
            @Override
            public void onResponse(Call<response_petugas> call, Response<response_petugas> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<petugas_item> data_menu= response.body().getData();
                    boolean status = response.body().isStatus();
                    Log.d("coba", String.valueOf(status));
                    if (status){
                        adapter_petugas adapter = new adapter_petugas(Petugas.this, data_menu);
                        recyclerView.setAdapter(adapter);
                        Toast.makeText(Petugas.this, "ini test", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Petugas.this, "Tidak Ada data Petugas saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<response_petugas> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });
    }
}