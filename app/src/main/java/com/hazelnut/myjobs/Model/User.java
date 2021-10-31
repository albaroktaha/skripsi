package com.hazelnut.myjobs.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

//    USER-PELAMAR / PERUSAHAAN

    @SerializedName("id_pelamar")
    private String idPelamar;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("nama")
    private String nama;

    @SerializedName("email")
    private String email;

    @SerializedName("notelp")
    private String notelp;

    @SerializedName("alamat")
    private String alamat;

    public String getIdPelamar() {
        return idPelamar;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getNotelp() {
        return notelp;
    }

    public String getAlamat() {
        return alamat;
    }
}
