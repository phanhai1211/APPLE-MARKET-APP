package com.haideag.market.View.Login_Register;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.haideag.market.Adapter.ViewPagerAdapterLogin;
import com.haideag.market.DataBase.DbHelper;
import com.haideag.market.R;

public class Login_activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_login);



        tabLayout =(TabLayout) findViewById(R.id.tablogin);
        viewPager = (ViewPager) findViewById(R.id.viewpagerlogin);
        toolbar = (Toolbar) findViewById(R.id.Toolbarlogin);

        setSupportActionBar(toolbar);



        ViewPagerAdapterLogin viewPagerAdapterLogin =  new ViewPagerAdapterLogin(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterLogin);
        viewPagerAdapterLogin.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);



    }
}
