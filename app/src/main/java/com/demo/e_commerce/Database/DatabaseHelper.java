package com.demo.e_commerce.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.demo.e_commerce.Model.MyCart;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ecomme.db";
    public static final String TABLE_NAME = "items3";
    public static final String ICOL_1 = "ID";
    public static final String ICOL_2 = "PID";
    public static final String ICOL_3 = "image";
    public static final String ICOL_4 = "title";
    public static final String ICOL_5 = "weight";
    public static final String ICOL_6 = "cost";
    public static final String ICOL_7 = "qty";
    public static final String ICOL_8 = "discount";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, PID TEXT , image TEXT ,title TEXT , weight TEXT , cost TEXT, qty TEXT , discount int )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(MyCart myCart) {
        if (getID(myCart.getPID(), myCart.getCost()) == -1) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ICOL_2, myCart.getPID());
            contentValues.put(ICOL_3, myCart.getImage());
            contentValues.put(ICOL_4, myCart.getTitle());
            contentValues.put(ICOL_5, myCart.getWeight());
            contentValues.put(ICOL_6, myCart.getCost());
            contentValues.put(ICOL_7, myCart.getQty());
            contentValues.put(ICOL_8, myCart.getDiscount());
            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1) {
                return false;
            } else {
                Cursor resw = getAllData();
                /*item_name.setText("" + rModel.getTitle());
                txt_countcard.setText("" + resw.getCount());*/
                return true;
            }
        } else {
            return updateData(myCart.getPID(), myCart.getCost(), myCart.getQty());
        }
    }
    @SuppressLint("Range")
    private int getID(String pid, String cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{"PID"}, "PID =? AND cost =? ", new String[]{pid, cost}, null, null, null, null);
        if (c.moveToFirst()) //if the row exist then return the id
            return c.getInt(c.getColumnIndex("PID"));
        return -1;
    }

    @SuppressLint("Range")
    public int getCard(String pid, String cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{"qty"}, "PID =? AND cost =? ", new String[]{pid, cost}, null, null, null, null);
        if (c.moveToFirst()) { //if the row exist then return the id
            return c.getInt(c.getColumnIndex("qty"));
        } else {
            return -1;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String cost, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ICOL_7, status);
        db.update(TABLE_NAME, contentValues, "PID = ? AND cost =?", new String[]{id, cost});
        Cursor res = getAllData();
        //txt_countcard.setText("" + res.getCount());
        return true;
    }
    public void DeleteCard() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);

        //txt_countcard.setText("0");
    }
    public Integer deleteRData(String id, String cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer a = db.delete(TABLE_NAME, "PID = ? AND cost =?", new String[]{id, cost});
        Cursor res = getAllData();
        //txt_countcard.setText("" + res.getCount());
        return a;
    }
}
