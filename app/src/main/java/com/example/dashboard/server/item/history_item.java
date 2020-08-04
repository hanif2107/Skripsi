package com.example.dashboard.server.item;

import com.google.gson.annotations.SerializedName;

public class history_item {

    @SerializedName("berat")
    private String berat1;

    @SerializedName("volume")
    private String volume1;

    @SerializedName("tanggal")
    private String tanggal1;

    public void setId(String berat){

        this.berat1 = berat;
    }

    public String getBerat(){
        return berat1;
    }

    public void setVolume1(String volume){

        this.volume1 = volume1;
    }

    public String getVolume(){
        return volume1;
    }

    public void setTanggal(String tanggal){

        this.tanggal1 = tanggal1;
    }

    public String getTanggal(){
        return tanggal1;
    }

    @Override
    public String toString(){
        return
                "history_item{" +
                        "berat= '" + berat1+ '\'' +
                        "volume= '" + volume1+ '\'' +
                        ",tanggal= '" + tanggal1+ '\'' +
                        "}";
    }
}

