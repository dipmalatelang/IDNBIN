package com.app.idnbin.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.idnbin.Assets.Base.SymbolsData;
import com.app.idnbin.LoginRegister.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.LinkedList;
import java.util.List;


public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteDatabaseHandler";
    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "SelectedItemDB";
    private static final String TABLE_NAME = "SelectedItemTbl";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "userid";
    private static final String KEY_IMAGEURL = "imageurl";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_ASSETNAME = "assetname";



    private static final String[] COLUMNS = { KEY_ID, KEY_NAME, KEY_IMAGEURL, KEY_CATEGORY, KEY_ASSETNAME };


    Context context;

    public SQLiteDatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, userid TEXT, imageurl TEXT , category TEXT , assetname TEXT)");
        db.execSQL("CREATE TRIGGER symbol_before_insert " +
                "before insert on SelectedItemTbl begin select case when" +
                "((select id from SelectedItemTbl " +
                "where userid=new.userid  and imageurl=new.imageurl and category=new.category and assetname=new.assetname" +
                 " )IS NOT NULL) then raise(abort,'Duplicate value') end; end");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int DATABASE_VERSION) {
// you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void addSymbol(SymbolsData res, String categry) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, FirebaseAuth.getInstance().getUid());
        values.put(KEY_IMAGEURL, res.getImgaeUrl());
        values.put(KEY_CATEGORY, categry);
        values.put(KEY_ASSETNAME, res.getSymbolName());

        db.insert(TABLE_NAME, null, values);
        db.close();

        Log.i("", "allsymbol: put "+ FirebaseAuth.getInstance().getUid()+" -> "+categry+" symbol "+res.getSymbolName());




    }

    public List<SymbolsData> allSymbols() {

        List<SymbolsData> symbols = new LinkedList<>();
        String query = "SELECT * FROM " + TABLE_NAME +" where userid = '"+ FirebaseAuth.getInstance().getUid()+"'";
//        String query ="SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase myDb = this.getReadableDatabase();
        Cursor cursor = myDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                SymbolsData sym = new SymbolsData();
                sym.setImgaeUrl(cursor.getString(2));
                sym.setCategory(cursor.getString(3));
                sym.setSymbolName(cursor.getString(4));
                symbols.add(sym);
            } while (cursor.moveToNext());
        }

        Log.i(TAG, "allSymbols: "+symbols.size());



        return symbols;
    }

    public boolean deleteData(String assetname, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,
                KEY_NAME + "=? AND " + KEY_ASSETNAME + "=? AND " + KEY_CATEGORY +"=?",
                new String[]{FirebaseAuth.getInstance().getUid(), assetname, category});
      return true;
    }


}
