package com.sarkisian.boilerplate.util.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sarkisian.boilerplate.sync.bus.BusProvider;
import com.sarkisian.boilerplate.sync.bus.event.Event;
import com.sarkisian.boilerplate.sync.bus.event.NetworkEvent;

public class NetworkStateReceiver extends BroadcastReceiver {

    private static NetworkStateReceiver sInstance;
    private Context mContext;

    public NetworkStateReceiver(Context context) {
        mContext = context;
    }

    public static NetworkStateReceiver getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NetworkStateReceiver(context);
        }
        return sInstance;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission")
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (activeNetwork != null) {
            BusProvider.getInstance().post(new NetworkEvent(Event.EventType.Network.CONNECTED));
            // TODO: Implement unregister logic depending on project demands

        } else {
            BusProvider.getInstance().post(new NetworkEvent(Event.EventType.Network.DISCONNECTED));
        }
    }

    public void registerBroadcast() {
        mContext.registerReceiver(sInstance,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void unregisterBroadcast() {
        try {
            mContext.unregisterReceiver(sInstance);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        mContext = null;
    }

}
