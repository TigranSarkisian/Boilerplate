package com.sarkisian.boilerplate.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import com.google.common.eventbus.Subscribe;
import com.sarkisian.boilerplate.R;
import com.sarkisian.boilerplate.sync.bus.BusProvider;
import com.sarkisian.boilerplate.sync.bus.event.ApiEvent;
import com.sarkisian.boilerplate.sync.bus.event.Event;
import com.sarkisian.boilerplate.sync.bus.event.NetworkEvent;
import com.sarkisian.boilerplate.sync.rest.HttpRequestManager;
import com.sarkisian.boilerplate.sync.rest.util.APIUtil;
import com.sarkisian.boilerplate.sync.service.BPIntentService;
import com.sarkisian.boilerplate.ui.fragment.MainFragment;
import com.sarkisian.boilerplate.util.AppUtil;
import com.sarkisian.boilerplate.util.Logger;
import com.sarkisian.boilerplate.util.manager.FragmentTransactionManager;
import com.sarkisian.boilerplate.util.manager.SnackBarManager;

public class MainActivity extends BaseActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.register(this);
        findViews();
        setListeners();
        customizeActionBar();
        initDrawer();
        openScreen(MainFragment.newInstance(), false, R.id.nav_one);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.unregister(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Subscribe
    public void onEventReceived(Event event) {
        if (event instanceof NetworkEvent) {
            handleNetworkEvent((NetworkEvent) event);

        } else if (event instanceof ApiEvent) {
            if (event.getSubscriber().equals(getClass().getSimpleName())) {
                handleApiEvents((ApiEvent) event);
            }
        }
    }

    private void handleNetworkEvent(NetworkEvent event) {
        switch (event.getEventType()) {
            case Event.EventType.Network.CONNECTED:
                break;
        }
    }

    private void handleApiEvents(ApiEvent event) {
        switch (event.getEventType()) {
            case Event.EventType.Api.LOGOUT_COMPLETED:
                AppUtil.logOutFromApp(this);
                break;

            case Event.EventType.Api.Error.NO_NETWORK:
                SnackBarManager.show(this, getString(R.string.msg_network_connection_error),
                        SnackBarManager.Duration.LONG);
                break;

            case Event.EventType.Api.Error.PAGE_NOT_FOUND:
                Logger.i(LOG_TAG, getString(R.string.msg_page_not_found));
                break;

            case Event.EventType.Api.Error.BAD_REQUEST:
                String body = (String) event.getEventData();
                if (body != null) {
                    SnackBarManager.show(this, body, SnackBarManager.Duration.LONG);
                    Logger.i(LOG_TAG, getString(R.string.msg_bad_request) + body);
                }
                break;

            case Event.EventType.Api.Error.UNAUTHORIZED:
                Logger.i(LOG_TAG, getString(R.string.msg_not_authorized));
                break;

            case Event.EventType.Api.Error.UNKNOWN:
                SnackBarManager.show(this, getString(R.string.msg_unknown_error),
                        SnackBarManager.Duration.LONG);
                Logger.i(LOG_TAG, getString(R.string.msg_unknown_error));
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_one:
                openScreen(MainFragment.newInstance(), false, R.id.nav_one);
                break;

            case R.id.nav_two:
                break;

            case R.id.nav_three:
                break;

            case R.id.nav_four:
                break;

            case R.id.nav_five:
                break;

            case R.id.nav_six:
                BPIntentService.start(
                        MainActivity.this,
                        MainActivity.class.getSimpleName(),
                        APIUtil.getURL(HttpRequestManager.RequestType.LOG_OUT),
                        HttpRequestManager.RequestType.LOG_OUT);
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
    }

    private void setListeners() {
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void findViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main);
        mNavigationView = (NavigationView) findViewById(R.id.nav_main);
    }

    private void customizeActionBar() {
        setActionBarTitle(getString(R.string.app_name));
    }

    private void initDrawer() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                getToolBar(),
                R.string.msg_navigation_drawer_open,
                R.string.msg_navigation_drawer_close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void openScreen(Fragment fragment, boolean addToBackStack, @IdRes int menuItem) {

        mNavigationView.getMenu().findItem(menuItem).setChecked(true);

        FragmentTransactionManager.displayFragment(
                getSupportFragmentManager(),
                fragment,
                R.id.fl_main_container,
                addToBackStack);
    }

}