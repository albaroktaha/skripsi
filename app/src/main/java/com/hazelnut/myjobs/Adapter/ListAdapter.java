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
import com.hazelnut.myjobs.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.AdapterHolder> {

    private final Context context;
    private final List<Data> dataList;
    private final View emptyview;
    private final ClickHandler handler;
    private static final String TAG = "ExampleAdapterTag";

    public ListAdapter(Context context, View emptyview, ClickHandler handler) {
        this.context = context;
        this.emptyview = emptyview;
        this.handler = handler;
        this.dataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListAdapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.AdapterHolder holder, int position) {
        Data data = dataList.get(position);
        holder.nama.setText(data.getJobs());
        holder.perusahaan.setText(data.getPerusahaan());
        holder.kota.setText(data.getKota());
        holder.tanggal.setText(data.getTanggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //disini mencakup 1 petakan rv
                handler.onItemClicked(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<Data> datas) {
        this.dataList.clear();
        this.dataList.addAll(datas);
        notifyDataSetChanged();

        updateView();
    }

    private void updateView() {
        //fungsi untuk menghilangkan text tidak ada data
        //sistem kerja nya, ketika tidak ada data dari api, maka text empty akan muncul, dan begitu sebaliknya
        if (dataList.size() == 0)
            emptyview.setVisibility(View.VISIBLE);
        else
            emptyview.setVisibility(View.GONE);
    }


    public class AdapterHolder extends RecyclerView.ViewHolder {

        TextView perusahaan, nama, kota, tanggal;
        ImageView iv_picture;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            perusahaan  = itemView.findViewById(R.id.txt_perusahaan);
            nama        = itemView.findViewById(R.id.txt_nama_pekerjaan);
            kota        = itemView.findViewById(R.id.txt_kota);
            tanggal     = itemView.findViewById(R.id.txt_tanggal);
        }
    }

    public interface ClickHandler {
        //ini fungsinya, ketika item dalam rv diklik maka fungsi ini akan dijalankan
        void onItemClicked(Data data);
    }
}
