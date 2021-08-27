package com.nastechdesign.maishapaycheckout;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

public class checkout {


    public static int MaishaPay(Context mContext, View view) {

        if (getActivity(mContext) != null) {
            Activity activity = getActivity(mContext);
            showPaymentScreen(activity, view);
            return 1;
        } else {
            return 0;
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private static void showPaymentScreen(Activity activity, View view) {
        Rect displayRectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.CustomAlertDialog);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog, viewGroup, false);
        dialogView.setMinimumWidth((int) (displayRectangle.width() * 1f));
        dialogView.setMinimumHeight((int) (displayRectangle.height() * 1f));
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();

        WebView mWebView = dialogView.findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("assets/checkout.html");


        dialogView.findViewById(R.id.btnCancel).setOnClickListener(v1 -> alertDialog.dismiss());
        alertDialog.setCancelable(false);
        alertDialog.show();
    }


    /**
     * Get activity instance from desired context.
     */
    public static Activity getActivity(Context context) {
        if (context == null) return null;
        if (context instanceof Activity) return (Activity) context;
        if (context instanceof ContextWrapper)
            return getActivity(((ContextWrapper) context).getBaseContext());
        return null;
    }
}