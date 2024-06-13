package com.haideag.market.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.haideag.market.View.Login_Register.Fragment.FragmentDangKy;
import com.haideag.market.View.Login_Register.Fragment.FragmentDangNhap;

public class ViewPagerAdapterLogin extends FragmentPagerAdapter {

    public ViewPagerAdapterLogin(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentDangNhap fragmentDangNhap = new FragmentDangNhap();
                return fragmentDangNhap;
            case 1:
                FragmentDangKy fragmentDangKy = new FragmentDangKy();
                return  fragmentDangKy;

            default: return  null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:

                return "Đăng Nhập";
            case 1:

                return  "Đăng Ký";

            default: return  null;
        }

    }
}
