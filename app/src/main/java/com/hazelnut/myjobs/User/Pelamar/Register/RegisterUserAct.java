package com.hazelnut.myjobs.User.Pelamar.Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hazelnut.myjobs.Model.ApiResponse;
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.User.Perusahaan.Register.RegisterActivity;
import com.hazelnut.myjobs.databinding.ActivityRegisterBinding;
import com.hazelnut.myjobs.databinding.ActivityRegisterUserBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserAct extends AppCompatActivity {

    private ActivityRegisterUserBinding registerUserBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerUserBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_user);

        registerUserBinding.btnRegister.setOnClickListener(v -> {
            signUpUser();
            registerUserBinding.showProgress.setVisibility(View.VISIBLE);
        });
    }

    private void signUpUser() {
        String username = registerUserBinding.txtUsername.getText().toString();
        String password = registerUserBinding.txtPassword.getText().toString();
        String nama = registerUserBinding.txtNama.getText().toString();
        String email = registerUserBinding.txtEmail.getText().toString();
        String notelp = registerUserBinding.txtNomortelp.getText().toString();
        String alamat = registerUserBinding.txtAlamat.getText().toString();

        Call<ApiResponse> call = ApiClient.getApiClient().create(ApiInterface.class)
                .UserSignUp(username, password, nama, email, notelp, alamat);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if(response.code() == 200) {

                    if(response.body().getStatus().equals("ok")) {

                        if(response.body().getResultCode() == 1) {
                            Toast.makeText(RegisterUserAct.this, "Registration success. Now you can login",
                                    Toast.LENGTH_LONG).show();
                            onBackPressed();
                            finish();

                        } else {
                            displayUserInfo("User already exists...");
                            registerUserBinding.txtPassword.setText("");
                        }
                    } else {
                        displayUserInfo("Something went wrong...");
                        registerUserBinding.txtPassword.setText("");
                    }
                } else {
                    displayUserInfo("Something went wrong...");
                    registerUserBinding.txtPassword.setText("");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    private void displayUserInfo(String message) {
        Snackbar.make(registerUserBinding.myCoordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
        registerUserBinding.txtPassword.setText("");
        registerUserBinding.showProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}