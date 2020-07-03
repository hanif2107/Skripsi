package com.example.dashboard.server.response;

import com.example.dashboard.server.item.petugas_item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class response_petugas {
    @SerializedName("data")
    private List<petugas_item> data;

    @SerializedName("status")
    private boolean status;

    public void setMenu(List<petugas_item> data) {
        this.data = data;
    }

    public List<petugas_item> getData()
    {
        return data;
    }

    public void setStatus(boolean status){

        this.status = status;
    }

    public boolean isStatus(){

        return status;
    }

    @Override
    public String toString(){
        return
                "Response_Petugas{" +
                        "data = '" + data+ '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
