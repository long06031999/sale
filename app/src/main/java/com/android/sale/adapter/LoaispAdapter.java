package com.android.sale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.sale.R;
import com.android.sale.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arrayListloaisp;
    Context context;
    int layout;

    public LoaispAdapter(ArrayList<Loaisp> arrayListloaisp, Context context, int layout) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListloaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView tvTenloaisp;
        ImageView imgLoaisp;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            viewHolder.tvTenloaisp=convertView.findViewById(R.id.textviewloaisp);
            viewHolder.imgLoaisp=convertView.findViewById(R.id.imageviewloaisp);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Loaisp loaisp= (Loaisp) getItem(position);
        viewHolder.tvTenloaisp.setText(loaisp.getTenLoaisp());
        Picasso.get().load(loaisp.getHinhanhloaisp()).into(viewHolder.imgLoaisp);
        return convertView;
    }
}
