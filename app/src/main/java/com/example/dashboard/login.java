package com.example.dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dashboard.Storage.SharedPrefManager;
import com.example.dashboard.server.ApiServices;
import com.example.dashboard.server.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    EditText Username,Password;
    Button Login,Registrasi;
    ApiServices mApiservice;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPrefManager = new SharedPrefManager(this);
        Username=(EditText) findViewById(R.id.email);
        Password=(EditText) findViewById(R.id.password);
        Login=(Button) findViewById(R.id.signin);
        Registrasi=(Button) findViewById(R.id.signup);
        Registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,registrasi.class);
                startActivity(intent);
                finish();
            }
        });
        if (sharedPrefManager.getSudahLogin()){
            startActivity(new Intent(login.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekinputan();
            }
        });
    }
    public void cekinputan(){
        loading = ProgressDialog.show(login.this,"Loading.....",null,true,true);
        String Email = Username.getText().toString();
        String Psw = Password.getText().toString();
        if (Email.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
        } else if (Psw.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else {
            RequestLogin();
        }
    }
    public void RequestLogin(){
        String Email = Username.getText().toString();
        String Psw = Password.getText().toString();

        retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().userLogin(Email,Psw);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("msg").equals("Berhasil Login")){
                            Log.d("response api", jsonRESULTS.toString());
                            Toast.makeText(login.this, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
//                            String id = jsonRESULTS.getJSONObject("user").getString("ID");
                            String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                            String alamat = jsonRESULTS.getJSONObject("user").getString("alamat");
                            String email = jsonRESULTS.getJSONObject("user").getString("email");
                            String telpon = jsonRESULTS.getJSONObject("user").getString("no_hp");
//                                        sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, id);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_ALAMAT, alamat);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_TELPON, telpon);
                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
//                            Log.d("alamat", alamat+telpon.toString());
                            startActivity(new Intent(login.this, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
//                            Intent intent= new Intent(login.this,MainActivity.class);
//                            startActivity(intent)
//                            ;
//                            finish();
                        } else {
                            String error_message = jsonRESULTS.getString("msg");
                            Toast.makeText(login.this, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                loading.dismiss();
                Toast.makeText(login.this, "Login Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }
    }

//    @OnClick(R.id.signin)
//    void dataHistori() {
//        Intent a = new Intent(login.this, MainActivity.class);
//        startActivity(a);
//        finish();
//    }
//
//    @OnClick(R.id.signup)
//    void dataSampah() {
//        Intent a = new Intent(login.this, registrasi.class);
//        startActivity(a);
//        finish();


//}