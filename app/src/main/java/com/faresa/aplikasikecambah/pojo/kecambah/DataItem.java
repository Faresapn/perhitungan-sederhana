package com.faresa.aplikasikecambah.pojo.kecambah;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataItem implements Parcelable {

	@SerializedName("nama")
	private String nama;

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("harga")
	private int harga;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("kecambah_id")
	private int kecambahId;

	@SerializedName("pembeli")
	private String pembeli;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("jumlah_bayar")
	private int jumlahBayar;

	@SerializedName("deleted_at")
	private Object deletedAt;

	protected DataItem(Parcel in) {
		this.nama = in.readString();
		this.jumlah = in.readInt();
		this.harga = in.readInt();
		this.updatedAt = in.readString();
		this.kecambahId = in.readInt();
		this.pembeli = in.readString();
		this.createdAt = in.readString();
		this.jumlahBayar = in.readInt();
	}

	public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
		@Override
		public DataItem createFromParcel(Parcel in) {
			return new DataItem(in);
		}

		@Override
		public DataItem[] newArray(int size) {
			return new DataItem[size];
		}
	};

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setJumlah(int jumlah){
		this.jumlah = jumlah;
	}

	public int getJumlah(){
		return jumlah;
	}

	public void setHarga(int harga){
		this.harga = harga;
	}

	public int getHarga(){
		return harga;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setKecambahId(int kecambahId){
		this.kecambahId = kecambahId;
	}

	public int getKecambahId(){
		return kecambahId;
	}

	public void setPembeli(String pembeli){
		this.pembeli = pembeli;
	}

	public String getPembeli(){
		return pembeli;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setJumlahBayar(int jumlahBayar){
		this.jumlahBayar = jumlahBayar;
	}

	public int getJumlahBayar(){
		return jumlahBayar;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"nama = '" + nama + '\'' + 
			",jumlah = '" + jumlah + '\'' + 
			",harga = '" + harga + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",kecambah_id = '" + kecambahId + '\'' + 
			",pembeli = '" + pembeli + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",jumlah_bayar = '" + jumlahBayar + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.nama);
		dest.writeInt(this.jumlah);
		dest.writeInt(this.harga);
		dest.writeString(this.updatedAt);
		dest.writeInt(this.kecambahId);
		dest.writeString(this.pembeli);
		dest.writeString(this.createdAt);
		dest.writeInt(this.jumlahBayar);
	}
}