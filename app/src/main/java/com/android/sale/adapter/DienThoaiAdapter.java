package com.android.sale.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.sale.R;
import com.android.sale.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DienThoaiAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Sanpham> sanphamArrayList;

    public DienThoaiAdapter(Context context, int layout, ArrayList<Sanpham> sanphamArrayList) {
        this.context = context;
        this.layout = layout;
        this.sanphamArrayList = sanphamArrayList;
    }

    @Override
    public int getCount() {
        return sanphamArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return sanphamArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView tvTenDienThoai,tvGiaDienThoai,tvMoTaDienThoai;
        public ImageView imgDienThoai;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            viewHolder.tvTenDienThoai=convertView.findViewById(R.id.textviewtendienthoai);
            viewHolder.tvGiaDienThoai=convertView.findViewById(R.id.textviewgiadienthoai);
            viewHolder.tvMoTaDienThoai=convertView.findViewById(R.id.textviewmotadienthoai);
            viewHolder.imgDienThoai=convertView.findViewById(R.id.imageviewdienthoai);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.tvTenDienThoai.setText(sanpham.getTensp());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.tvGiaDienThoai.setText("Giá: "+decimalFormat.format(sanpham.getGiasp())+" Đ");
        viewHolder.tvMoTaDienThoai.setMaxLines(2);
        viewHolder.tvMoTaDienThoai.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tvMoTaDienThoai.setText(sanpham.getMotasp());
        Picasso.get().load(sanpham.getHinhanhsp()).into(viewHolder.imgDienThoai);
        return convertView;
    }
}
