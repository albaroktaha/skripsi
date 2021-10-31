package com.hazelnut.myjobs.User.Pelamar.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hazelnut.myjobs.Adapter.ListAdapter;
import com.hazelnut.myjobs.App.AppConfig;
import com.hazelnut.myjobs.Model.Data;
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.User.Pelamar.Login.LoginUserAct;
import com.hazelnut.myjobs.databinding.ActivityMainUserBinding;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainUserAct extends AppCompatActivity {

    private ActivityMainUserBinding mainUserBinding;
    private ApiInterface apiInterface;
    private AppConfig appConfig;
    private ListAdapter listAdapter;

    private static final String TAG = "MainUserAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainUserBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_user);
        appConfig = new AppConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String nama = appConfig.getNameOfPelamar();
        int id = appConfig.getIdOfPelamar();

        mainUserBinding.txtWelcome.setText("Welcome " + nama + "\n ID USER : " + id);

        mainUserBinding.btnLogout.setOnClickListener(v -> {
            appConfig.updateUserLoginStatus(false);
            appConfig.resetStatusUser();
            startActivity(new Intent(MainUserAct.this, LoginUserAct.class));
            finish();
        });

//        mainUserBinding.btnProfile.setOnClickListener(v -> {
//            startActivity(new Intent(MainUserAct.this, ProfileUserAct.class));
////            Intent update = new Intent(MainUserAct.this, ProfileUserAct.class);
////            startActivity(update);
//        });

        mainUserBinding.btnQuicksort.setOnClickListener(v -> {
//            startActivity(new Intent(MainUserAct.this, QuickSort.class));
            startActivity(new Intent(MainUserAct.this, QuickSort.class));
            finish();
        });

//        Bottom Navigation
//        mainUserBinding.bottomNavigation.setOnNavigationItemSelectedListener(navListener);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
//
        init();
        getData(String.valueOf(id));
    }

    public void getData(String id) {
        //get seluruh data dalam api

        Call<List<Data>> loker = apiInterface.loker(id);
        loker.enqueue(new Callback<List<Data>>() {
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
                Intent intent = new Intent(MainUserAct.this, DetailUserAct.class);
                intent.putExtra("intent_detail", data);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(listAdapter);
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Fragment selectedFragment = null;
//
//            switch (item.getItemId()){
//                case R.id.nav_home:
//                    selectedFragment = new MainFragment();
//                    break;
//                case R.id.nav_inbox:
//                    selectedFragment = new MainFragment();
//
//                    break;
//                case R.id.nav_user:
//                    selectedFragment = new ProfileFragment();
//                    break;
//            }
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
//
//            return true;
//        }
//    };
}