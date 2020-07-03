package com.example.dashboard.server;

import com.example.dashboard.models.LoggingInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import static com.google.android.gms.common.internal.ImagesContract.URL;

public class Network {

    public static final String BASE_URL ="http://192.168.43.33:8001/";
    public static final String BASET_URL ="https://monitoringaki.000webhostapp.com/sim900/";
//    https://monitoringaki.000webhostapp.com/sim900//

    private static Network mInstance;
    private Retrofit retrofit;
    public static Retrofit RETROFIT     = null;

    private Network(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized Network getInstance(){
        if (mInstance == null ){
            mInstance = new Network();
        }
        return mInstance;
    }
    public ApiServices getApi(){
        return retrofit.create(ApiServices.class);
    }
    public static Retrofit getClient(){
        if(RETROFIT==null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .build();
            RETROFIT = new Retrofit.Builder()
                    .baseUrl(BASET_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return RETROFIT;
    }
}
