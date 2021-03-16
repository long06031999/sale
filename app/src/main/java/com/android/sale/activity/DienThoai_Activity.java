package com.android.sale.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.sale.R;
import com.android.sale.adapter.DienThoaiAdapter;
import com.android.sale.model.Loaisp;
import com.android.sale.model.Sanpham;
import com.android.sale.ultil.Server;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienThoai_Activity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<Sanpham> dienthoaiArrayList;
    DienThoaiAdapter adapter;
    int idloaisp=0;
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        SearchView();
        GetIDLoaiSanPham();
        ActionToolbar();
        GetData(page);
        CatchOnListview();
    }

    private void CatchOnListview() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(DienThoai_Activity.this,ChiTiet_Activity.class);
                intent.putExtra("chitiet",dienthoaiArrayList.get(position));
                startActivity(intent);
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String link=Server.LinkDienThoai+String.valueOf(page);
        StringRequest stringRequest =new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null)
                {
                    try {
                        JSONArray jsonArray =new JSONArray(response);
                        for (int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject jsonObject =jsonArray.getJSONObject(i);
                                int Id=jsonObject.getInt("id");
                                String TenSp=jsonObject.getString("tensanpham");
                                Integer Giasp=jsonObject.getInt("giasanpham");
                                String Hinhanhsp=jsonObject.getString("hinhanhsanpham");
                                String Motasp=jsonObject.getString("motasanpham");
                                int Idloaisp =jsonObject.getInt("idloaisanpham");
                                dienthoaiArrayList.add(new Sanpham(Id,TenSp,Giasp,Hinhanhsp,Motasp,Idloaisp));
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DienThoai_Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<String, String>();
                param.put("idloaisanpham",String.valueOf(idloaisp));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIDLoaiSanPham() {
        idloaisp=getIntent().getIntExtra("IdLoaisanpham",-1);
    }

    private void SearchView() {
        toolbar=findViewById(R.id.toolbardienthoai);
        listView=findViewById(R.id.listviewdienthoai);
        dienthoaiArrayList=new ArrayList<>();
        adapter=new DienThoaiAdapter(getApplicationContext(),R.layout.row_dien_thoai,dienthoaiArrayList);
        listView.setAdapter(adapter);
    }
}