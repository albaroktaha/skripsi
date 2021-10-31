package com.hazelnut.myjobs.User.Perusahaan.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.hazelnut.myjobs.App.AppConfig;
import com.hazelnut.myjobs.MainActivity;
import com.hazelnut.myjobs.Model.ApiResponse;
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.User.Perusahaan.Main.MainPHAct;
import com.hazelnut.myjobs.databinding.ActivityLoginBinding;
import com.hazelnut.myjobs.User.Perusahaan.Register.RegisterActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;
    private boolean isRememberUserLogin = false;
    private AppConfig appConfig;

    private static final String TAG = "LoginActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        appConfig = new AppConfig(this);

        if(appConfig.isUserLogin()){
            String nama = appConfig.getNameOfUser();
            Integer id  = appConfig.getIdOfUser();
            Intent intent = new Intent(LoginActivity.this, MainPHAct.class);
            intent.putExtra("nama", nama);
            intent.putExtra("id", id);
            startActivity(intent);
            finish();
        }

        loginBinding.tvCreateaccount.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        loginBinding.btnLogin.setOnClickListener(v -> {
            performLogin();
            loginBinding.showProgress.setVisibility(View.VISIBLE);
        });

    }

    public void performLogin() {
        String username = loginBinding.txtUsername.getText().toString();
        String password = loginBinding.txtPassword.getText().toString();

        Call<ApiResponse> call = ApiClient.getApiClient().create(ApiInterface.class).perfomUserLogin(username, password);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code() == 200) {

                    if(response.body().getStatus().equals("ok")) {

                        if(response.body().getResultCode() == 1) {

                            String nama = response.body().getNama();
                            Integer id = response.body().getIdPerusahaan();
                            if (isRememberUserLogin){
                                appConfig.updateUserLoginStatus(true);
                                appConfig.saveNameOfUser(nama);
                                appConfig.saveIdOfUser(id);
                            }

                            Intent intent = new Intent(LoginActivity.this, MainPHAct.class);
                            startActivity(intent);
                            finish();

                        } else {
                            displayUserInformation("Login failed...");
                            loginBinding.txtPassword.setText("");
                        }

                    } else {
                        displayUserInformation("Something went wrong...");
                        loginBinding.txtPassword.setText("");
                    }

                } else {
                    displayUserInformation("Something went wrong...");
                    loginBinding.txtPassword.setText("");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    private void displayUserInformation(String message) {
        Snackbar.make(loginBinding.myCoordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
        loginBinding.showProgress.setVisibility(View.INVISIBLE);
    }

    public void checkBoxClicked(View view) {
        isRememberUserLogin = ((CheckBox)view).isChecked();
    }
}