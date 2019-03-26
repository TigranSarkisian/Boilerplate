package com.sarkisian.boilerplate.sync.rest.entity;

public class HttpResponseHeader {

    private int httpConnectionCode;
    private boolean httpConnectionSucceeded;
    private String httpConnectionMessage;
    private String token;
    private String eTag;
    private String lastModified;

    public HttpResponseHeader(String token, String eTag) {
        this.token = token;
        this.eTag = eTag;
    }

    public HttpResponseHeader(String token, String eTag, String lastModified) {
        this.token = token;
        this.eTag = eTag;
        this.lastModified = lastModified;
    }

    public HttpResponseHeader(String token, String eTag, String lastModified,
                              boolean httpConnectionSucceeded) {
        this.token = token;
        this.eTag = eTag;
        this.lastModified = lastModified;
        this.httpConnectionSucceeded = httpConnectionSucceeded;
    }

    public HttpResponseHeader(String token, String eTag, String lastModified, int httpConnectionCode,
                              boolean httpConnectionSucceeded, String httpConnectionMessage) {
        this.token = token;
        this.eTag = eTag;
        this.lastModified = lastModified;
        this.httpConnectionCode = httpConnectionCode;
        this.httpConnectionSucceeded = httpConnectionSucceeded;
        this.httpConnectionMessage = httpConnectionMessage;
    }

    public HttpResponseHeader() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public boolean isHttpConnectionSucceeded() {
        return httpConnectionSucceeded;
    }

    public void setHttpConnectionSucceeded(boolean httpConnectionSucceeded) {
        this.httpConnectionSucceeded = httpConnectionSucceeded;
    }

    public int getHttpConnectionCode() {
        return httpConnectionCode;
    }

    public void setHttpConnectionCode(int httpConnectionCode) {
        this.httpConnectionCode = httpConnectionCode;
    }

    public String getHttpConnectionMessage() {
        return httpConnectionMessage;
    }

    public void setHttpConnectionMessage(String httpConnectionMessage) {
        this.httpConnectionMessage = httpConnectionMessage;
    }

}
