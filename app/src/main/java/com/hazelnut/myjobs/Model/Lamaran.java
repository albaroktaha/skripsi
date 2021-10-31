package com.hazelnut.myjobs.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Lamaran implements Serializable{

    //Serializable berfungsi untuk pengganti intent
    //biar tidak terlalu banyak put extra

    @SerializedName("id_lamaran")
    private String idLamaran;

    @SerializedName("id_pelamar")
    private String idPelamar;

    @SerializedName("id_loker")
    private String idLoker;

    @SerializedName("id_perusahaan")
    private String idPerusahaan;

    private String nama;

    private String perusahaan;

    private String email;

    private String notelp;

    private String kota;

    private String jobs;

    public String getIdLamaran() {
        return idLamaran;
    }

    public String getIdPelamar() {
        return idPelamar;
    }

    public String getIdLoker() {
        return idLoker;
    }

    public String getIdPerusahaan() {
        return idPerusahaan;
    }

    public String getNama() {
        return nama;
    }

    public String getPerusahaan() {
        return perusahaan;
    }

    public String getEmail() {
        return email;
    }

    public String getNotelp() {
        return notelp;
    }

    public String getKota() {
        return kota;
    }

    public String getJobs() {
        return jobs;
    }
}
