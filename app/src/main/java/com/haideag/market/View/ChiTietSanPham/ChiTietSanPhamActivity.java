package com.haideag.market.View.ChiTietSanPham;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.haideag.market.DataBase.DbHelper;
import com.haideag.market.R;
import com.haideag.market.View.Donhang.ThanhToan;

public class ChiTietSanPhamActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnMuaNgay;
    TextView tvGiaTien, tvTenSanPham;
    ImageView imgSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanphamiphone);

        imgSanPham = findViewById(R.id.imgSanPham);
        tvGiaTien = findViewById(R.id.tvgiatien);
        tvTenSanPham = findViewById(R.id.tvtensanpham);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);

        // Register the OnClickListener
        btnMuaNgay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnMuaNgay) {
            Intent intent = new Intent(ChiTietSanPhamActivity.this, ThanhToan.class);
            intent.putExtra("imgSanPham", R.drawable.iphone15promaxwhite);
            intent.putExtra("tenSanPham", tvTenSanPham.getText().toString());
            intent.putExtra("giaTien", tvGiaTien.getText().toString());
            startActivity(intent);
        }
    }
}