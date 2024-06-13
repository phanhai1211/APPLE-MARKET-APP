package com.haideag.market.View.TrangChu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;




import com.haideag.market.DataBase.DbHelper;
import com.haideag.market.R;
import com.haideag.market.View.ChiTietSanPham.ChiTietSanPhamActivity;


public class Fragment_iPhone extends Fragment  {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_iphone, container, false);

        LinearLayout iPhone15Promax = view.findViewById(R.id.iPhone15Promax);
        iPhone15Promax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChiTietSanPhamActivity.class));
            }
        });

        return view;
    }
}