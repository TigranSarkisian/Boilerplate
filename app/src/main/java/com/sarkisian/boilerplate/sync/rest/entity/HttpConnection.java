package com.sarkisian.boilerplate.sync.rest.entity;

import java.net.HttpURLConnection;

public class HttpConnection {

    private int httpConnectionCode;
    private boolean httpConnectionSucceeded;
    private String httpConnectionMessage;
    private StringBuilder httpResponseBody;
    private HttpURLConnection httpURLConnection;
    private HttpResponseHeader mHttpResponseHeader;

    public HttpConnection(HttpURLConnection httpURLConnection, String httpConnectionMessage,
                          int httpConnectionCode) {
        this.httpConnectionCode = httpConnectionCode;
        this.httpConnectionMessage = httpConnectionMessage;
        this.httpURLConnection = httpURLConnection;
    }

    public HttpConnection(HttpURLConnection httpURLConnection, String httpConnectionMessage,
                          int httpConnectionCode, boolean httpConnectionSucceeded) {
        this.httpConnectionCode = httpConnectionCode;
        this.httpConnectionSucceeded = httpConnectionSucceeded;
        this.httpConnectionMessage = httpConnectionMessage;
        this.httpURLConnection = httpURLConnection;
    }

    public HttpConnection(HttpURLConnection httpURLConnection, StringBuilder httpResponseBody,
                          boolean httpConnectionSucceeded, String httpConnectionMessage,
                          int httpConnectionCode) {
        this.httpConnectionSucceeded = httpConnectionSucceeded;
        this.httpConnectionMessage = httpConnectionMessage;
        this.httpConnectionCode = httpConnectionCode;
        this.httpURLConnection = httpURLConnection;
        this.httpResponseBody = httpResponseBody;
    }

    public HttpConnection(HttpURLConnection httpURLConnection, HttpResponseHeader httpResponseHeader,
                          StringBuilder httpResponseBody, int httpConnectionCode,
                          boolean httpConnectionSucceeded, String httpConnectionMessage) {
        this.httpConnectionCode = httpConnectionCode;
        this.httpConnectionSucceeded = httpConnectionSucceeded;
        this.httpConnectionMessage = httpConnectionMessage;
        this.httpURLConnection = httpURLConnection;
        this.httpResponseBody = httpResponseBody;
        this.mHttpResponseHeader = httpResponseHeader;
    }

    public HttpConnection() {
    }

    public HttpURLConnection getHttpURLConnection() {
        return httpURLConnection;
    }

    public void setHttpURLConnection(HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }

    public int getHttpConnectionCode() {
        return httpConnectionCode;
    }

    public void setHttpConnectionCode(int httpConnectionErrorCode) {
        this.httpConnectionCode = httpConnectionErrorCode;
    }

    public String getHttpConnectionMessage() {
        return httpConnectionMessage;
    }

    public void setHttpConnectionMessage(String httpConnectionErrorMessage) {
        this.httpConnectionMessage = httpConnectionErrorMessage;
    }

    public boolean isHttpConnectionSucceeded() {
        return httpConnectionSucceeded;
    }

    public void setHttpConnectionSucceeded(boolean httpConnectionEstablished) {
        this.httpConnectionSucceeded = httpConnectionEstablished;
    }

    public StringBuilder getHttpResponseBody() {
        return httpResponseBody;
    }

    public void setHttpResponseBody(StringBuilder httpRequestResponseBody) {
        this.httpResponseBody = httpRequestResponseBody;
    }

    public HttpResponseHeader getHttpResponseHeader() {
        return mHttpResponseHeader;
    }

    public void setHttpResponseHeader(HttpResponseHeader httpResponseHeader) {
        this.mHttpResponseHeader = httpResponseHeader;
    }

}
