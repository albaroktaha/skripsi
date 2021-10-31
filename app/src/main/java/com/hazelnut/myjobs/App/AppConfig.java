package com.hazelnut.myjobs.App;

import android.content.Context;
import android.content.SharedPreferences;

import com.hazelnut.myjobs.R;

public class AppConfig {
    private Context context;
    private SharedPreferences sharedPreferences;

    public AppConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file_key), Context.MODE_PRIVATE);
    }

    public boolean isUserLogin() {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_is_user_login), false);
    }

    public void updateUserLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_is_user_login), status);
        editor.apply();
    }

    public void resetStatusUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(context.getString(R.string.pref_name_of_user));
        editor.remove(context.getString(R.string.pref_id_of_user));
        editor.commit();
    }

    public void saveNameOfUser(String nama) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_name_of_user), nama);
        editor.apply();
    }

    public void saveIdOfUser(int id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(context.getString(R.string.pref_id_of_user), id);
        editor.apply();
    }

    public void saveNameOfPelamar(String nama) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_name_of_pelamar), nama);
        editor.apply();
    }

    public void saveIdOfPelamar(int id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(context.getString(R.string.pref_id_of_pelamar), id);
        editor.apply();
    }

    public String getNameOfUser() {
        return sharedPreferences.getString(context.getString(R.string.pref_name_of_user), "Unknown");
    }

    public String getNameOfPelamar() {
        return sharedPreferences.getString(context.getString(R.string.pref_name_of_pelamar), "Unknown");
    }

    public Integer getIdOfUser() {
        return sharedPreferences.getInt(context.getString(R.string.pref_id_of_user), 0);
    }

    public Integer getIdOfPelamar() {
        return sharedPreferences.getInt(context.getString(R.string.pref_id_of_pelamar), 0);
    }

}
