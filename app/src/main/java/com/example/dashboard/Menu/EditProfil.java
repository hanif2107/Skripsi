package com.example.dashboard.Menu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dashboard.MainActivity;
import com.example.dashboard.R;
import com.example.dashboard.Storage.SharedPrefManager;
import com.example.dashboard.login;
import com.example.dashboard.server.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfil extends AppCompatActivity {
EditText UsernameEd,NamaLengkp,NoTelp,Alamat,Passw;
Button Simpan;
SharedPrefManager sharedPrefManager;
ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        sharedPrefManager =new SharedPrefManager(this);
        Simpan=(Button) findViewById(R.id.simpan);
        UsernameEd = (EditText) findViewById(R.id.usernameregister1);
        NamaLengkp = (EditText) findViewById(R.id.namalengkap1);
        NoTelp = (EditText) findViewById(R.id.telp1);
        Alamat = (EditText) findViewById(R.id.alamat1);
        Passw = (EditText) findViewById(R.id.passwordreg1);
        UsernameEd.setText(sharedPrefManager.getSPEmail());
        NamaLengkp.setText(sharedPrefManager.getSPNama());
        NoTelp.setText(sharedPrefManager.getSPTelpon());
        Alamat.setText(sharedPrefManager.getSPAlamat());

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekedit();
            }
        });
    }

    public void cekedit(){
        loading = ProgressDialog.show(EditProfil.this,"Loading.....",null,true,true);
        String usernameregi = UsernameEd.getText().toString();
        String namalengk = NamaLengkp.getText().toString();
        String telpon = NoTelp.getText().toString();
        String alama = Alamat.getText().toString();
        String passw = Passw.getText().toString();
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
            RequestEdit();
        }
    }

    private void RequestEdit() {
        String usernameregi = UsernameEd.getText().toString();
        String namalengk = NamaLengkp.getText().toString();
        String telpon = NoTelp.getText().toString();
        String alama = Alamat.getText().toString();
        String passw = Passw.getText().toString();

        retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().Edit(usernameregi,namalengk,telpon,alama,passw);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("msg").equals("Berhasil")){
                            Log.d("response api", jsonRESULTS.toString());
                            Toast.makeText(EditProfil.this, "BERHASIL EDIT PROFILE", Toast.LENGTH_SHORT).show();
//                            String id = jsonRESULTS.getJSONObject("user").getString("ID");

                            Intent intent= new Intent(EditProfil.this, login.class);
                            startActivity(intent)
                            ;
                            finish();
                        } else {
                            String error_message = jsonRESULTS.getString("msg");
                            Toast.makeText(EditProfil.this, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(EditProfil.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                loading.dismiss();
                Toast.makeText(EditProfil.this, "Login Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void onBackPressed() {
        Intent a = new Intent(EditProfil.this, MainActivity.class);
        startActivity(a);
        finish();
    }
}