package com.haideag.market.DataBase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserDatabase.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "donhang";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY,hoten TEXT, email TEXT, password TEXT)");
        db.execSQL("CREATE TABLE products(productId TEXT PRIMARY KEY,productName TEXT, productPrice INTEGER)");
        db.execSQL("CREATE TABLE orders(orderId INTEGER PRIMARY KEY AUTOINCREMENT, productId TEXT, hoten TEXT, address TEXT, phone TEXT, email TEXT,giatien INTEGER, productPrice INTEGER)");
        db.execSQL("CREATE TABLE donhang(" +
                "id INTEGER PRIMARY KEY," +
                "tenSanPham TEXT," +
                "giaTien TEXT" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS products");

        onCreate(db);
    }
    public boolean insertUser(String hoten, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten", hoten);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public String checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT hoten FROM users WHERE email=? AND password=?", new String[] {email, password});
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(0); // return user's name
        } else {
            return null;
        }
    }
    public boolean insertOrder(String productId, String hoten, String address, String phone, String email, int productPrice,int giatien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", productId);
        contentValues.put("hoten", hoten);
        contentValues.put("giatien", giatien);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("productPrice", productPrice);
        long result = db.insert("orders", null, contentValues);
        return !(result == -1);
    }

    public void insertDonHang(String tenSanPham, String giaTien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSanPham", tenSanPham);
        contentValues.put("giaTien", giaTien);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

}