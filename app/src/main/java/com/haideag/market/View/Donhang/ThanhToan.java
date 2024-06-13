package com.haideag.market.View.Donhang;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.haideag.market.DataBase.DbHelper;
import com.haideag.market.R;

public class ThanhToan extends AppCompatActivity {
    DbHelper dbHelper = new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoan);

        ImageView imgSanPham = findViewById(R.id.ivProduct1);
        Intent intent = getIntent();
        String tenSanPham = intent.getStringExtra("tenSanPham");
        String giaTien = intent.getStringExtra("giaTien");
        int imgSanPhamResource = intent.getIntExtra("imgSanPham", 0);
        imgSanPham.setImageResource(imgSanPhamResource);

        ImageView ivProduct1 = findViewById(R.id.ivProduct1);
        TextView tvProductName1 = findViewById(R.id.tvProductName1);
        TextView tvProductPrice1 = findViewById(R.id.tvProductPrice1);
        TextView tvTotalAmount = findViewById(R.id.tổng);

        imgSanPham.setImageResource(imgSanPhamResource);
        tvProductName1.setText(tenSanPham);
        tvProductPrice1.setText(giaTien);
        tvTotalAmount.setText("Tổng cộng: " + giaTien);

        SQLiteDatabase database = openOrCreateDatabase("OrderDB", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS orders(tenSanPham VARCHAR, giaTien VARCHAR);");

        Button btnThanhToan = findViewById(R.id.btnDatHang);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.insertDonHang(tenSanPham, giaTien);
                Toast.makeText(ThanhToan.this, "Đã đặt hàng thành công!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}