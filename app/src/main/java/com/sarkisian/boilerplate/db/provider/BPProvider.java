package com.sarkisian.boilerplate.db.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.sarkisian.boilerplate.BuildConfig;
import com.sarkisian.boilerplate.db.BPDataBase;
import com.sarkisian.boilerplate.db.BPDataBaseHelper;


public class BPProvider extends ContentProvider {

    private static final String LOG_TAG = BPProvider.class.getName();

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID;

    static class Path {
        static final String USER_LOCATION = BPDataBase.USER_TABLE;
    }

    private static class Code {
        private static final int ALL_USERS = 1;
        private static final int SINGLE_USER = 2;
    }

    private static class ContentType {
        private static final String ALL_USERS = "vnd.android.cursor.dir/vnd."
                + AUTHORITY + "." + Path.USER_LOCATION;

        private static final String SINGLE_USER = "vnd.android.cursor.item/vnd."
                + AUTHORITY + "." + Path.USER_LOCATION;
    }

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private BPDataBaseHelper mDataBaseHelper;

    @Override
    public boolean onCreate() {
        mDataBaseHelper = new BPDataBaseHelper(getContext());
        return true;
    }

    @Override
    @Nullable
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case Code.ALL_USERS:
                return ContentType.ALL_USERS;

            case Code.SINGLE_USER:
                return ContentType.SINGLE_USER;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri.toString());
        }
    }

    @Override
    @Nullable
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        Uri returnUri;
        long rowId;

        switch (sUriMatcher.match(uri)) {
            case Code.ALL_USERS:
                // TODO: implement insert logic according to the project requirements
                rowId = db.insertWithOnConflict(
                        BPDataBase.USER_TABLE,
                        null,
                        values,
                        SQLiteDatabase.CONFLICT_REPLACE
                );
                break;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri.toString());
        }

        if (rowId != -1) {
            returnUri = ContentUris.withAppendedId(UriBuilder.buildUserUri(), rowId);
            getContext().getContentResolver().notifyChange(uri, null);
        } else {
            throw new UnsupportedOperationException("Unable to insert rows into " + uri);
        }
        return returnUri;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        Cursor cursor;

        switch (sUriMatcher.match(uri)) {
            case Code.ALL_USERS:
                cursor = db.query(
                        BPDataBase.USER_TABLE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            case Code.SINGLE_USER:
                cursor = db.query(
                        BPDataBase.USER_TABLE,
                        projection,
                        BPDataBase.USER_PK + "=?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder
                );
                break;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri.toString());
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        int rowsDeleted;

        switch (sUriMatcher.match(uri)) {
            case Code.ALL_USERS:
                rowsDeleted = db.delete(
                        BPDataBase.USER_TABLE,
                        selection,
                        selectionArgs
                );
                break;

            case Code.SINGLE_USER:
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = db.delete(
                            BPDataBase.USER_TABLE,
                            BPDataBase.USER_PK + "=" + ContentUris.parseId(uri),
                            null
                    );
                } else {
                    rowsDeleted = db.delete(
                            BPDataBase.USER_TABLE,
                            BPDataBase.USER_PK + "=" + ContentUris.parseId(uri) + " AND " + selection,
                            selectionArgs
                    );
                }
                break;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri.toString());
        }

        if (selection == null || rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdated;
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();

        switch (sUriMatcher.match(uri)) {
            case Code.ALL_USERS:
                rowsUpdated = db.update(
                        BPDataBase.USER_TABLE,
                        values,
                        selection,
                        selectionArgs
                );
                break;

            case Code.SINGLE_USER:
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = db.update(
                            BPDataBase.USER_TABLE,
                            values,
                            BPDataBase.USER_PK + "=" + ContentUris.parseId(uri),
                            null
                    );

                } else {
                    rowsUpdated = db.update(
                            BPDataBase.USER_TABLE,
                            values,
                            BPDataBase.USER_PK + "=" + ContentUris.parseId(uri) + " AND " + selection,
                            selectionArgs
                    );
                }
                break;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri.toString());
        }

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, Path.USER_LOCATION, Code.ALL_USERS);
        uriMatcher.addURI(AUTHORITY, Path.USER_LOCATION + "/#", Code.SINGLE_USER);
        return uriMatcher;
    }

}
