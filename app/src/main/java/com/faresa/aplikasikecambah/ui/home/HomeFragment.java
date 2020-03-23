package com.faresa.aplikasikecambah.ui.home;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faresa.aplikasikecambah.R;
import com.faresa.aplikasikecambah.pojo.kecambah.DataItem;
import com.faresa.aplikasikecambah.pojo.kecambah.KecambahResponse;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private KecambahViewModel kecambahViewModel;
    List<DataItem>dataItems = new ArrayList<>();
    RecyclerView rv;
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    AdapterKecambah adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        kecambahViewModel = ViewModelProviders.of(this).get(KecambahViewModel.class);
        adapter = new AdapterKecambah(HomeFragment.this);
        adapter.setDataGets(dataItems);
        rv = v.findViewById(R.id.rv_sawit);

        prepare();
        addItem();

        return v;
    }

    private void addItem() {
        kecambahViewModel.kecGet().observe(this, new Observer<KecambahResponse>() {
            @Override
            public void onChanged(KecambahResponse kecambahResponse) {

                try {
                    dataItems.clear();
                    dataItems.addAll(kecambahResponse.getData());
                    rv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    Log.e("failure", e.toString());
                }
            }
        });
    }

    private void prepare() {

        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);


    }
    @Override
    public void onResume() {
        super.onResume();
        kecambahViewModel.LoadKecambah();

    }
}
