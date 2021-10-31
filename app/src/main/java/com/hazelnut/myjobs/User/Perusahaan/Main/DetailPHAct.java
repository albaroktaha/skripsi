package com.hazelnut.myjobs.User.Perusahaan.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.hazelnut.myjobs.Model.Data;
import com.hazelnut.myjobs.R;
import com.hazelnut.myjobs.databinding.ActivityDetailPhBinding;
import com.hazelnut.myjobs.databinding.ActivityDetailUserBinding;

public class DetailPHAct extends AppCompatActivity {

    private ActivityDetailPhBinding detailPhBinding;
    //temp data
    private Data detailData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailPhBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_ph);

        detailData = (Data) getIntent().getSerializableExtra("intent_detail");

        init();
    }

    public void init() {
        detailPhBinding.txtNamaPekerjaan.setText(detailData.getJobs());
        detailPhBinding.txtPerusahaan.setText(detailData.getPerusahaan());
        detailPhBinding.txtKota.setText(detailData.getKota());
        detailPhBinding.txtTanggal.setText(detailData.getTanggal());
        detailPhBinding.txtJobdesc.setText(detailData.getJobsDesc());

//        detailUserBinding.btnKirim.setOnClickListener(v -> {
//            //disini untuk intent ke gmail atau keapllikasi email yang terinstall didalam smartphone user
//            Intent intent = new Intent(Intent.ACTION_SENDTO);
//            intent.setType("message/rfc822");
//            intent.setData(Uri.parse("mailto:"));
//            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {detailData.getEmail()});
//            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name) + "Kirim Lamaran Pekerjaan");
//            intent.putExtra(Intent.EXTRA_TEXT, "Saya ingin melamar di perusahaan ini, saya mendapatkan informasi lowongan ini dari MyJobs");
//
//            startActivity(Intent.createChooser(intent, "Kirim melalui"));
//        });
    }
}