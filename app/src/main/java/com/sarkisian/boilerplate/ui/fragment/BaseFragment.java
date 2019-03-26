package com.sarkisian.boilerplate.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarkisian.boilerplate.ui.activity.BaseActivity;

public abstract class BaseFragment extends Fragment {

    private static final String LOG_TAG = BaseFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void hideActionBarIcon() {
        ((BaseActivity) getActivity()).hideActionBarIcon();
    }

    protected void showActionBarIcon() {
        ((BaseActivity) getActivity()).showActionBarIcon();
    }

    protected void setActionBarIcon() {
        ((BaseActivity) getActivity()).hideActionBarIcon();
    }

    protected void setActionBarTitle(String actionBarTitle) {
        ((BaseActivity) getActivity()).setActionBarTitle(actionBarTitle);
    }

}
