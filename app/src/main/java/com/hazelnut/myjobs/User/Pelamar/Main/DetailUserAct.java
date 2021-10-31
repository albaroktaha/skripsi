package com.hazelnut.myjobs.User.Pelamar.Main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hazelnut.myjobs.Adapter.LamaranAdapter;
import com.hazelnut.myjobs.Adapter.ListAdapter;
import com.hazelnut.myjobs.App.AppConfig;
import com.hazelnut.myjobs.Model.ApiResponse;
import com.hazelnut.myjobs.Model.Data;
import com.hazelnut.myjobs.Model.Lamaran;
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.Retrofit.ApiClient;
import com.hazelnut.myjobs.Retrofit.ApiInterface;
import com.hazelnut.myjobs.User.Perusahaan.Main.DetailPHAct;
import com.hazelnut.myjobs.User.Perusahaan.Main.FormActivity;
import com.hazelnut.myjobs.databinding.ActivityDetailUserBinding;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserAct extends AppCompatActivity {

    private ActivityDetailUserBinding detailUserBinding;
    //temp data
    private Data detailData;
    private Lamaran lamaran;
    private AppConfig appConfig;
    private ApiInterface apiInterface;
//    private ListAdapter listAdapter;
    private LamaranAdapter lamaranAdapter;

//    private int REQ_PDF = 21;
//    private String pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailUserBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_user);
        appConfig = new AppConfig(this);
        int id = appConfig.getIdOfPelamar();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        detailData = (Data) getIntent().getSerializableExtra("intent_detail");

//        detailUserBinding.btnFile.setOnClickListener(v -> {
//            Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
//            chooseFile.setType("application/pdf");
//            chooseFile = Intent.createChooser(chooseFile, "Choose a file");
//            startActivityForResult(chooseFile, REQ_PDF);
//        });

        detailUserBinding.btnKirim.setOnClickListener(v -> {
            sendCV();
            detailUserBinding.showProgress.setVisibility(View.VISIBLE);
        });

        init();
        getData(String.valueOf(id));
    }

    public void init() {
        detailUserBinding.txtIdLoker.setText(detailData.getIdLoker());
        detailUserBinding.txtNamaPekerjaan.setText(detailData.getJobs());
        detailUserBinding.txtPerusahaan.setText(detailData.getPerusahaan());
        detailUserBinding.txtKota.setText(detailData.getKota());
        detailUserBinding.txtTanggal.setText(detailData.getTanggal());
        detailUserBinding.txtJobdesc.setText(detailData.getJobsDesc());
    }

    public void getData(String id) {
        String nama = appConfig.getNameOfPelamar();
        appConfig.getIdOfPelamar();
        detailUserBinding.txtIdPelamar.setText(id);
        detailUserBinding.txtNama.setText(nama);
    }

    public void sendCV() {
        String idPelamar = detailUserBinding.txtIdPelamar.getText().toString();
        String idLoker   = detailUserBinding.txtIdLoker.getText().toString();

        Call<ApiResponse> call = ApiClient.getApiClient().create(ApiInterface.class)
                .sendCV(idPelamar, idLoker);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code() == 200) {

                    if(response.body().getStatus().equals("ok")) {

                        if(response.body().getResultCode() == 1) {
                            Toast.makeText(DetailUserAct.this, "Successful Sending CV",
                                    Toast.LENGTH_LONG).show();
                            onBackPressed();
                            finish();

                        } else {
                            displayUserInfo("Failed to Enter Information...");
                        }
                    } else {
                        displayUserInfo("Failed to Enter Information...");
                    }
                } else {
                    displayUserInfo("Failed to Enter Information...");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    private void displayUserInfo(String message) {
        Snackbar.make(detailUserBinding.myCoordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
        detailUserBinding.showProgress.setVisibility(View.INVISIBLE);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == REQ_PDF && resultCode == RESULT_OK && data != null) {
//
//            Uri patch = data.getData();
//
//            try {
//                InputStream inputStream = DetailUserAct.this.getContentResolver().openInputStream(patch);
//                byte[] pdfInBytes = new byte[inputStream.available()];
//                inputStream.read(pdfInBytes);
//                pdf =  Base64.encodeToString(pdfInBytes, Base64.DEFAULT);
//
//                Toast.makeText(this, "Document Selected", Toast.LENGTH_SHORT).show();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}