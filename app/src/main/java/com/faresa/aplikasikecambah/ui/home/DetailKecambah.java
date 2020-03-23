package com.faresa.aplikasikecambah.ui.home;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.faresa.aplikasikecambah.HomeActivity;
import com.faresa.aplikasikecambah.R;
import com.faresa.aplikasikecambah.pojo.kecambah.DataItem;

public class DetailKecambah extends AppCompatActivity {
    TextView nama, harga,totalharga,uangkembali,ket;
    EditText uang;
    DataItem dataItem;
    double hasil,kembalian;
    Button proses,reset,keluar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kecambah);
        dataItem = getIntent().getParcelableExtra("kecambah");
        nama = findViewById(R.id.namaKecambah);
        harga = findViewById(R.id.Harga);
        uang = findViewById(R.id.UangBayar);
        totalharga = findViewById(R.id.totalSemua);
        keluar = findViewById(R.id.keluar);
        ket = findViewById(R.id.Keterangan);
        uangkembali = findViewById(R.id.uangKembali);
        final EditText jumlah = findViewById(R.id.JumlahKecambah);
        final EditText bayar = findViewById(R.id.UangBayar);
        proses = findViewById(R.id.Proses);
        reset = findViewById(R.id.reset);
        String Kecambah = dataItem.getNama();
        final String Harga = String.valueOf(dataItem.getHarga());
        nama.setText(Kecambah);
        harga.setText(Harga);

        proses.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final double bayaran =Double.parseDouble(bayar.getText().toString().trim());
                final double item =Double.parseDouble(jumlah.getText().toString().trim());
                final double total =Double.parseDouble(Harga);
                hasil = item*total;
                kembalian = bayaran - (item*total);
               totalharga.setText(Double.toString(hasil));
               uangkembali.setText(Double.toString(kembalian));
               if (kembalian<0){
                    ket.setText("Belum Lunas");
               }else {
                    ket.setText("lunas");
               }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalharga.setText("");
                uangkembali.setText("");
                jumlah.setText("");
                bayar.setText("");
            }
        });
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailKecambah.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
