package com.hazelnut.myjobs.User.Pelamar.Main;

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
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.User.Pelamar.Login.LoginUserAct;
import com.hazelnut.myjobs.databinding.ActivityMainUserBinding;
import com.hazelnut.myjobs.databinding.ActivityQuickSortBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuickSort extends AppCompatActivity {

    private ActivityQuickSortBinding quickSortBinding;
    private ApiInterface apiInterface;
    private AppConfig appConfig;
    private ListAdapter listAdapter;

    private static final String TAG = "MainUserAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quickSortBinding = DataBindingUtil.setContentView(this, R.layout.activity_quick_sort);
        appConfig = new AppConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String nama = appConfig.getNameOfPelamar();
        int id = appConfig.getIdOfPelamar();

        quickSortBinding.txtWelcome.setText("Welcome " + nama + "\n ID USER : " + id);

        quickSortBinding.btnLogout.setOnClickListener(v -> {
            appConfig.updateUserLoginStatus(false);
            startActivity(new Intent(QuickSort.this, LoginUserAct.class));
            finish();
        });

//        quickSortBinding.btnProfile.setOnClickListener(v -> {
//            startActivity(new Intent(QuickSort.this, ProfileUserAct.class));
////            Intent update = new Intent(MainUserAct.this, ProfileUserAct.class);
////            startActivity(update);
//        });

        quickSortBinding.btnHome.setOnClickListener(v -> {
            startActivity(new Intent(QuickSort.this, MainUserAct.class));
            finish();
        });

        init();
        getData(String.valueOf(id));
    }

    public void getData(String id) {
        //get seluruh data dalam api

        Call<List<Data>> quicksort = apiInterface.quicksort(id);
        quicksort.enqueue(new Callback<List<Data>>() {
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

    public void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        View emptyview = findViewById(R.id.empty_view);

        listAdapter = new ListAdapter(this, emptyview, new ListAdapter.ClickHandler() {
            @Override
            public void onItemClicked(Data data) {
                //ketika item recycleview diklik
                Intent intent = new Intent(QuickSort.this, DetailUserAct.class);
                intent.putExtra("intent_detail", data);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(listAdapter);
    }
}