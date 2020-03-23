package com.faresa.aplikasikecambah.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.faresa.aplikasikecambah.MainActivity;
import com.faresa.aplikasikecambah.R;
import com.faresa.aplikasikecambah.SharedPrefManager;
import com.faresa.aplikasikecambah.koneksi.Client;
import com.faresa.aplikasikecambah.koneksi.Service;
import com.faresa.aplikasikecambah.pojo.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private Button logout;
    private SharedPrefManager sharedPrefManager;
    private TextView nama, email, id, created;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        sharedPrefManager = new SharedPrefManager(getContext());
        View v = inflater.inflate(R.layout.fragment_profile, container, false);



        final TextView nama = v.findViewById(R.id.prof_nama);

        final TextView  email = v.findViewById(R.id.prof_email);
        final TextView  id = v.findViewById(R.id.prof_id);
        final TextView created = v.findViewById(R.id.prof_created);
        Service service = Client.getClient().create(Service.class);
        String token = sharedPrefManager.getSpToken();
        Call<UserResponse> eventCall = service.getUser("Bearer " + token);
        eventCall.enqueue(new Callback<UserResponse>() {
            private Response<UserResponse> response;
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                this.response = response;
                try {
                    nama.setText(response.body().getUser().getName());
                    email.setText(response.body().getUser().getEmail());
                    id.setText(String.valueOf(response.body().getUser().getPhone()));
                    created.setText(String.valueOf(response.body().getUser().getUserId()));

                }catch (Exception e){
                    Log.e("gagal", e.toString());

                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

        logout = v.findViewById(R.id.but_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(getActivity(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finishActivity();

            }
        });

        return v;
    }
    private void finishActivity() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
