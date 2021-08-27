package com.nastechdesign.maishapaycheckout;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AlertDialog;

public class checkout {


    public static int MaishaPay(Context mContext, View view, String apiOptions,
                                String apikey, String gateway_mode, String montant,
                                String monnaie, String payment_description, String logo_url,
                                String page_callback_success, String page_callback_failure,
                                String page_callback_cancel) {

        if (getActivity(mContext) != null) {
            Activity activity = getActivity(mContext);
            showPaymentScreen(activity, view, apiOptions, apikey, gateway_mode, montant, monnaie,
                    payment_description, logo_url, page_callback_success,
                    page_callback_failure, page_callback_cancel);

            return 1;
        } else {
            return 0;
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private static void showPaymentScreen(Activity activity, View view, String apiOptions,
                                          String apikey, String gateway_mode, String montant,
                                          String monnaie, String payment_description, String logo_url,
                                          String page_callback_success, String page_callback_failure,
                                          String page_callback_cancel) {
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

        String html = "<html> <body> <form action=\"https://maishapay.shop/marchand/checkout/\" method=\"post\">\n" +
                "\n" +
                "    <input type='hidden' name='apiOptions' value='" + apiOptions + "'>\n" +
                "    <input type='hidden' name='apikey' value='" + apikey + "'>\n" +
                "    <input type='hidden' name='gateway_mode' value='" + gateway_mode + "'>\n" +
                "    <input type='hidden' name='montant' value='" + montant + "'>\n" +
                "    <input type='hidden' name='monnaie' value='" + monnaie + "'>\n" +
                "    <input type='hidden' name='payment_description' value='" + payment_description + "'>\n" +
                "    <input type='hidden' name='logo_url' value='" + logo_url + "'>\n" +
                "    <input type='hidden' name='page_callback_success' value='" + page_callback_success + "'>\n" +
                "    <input type='hidden' name='page_callback_failure' value='" + page_callback_failure + "'>\n" +
                "    <input type='hidden' name='page_callback_cancel' value='" + page_callback_cancel + "'>\n" +
                "    <input type='hidden' name='submit' value='Paiment ici'>\n" +
                "</form></body>" +
                "<script>\n" +
                "  document.getElementsByName('submit')[0].click();\n" +
                "</script>" +
                "</html>";

        String mime = "text/html";
        String encoding = "utf-8";

        mWebView.loadData(html, mime, encoding);

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