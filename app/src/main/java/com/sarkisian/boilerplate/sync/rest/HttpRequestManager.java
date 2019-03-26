package com.sarkisian.boilerplate.sync.rest;

import android.content.Context;
import android.os.Bundle;

import com.sarkisian.boilerplate.sync.bus.BusProvider;
import com.sarkisian.boilerplate.sync.bus.event.ApiEvent;
import com.sarkisian.boilerplate.sync.bus.event.Event;
import com.sarkisian.boilerplate.sync.rest.entity.HttpConnection;
import com.sarkisian.boilerplate.sync.rest.util.HttpErrorUtil;
import com.sarkisian.boilerplate.sync.rest.util.HttpResponseUtil;

public class HttpRequestManager {

    private static final String LOG_TAG = HttpRequestManager.class.getSimpleName();

    public static class RequestType {
        public static final int LOG_IN = 1;
        public static final int LOG_OUT = 2;
        public static final int ITEM = 3;
    }

    /**
     * @param url           - API url
     * @param token         - pass authorization token if required, otherwise pass null
     * @param postEntity    - post request json entity) if required, otherwise pass null
     * @param requestMethod - post, put, delete, get or other
     */
    public static HttpConnection executeRequest(Context context, String requestMethod, String url,
                                                String token, String postEntity) {
        Bundle bundle = new Bundle();
        bundle.putString(RestHttpClient.BundleData.JSON_ENTITY, postEntity);
        bundle.putString(RestHttpClient.BundleData.TOKEN, token);
        HttpConnection httpConnection = null;

        switch (requestMethod) {
            case RestHttpClient.RequestMethod.POST:
                httpConnection = RestHttpClient.executePostRequest(context, url, bundle);
                break;

            case RestHttpClient.RequestMethod.GET:
                httpConnection = RestHttpClient.executeGetRequest(context, url, bundle);
                break;

            case RestHttpClient.RequestMethod.PATCH:
                httpConnection = RestHttpClient.executePatchRequest(context, url, bundle);
                break;

            case RestHttpClient.RequestMethod.PUT:
                httpConnection = RestHttpClient.executePutRequest(context, url, bundle);
                break;

            case RestHttpClient.RequestMethod.DELETE:
                httpConnection = RestHttpClient.executeDeleteRequest(context, url, bundle);
                break;
        }
        httpConnection = HttpResponseUtil.handleConnection(httpConnection);
        return httpConnection;
    }

    public static void handleFailedRequest(String subscriber, HttpConnection httpConnection) {

        switch (httpConnection.getHttpConnectionCode()) {

            case HttpErrorUtil.NumericStatusCode.HTTP_NO_NETWORK:
            case HttpErrorUtil.NumericStatusCode.UNABLE_TO_RESOLVE_HOST:
                BusProvider.getInstance().post(new ApiEvent(Event.EventType.Api.Error.NO_NETWORK,
                        subscriber));
                break;

            case HttpErrorUtil.NumericStatusCode.HTTP_SERVER_TIMEOUT:
                BusProvider.getInstance().post(new ApiEvent(Event.EventType.Api.Error.SERVER_TIMEOUT,
                        subscriber));
                break;

            case HttpErrorUtil.NumericStatusCode.HTTP_UNKNOWN_SERVER_ERROR:
                BusProvider.getInstance().post(new ApiEvent(Event.EventType.Api.Error.UNKNOWN,
                        subscriber));
                break;

            case HttpErrorUtil.NumericStatusCode.HTTP_CONNECTION_REFUSED:
                BusProvider.getInstance().post(new ApiEvent(Event.EventType.Api.Error.CONNECTION_REFUSED,
                        subscriber));
                break;

            case HttpErrorUtil.NumericStatusCode.HTTP_UNAUTHORIZED:
                BusProvider.getInstance().post(new ApiEvent(Event.EventType.Api.Error.UNAUTHORIZED,
                        subscriber));
                break;

            case HttpErrorUtil.NumericStatusCode.HTTP_BAD_REQUEST:
                BusProvider.getInstance().post(new ApiEvent<>(httpConnection.getHttpResponseBody().toString(),
                        Event.EventType.Api.Error.BAD_REQUEST, subscriber));
                break;

            case HttpErrorUtil.NumericStatusCode.HTTP_NOT_FOUND:
                BusProvider.getInstance().post(new ApiEvent(Event.EventType.Api.Error.PAGE_NOT_FOUND,
                        subscriber));
                break;

            default:
                BusProvider.getInstance().post(new ApiEvent(Event.EventType.Api.Error.UNKNOWN,
                        subscriber));
                break;
        }
    }

}
