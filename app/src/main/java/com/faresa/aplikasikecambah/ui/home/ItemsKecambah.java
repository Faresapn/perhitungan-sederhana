package com.faresa.aplikasikecambah.ui.home;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemsKecambah implements Parcelable {
    private int img;
    private String nama, harga;
    public ItemsKecambah(){

    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }



    protected ItemsKecambah(Parcel in) {
        this.img = in.readInt();
        this.nama = in.readString();
        this.harga = in.readString();
    }

    public static final Creator<ItemsKecambah> CREATOR = new Creator<ItemsKecambah>() {
        @Override
        public ItemsKecambah createFromParcel(Parcel in) {
            return new ItemsKecambah(in);
        }

        @Override
        public ItemsKecambah[] newArray(int size) {
            return new ItemsKecambah[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.img);
        dest.writeString(this.nama);
        dest.writeString(this.harga);
    }
}
