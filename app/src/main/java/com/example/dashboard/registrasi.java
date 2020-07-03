package com.example.dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dashboard.Storage.SharedPrefManager;
import com.example.dashboard.server.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registrasi extends AppCompatActivity {
    EditText usernamereg,namaleng,tel,almt,psw;
    Button dft,akun;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        usernamereg=(EditText) findViewById(R.id.usernameregister);
        namaleng=(EditText) findViewById(R.id.namalengkap);
        tel=(EditText) findViewById(R.id.telp);
        almt=(EditText) findViewById(R.id.alamat);
        psw=(EditText) findViewById(R.id.passwordreg);
        dft=(Button) findViewById(R.id.daftar);
        akun=(Button) findViewById(R.id.punyaakun);

        ButterKnife.bind(this);

        akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(registrasi.this,login.class);
                startActivity(intent);
                finish();
            }
        });
        dft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekinputan();
            }
        });

        }
    public void cekinputan(){
        loading = ProgressDialog.show(registrasi.this,"Loading.....",null,true,true);
        String usernameregi = usernamereg.getText().toString();
        String namalengk = namaleng.getText().toString();
        String telpon = tel.getText().toString();
        String alama = almt.getText().toString();
        String passw = psw.getText().toString();
        if (usernameregi.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
        } else if (namalengk.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Nama Lengkap tidak boleh kosong", Toast.LENGTH_LONG).show();
        }
        else if (telpon.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Telephon tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else if (alama.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Alamat tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else if (passw.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else {
            RequestRegister();
        }
    }

    private void RequestRegister() {
        String usernameregi = usernamereg.getText().toString();
        String namalengk = namaleng.getText().toString();
        String telpon = tel.getText().toString();
        String alama = almt.getText().toString();
        String passw = psw.getText().toString();

        retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().Register(usernameregi,namalengk,telpon,alama,passw);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("msg").equals("Berhasil Registrasi")){
                            Log.d("response api", jsonRESULTS.toString());
                            Toast.makeText(registrasi.this, "BERHASIL REGISTER", Toast.LENGTH_SHORT).show();
//                            String id = jsonRESULTS.getJSONObject("user").getString("ID");

                            Intent intent= new Intent(registrasi.this,login.class);
                            startActivity(intent)
                            ;
                            finish();
                        } else {
                            String error_message = jsonRESULTS.getString("msg");
                            Toast.makeText(registrasi.this, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(registrasi.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                loading.dismiss();
                Toast.makeText(registrasi.this, "Login Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
