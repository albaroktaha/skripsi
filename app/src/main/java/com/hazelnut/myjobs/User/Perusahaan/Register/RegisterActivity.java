package com.hazelnut.myjobs.User.Perusahaan.Register;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.hazelnut.myjobs.Model.ApiResponse;
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.databinding.ActivityRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding registerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        registerBinding.btnRegister.setOnClickListener(v -> {
            performSignUp();
            registerBinding.showProgress.setVisibility(View.VISIBLE);
        });
    }

    private void performSignUp() {
        String username = registerBinding.txtUsername.getText().toString();
        String password = registerBinding.txtPassword.getText().toString();
        String perusahaan = registerBinding.txtPerusahaan.getText().toString();
        String nama = registerBinding.txtNama.getText().toString();
        String email = registerBinding.txtEmail.getText().toString();
        String notelp = registerBinding.txtNomortelp.getText().toString();
        String alamat = registerBinding.txtAlamat.getText().toString();

        Call<ApiResponse> call = ApiClient.getApiClient().create(ApiInterface.class).perfomUserSignIn(username,
                password, perusahaan, nama, email, notelp, alamat);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if(response.code() == 200) {

                    if(response.body().getStatus().equals("ok")) {

                        if(response.body().getResultCode() == 1) {
                            Toast.makeText(RegisterActivity.this, "Registration success. Now you can login",
                                    Toast.LENGTH_LONG).show();
                            onBackPressed();
                            finish();

                        } else {
                            displayUserInfo("User already exists...");
                            registerBinding.txtPassword.setText("");
                        }
                    } else {
                        displayUserInfo("Something went wrong...");
                        registerBinding.txtPassword.setText("");
                    }
                } else {
                    displayUserInfo("Something went wrong...");
                    registerBinding.txtPassword.setText("");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    private void displayUserInfo(String message) {
        Snackbar.make(registerBinding.myCoordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
        registerBinding.txtPassword.setText("");
        registerBinding.showProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}