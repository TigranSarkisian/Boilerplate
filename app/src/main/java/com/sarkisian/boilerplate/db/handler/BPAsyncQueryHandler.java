package com.sarkisian.boilerplate.db.handler;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.sarkisian.boilerplate.db.BPDataBase;
import com.sarkisian.boilerplate.db.entity.User;
import com.sarkisian.boilerplate.db.provider.UriBuilder;

import java.lang.ref.WeakReference;

public class BPAsyncQueryHandler extends AsyncQueryHandler {

    private final static String LOG_TAG = BPAsyncQueryHandler.class.getSimpleName();

    public static class QueryToken {
        public static final int GET_USER = 100;
        public static final int GET_USERS = 101;
        public static final int ADD_USER = 102;
        public static final int UPDATE_USER = 104;
        public static final int DELETE_USER = 105;
        public static final int DELETE_USERS = 106;
    }

    private WeakReference<AsyncQueryListener> mQueryListenerReference;

    public BPAsyncQueryHandler(Context context, AsyncQueryListener queryListenerReference) {
        super(context.getContentResolver());
        mQueryListenerReference = new WeakReference<>(queryListenerReference);
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        final AsyncQueryListener queryListener = mQueryListenerReference.get();
        if (queryListener != null) {
            queryListener.onQueryComplete(token, cookie, cursor);
        } else if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    protected void onInsertComplete(int token, Object cookie, Uri uri) {
        final AsyncQueryListener queryListener = mQueryListenerReference.get();
        if (queryListener != null) {
            queryListener.onInsertComplete(token, cookie, uri);
        }
    }

    @Override
    protected void onUpdateComplete(int token, Object cookie, int result) {
        final AsyncQueryListener queryListener = mQueryListenerReference.get();
        if (queryListener != null) {
            queryListener.onUpdateComplete(token, cookie, result);
        }
    }

    @Override
    protected void onDeleteComplete(int token, Object cookie, int result) {
        final AsyncQueryListener queryListener = mQueryListenerReference.get();
        if (queryListener != null) {
            queryListener.onDeleteComplete(token, cookie, result);
        }
    }

    public synchronized void getUser(long userId) {
        startQuery(
                QueryToken.GET_USER,
                null,
                UriBuilder.buildUserUri(),
                BPDataBase.Projection.USER,
                BPDataBase.USER_PK + "=?",
                new String[]{String.valueOf(userId)},
                null
        );
    }

    public synchronized void getUserByPk(long pk) {
        startQuery(
                QueryToken.GET_USER,
                null,
                UriBuilder.buildUserUri(pk),
                BPDataBase.Projection.USER,
                null,
                null,
                null
        );
    }

    public synchronized void getUsers() {
        startQuery(
                QueryToken.GET_USERS,
                null,
                UriBuilder.buildUserUri(),
                BPDataBase.Projection.USER,
                null,
                null,
                null
        );
    }

    public synchronized void addUser(User user) {
        startInsert(
                QueryToken.ADD_USER,
                null,
                UriBuilder.buildUserUri(),
                BPDataBase.composeValues(user, BPDataBase.USER_TABLE)
        );
    }

    public synchronized void updateUser(User user) {
        startUpdate(
                QueryToken.UPDATE_USER,
                null,
                UriBuilder.buildUserUri(),
                BPDataBase.composeValues(user, BPDataBase.USER_TABLE),
                BPDataBase.USER_ID + "=?",
                new String[]{String.valueOf(user.getId())}
        );
    }

    public synchronized void updateUserByPk(User user) {
        startUpdate(
                QueryToken.UPDATE_USER,
                null,
                UriBuilder.buildUserUri(user.getPk()),
                BPDataBase.composeValues(user, BPDataBase.USER_TABLE),
                null,
                null
        );
    }

    public synchronized void deleteUser(User user) {
        startDelete(
                QueryToken.DELETE_USER,
                null,
                UriBuilder.buildUserUri(),
                BPDataBase.USER_ID + "=?",
                new String[]{String.valueOf(user.getId())}
        );
    }

    public synchronized void deleteUserByPk(User user) {
        startDelete(
                QueryToken.DELETE_USER,
                null,
                UriBuilder.buildUserUri(user.getPk()),
                null,
                null
        );
    }

    public synchronized void deleteUsers() {
        startDelete(
                QueryToken.DELETE_USERS,
                null,
                UriBuilder.buildUserUri(),
                null,
                null
        );
    }

    public interface AsyncQueryListener {
        void onQueryComplete(int token, Object cookie, Cursor cursor);

        void onInsertComplete(int token, Object cookie, Uri uri);

        void onUpdateComplete(int token, Object cookie, int result);

        void onDeleteComplete(int token, Object cookie, int result);
    }

}
