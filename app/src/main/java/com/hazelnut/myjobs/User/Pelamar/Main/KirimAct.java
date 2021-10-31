package com.hazelnut.myjobs.User.Pelamar.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hazelnut.myjobs.Adapter.LamaranAdapter;
import com.hazelnut.myjobs.Adapter.ListAdapter;
import com.hazelnut.myjobs.App.AppConfig;
import com.hazelnut.myjobs.Model.ApiResponse;
import com.hazelnut.myjobs.Model.Data;
import com.hazelnut.myjobs.Model.Lamaran;
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.User.Perusahaan.Main.FormActivity;
import com.hazelnut.myjobs.databinding.ActivityKirimBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KirimAct extends AppCompatActivity {

    private ActivityKirimBinding kirimBinding;
    private AppConfig appConfig;
    private ApiInterface apiInterface;
    private ListAdapter listAdapter;
    private LamaranAdapter lamaranAdapter;
    private Lamaran lamaran;
    private Data detailData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kirimBinding = DataBindingUtil.setContentView(this, R.layout.activity_kirim);

        appConfig = new AppConfig(this);
//        int id = appConfig.getIdOfPelamar();

//        lamaran = (Lamaran) getIntent().getSerializableExtra("intent_lamaran");

        detailData = (Data) getIntent().getSerializableExtra("intent_detail");

        kirimBinding.btnDaftar.setOnClickListener(v -> {
//            createLamaran();
            kirimBinding.showProgress.setVisibility(View.VISIBLE);
        });

        init();
//        getData(String.valueOf(id));
    }

    public void init() {
        String nama = appConfig.getNameOfPelamar();
        kirimBinding.txtNama.setText(nama);
//        kirimBinding.txtPerusahaan.setText(detailData.getPerusahaan());
    }

//    public void getData(String id) {
//        Call<List<Data>> lamaran = apiInterface.lamaran(id);
//        lamaran.enqueue(new Callback<List<Data>>() {
//            @Override
//            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
//                if (response.code() == 200 && response.body() != null) {
//                    listAdapter.addItems(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Data>> call, Throwable t) {
//
//            }
//        });
//    }

}