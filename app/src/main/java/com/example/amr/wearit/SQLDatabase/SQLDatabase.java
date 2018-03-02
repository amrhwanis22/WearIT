package com.example.amr.wearit.SQLDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.amr.wearit.Model.OrderModel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amr on 12/30/17.
 */

public class SQLDatabase extends SQLiteAssetHelper {

    private static final String Db_Name="UsersOrder";
    private static final int DB_Version=1;
    public SQLDatabase(Context context, String name, String storageDirectory, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, storageDirectory, factory, version);
    }

    public SQLDatabase(Context context) {
        super(context, Db_Name, null, DB_Version);

    }
    public List<OrderModel> GetCart()
    {
        SQLiteDatabase db=getReadableDatabase();
        SQLiteQueryBuilder dbqbuilder=new SQLiteQueryBuilder();
        String[] SQLSelect={"ProductName","ProductID","Quantity","Price","Discount","Size"};
        String SQLTable="OrderDetails";
        dbqbuilder.setTables(SQLTable);
        Cursor c=dbqbuilder.query(db,SQLSelect,null,null,null,null,null);

        final List<OrderModel> result = new ArrayList<>();
        if(c.moveToFirst())
        {
            do {
                result.add(new OrderModel(c.getString(c.getColumnIndex("ProductID")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                c.getString(c.getColumnIndex("Price")),
                c.getString(c.getColumnIndex("Discount"))));
            }
            while (c.moveToNext());

        }
        return result;
    }

    public void addToCart(OrderModel orderModel){


        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetails(ProductID, ProductName, Quantity, Price, Discount) VALUES('%s','%s','%s','%s','%s')",
                orderModel.getItemID(),
                orderModel.getItemName(),
                orderModel.getItemQuantity(),
                orderModel.getItemPrice(),
                orderModel.getItemDiscount());

        db.execSQL(query);
    }

    public void cleanCart(){


        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE From OrderDetails");
        db.execSQL(query);

    }



}
