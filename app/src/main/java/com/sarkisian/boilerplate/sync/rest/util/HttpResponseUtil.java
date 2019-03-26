package com.sarkisian.boilerplate.sync.rest.util;

import com.sarkisian.boilerplate.sync.rest.entity.HttpConnection;
import com.sarkisian.boilerplate.sync.rest.entity.HttpResponseHeader;
import com.sarkisian.boilerplate.util.Constant;
import com.sarkisian.boilerplate.util.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class HttpResponseUtil {

    private static final String LOG_TAG = HttpResponseUtil.class.getSimpleName();

    public static HttpConnection handleConnection(HttpConnection httpConnection) {

        StringBuilder responseStrBuilder = new StringBuilder();
        HttpURLConnection httpURLConnection = httpConnection.getHttpURLConnection();

        if (httpURLConnection != null) {
            InputStream httpInputStream = null;
            InputStreamReader inputStreamReader = null;
            String httpResponseMsg;
            int httpResponseCode;

            try {
                httpResponseCode = httpURLConnection.getResponseCode();
                httpResponseMsg = httpURLConnection.getResponseMessage();
                Logger.i(LOG_TAG, String.valueOf(httpResponseCode) + Constant.Symbol.SPACE
                        + httpResponseMsg);

                if (httpResponseCode <= HttpURLConnection.HTTP_BAD_REQUEST) {
                    if (httpResponseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                        httpInputStream = httpURLConnection.getErrorStream();
                        httpConnection.setHttpConnectionSucceeded(false);
                    } else {
                        httpInputStream = httpURLConnection.getInputStream();
                        httpConnection.setHttpConnectionSucceeded(true);
                    }

                    inputStreamReader = new InputStreamReader(httpInputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String tempLine = bufferedReader.readLine();
                    responseStrBuilder = new StringBuilder();
                    while (tempLine != null) {
                        responseStrBuilder.append(tempLine);
                        tempLine = bufferedReader.readLine();
                    }
                    bufferedReader.close();

                    Logger.i(LOG_TAG, responseStrBuilder.toString());

                    httpConnection.setHttpConnectionCode(httpResponseCode);
                    httpConnection.setHttpConnectionMessage(httpResponseMsg);
                    httpConnection.setHttpResponseHeader(readHeader(httpConnection));
                    httpConnection.setHttpResponseBody(responseStrBuilder);

                } else {
                    if (httpResponseCode == HttpErrorUtil.NumericStatusCode.HTTP_UNAUTHORIZED) {
                        // 401 user not authorized
                        httpConnection.setHttpConnectionSucceeded(false);
                        httpConnection.setHttpConnectionCode(httpResponseCode);
                        httpConnection.setHttpConnectionMessage(httpResponseMsg);
                        httpConnection.setHttpResponseHeader(readHeader(httpConnection));
                        httpConnection.setHttpResponseBody(responseStrBuilder);

                    } else if (httpResponseCode == HttpErrorUtil.NumericStatusCode.HTTP_NOT_FOUND) {
                        // 404 Page not found
                        httpConnection.setHttpConnectionSucceeded(false);
                        httpConnection.setHttpConnectionCode(httpResponseCode);
                        httpConnection.setHttpConnectionMessage(httpResponseMsg);
                        httpConnection.setHttpResponseHeader(readHeader(httpConnection));
                        httpConnection.setHttpResponseBody(responseStrBuilder);

                    } else {
                        // Other errors (405, 500, 501 or else)
                        httpConnection.setHttpConnectionSucceeded(false);
                        httpConnection.setHttpConnectionCode(httpResponseCode);
                        httpConnection.setHttpConnectionMessage(httpResponseMsg);
                        httpConnection.setHttpResponseHeader(readHeader(httpConnection));
                        httpConnection.setHttpResponseBody(responseStrBuilder);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                try {
                    httpURLConnection.disconnect();
                    if (httpInputStream != null) {
                        httpInputStream.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return httpConnection;
    }

    private static HttpResponseHeader readHeader(HttpConnection httpConnection) {

        class Header {
            private static final String AUTHORIZATION = "Authorization";
            private static final String CONTENT_TYPE = "Content-Type";
            private static final String X_TOKEN = "X-Token";
            private static final String E_TAG = "eTag";
            private static final String LAST_MODIFIED = "Last-Modified";
        }

        HttpResponseHeader httpResponseHeader = new HttpResponseHeader();

        try {
            int httpResponseCode = httpConnection.getHttpConnectionCode();
            String httpResponseMsg = httpConnection.getHttpConnectionMessage();

            if (httpConnection.getHttpURLConnection() != null) {
                httpResponseHeader.setToken(httpConnection.getHttpURLConnection()
                        .getHeaderField(Header.X_TOKEN));
                httpResponseHeader.setETag(httpConnection.getHttpURLConnection()
                        .getHeaderField(Header.E_TAG));
                httpResponseHeader.setLastModified(httpConnection.getHttpURLConnection()
                        .getHeaderField(Header.LAST_MODIFIED));
                httpResponseHeader.setHttpConnectionSucceeded(true);
                httpResponseHeader.setHttpConnectionCode(httpResponseCode);
                httpResponseHeader.setHttpConnectionMessage(httpResponseMsg);

                Logger.i(LOG_TAG, "Request header data: "
                        + "\nToken - " + httpResponseHeader.getToken()
                        + "\neTag - " + httpResponseHeader.getETag()
                        + "\nLast-Modified - " + httpResponseHeader.getLastModified());

            } else {
                httpResponseHeader.setHttpConnectionSucceeded(false);
                httpResponseHeader.setHttpConnectionCode(httpResponseCode);
                httpResponseHeader.setHttpConnectionMessage(httpResponseMsg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpResponseHeader;
    }

}
