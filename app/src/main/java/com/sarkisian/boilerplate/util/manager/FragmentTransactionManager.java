package com.sarkisian.boilerplate.util.manager;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class FragmentTransactionManager {

    public static void displayFragment(FragmentManager fragmentManager,
                                       Fragment fragment,
                                       @IdRes int view,
                                       boolean addToBackStack) {
        if (addToBackStack) {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(view, fragment, fragment.getClass().getSimpleName())
                    .commit();

        } else {
            fragmentManager.beginTransaction()
                    .replace(view, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

}
