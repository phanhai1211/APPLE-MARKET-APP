package com.haideag.market.View.Donhang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.haideag.market.DataBase.DbHelper;
import com.haideag.market.R;


public class Donhang extends AppCompatActivity {
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donhang);
        dbHelper = new DbHelper(this);
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM donhang", null);

        if(cursor.moveToFirst()) {
            do {
                int tenSanPhamColumnIndex = cursor.getColumnIndex("tenSanPham");
                int giaTienColumnIndex = cursor.getColumnIndex("giaTien");

                if (tenSanPhamColumnIndex == -1 || giaTienColumnIndex == -1) {
                    continue;
                }

                String tenSanPham = cursor.getString(tenSanPhamColumnIndex);
                String giaTien = cursor.getString(giaTienColumnIndex);

                addOrderToLayout(tenSanPham, giaTien, R.drawable.iphone15promaxwhite);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
    }

    public void insertData(String tenSanPham, String giaTien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSanPham", tenSanPham);
        contentValues.put("giaTien", giaTien);
        db.insert("donhang", null, contentValues);
        db.close();
    }

    public void removeOrderFromDatabase(String tenSanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("donhang", "tenSanPham = ?", new String[]{tenSanPham});
        db.close();
    }

    private void addOrderToLayout(String productName, String productPrice, int productImageId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View newOrderView = inflater.inflate(R.layout.custom_donhang, null);

        ImageView productImageView = newOrderView.findViewById(R.id.ivProduct1);
        TextView productNameTextView = newOrderView.findViewById(R.id.tenSP);
        TextView productPriceTextView = newOrderView.findViewById(R.id.giatien);
        Button removeButton = newOrderView.findViewById(R.id.btnxoa);

        productNameTextView.setText(productName);
        productPriceTextView.setText(productPrice);
        productImageView.setImageResource(productImageId);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup) newOrderView.getParent()).removeView(newOrderView);
                removeOrderFromDatabase(productName);
            }
        });

        LinearLayout ordersLayout = findViewById(R.id.llProductList);
        ordersLayout.addView(newOrderView);
    }
}