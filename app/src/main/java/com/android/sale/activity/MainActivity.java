package com.android.sale.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.sale.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchView();
        ActionBar();
        ActionViewFlipper();
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
    }
}