package com.android.sale.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.sale.R;
import com.android.sale.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTiet_Activity extends AppCompatActivity {
    Toolbar toolbarChitiet;
    ImageView imageViewchitiet;
    TextView tvTen,tvGia,tvMota;
    Button btnDatmua;
    Spinner spinner;
    ArrayList<Integer> arrayList;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        SearchView();
        ActionToolbar();
        GetInfomation();
    }

    private void GetInfomation() {
        Sanpham sanpham= (Sanpham) getIntent().getSerializableExtra("chitiet");
        int Id=sanpham.getId();
        String TenSp=sanpham.getTensp();
        Integer Giasp=sanpham.getGiasp();
        String Hinhanhsp=sanpham.getHinhanhsp();
        String Motasp=sanpham.getMotasp();
        int Idloaisp =sanpham.getIdLoaisp();
        tvTen.setText(TenSp);
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        tvGia.setText("Giá: "+decimalFormat.format(Giasp)+" Đ");
        Picasso.get().load(Hinhanhsp).into(imageViewchitiet);
        tvMota.setText(sanpham.getMotasp());
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void SearchView()
    {
        toolbarChitiet=findViewById(R.id.toolbarchitietsanpham);
        imageViewchitiet=findViewById(R.id.imageviewchitietsanpham);
        tvTen=findViewById(R.id.textviewtenchitietsanpham);
        tvGia=findViewById(R.id.textviewgiasanphamchitiet);
        tvMota=findViewById(R.id.textViewmotachitietsanpham);
        btnDatmua=findViewById(R.id.btnthemgiohang);
        spinner=findViewById(R.id.spinner);
        arrayList=new ArrayList<>();
        for(int i=1;i<=10;i++)
        {
            arrayList.add(i);
        }
        adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,arrayList);
        spinner.setAdapter(adapter);
    }

}