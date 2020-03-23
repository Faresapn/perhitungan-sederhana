package com.faresa.aplikasikecambah.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.faresa.aplikasikecambah.R;
import com.faresa.aplikasikecambah.SharedPrefManager;
import com.faresa.aplikasikecambah.pojo.kecambah.DataItem;
import com.faresa.aplikasikecambah.pojo.kecambah.KecambahResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterKecambah extends RecyclerView.Adapter<AdapterKecambah.SawitViewHolder>{
    private List<DataItem> dataGets;
    private SharedPrefManager sharedPrefManager;

    private HomeFragment fragment;




    void setDataGets(List<DataItem> dataGets) {
        this.dataGets = dataGets;
    }

    public AdapterKecambah(HomeFragment homeFragment) {

        this.fragment = homeFragment;
        this.sharedPrefManager = sharedPrefManager;
    }



    @NonNull
    @Override
    public AdapterKecambah.SawitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_kecambah, parent, false);
        return new AdapterKecambah.SawitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKecambah.SawitViewHolder holder, int position) {

        holder.bind(dataGets.get(position));
    }

    @Override
    public int getItemCount() {
        return dataGets.size();
    }

    public class SawitViewHolder extends RecyclerView.ViewHolder {
        TextView nama, kecambah;
        public SawitViewHolder(@NonNull View v) {
            super(v);
            nama = v.findViewById(R.id.name);
            kecambah = v.findViewById(R.id.harga);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        DataItem data = dataGets.get(position);
                        Intent intent = new Intent(fragment.getActivity(), DetailKecambah.class);
                        intent.putExtra("kecambah", data);
                        Objects.requireNonNull(fragment.getActivity()).startActivity(intent);
                    }
                }
            });

        }

        void bind(DataItem printeritem) {
            nama.setText(printeritem.getNama());
            kecambah.setText(String.valueOf(printeritem.getHarga()));


        }
    }
}
