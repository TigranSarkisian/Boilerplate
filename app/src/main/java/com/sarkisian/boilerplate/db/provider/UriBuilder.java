package com.sarkisian.boilerplate.db.provider;

import android.net.Uri;

public class UriBuilder {

    public static Uri buildUserUri(long pk) {
        return Uri.parse("content://" + BPProvider.AUTHORITY + "/"
                + BPProvider.Path.USER_LOCATION + "/" + pk);
    }

    public static Uri buildUserUri() {
        return Uri.parse("content://" + BPProvider.AUTHORITY + "/"
                + BPProvider.Path.USER_LOCATION);
    }

}
