package com.sarkisian.boilerplate.sync.bus.event;

public abstract class Event {

    private String subscriber;

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public static class EventType {

        public static class Api {

            public static class Error {
                public static final int UNKNOWN = 100;
                public static final int NO_NETWORK = 101;
                public static final int PAGE_NOT_FOUND = 102;
                public static final int BAD_REQUEST = 103;
                public static final int UNAUTHORIZED = 104;
                public static final int SERVER_TIMEOUT = 105;
                public static final int CONNECTION_REFUSED = 106;
            }

            public static final int LOGIN_COMPLETED = 200;
            public static final int LOGOUT_COMPLETED = 201;
        }

        public static class Network {
            public static final int CONNECTED = 300;
            public static final int DISCONNECTED = 301;
        }

    }

}
