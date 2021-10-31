package com.hazelnut.myjobs.User.Perusahaan.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hazelnut.myjobs.App.AppConfig;
import com.hazelnut.myjobs.Model.ApiResponse;
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.User.Perusahaan.Login.LoginActivity;
import com.hazelnut.myjobs.User.Perusahaan.Register.RegisterActivity;
import com.hazelnut.myjobs.databinding.ActivityFormBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {

    private ActivityFormBinding formBinding;
    private AppConfig appConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        formBinding = DataBindingUtil.setContentView(this, R.layout.activity_form);

        appConfig = new AppConfig(this);
        int id = appConfig.getIdOfUser();

        formBinding.btnDaftar.setOnClickListener(v -> {
            createLoker();
            formBinding.showProgress.setVisibility(View.VISIBLE);
        });

        getData(String.valueOf(id));
    }

    public void getData(String id) {
        formBinding.txtIdPerusahaan.setText(id);
    }

    public void createLoker() {
        String id        = formBinding.txtIdPerusahaan.getText().toString();
        String kota      = formBinding.txtKota.getText().toString();
        String jobs      = formBinding.txtNamaPekerjaan.getText().toString();
        String jobs_desc = formBinding.txtJobdesc.getText().toString();
        String tanggal   = formBinding.txtTanggal.getText().toString();

        Call<ApiResponse> call = ApiClient.getApiClient().create(ApiInterface.class)
                .createLoker(id, kota, jobs, jobs_desc, tanggal);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code() == 200) {

                    if(response.body().getStatus().equals("ok")) {

                        if(response.body().getResultCode() == 1) {
                            Toast.makeText(FormActivity.this, "Successfully Created Job Vacancy Information on MYJOBS",
                                    Toast.LENGTH_LONG).show();
                            onBackPressed();
                            finish();

                        } else {
                            displayUserInfo("Failed to Enter Information...");
                            formBinding.txtKota.setText("");
                            formBinding.txtNamaPekerjaan.setText("");
                            formBinding.txtJobdesc.setText("");
                            formBinding.txtTanggal.setText("");
                        }
                    } else {
                        displayUserInfo("Something went wrong...");
                        formBinding.txtKota.setText("");
                        formBinding.txtNamaPekerjaan.setText("");
                        formBinding.txtJobdesc.setText("");
                        formBinding.txtTanggal.setText("");
                    }
                } else {
                    displayUserInfo("Something went wrong...");
                    formBinding.txtKota.setText("");
                    formBinding.txtNamaPekerjaan.setText("");
                    formBinding.txtJobdesc.setText("");
                    formBinding.txtTanggal.setText("");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    private void displayUserInfo(String message) {
        Snackbar.make(formBinding.myCoordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
        formBinding.txtKota.setText("");
        formBinding.txtNamaPekerjaan.setText("");
        formBinding.txtJobdesc.setText("");
        formBinding.txtTanggal.setText("");
        formBinding.showProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}