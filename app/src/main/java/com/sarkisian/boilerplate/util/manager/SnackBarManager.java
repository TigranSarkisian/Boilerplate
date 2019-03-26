package com.sarkisian.boilerplate.util.manager;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackBarManager {

    private static final String LOG_TAG = SnackBarManager.class.getSimpleName();

    public static class Duration {
        public static final int TOO_SHORT = 2000;
        public static final int SHORT = 3000;
        public static final int LONG = 4000;
        public static final int EXTRA_LONG = 5000;
    }

    public static void show(Context context, String msg, int length) {
        try {
            if (context != null) {
                View decorView = ((Activity) context).getWindow().getDecorView();
                if (decorView != null) {
                    View view = decorView.findViewById(android.R.id.content);
                    if (view != null) {
                        Snackbar snack = Snackbar.make(view, msg, length);
                        snack.getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                            @Override
                            public void onViewAttachedToWindow(View v) {
                            }

                            @Override
                            public void onViewDetachedFromWindow(View v) {
                            }
                        });
                        snack.show();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void show(Context context, View view, String msg, int length) {
        try {
            if (context != null) {
                if (view != null) {
                    Snackbar snack = Snackbar.make(view, msg, length);
                    snack.getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                        @Override
                        public void onViewAttachedToWindow(View v) {
                        }

                        @Override
                        public void onViewDetachedFromWindow(View v) {
                        }
                    });
                    snack.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showWithAction(Context context, View view, String msg, int length,
                                      String actionMsg, View.OnClickListener onClickListener) {
        try {
            if (context != null) {
                if (view != null) {
                    Snackbar snack = Snackbar.make(view, msg, length);
                    snack.setAction(actionMsg, onClickListener);
                    snack.getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                        @Override
                        public void onViewAttachedToWindow(View v) {
                        }

                        @Override
                        public void onViewDetachedFromWindow(View v) {
                        }
                    });
                    snack.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showWithAction(Context context, String msg, int length, String actionMsg,
                                      View.OnClickListener onClickListener) {
        try {
            if (context != null) {
                View decorView = ((Activity) context).getWindow().getDecorView();
                if (decorView != null) {
                    Snackbar snack = Snackbar.make(decorView, msg, length);
                    snack.setAction(actionMsg, onClickListener);
                    snack.getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                        @Override
                        public void onViewAttachedToWindow(View v) {
                        }

                        @Override
                        public void onViewDetachedFromWindow(View v) {
                        }
                    });
                    snack.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
