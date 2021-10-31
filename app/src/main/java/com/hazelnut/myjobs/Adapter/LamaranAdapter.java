package com.hazelnut.myjobs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hazelnut.myjobs.Model.Data;
import com.hazelnut.myjobs.Model.Lamaran;
import com.hazelnut.myjobs.R;

import java.util.ArrayList;
import java.util.List;

public class LamaranAdapter extends RecyclerView.Adapter<LamaranAdapter.AdapterHolder>{

    private final Context context;
    private final List<Lamaran> lamaranList;
    private final View emptyview;
    private final LamaranAdapter.ClickHandler handler;
    private static final String TAG = "ExampleAdapterTag";

    public LamaranAdapter(Context context, View emptyview, LamaranAdapter.ClickHandler handler) {
        this.context = context;
        this.emptyview = emptyview;
        this.handler = handler;
        this.lamaranList = new ArrayList<>();
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_lamaran, parent, false);
        return new LamaranAdapter.AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        Lamaran lamaran = lamaranList.get(position);
        holder.nama.setText(lamaran.getNama());
        holder.id.setText(lamaran.getIdPerusahaan());
        holder.perusahaan.setText(lamaran.getPerusahaan());
        holder.jobs.setText(lamaran.getJobs());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //disini mencakup 1 petakan rv
                handler.onItemClicked(lamaran);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lamaranList.size();
    }

    public void addItems(List<Lamaran> lamarans) {
        this.lamaranList.clear();
        this.lamaranList.addAll(lamarans);
        notifyDataSetChanged();

        updateView();
    }

    private void updateView() {
        //fungsi untuk menghilangkan text tidak ada data
        //sistem kerja nya, ketika tidak ada data dari api, maka text empty akan muncul, dan begitu sebaliknya
        if (lamaranList.size() == 0)
            emptyview.setVisibility(View.VISIBLE);
        else
            emptyview.setVisibility(View.GONE);
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {

        TextView nama, id, perusahaan, jobs;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            nama        = itemView.findViewById(R.id.txt_nama);
            id          = itemView.findViewById(R.id.txt_id_perusahaan);
            perusahaan  = itemView.findViewById(R.id.txt_perusahaan);
            jobs        = itemView.findViewById(R.id.txt_nama_pekerjaan);
        }
    }

    public interface ClickHandler {
        //ini fungsinya, ketika item dalam rv diklik maka fungsi ini akan dijalankan
        void onItemClicked(Lamaran lamaran);
    }
}
