package com.hazelnut.myjobs.User.Perusahaan.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hazelnut.myjobs.Adapter.LamaranAdapter;
import com.hazelnut.myjobs.Adapter.ListAdapter;
import com.hazelnut.myjobs.App.AppConfig;
import com.hazelnut.myjobs.Model.Data;
import com.hazelnut.myjobs.Model.Lamaran;
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.User.Perusahaan.Login.LoginActivity;
import com.hazelnut.myjobs.databinding.ActivityListLamaranBinding;
import com.hazelnut.myjobs.databinding.ActivityMainPhBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListLamaranActivity extends AppCompatActivity {

    private ActivityListLamaranBinding listLamaranBinding;
    private AppConfig appConfig;
    private ApiInterface apiInterface;
    private LamaranAdapter lamaranAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listLamaranBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_lamaran);
        appConfig = new AppConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String nama = appConfig.getNameOfUser();
        int id = appConfig.getIdOfUser();

        listLamaranBinding.txtWelcome.setText("Welcome, "+nama+"\n"+"ID Perusahaan : "+id);

        listLamaranBinding.btnHome.setOnClickListener(v -> {
            startActivity(new Intent(ListLamaranActivity.this, MainPHAct.class));
            finish();
        });

        init();
        getData(String.valueOf(id));
    }

    public void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        View emptyview = findViewById(R.id.empty_view);

        lamaranAdapter = new LamaranAdapter (this, emptyview, new LamaranAdapter.ClickHandler() {
            @Override
            public void onItemClicked(Lamaran lamaran) {
                //Ketika item RV di Klik
                Intent intent = new Intent(ListLamaranActivity.this, DetailLamaranActivity.class);
                intent.putExtra("intent_detail", lamaran);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(lamaranAdapter);
    }

    public void getData(String id) {
        //GET SELURUH DATA DALAM API

        Call<List<Lamaran>> listlamaran = apiInterface.listLamaran(id);
        listlamaran.enqueue(new Callback<List<Lamaran>>() {
            @Override
            public void onResponse(Call<List<Lamaran>> call, Response<List<Lamaran>> response) {
                if (response.code() == 200 && response.body() != null) {
                    lamaranAdapter.addItems(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Lamaran>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}