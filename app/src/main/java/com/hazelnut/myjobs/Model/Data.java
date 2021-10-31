package com.hazelnut.myjobs.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {
    //Serializable berfungsi untuk pengganti intent
    //biar tidak terlalu banyak put extra

    @SerializedName("id_loker")
    private String idLoker;

    @SerializedName("id_perusahaan")
    private String idPerusahaan;

    @SerializedName("id_lamaran")
    private String idLamaran;

    @SerializedName("jobs_desc")
    private String jobsDesc;

    private String perusahaan;
    private String email;
    private String nama;
    private String kota;
    private String jobs;
    private String tanggal;

    public String getIdLoker() {
        return idLoker;
    }

    public String getNama() {
        return nama;
    }

    public String getIdPerusahaan() {
        return idPerusahaan;
    }

    public String getPerusahaan() {
        return perusahaan;
    }

    public String getEmail() {
        return email;
    }

    public String getKota() {
        return kota;
    }

    public String getJobs() {
        return jobs;
    }

    public String getJobsDesc() {
        return jobsDesc;
    }

    public String getTanggal() {
        return tanggal;
    }
}
