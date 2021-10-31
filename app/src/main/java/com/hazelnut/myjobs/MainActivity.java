package com.hazelnut.myjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hazelnut.myjobs.Adapter.ListAdapter;
import com.hazelnut.myjobs.App.AppConfig;
import com.hazelnut.myjobs.Model.Data;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.User.Pelamar.Login.LoginUserAct;
import com.hazelnut.myjobs.User.Perusahaan.Login.LoginActivity;
import com.hazelnut.myjobs.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainBinding.btnPerusahaan.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

        mainBinding.btnPelamar.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginUserAct.class));
            finish();
        });
    }
}
