package com.faresa.aplikasikecambah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.faresa.aplikasikecambah.koneksi.Client;
import com.faresa.aplikasikecambah.koneksi.Service;
import com.faresa.aplikasikecambah.pojo.login.TokenResponse;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextInputLayout Etemail,password;
    Button login;
    SharedPrefManager sharedPrefManager;
    Context mContext = this;
    ProgressDialog loading;
    Service mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Etemail = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        login = findViewById(R.id.btnLogin);
        mContext = this;
        mApiService = Client.getClient().create(Service.class);

        init();
        sharedPrefManager = new SharedPrefManager(this);
        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(MainActivity.this, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

    }

    private void init() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validatePassword()) {
                    return;
                }
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();


            }
        });
    }

    private boolean validatePassword() {
        String pw = password.getEditText().getText().toString().trim();

        if (pw.isEmpty()) {
            password.setError("Password tidak boleh kosong");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String email = (Etemail.getEditText()).getText().toString().trim();

        if (email.isEmpty()) {
            Etemail.setError("Email tidak boleh kosong");
            return false;
        }  else {
            Etemail.setError(null);
            return true;
        }
    }

    private void requestLogin() {mApiService.loginRequest(Etemail.getEditText().getText().toString(), password.getEditText().getText().toString())
            .enqueue(new Callback<TokenResponse>()
            {

                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    if (response.isSuccessful()){
                        loading.dismiss();
                        TokenResponse tokenResponse = response.body();
                        Log.d("tes", tokenResponse.getToken());

                        sharedPrefManager.saveToken(SharedPrefManager.SP_TOKEN,tokenResponse.getToken());
                        sharedPrefManager.getSpToken();
                        Log.d("token", sharedPrefManager.getSpToken());


                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(mContext);

                        dlgAlert.setMessage("Mohon Periksa Kembali");
                        dlgAlert.setTitle("Email Atau Password Anda Salah");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();


                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        loading.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
                    loading.dismiss();
                }
            });
    }
}
