package com.hazelnut.myjobs.Adapter;

public class UserAdapter{

    private String idPelamar;
    private String username;
    private String password;
    private String nama;
    private String email;
    private String notelp;
    private String alamat;

    public UserAdapter(String idPelamar, String username, String password, String nama, String email, String notelp, String alamat) {
        this.idPelamar = idPelamar;
        this.username  = username;
        this.password  = password;
        this.nama      = nama;
        this.email     = email;
        this.notelp    = notelp;
        this.alamat    = alamat;
    }

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
