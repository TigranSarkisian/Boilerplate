package com.sarkisian.boilerplate.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BPDataBaseHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = BPDataBaseHelper.class.getName();

    private static final String DATABASE_NAME = "BP.DB";
    private static final int DATABASE_VERSION = 1;

    public BPDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BPDataBase.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        BPDataBase.onUpgrade(db, oldVersion, newVersion);
    }

}
