package com.android.sale.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.sale.R;
import com.android.sale.adapter.LoaispAdapter;
import com.android.sale.adapter.SanphamAdapter;
import com.android.sale.model.Loaisp;
import com.android.sale.model.Sanpham;
import com.android.sale.ultil.CheckConnection;
import com.android.sale.ultil.Server;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> loaispArrayList;
    ArrayList<Sanpham> sanphamArrayList;
    LoaispAdapter loaispAdapter;
    SanphamAdapter sanphamAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchView();
        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
        {
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaiSanPham();
            GetDuLieuSanPhamMoiNhat();
            CatchOnItemListview();
        }
        else
        {
            Toast.makeText(this, "Không Có Kết Nối Internet!", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void CatchOnItemListview() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent=new Intent(MainActivity.this,DienThoai_Activity.class);
                            intent.putExtra("IdLoaisanpham",loaispArrayList.get(position).getId());
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Không Có Kết Nối Internet!", Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent=new Intent(MainActivity.this,Laptop_Activity.class);
                            intent.putExtra("IdLoaisanpham",loaispArrayList.get(position).getId());
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Không Có Kết Nối Internet!", Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(MainActivity.this, LienHe_Activity.class);
                            intent.putExtra("IdLoaisanpham", loaispArrayList.get(position).getId());
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Không Có Kết Nối Internet!", Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(MainActivity.this, ThongTin_Activity.class);
                            intent.putExtra("IdLoaisanpham", loaispArrayList.get(position).getId());
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Không Có Kết Nối Internet!", Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetDuLieuSanPhamMoiNhat() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Server.LinkLoaiSanphamMoiNhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    for (int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject =response.getJSONObject(i);
                            int Id=jsonObject.getInt("id");
                            String TenSp=jsonObject.getString("tensanpham");
                            Integer Giasp=jsonObject.getInt("giasanpham");
                            String Hinhanhsp=jsonObject.getString("hinhanhsanpham");
                            String Motasp=jsonObject.getString("motasanpham");
                            int Idloaisp =jsonObject.getInt("idloaisanpham");
                            sanphamArrayList.add(new Sanpham(Id,TenSp,Giasp,Hinhanhsp,Motasp,Idloaisp));
                            sanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaiSanPham() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Server.LinkLoaiSanpham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                   for (int i=0;i<response.length();i++)
                   {
                       try {
                           JSONObject jsonObject =response.getJSONObject(i);
                           int Id=jsonObject.getInt("id");
                           String TenLoaiSp=jsonObject.getString("tenloaisp");
                           String HinhanhLoaisp=jsonObject.getString("hinhanhloaisp");
                           loaispArrayList.add(new Loaisp(Id,TenLoaiSp,HinhanhLoaisp));
                           loaispAdapter.notifyDataSetChanged();
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
                    loaispArrayList.add(3,new Loaisp(3,"Liên Hệ","https://voip24h.vn/wp-content/uploads/2019/03/phone-png-mb-phone-icon-256.png"));
                    loaispArrayList.add(4,new Loaisp(4,"Thông Tin","https://i.pinimg.com/474x/c1/f5/5a/c1f55af870309edb2afd340d6573db0f.jpg"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> arrayListQuangCao=new ArrayList<>();
        arrayListQuangCao.add("https://sa.tinhte.vn/2014/08/2572609_Hinh_2.jpg");
        arrayListQuangCao.add("https://cellphones.com.vn/sforum/wp-content/uploads/2019/12/Redmi-K30-render-1.jpg");
        arrayListQuangCao.add("https://photo-cms-sggp.zadn.vn/w580/Uploaded/2021/yfsgf/2020_09_24/hinh2_ujfx.jpg");
        arrayListQuangCao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ29nAoUgY1Vu-MDtSNWLA38f21jAYL3eyRcQ&usqp=CAU");
        for (int i=0;i<arrayListQuangCao.size();i++)
        {
            ImageView imageView =new ImageView(getApplicationContext());
            Picasso.get().load(arrayListQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void SearchView() {
        drawerLayout=findViewById(R.id.drawerlayout);
        toolbar=findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper=findViewById(R.id.viewflipper);
        recyclerView=findViewById(R.id.recyclerview);
        navigationView=findViewById(R.id.navigationview);
        listView=findViewById(R.id.lvmanhinhchinh);
        loaispArrayList=new ArrayList<>();
        loaispArrayList.add(0,new Loaisp(0,"Trang Chính","https://bikersaigon.net/wp-content/uploads/2019/02/ngon_la_ca_mau_icon_home-300x300.png"));

        loaispAdapter =new LoaispAdapter(loaispArrayList,getApplicationContext(),R.layout.row_listview_loaisp);
        listView.setAdapter(loaispAdapter);

        sanphamArrayList=new ArrayList<>();
        sanphamAdapter=new SanphamAdapter(getApplicationContext(),R.layout.row_recyclerview_spmoinhat,sanphamArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(sanphamAdapter);
    }
}