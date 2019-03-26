package com.sarkisian.boilerplate.sync.rest.util;

import com.sarkisian.boilerplate.util.EncodeUtil;
import com.sarkisian.boilerplate.util.Logger;

public class PostEntityUtil {

    private static final String LOG_TAG = PostEntityUtil.class.getSimpleName();

    public static String composeSignInPostEntity(String email, String pass) {
        String entityString = "{\"email\": \"" + EncodeUtil.escapeString(email)
                + "\", \"password\": \"" + EncodeUtil.escapeString(pass) + "\"}";
        Logger.i(LOG_TAG, entityString);
        return entityString;
    }

}
