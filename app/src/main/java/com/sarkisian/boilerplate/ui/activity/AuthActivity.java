package com.sarkisian.boilerplate.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.common.eventbus.Subscribe;
import com.sarkisian.boilerplate.R;
import com.sarkisian.boilerplate.sync.bus.BusProvider;
import com.sarkisian.boilerplate.sync.bus.event.ApiEvent;
import com.sarkisian.boilerplate.sync.bus.event.Event;
import com.sarkisian.boilerplate.sync.rest.HttpRequestManager;
import com.sarkisian.boilerplate.sync.rest.util.APIUtil;
import com.sarkisian.boilerplate.sync.rest.util.PostEntityUtil;
import com.sarkisian.boilerplate.sync.service.BPIntentService;
import com.sarkisian.boilerplate.util.AppUtil;
import com.sarkisian.boilerplate.util.Logger;
import com.sarkisian.boilerplate.util.manager.DialogManager;
import com.sarkisian.boilerplate.util.manager.SnackBarManager;

public class AuthActivity extends BaseActivity implements View.OnClickListener {

    private static final String LOG_TAG = AuthActivity.class.getSimpleName();

    private Button mBtnSign;
    private TextInputLayout mTilEmail;
    private TextInputEditText mTietEmail;
    private TextInputLayout mTilPass;
    private TextInputEditText mTietPass;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.unregister(this);
        DialogManager.getInstance().dismissPreloader(this.getClass());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.register(this);
        findViews();
        setListeners();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_auth;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onEventReceived(Event event) {
        if (event instanceof ApiEvent) {
            if (event.getSubscriber().equals(getClass().getSimpleName())) {
                handleApiEvents((ApiEvent) event);
            }
        }
    }

    private void handleApiEvents(ApiEvent event) {
        DialogManager.getInstance().dismissPreloader(this.getClass());

        switch (event.getEventType()) {

            case Event.EventType.Api.LOGIN_COMPLETED:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_auth_sign:
                String mail = mTietEmail.getText().toString();
                String pass = mTietPass.getText().toString();
                grabDataAndSingIn(mail, pass);
                break;
        }
    }

    private void setListeners() {
        mBtnSign.setOnClickListener(this);
    }

    private void findViews() {
        mTilEmail = (TextInputLayout) findViewById(R.id.til_auth_email);
        mTietEmail = (TextInputEditText) findViewById(R.id.tiet_auth_email);
        mTilPass = (TextInputLayout) findViewById(R.id.til_auth_pass);
        mTietPass = (TextInputEditText) findViewById(R.id.tiet_auth_pass);
        mBtnSign = (Button) findViewById(R.id.btn_auth_sign);
    }

    private void grabDataAndSingIn(String mail, String pass) {
        boolean isValidationSucceeded = true;

        // validate password (empty or not)
        if (pass.trim().length() == 0) {
            isValidationSucceeded = false;
            mTilPass.setError(getString(R.string.msg_edt_pass_error));
        }

        // validate email (empty or not)
        if (mail.trim().length() == 0) {
            isValidationSucceeded = false;
            mTilEmail.setError(getString(R.string.msg_edt_error_mail));
        }

        // if required fields are filled up - proceed with login
        if (isValidationSucceeded) {
            DialogManager.getInstance().showPreloader(this);

            mTilEmail.setErrorEnabled(false);
            mTilPass.setErrorEnabled(false);

            AppUtil.closeKeyboard(this);

            String postEntity = PostEntityUtil.composeSignInPostEntity(
                    mail,
                    pass);

            BPIntentService.start(
                    this,
                    getClass().getSimpleName(),
                    APIUtil.getURL(HttpRequestManager.RequestType.LOG_IN),
                    postEntity,
                    HttpRequestManager.RequestType.LOG_IN);
        }
    }

}