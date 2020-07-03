package com.example.dashboard.server.item;

import com.google.gson.annotations.SerializedName;

public class petugas_item {

    @SerializedName("nama")
    private String nama;

    @SerializedName("no_hp")
    private String no_hp;

    @SerializedName("alamat")
    private String alamat;

    public void setId(String nama){

        this.nama = nama;
    }

    public String getNama(){
        return nama;
    }

    public void setNo_hp(String no_hp){

        this.no_hp = no_hp;
    }

    public String getNo_hp(){
        return no_hp;
    }

    public void setAlamat(String alamat){

        this.alamat = alamat;
    }

    public String getAlamat(){
        return alamat;
    }

    @Override
    public String toString(){
        return
                "petugas_item{" +
                        "nama= '" + nama+ '\'' +
                        "no_hp= '" + no_hp+ '\'' +
                        ",alamat= '" + alamat+ '\'' +
                        "}";
    }
}

