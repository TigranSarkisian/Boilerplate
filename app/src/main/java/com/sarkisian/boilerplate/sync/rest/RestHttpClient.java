package com.sarkisian.boilerplate.sync.rest;

import android.content.Context;
import android.os.Bundle;

import com.sarkisian.boilerplate.sync.rest.entity.HttpConnection;
import com.sarkisian.boilerplate.sync.rest.util.HttpErrorUtil;
import com.sarkisian.boilerplate.util.Constant;
import com.sarkisian.boilerplate.util.Logger;
import com.sarkisian.boilerplate.util.NetworkUtil;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestHttpClient {

    private static final String LOG_TAG = RestHttpClient.class.getSimpleName();

    private static final String UTF_8 = "UTF-8";
    private static final String TOKEN_VALUE = "Token ";

    private static class Header {
        private static final String AUTHORIZATION = "Authorization";
        private static final String CONTENT_TYPE = "Content-Type";
        private static final String ACCEPT_ENCODING = "Accept-Encoding";
        private static final String X_HTTP_METHOD_OVERRIDE = "X-HTTP-Method-Override";
    }

    private static class PayloadType {
        private static final String APPLICATION_JSON = "application/json";
    }

    public class RequestMethod {
        public static final String POST = "POST";
        public static final String GET = "GET";
        public static final String PUT = "PUT";
        public static final String HEAD = "HEAD";
        public static final String DELETE = "DELETE";
        public static final String PATCH = "PATCH";
    }

    /* All extra data - headers, tokens, json post values will be put to bundle */
    public static class BundleData {
        public static final String TOKEN = "token";
        public static final String JSON_ENTITY = "entity";
    }

    /**
     * HEAD Request
     */
    static HttpConnection executeHeadRequest(Context context, String url, Bundle bundle) {
        Object token = null;
        if (bundle != null) {
            token = bundle.get(BundleData.TOKEN);
        }

        HttpConnection httpConnection = new HttpConnection();
        HttpURLConnection httpURLConnection = null;

        try {
            Logger.i(LOG_TAG, "Calling URL: " + url);
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();

            if (token != null) {
                Logger.i(LOG_TAG, "Token: " + String.valueOf(token));
                httpURLConnection.setRequestProperty(Header.AUTHORIZATION, TOKEN_VALUE + token);
            }

            httpURLConnection.setRequestMethod(RequestMethod.HEAD);
            httpURLConnection.setRequestProperty(Header.CONTENT_TYPE, PayloadType.APPLICATION_JSON);
            httpURLConnection.setRequestProperty(Header.ACCEPT_ENCODING, Constant.Symbol.NULL);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.connect();

            Logger.i(LOG_TAG, "Server response: " + String.valueOf(httpURLConnection.getResponseCode())
                    + Constant.Symbol.SPACE + httpURLConnection.getResponseMessage());

            httpConnection.setHttpURLConnection(httpURLConnection);
            httpConnection.setHttpConnectionSucceeded(true);
            httpConnection.setHttpConnectionCode(httpURLConnection.getResponseCode());
            httpConnection.setHttpConnectionMessage(httpURLConnection.getResponseMessage());

        } catch (Exception e) {
            e.printStackTrace();
            handleFailedConnection(context, url, httpConnection, httpURLConnection, e);
        }
        return httpConnection;
    }

    /**
     * POST Request
     */
    static HttpConnection executePostRequest(Context context, String url, Bundle bundle) {
        Object token = null;
        String postEntity = null;
        if (bundle != null) {
            token = bundle.get(BundleData.TOKEN);
            postEntity = String.valueOf(bundle.get(BundleData.JSON_ENTITY));
        }

        HttpConnection httpConnection = new HttpConnection();
        HttpURLConnection httpURLConnection = null;

        try {
            Logger.i(LOG_TAG, "Calling URL: " + url);
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();

            if (token != null) {
                httpURLConnection.setRequestProperty(Header.AUTHORIZATION, TOKEN_VALUE + token);
                Logger.i(LOG_TAG, "Token: " + String.valueOf(token));
            }

            httpURLConnection.setRequestMethod(RequestMethod.POST);
            httpURLConnection.setRequestProperty(Header.CONTENT_TYPE, PayloadType.APPLICATION_JSON);
            httpURLConnection.setRequestProperty(Header.ACCEPT_ENCODING, Constant.Symbol.NULL);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            OutputStream out = httpURLConnection.getOutputStream();
            out.write(postEntity != null ? postEntity.getBytes(UTF_8) : new byte[]{0});
            out.flush();
            out.close();

            Logger.i(LOG_TAG, "Server response: " + String.valueOf(httpURLConnection.getResponseCode())
                    + Constant.Symbol.SPACE + httpURLConnection.getResponseMessage());

            httpConnection.setHttpURLConnection(httpURLConnection);
            httpConnection.setHttpConnectionSucceeded(true);
            httpConnection.setHttpConnectionCode(httpURLConnection.getResponseCode());
            httpConnection.setHttpConnectionMessage(httpURLConnection.getResponseMessage());

        } catch (Exception e) {
            e.printStackTrace();
            handleFailedConnection(context, url, httpConnection, httpURLConnection, e);
        }
        return httpConnection;
    }

    /**
     * PATCH Request
     */
    static HttpConnection executePatchRequest(Context context, String url, Bundle bundle) {
        Object token = null;
        String postEntity = null;
        if (bundle != null) {
            token = bundle.get(BundleData.TOKEN);
            postEntity = String.valueOf(bundle.get(BundleData.JSON_ENTITY));
        }

        HttpConnection httpConnection = new HttpConnection();
        HttpURLConnection httpURLConnection = null;

        try {
            Logger.i(LOG_TAG, "Calling URL: " + url);
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();

            if (token != null) {
                httpURLConnection.setRequestProperty(Header.AUTHORIZATION, TOKEN_VALUE + token);
                Logger.i(LOG_TAG, "Token: " + String.valueOf(token));
            }

            httpURLConnection.setRequestMethod(RequestMethod.PUT);
            httpURLConnection.setRequestProperty(Header.X_HTTP_METHOD_OVERRIDE, RequestMethod.PATCH);
            httpURLConnection.setRequestProperty(Header.CONTENT_TYPE, PayloadType.APPLICATION_JSON);
            httpURLConnection.setRequestProperty(Header.ACCEPT_ENCODING, Constant.Symbol.NULL);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            OutputStream out = httpURLConnection.getOutputStream();
            out.write(postEntity != null ? postEntity.getBytes(UTF_8) : new byte[]{0});
            out.flush();
            out.close();

            Logger.i(LOG_TAG, "Server response: " + String.valueOf(httpURLConnection.getResponseCode())
                    + Constant.Symbol.SPACE + httpURLConnection.getResponseMessage());

            httpConnection.setHttpURLConnection(httpURLConnection);
            httpConnection.setHttpConnectionSucceeded(true);
            httpConnection.setHttpConnectionCode(httpURLConnection.getResponseCode());
            httpConnection.setHttpConnectionMessage(httpURLConnection.getResponseMessage());

        } catch (Exception e) {
            e.printStackTrace();
            handleFailedConnection(context, url, httpConnection, httpURLConnection, e);
        }
        return httpConnection;
    }

    /**
     * PUT Request
     */
    static HttpConnection executePutRequest(Context context, String url, Bundle bundle) {
        Object token = null;
        String postEntity = null;
        if (bundle != null) {
            token = bundle.get(BundleData.TOKEN);
            postEntity = String.valueOf(bundle.get(BundleData.JSON_ENTITY));
        }

        HttpConnection httpConnection = new HttpConnection();
        HttpURLConnection httpURLConnection = null;

        try {
            Logger.i(LOG_TAG, "Calling URL: " + url);
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();

            if (token != null) {
                httpURLConnection.setRequestProperty(Header.AUTHORIZATION, TOKEN_VALUE + token);
                Logger.i(LOG_TAG, "Token: " + String.valueOf(token));
            }

            httpURLConnection.setRequestMethod(RequestMethod.PUT);
            httpURLConnection.setRequestProperty(Header.CONTENT_TYPE, PayloadType.APPLICATION_JSON);
            httpURLConnection.setRequestProperty(Header.ACCEPT_ENCODING, Constant.Symbol.NULL);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            OutputStream out = httpURLConnection.getOutputStream();
            out.write(postEntity != null ? postEntity.getBytes(UTF_8) : new byte[]{0});
            out.flush();
            out.close();

            Logger.i(LOG_TAG, "Server response: " + String.valueOf(httpURLConnection.getResponseCode())
                    + Constant.Symbol.SPACE + httpURLConnection.getResponseMessage());

            httpConnection.setHttpURLConnection(httpURLConnection);
            httpConnection.setHttpConnectionSucceeded(true);
            httpConnection.setHttpConnectionCode(httpURLConnection.getResponseCode());
            httpConnection.setHttpConnectionMessage(httpURLConnection.getResponseMessage());

        } catch (Exception e) {
            e.printStackTrace();
            handleFailedConnection(context, url, httpConnection, httpURLConnection, e);
        }
        return httpConnection;
    }

    /**
     * GET Request
     */
    static HttpConnection executeGetRequest(Context context, String url, Bundle bundle) {
        Object token = null;
        if (bundle != null) {
            token = bundle.get(BundleData.TOKEN);
        }

        HttpConnection httpConnection = new HttpConnection();
        HttpURLConnection httpURLConnection = null;

        try {
            Logger.i(LOG_TAG, "Calling URL: " + url);
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();

            if (token != null) {
                Logger.i(LOG_TAG, "Token: " + String.valueOf(token));
                httpURLConnection.setRequestProperty(Header.AUTHORIZATION, TOKEN_VALUE + token);
            }

            httpURLConnection.setRequestMethod(RequestMethod.GET);
            httpURLConnection.setRequestProperty(Header.CONTENT_TYPE, PayloadType.APPLICATION_JSON);
            httpURLConnection.setRequestProperty(Header.ACCEPT_ENCODING, Constant.Symbol.NULL);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.connect();

            Logger.i(LOG_TAG, "Server response: " + String.valueOf(httpURLConnection.getResponseCode())
                    + Constant.Symbol.SPACE + httpURLConnection.getResponseMessage());

            httpConnection.setHttpURLConnection(httpURLConnection);
            httpConnection.setHttpConnectionSucceeded(true);
            httpConnection.setHttpConnectionCode(httpURLConnection.getResponseCode());
            httpConnection.setHttpConnectionMessage(httpURLConnection.getResponseMessage());

        } catch (Exception e) {
            e.printStackTrace();
            handleFailedConnection(context, url, httpConnection, httpURLConnection, e);
        }

        return httpConnection;

    }

    /**
     * DELETE Request
     */
    static HttpConnection executeDeleteRequest(Context context, String url, Bundle bundle) {
        Object token = null;
        if (bundle != null) {
            token = bundle.get(BundleData.TOKEN);
        }

        HttpConnection httpConnection = new HttpConnection();
        HttpURLConnection httpURLConnection = null;

        try {
            Logger.i(LOG_TAG, "Calling URL: " + url);
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();

            if (token != null) {
                Logger.i(LOG_TAG, "Token: " + String.valueOf(token));
                httpURLConnection.setRequestProperty(Header.AUTHORIZATION, TOKEN_VALUE + token);
            }

            httpURLConnection.setRequestMethod(RequestMethod.DELETE);
            httpURLConnection.setRequestProperty(Header.CONTENT_TYPE, PayloadType.APPLICATION_JSON);
            httpURLConnection.setRequestProperty(Header.ACCEPT_ENCODING, Constant.Symbol.NULL);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.connect();

            Logger.i(LOG_TAG, "Server response: " + String.valueOf(httpURLConnection.getResponseCode())
                    + Constant.Symbol.SPACE + httpURLConnection.getResponseMessage());

            httpConnection.setHttpURLConnection(httpURLConnection);
            httpConnection.setHttpConnectionSucceeded(true);
            httpConnection.setHttpConnectionCode(httpURLConnection.getResponseCode());
            httpConnection.setHttpConnectionMessage(httpURLConnection.getResponseMessage());

        } catch (Exception e) {
            e.printStackTrace();

            handleFailedConnection(context, url, httpConnection, httpURLConnection, e);
        }
        return httpConnection;
    }

    private static void handleFailedConnection(Context context, String url, HttpConnection httpConnection,
                                               HttpURLConnection httpURLConnection, Exception e) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }

        if (!NetworkUtil.isConnected(context)) {
            Logger.e(LOG_TAG, "Internet connection error ");
            httpConnection.setHttpConnectionSucceeded(false);
            httpConnection.setHttpConnectionCode(HttpErrorUtil.NumericStatusCode.HTTP_NO_NETWORK);
            httpConnection.setHttpConnectionMessage(e.getMessage());

        } else if (e.getMessage() != null && e.getMessage().contains
                (HttpErrorUtil.NonNumericStatusCode.UNABLE_TO_RESOLVE_HOST)) {
            Logger.e(LOG_TAG, "Connection time out " + url);
            httpConnection.setHttpConnectionSucceeded(false);
            httpConnection.setHttpConnectionCode(HttpErrorUtil.NumericStatusCode.UNABLE_TO_RESOLVE_HOST);
            httpConnection.setHttpConnectionMessage(e.getMessage());

        } else if (e.getMessage() != null && e.getMessage().contains
                (HttpErrorUtil.NonNumericStatusCode.HTTP_SERVER_TIMEOUT)) {
            Logger.e(LOG_TAG, "Connection time out " + url);
            httpConnection.setHttpConnectionSucceeded(false);
            httpConnection.setHttpConnectionCode(HttpErrorUtil.NumericStatusCode.HTTP_SERVER_TIMEOUT);
            httpConnection.setHttpConnectionMessage(e.getMessage());

        } else if (e.getMessage() != null && e.getMessage().contains
                (HttpErrorUtil.NonNumericStatusCode.HTTP_CONNECTION_REFUSED)) {
            Logger.e(LOG_TAG, "Connection refused by server " + url);
            httpConnection.setHttpConnectionSucceeded(false);
            httpConnection.setHttpConnectionCode(HttpErrorUtil.NumericStatusCode.HTTP_CONNECTION_REFUSED);
            httpConnection.setHttpConnectionMessage(e.getMessage());

        } else {
            if (e.getMessage() != null) {
                Logger.e(LOG_TAG, e.getMessage());
                httpConnection.setHttpConnectionSucceeded(false);
                httpConnection.setHttpConnectionCode(HttpErrorUtil.NumericStatusCode.HTTP_UNKNOWN_SERVER_ERROR);
                httpConnection.setHttpConnectionMessage(e.getMessage());
            }
        }
    }

}
