package com.sarkisian.boilerplate.sync.bus.event;

import android.support.annotation.Nullable;

public class ApiEvent<T> extends Event {

    private int eventType;
    private boolean success;
    private T eventData;

    public ApiEvent() {
    }

    public ApiEvent(int eventType, boolean success) {
        this.eventType = eventType;
        this.success = success;
    }

    public ApiEvent(int eventType) {
        this.eventType = eventType;
    }

    public ApiEvent(T eventData, int eventType) {
        this.eventType = eventType;
        this.eventData = eventData;
    }

    public ApiEvent(int eventType, String subscriber) {
        this.eventType = eventType;
        setSubscriber(subscriber);
    }

    public ApiEvent(T eventData, int eventType, String subscriber) {
        this.eventType = eventType;
        this.eventData = eventData;
        setSubscriber(subscriber);
    }

    @Nullable
    public T getEventData() {
        return eventData;
    }

    public void setEventData(T eventData) {
        this.eventData = eventData;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
