package com.hazelnut.myjobs.User.Pelamar.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.hazelnut.myjobs.App.AppConfig;
import com.hazelnut.myjobs.Model.ApiResponse;
import com.hazelnut.myjobs.Model.Data;
import com.hazelnut.myjobs.Model.User;
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.User.Perusahaan.Main.FormActivity;
import com.hazelnut.myjobs.databinding.ActivityProfileUserBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileUserAct extends AppCompatActivity {

    private ActivityProfileUserBinding profileUserBinding;
    private AppConfig appConfig;
    private ApiInterface apiInterface;
    private User userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileUserBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile_user);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        appConfig = new AppConfig(this);
        int id = appConfig.getIdOfPelamar();

        profileUserBinding.btnUpdate.setOnClickListener(v -> {
//            updateUser();
            profileUserBinding.showProgress.setVisibility(View.VISIBLE);
        });

//        Bottom Navigation
        profileUserBinding.bottomNavigation.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

        init();
        getData(String.valueOf(id));
    }

    public void init() {
        profileUserBinding.txtUsername.setText(userData.getUsername());
        profileUserBinding.txtPassword.setText(userData.getPassword());
        profileUserBinding.txtEmail.setText(userData.getEmail());
        profileUserBinding.txtNomortelp.setText(userData.getNotelp());
        profileUserBinding.txtAlamat.setText(userData.getAlamat());
    }

    public void getData(String id) {
        appConfig.getIdOfPelamar();
        String nama = appConfig.getNameOfPelamar();
        profileUserBinding.txtIdPelamar.setText(id);
        profileUserBinding.txtNama.setText(nama);
        
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new MainFragment();
                    break;
                case R.id.nav_inbox:
                    selectedFragment = new MainFragment();

                    break;
                case R.id.nav_user:
                    selectedFragment = new ProfileFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };



//    public void updateUser() {
//        String id        = profileUserBinding.txtIdPelamar.getText().toString();
//        String username  = profileUserBinding.txtUsername.getText().toString();
//        String password  = profileUserBinding.txtPassword.getText().toString();
//        String nama      = profileUserBinding.txtNama.getText().toString();
//        String email     = profileUserBinding.txtEmail.getText().toString();
//        String notelp    = profileUserBinding.txtNomortelp.getText().toString();
//        String alamat    = profileUserBinding.txtAlamat.getText().toString();
//
//        Call<ApiResponse> call = ApiClient.getApiClient().create(ApiInterface.class)
//                .userUpdate(id, username, password, nama, email, notelp, alamat);
//        call.enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                if(response.code() == 200) {
//
//                    if(response.body().getStatus().equals("ok")) {
//
//                        if(response.body().getResultCode() == 1) {
//                            Toast.makeText(ProfileUserAct.this, "Successfully Changed",
//                                    Toast.LENGTH_LONG).show();
//                            onBackPressed();
//                            finish();
//
//                        } else {
//                            displayUserInfo("Failed to Enter Information...");
//                            profileUserBinding.txtPassword.setText("");
//                        }
//                    } else {
//                        displayUserInfo("Something went wrong...");
//                        profileUserBinding.txtPassword.setText("");
//                    }
//                } else {
//                    displayUserInfo("Something went wrong...");
//                    profileUserBinding.txtPassword.setText("");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void displayUserInfo(String message) {
//        Snackbar.make(profileUserBinding.myCoordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
//        profileUserBinding.txtPassword.setText("");
//        profileUserBinding.showProgress.setVisibility(View.INVISIBLE);
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
}