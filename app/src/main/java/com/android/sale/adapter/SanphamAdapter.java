package com.android.sale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.sale.R;
import com.android.sale.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {

    Context context;
    int layout;
    ArrayList<Sanpham> sanphamArrayList;

    public SanphamAdapter(Context context, int layout, ArrayList<Sanpham> sanphamArrayList) {
        this.context = context;
        this.layout = layout;
        this.sanphamArrayList = sanphamArrayList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,null);
        ItemHolder itemHolder=new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Sanpham sanpham =sanphamArrayList.get(position);
        holder.tvTensp.setText(sanpham.getTensp());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        holder.tvGiasp.setText("Giá: "+decimalFormat.format(sanpham.getGiasp())+" Đ");
        Picasso.get().load(sanpham.getHinhanhsp()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return sanphamArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView tvTensp,tvGiasp;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageviewsanpham);
            tvTensp=itemView.findViewById(R.id.textviewtensanpham);
            tvGiasp=itemView.findViewById(R.id.textviewgiasp);
        }
    }

}
