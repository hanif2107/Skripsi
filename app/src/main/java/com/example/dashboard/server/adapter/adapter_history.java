package com.example.dashboard.server.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dashboard.R;
import com.example.dashboard.server.item.history_item;

import java.util.List;

public class adapter_history extends RecyclerView.Adapter<adapter_history.MyViewHolder>{
    Context context;
    List<history_item> menu;



    public adapter_history(Context context, List<history_item> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_history, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set widget
        holder.Br.setText(menu.get(position).getBerat());
        holder.Vol.setText(menu.get(position).getVolume());
//        Log.d("data",menu.get(position).getVolume());
        holder.Tgl.setText(menu.get(position).getTanggal());
//        final String urlGambar = InitRetrofit.BASE_URL+"/Images/" + menu.get(position).getFoto();
//        Picasso.with(context).load(urlGambar).into(holder.gambarmenu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Intent varIntent = new Intent(context, Detail_Menu.class);
//                varIntent.putExtra("ID", menu.get(position).getId());
//                varIntent.putExtra("NAMA", menu.get(position).getNama());
//                varIntent.putExtra("HARGA", menu.get(position).getHarga());
//                varIntent.putExtra("DESKRIPSI", menu.get(position).getDeskripsi());
//                varIntent.putExtra("GAMBAR_MENU", urlGambar);
//                varIntent.putExtra("GAMBAR", menu.get(position).getFoto());
//                context.startActivity(varIntent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Br,Vol,Tgl;
        public MyViewHolder(View itemView) {
            super(itemView);
            Br = (TextView)itemView.findViewById(R.id.berat1);
            Vol = (TextView)itemView.findViewById(R.id.volume1);
            Tgl = (TextView)itemView.findViewById(R.id.tanggal1);

        }
    }
}