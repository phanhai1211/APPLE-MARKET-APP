package com.haideag.market.View.TrangChu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.tabs.TabLayout;

import com.google.android.material.tabs.TabLayoutMediator;
import com.haideag.market.Adapter.ViewPagerAdapter;
import com.haideag.market.DataBase.PreferenceHelper;
import com.haideag.market.Presenter.TrangChuXyLyMenu.IPersenterLogicXuLyMenu;
import com.haideag.market.R;
import com.haideag.market.View.Donhang.Donhang;
import com.haideag.market.View.Login_Register.Login_activity;
import com.haideag.market.View.TrangChu.Fragment.Fragment_iPhone;
import com.haideag.market.View.TrangChu.Fragment.Fragment_iPad;
import com.haideag.market.View.TrangChu.Fragment.Fragment_Mac;
import com.haideag.market.View.TrangChu.Fragment.Fragment_Airpods;

import androidx.viewpager2.widget.ViewPager2;


public class TrangChuActivity extends AppCompatActivity implements View.OnClickListener {



    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    DrawerLayout drawerLayout;

    FrameLayout donHang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IPersenterLogicXuLyMenu logicXuLyMenu = new IPersenterLogicXuLyMenu(this);
        logicXuLyMenu.NameFB(this);
        setContentView(R.layout.trangchu_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);



        viewPager2 = findViewById(R.id.viewpager2);
        tabLayout = findViewById(R.id.tabLayout);


        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        adapter.addFragment(new Fragment_iPhone(), "iPhone");
        adapter.addFragment(new Fragment_iPad(), "iPad");
        adapter.addFragment(new Fragment_Mac(), "Mac");
        adapter.addFragment(new Fragment_Airpods(), "Airpods");




        viewPager2.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                tab.setText(adapter.getPageTitle(i));
            }
        }
        );
        tabLayoutMediator.attach();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);


        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.itdangnhap) {
            Intent iDangNhap = new Intent(this, Login_activity.class);
            startActivity(iDangNhap);
            return true;
        }
        if (id == R.id.itdangxuat) {
            new PreferenceHelper(this).clearName();
            this.invalidateOptionsMenu();
        }if (id == R.id.itdonhangcuatoi) {
            Intent intent = new Intent(TrangChuActivity.this, Donhang.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Find menu item
        MenuItem loginItem = menu.findItem(R.id.itdangnhap);
        PreferenceHelper pref = new PreferenceHelper(this);
        String name = pref.getName();

        if(name != null && name.length() > 0) {
            loginItem.setTitle(name);
        } else {
            loginItem.setTitle("Đăng nhập");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {

    }
}