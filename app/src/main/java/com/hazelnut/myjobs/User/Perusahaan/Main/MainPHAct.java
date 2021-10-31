package com.hazelnut.myjobs.User.Perusahaan.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hazelnut.myjobs.Adapter.ListAdapter;
import com.hazelnut.myjobs.App.AppConfig;
import com.hazelnut.myjobs.Model.Data;
import com.hazelnut.myjobs.Model.User;
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.User.Pelamar.Main.ProfileUserAct;
import com.hazelnut.myjobs.User.Perusahaan.Login.LoginActivity;
import com.hazelnut.myjobs.databinding.ActivityMainPhBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPHAct extends AppCompatActivity {

    private ActivityMainPhBinding mainPhBinding;
    private AppConfig appConfig;
    private ApiInterface apiInterface;
    private ListAdapter listAdapter;

    private static final String TAG = "MainPHAct";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPhBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_ph);
        appConfig = new AppConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String nama = appConfig.getNameOfUser();
        int id = appConfig.getIdOfUser();

        mainPhBinding.txtWelcome.setText("Welcome, "+nama+"\n"+"ID Perusahaan : "+id);

        mainPhBinding.btnLogout.setOnClickListener(v -> {
            appConfig.updateUserLoginStatus(false);
            appConfig.resetStatusUser();
            startActivity(new Intent(MainPHAct.this, LoginActivity.class));
            finish();
        });

//        mainPhBinding.btnProfile.setOnClickListener(v -> {
//            Intent intent = new Intent(MainPHAct.this, ProfileActivity.class);
//            intent.putExtra("intent_update", user);
//            startActivity(intent);
//        });

        mainPhBinding.btnLamaran.setOnClickListener(v -> {
            Intent intent = new Intent(MainPHAct.this, ListLamaranActivity.class);
            startActivity(intent);
        });

        mainPhBinding.btnCreate.setOnClickListener(v -> {
            Intent intent = new Intent(MainPHAct.this, FormActivity.class);
            startActivity(intent);
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

        listAdapter = new ListAdapter(this, emptyview, new ListAdapter.ClickHandler() {
            @Override
            public void onItemClicked(Data data) {
                //Ketika item RV di Klik
                Intent intent = new Intent(MainPHAct.this, DetailPHAct.class);
                intent.putExtra("intent_detail", data);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(listAdapter);
    }

    public void getData(String id) {
        //GET SELURUH DATA DALAM API

        Call<List<Data>> lokerlist = apiInterface.lokerList(id);
        lokerlist.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (response.code() == 200 && response.body() != null) {
                    listAdapter.addItems(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}