package com.sarkisian.boilerplate.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.sarkisian.boilerplate.db.entity.User;
import com.sarkisian.boilerplate.util.Logger;

import java.util.ArrayList;

public class BPDataBase {

    private static final String LOG_TAG = BPDataBase.class.getName();

    public static final String USER_TABLE = "USER_TABLE";

    public static final String USER_PK = "_id";
    public static final String USER_ID = "USER_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_FIRST_NAME = "USER_FIRST_NAME";
    public static final String USER_LAST_NAME = "USER_LAST_NAME";
    public static final String USER_EMAIL = "USER_EMAIL";
    public static final String USER_PHONE_NUMBER = "USER_PHONE_NUMBER";
    public static final String USER_AVATAR = "USER_AVATAR";

    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE
            + " ("
            + USER_PK + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER UNIQUE, "
            + USER_NAME + " TEXT, "
            + USER_FIRST_NAME + " TEXT, "
            + USER_LAST_NAME + " TEXT, "
            + USER_EMAIL + " TEXT, "
            + USER_PHONE_NUMBER + " TEXT, "
            + USER_AVATAR + " TEXT"
            + ");";

    public static class Projection {
        public static final String[] USER = {
                BPDataBase.USER_PK,
                BPDataBase.USER_ID,
                BPDataBase.USER_NAME,
                BPDataBase.USER_FIRST_NAME,
                BPDataBase.USER_LAST_NAME,
                BPDataBase.USER_EMAIL,
                BPDataBase.USER_PHONE_NUMBER,
                BPDataBase.USER_AVATAR
        };

        public static final String[] COUNT = {
                "COUNT(*)"
        };
    }

    static void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // implement update according to the project requirements
        Logger.i(LOG_TAG, "Upgrading database from version "
                + oldVersion + " to " + newVersion);

        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public static ContentValues composeValues(Object object, String table) {
        ContentValues values = new ContentValues();

        switch (table) {
            case BPDataBase.USER_TABLE:
                User user = (User) object;
                values.put(BPDataBase.USER_ID, user.getId());
                values.put(BPDataBase.USER_NAME, user.getName());
                values.put(BPDataBase.USER_FIRST_NAME, user.getFirstName());
                values.put(BPDataBase.USER_LAST_NAME, user.getLastName());
                values.put(BPDataBase.USER_EMAIL, user.getEmail());
                values.put(BPDataBase.USER_PHONE_NUMBER, user.getPhoneNumber());
                values.put(BPDataBase.USER_AVATAR, user.getAvatar());
                break;
        }
        return values;
    }

    public static ContentValues[] composeValuesArray(ArrayList<?> objects, String table) {
        ArrayList<ContentValues> valuesList = new ArrayList<>();

        switch (table) {
            case BPDataBase.USER_TABLE:
                ArrayList<User> users = (ArrayList<User>) objects;
                for (User user : users) {
                    ContentValues values = new ContentValues();
                    values.put(BPDataBase.USER_ID, user.getId());
                    values.put(BPDataBase.USER_NAME, user.getName());
                    values.put(BPDataBase.USER_FIRST_NAME, user.getFirstName());
                    values.put(BPDataBase.USER_LAST_NAME, user.getLastName());
                    values.put(BPDataBase.USER_EMAIL, user.getEmail());
                    values.put(BPDataBase.USER_PHONE_NUMBER, user.getPhoneNumber());
                    values.put(BPDataBase.USER_AVATAR, user.getAvatar());
                    valuesList.add(values);
                }
                break;
        }
        return valuesList.toArray(new ContentValues[valuesList.size()]);
    }

}
