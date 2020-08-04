package com.example.dashboard.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.dashboard.MainActivity;
import com.example.dashboard.R;
import com.example.dashboard.server.ApiServices;
import com.example.dashboard.server.Network;
import com.example.dashboard.server.adapter.adapter_history;
import com.example.dashboard.server.item.history_item;
import com.example.dashboard.server.response.response_history;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends AppCompatActivity {
    private RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = (RecyclerView)findViewById(R.id.listhistory);
        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//
//        //GRID 2 kolom
        GridLayoutManager gridLayoutManager=new GridLayoutManager(History.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        tampilhistory();
//

    }

    private void tampilhistory() {
        ApiServices api = Network.getInstance().getApi();
        Call<response_history> menuCall = api.gethistory();
        menuCall.enqueue(new Callback<response_history>() {
            @Override
            public void onResponse(Call<response_history> call, Response<response_history> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<history_item> data_menu= response.body().getData();
                    boolean status = response.body().isStatus();
                    Log.d("coba", String.valueOf(status));
                    if (status){
                        adapter_history adapter = new adapter_history(History.this, data_menu);
                        recyclerView.setAdapter(adapter);
//                        Toast.makeText(Petugas.this, "Data Petugas", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(History.this, "Tidak Ada data Petugas saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<response_history> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });
    }
    public void onBackPressed() {
        Intent a = new Intent(History.this, MainActivity.class);
        startActivity(a);
        finish();
    }
}
