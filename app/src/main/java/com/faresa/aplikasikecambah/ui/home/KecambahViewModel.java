package com.faresa.aplikasikecambah.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.faresa.aplikasikecambah.SharedPrefManager;
import com.faresa.aplikasikecambah.koneksi.Client;
import com.faresa.aplikasikecambah.koneksi.Service;
import com.faresa.aplikasikecambah.pojo.kecambah.KecambahResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KecambahViewModel extends AndroidViewModel {
    private MutableLiveData<KecambahResponse> getKecambah;
    SharedPrefManager sharedPrefManager;
    public KecambahViewModel(@NonNull Application application) {
        super(application);
        sharedPrefManager = new SharedPrefManager(application);
    }
    public void LoadKecambah(){
        String auth = sharedPrefManager.getSpToken();
        Service service = Client.getClient().create(Service.class);
        Call<KecambahResponse> eventCall = service.getKecambah("Bearer " + auth);
        eventCall.enqueue(new Callback<KecambahResponse>(){
            private Response<KecambahResponse> response;
            @Override
            public void onResponse(Call<KecambahResponse> call, Response<KecambahResponse> response) {
            this.response = response;
            getKecambah.setValue(response.body());
            }

            @Override
            public void onFailure(Call<KecambahResponse> call, Throwable t) {
                Log.e("failure", t.toString());

            }
        });
    }
    public LiveData<KecambahResponse> kecGet() {
        if (getKecambah == null) {
            getKecambah = new MutableLiveData<>();
            LoadKecambah();
        }
        return getKecambah;
    }
}
