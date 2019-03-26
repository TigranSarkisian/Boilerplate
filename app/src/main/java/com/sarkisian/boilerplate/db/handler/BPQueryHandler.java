package com.sarkisian.boilerplate.db.handler;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.sarkisian.boilerplate.db.BPDataBase;
import com.sarkisian.boilerplate.db.cursor.CursorReader;
import com.sarkisian.boilerplate.db.entity.User;
import com.sarkisian.boilerplate.db.provider.UriBuilder;

import java.util.ArrayList;

public class BPQueryHandler {

    // ===========================================================
    // Constants
    // ===========================================================

    private final static String LOG_TAG = BPQueryHandler.class.getSimpleName();

    // ===========================================================
    // Methods
    // ===========================================================

    public synchronized static void addUser(Context context, User user) {
        context.getContentResolver().insert(
                UriBuilder.buildUserUri(),
                BPDataBase.composeValues(user, BPDataBase.USER_TABLE)
        );
    }

    public synchronized static void addUsers(Context context, ArrayList<User> userArrayList) {
        context.getContentResolver().bulkInsert(
                UriBuilder.buildUserUri(),
                BPDataBase.composeValuesArray(userArrayList, BPDataBase.USER_TABLE)
        );
    }

    public synchronized static void updateUser(Context context, User user) {
        context.getContentResolver().update(
                UriBuilder.buildUserUri(),
                BPDataBase.composeValues(user, BPDataBase.USER_TABLE),
                BPDataBase.USER_ID + "=?",
                new String[]{String.valueOf(user.getId())}
        );
    }

    public synchronized static void updateUserByPk(Context context, User user) {
        context.getContentResolver().update(
                UriBuilder.buildUserUri(user.getPk()),
                BPDataBase.composeValues(user, BPDataBase.USER_TABLE),
                null,
                null
        );
    }

    public synchronized static void updateUsers(Context context, ArrayList<User> users) {
        for (User user : users) {
            context.getContentResolver().update(
                    UriBuilder.buildUserUri(),
                    BPDataBase.composeValues(user, BPDataBase.USER_TABLE),
                    BPDataBase.USER_ID + "=?",
                    new String[]{String.valueOf(user.getId())}
            );
        }
    }

    public synchronized static User getUser(Context context, long userId) {
        Cursor cursor = context.getContentResolver().query(
                UriBuilder.buildUserUri(),
                BPDataBase.Projection.USER,
                BPDataBase.USER_ID + "=?",
                new String[]{String.valueOf(userId)},
                null
        );
        return CursorReader.parseUser(cursor);
    }

    public synchronized static User getUserByPk(Context context, long userPk) {
        Cursor cursor = context.getContentResolver().query(
                UriBuilder.buildUserUri(userPk),
                BPDataBase.Projection.USER,
                null,
                null,
                null
        );
        return CursorReader.parseUser(cursor);
    }

    public synchronized static ArrayList<User> getUsers(Context context) {
        Cursor cursor = context.getContentResolver().query(
                UriBuilder.buildUserUri(),
                BPDataBase.Projection.USER,
                null,
                null,
                null
        );
        return CursorReader.parseUsers(cursor);
    }

    public synchronized static void deleteUser(Context context, User user) {
        context.getContentResolver().delete(
                UriBuilder.buildUserUri(),
                BPDataBase.USER_ID + "=?",
                new String[]{String.valueOf(user.getId())}
        );
    }

    public synchronized static void deleteUserByPk(Context context, long userPk) {
        context.getContentResolver().delete(
                UriBuilder.buildUserUri(userPk),
                null,
                null
        );
    }

    public synchronized static void deleteUsers(Context context) {
        context.getContentResolver().delete(
                UriBuilder.buildUserUri(),
                null,
                null
        );
    }

    public synchronized static boolean isTableEmpty(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(
                uri,
                BPDataBase.Projection.COUNT,
                null,
                null,
                null
        );

        boolean exist = false;
        if (cursor != null) {
            cursor.moveToFirst();
            exist = cursor.getInt(0) > 0;
            cursor.close();
        }

        return exist;
    }

}