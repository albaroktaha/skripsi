package com.hazelnut.myjobs.Model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

//    LOGIN & REGISTER
    @SerializedName("status")
    private String status;

    @SerializedName("result_code")
    private int resultCode;

    @SerializedName("id_perusahaan")
    private Integer idPerusahaan;

    @SerializedName("id_pelamar")
    private Integer idPelamar;

    @SerializedName("id_loker")
    private Integer idLoker;

    @SerializedName("id_lamaran")
    private Integer idLamaran;

    @SerializedName("nama")
    private String nama;

    public String getStatus() {
        return status;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getNama() {
        return nama;
    }

    public Integer getIdPerusahaan() {
        return idPerusahaan;
    }

    public Integer getIdPelamar() {
        return idPelamar;
    }

    public Integer getIdLoker() {
        return idLoker;
    }

    public Integer getIdLamaran() {
        return idLamaran;
    }
}
