package com.sarkisian.boilerplate.util.manager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.sarkisian.boilerplate.R;

public class DialogManager {

    public static class DialogIdentifier {
        public static final int COMMON_DIALOG = 1;
    }

    public static class DialogButtonText {
        public static final String OK = "OK";
        public static final String CANCEL = "Cancel";
    }

    private int mIdentifier;
    private String mContextClassName;
    private AlertDialog mAlertDialog;
    private AlertDialog mPreloaderDialog;
    private ProgressDialog mProgressDialog;
    private static DialogManager sInstance;

    private DialogManager() {
    }

    public static DialogManager getInstance() {
        if (sInstance == null) {
            sInstance = new DialogManager();
        }
        return sInstance;
    }

    public int getIdentifier() {
        return mIdentifier;
    }

    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }

    public AlertDialog getPreloaderDialog() {
        return mPreloaderDialog;
    }

    private String getPositiveButtonText(int identifier) {
        switch (identifier) {
            case DialogIdentifier.COMMON_DIALOG:
                return DialogButtonText.OK;
        }
        return null;
    }

    private String getNegativeButtonText(int identifier) {
        switch (identifier) {
            case DialogIdentifier.COMMON_DIALOG:
                return DialogButtonText.CANCEL;
        }
        return null;
    }

    private boolean isAlertDialogShowing(Class startClass) {
        return mContextClassName != null && startClass != null
                && startClass.getName().contains(mContextClassName)
                && mAlertDialog != null && mAlertDialog.isShowing();
    }

    private boolean isPreloaderDialogShowing(Class startClass) {
        return mContextClassName != null && startClass != null
                && startClass.getName().contains(mContextClassName)
                && mPreloaderDialog != null && mPreloaderDialog.isShowing();
    }

    private boolean isProgressDialogShowing(Class startClass) {
        return mContextClassName != null && startClass != null
                && startClass.getName().contains(mContextClassName)
                && mProgressDialog != null && mProgressDialog.isShowing();
    }

    public void dismissAlertDialog(Class startClass) {
        if (mContextClassName != null && startClass != null
                && startClass.getName().contains(mContextClassName)) {
            if (isAlertDialogShowing(startClass)) {
                mAlertDialog.dismiss();
            }
            mAlertDialog = null;
        }
    }

    public void dismissPreloader(Class startClass) {
        if (mContextClassName != null && startClass != null
                && startClass.getName().contains(mContextClassName)) {
            if (isPreloaderDialogShowing(startClass)) {
                mPreloaderDialog.dismiss();
            }
            mPreloaderDialog = null;
        }
    }

    public void dismissProgressDialog(Class startClass) {
        if (mContextClassName != null && startClass != null
                && startClass.getName().contains(mContextClassName)) {
            if (isProgressDialogShowing(startClass)) {
                mProgressDialog.dismiss();
            }
            mProgressDialog = null;
        }
    }

    public void showCustomAlertDialog(Context context,
                                      String title,
                                      String message,
                                      View view,
                                      DialogInterface.OnClickListener positiveListener,
                                      DialogInterface.OnClickListener negativeListener,
                                      int identifier,
                                      boolean isCancelable) {

        if (!((Activity) context).isFinishing()) {
            // otherwise WindowManager$BadTokenException: Unable to add window will be thrown

            mIdentifier = identifier;
            mContextClassName = context.getClass().getName();

            if (title == null) {
                mAlertDialog = new AlertDialog.Builder(context)
                        .setMessage(message)
                        .setView(view)
                        .setCancelable(isCancelable)
                        .setPositiveButton(getPositiveButtonText(identifier), positiveListener)
                        .setNegativeButton(getNegativeButtonText(identifier), negativeListener)
                        .show();

            } else if (message == null) {
                mAlertDialog = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setView(view)
                        .setCancelable(isCancelable)
                        .setPositiveButton(getPositiveButtonText(identifier), positiveListener)
                        .setNegativeButton(getNegativeButtonText(identifier), negativeListener)
                        .show();
            } else {
                mAlertDialog = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(message)
                        .setView(view)
                        .setCancelable(isCancelable)
                        .setPositiveButton(getPositiveButtonText(identifier), positiveListener)
                        .setNegativeButton(getNegativeButtonText(identifier), negativeListener)
                        .show();
            }
        }
    }

    public void showAlertDialog(Context context,
                                String title,
                                String message,
                                DialogInterface.OnClickListener positiveListener,
                                DialogInterface.OnClickListener negativeListener,
                                int identifier,
                                boolean isCancelable) {

        if (!((Activity) context).isFinishing()) {
            // otherwise WindowManager$BadTokenException: Unable to add window will be thrown

            mIdentifier = identifier;
            mContextClassName = context.getClass().getName();

            if (title == null) {
                mAlertDialog = new AlertDialog.Builder(context)
                        .setMessage(message)
                        .setCancelable(isCancelable)
                        .setPositiveButton(getPositiveButtonText(identifier), positiveListener)
                        .setNegativeButton(getNegativeButtonText(identifier), negativeListener)
                        .show();

            } else if (message == null) {
                mAlertDialog = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setCancelable(isCancelable)
                        .setPositiveButton(getPositiveButtonText(identifier), positiveListener)
                        .setNegativeButton(getNegativeButtonText(identifier), negativeListener)
                        .show();
            } else {
                mAlertDialog = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(message)
                        .setCancelable(isCancelable)
                        .setPositiveButton(getPositiveButtonText(identifier), positiveListener)
                        .setNegativeButton(getNegativeButtonText(identifier), negativeListener)
                        .show();
            }
        }
    }

    public void showAlertDialog(Context context,
                                String title,
                                String message,
                                DialogInterface.OnClickListener positiveListener,
                                int identifier,
                                boolean isCancelable) {

        if (!((Activity) context).isFinishing()) {
            // otherwise WindowManager$BadTokenException: Unable to add window will be thrown

            mIdentifier = identifier;
            mContextClassName = context.getClass().getName();

            if (title == null) {
                mAlertDialog = new AlertDialog.Builder(context)
                        .setMessage(message)
                        .setCancelable(isCancelable)
                        .setPositiveButton(getPositiveButtonText(identifier), positiveListener)
                        .show();

            } else if (message == null) {
                mAlertDialog = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setCancelable(isCancelable)
                        .setPositiveButton(getPositiveButtonText(identifier), positiveListener)
                        .show();
            } else {
                mAlertDialog = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(message)
                        .setCancelable(isCancelable)
                        .setPositiveButton(getPositiveButtonText(identifier), positiveListener)
                        .show();
            }
        }
    }

    public void showSingleChoiceAlertDialog(Context context,
                                            String title,
                                            CharSequence[] items,
                                            DialogInterface.OnClickListener positiveListener,
                                            DialogInterface.OnClickListener negativeListener,
                                            int identifier,
                                            boolean isCancelable) {

        if (!((Activity) context).isFinishing()) {
            // otherwise WindowManager$BadTokenException: Unable to add window will be thrown

            mIdentifier = identifier;
            mContextClassName = context.getClass().getName();

            mAlertDialog = new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setCancelable(isCancelable)
                    .setSingleChoiceItems(items, -1, null)
                    .setPositiveButton(getPositiveButtonText(identifier), positiveListener)
                    .setNegativeButton(getNegativeButtonText(identifier), negativeListener)
                    .show();
        }
    }

    public void showProgressDialog(Context context, String title, String message, boolean isCancelable) {

        if (!((Activity) context).isFinishing()) {
            // otherwise WindowManager$BadTokenException: Unable to add window will be thrown

            mContextClassName = context.getClass().getName();
            mProgressDialog = ProgressDialog.show(context, title, message, true, isCancelable);
        }
    }

    public void showPreloader(Context context) {

        if (!((Activity) context).isFinishing()) {
            // otherwise WindowManager$BadTokenException: Unable to add window will be thrown

            mContextClassName = context.getClass().getName();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_preloader, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            mPreloaderDialog = builder.create();
            mPreloaderDialog.setCanceledOnTouchOutside(true);
            mPreloaderDialog.getWindow().setBackgroundDrawable(
                    new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mPreloaderDialog.setCanceledOnTouchOutside(false);
            mPreloaderDialog.setCancelable(false);
            mPreloaderDialog.show();

            mPreloaderDialog.setContentView(view);
        }
    }

}
