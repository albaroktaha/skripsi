package com.hazelnut.myjobs.User.Perusahaan.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.hazelnut.myjobs.App.AppConfig;
import com.hazelnut.myjobs.Model.User;
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.databinding.ActivityProfileBinding;
import com.hazelnut.myjobs.databinding.ActivityProfileUserBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding profileBinding;
    private AppConfig appConfig;
    private ApiInterface apiInterface;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        appConfig = new AppConfig(this);
        int id = appConfig.getIdOfPelamar();

        user = (User) getIntent().getSerializableExtra("intent_update");

        profileBinding.btnUpdate.setOnClickListener(v -> {
//            updateUser();
            profileBinding.showProgress.setVisibility(View.VISIBLE);
        });

        init();
    }

    public void init() {
        profileBinding.txtIdPerusahaan.setText(user.getIdPelamar());
        profileBinding.txtUsername.setText(user.getUsername());
        profileBinding.txtPassword.setText(user.getPassword());
        profileBinding.txtNama.setText(user.getNama());
        profileBinding.txtEmail.setText(user.getEmail());
        profileBinding.txtNomortelp.setText(user.getNotelp());
        profileBinding.txtAlamat.setText(user.getAlamat());
    }

}