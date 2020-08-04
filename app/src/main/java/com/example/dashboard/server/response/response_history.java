package com.example.dashboard.server.response;

import com.example.dashboard.server.item.history_item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class response_history {
    @SerializedName("data")
    private List<history_item> data;

    @SerializedName("status")
    private boolean status;

    public void setMenu(List<history_item> data) {
        this.data = data;
    }

    public List<history_item> getData()
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
                "Response_History{" +
                        "data = '" + data+ '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
