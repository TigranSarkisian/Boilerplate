package com.sarkisian.boilerplate.sync.bus;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executor;

public class BusProvider {

    private static final String LOG_TAG = BusProvider.class.getName();

    private static final EventBus BUS = new AsyncEventBus(new Executor() {
        private Handler handler;
        @Override
        public void execute(@NonNull Runnable command) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(command);
        }
    });

    public static EventBus getInstance() {
        return BUS;
    }

    private BusProvider() {
    }

    public static void register(Object object) {
        BusProvider.getInstance().register(object);
    }

    public static void unregister(Object object) {
        try {
            BusProvider.getInstance().unregister(object);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
